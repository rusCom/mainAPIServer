package aTaxi.main;

import aTaxi.ATaxiApplication;
import aTaxi.data.DriverStatus;
import aTaxi.data.Order;
import aTaxi.data.RoutePoint;
import aTaxiAPI.Asterisk;
import aTaxiAPI.GEO;
import aTaxiAPI.Main;
import com.intersys.objects.CacheException;
import geo.GeoApplication;
import geo.data.GeoObject;
import geo.data.Location;
import org.eclipse.jetty.server.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class MainAppServer extends AppServer {
    private final ATaxiApplication aTaxiApplication;

    public MainAppServer(ATaxiApplication aTaxiApplication) {
        this.aTaxiApplication = aTaxiApplication;
    }

    @Override
    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        super.response(target, baseRequest);
        appServerResponse.setStatus(400);

        switch (target) {
            case "/ataxi/main/operator/calc" -> OperatorCalc();
            case "/ataxi/main/drivers/monitor" -> DriversMonitor();
            case "/ataxi/main/asterisk/monitor/record" -> AsteriskMonitorRecord();
        }

        return appServerResponse;

    }

    private void AsteriskMonitorRecord() throws CheckDataException, CacheException {
        JSONObject response = new JSONObject(Asterisk.MonitorData(aTaxiApplication.getDataBase(), paramInt("monitor_id")));
        appServerResponse.setFileName("\\\\192.168.1.2\\monitor2\\" + response.getString("file_name"));
    }


    private void OperatorCalc() throws CheckDataException, SQLException, CacheException {
        String[] calcData = paramString("order").split("\\|");
        if (calcData.length < 6){
            calcData = Arrays.copyOf(calcData, calcData.length + 1);
            calcData[5] = "";
        }
        if (calcData[calcData.length - 1].equals("]}")){
            calcData = Arrays.copyOf(calcData, calcData.length - 1);
        }

        Order order = new Order();
        order.setCityByID(1);

        order.setHour(MainUtils.parseBoolean(calcData[0]));
        order.setPrior(MainUtils.parseBoolean(calcData[1]));
        order.setRegDate(DateTimeUtils.convertFromCache(calcData[2]));
        order.setWorkDate(DateTimeUtils.convertFromCache(calcData[3]));
        order.setClientID(MainUtils.parseInteger(calcData[4], 0), aTaxiApplication.getDataBase());
        order.setCalledID(calcData[5]);


        Connection connection = null;
        try {
            connection = GeoApplication.getInstance().getGeoMySQLConnection();
            for (int itemID = 6; itemID < calcData.length; itemID = itemID + 5) { // Разбираем actions

                String actionConstantID = calcData[itemID];
                String actionValue = calcData[itemID + 1];
                String actionNote = calcData[itemID + 2];
                String actionDate = calcData[itemID + 3];
                String actionIsDeleted = calcData[itemID + 4];

                // System.out.println(actionConstantID + "|" + actionValue + "|"+ actionNote);
                if (actionIsDeleted.equals("0")){
                    if (actionConstantID.equals("85")) { // Входящий звонок
                        order.setCallerID(actionNote);
                    }
                    if (actionConstantID.equals("1") || actionConstantID.equals("28")) { // Если точка маршрута
                        String str = calcData[itemID + 2].split(" - ")[0];
                        GeoObject routePoint = GeoApplication.getInstance().getGeoCode().geoCodeDispatcher(str, connection);
                        order.addRoutePoint(RoutePoint.fromGeoObject(routePoint));
                        if (routePoint.isCheck()){
                            GEO.SetGeoCode(aTaxiApplication.getDataBase(), routePoint.searchString, routePoint.getTaxiGeoCodeData());
                            if (!routePoint.searchString.equalsIgnoreCase(routePoint.clearSearchString)){
                                GEO.SetGeoCode(aTaxiApplication.getDataBase(), routePoint.clearSearchString, routePoint.getTaxiGeoCodeData());
                            }

                        }
                    } // Если точка маршрут
                }

            } // Разбираем actions
            order.calcOrder();

        }
        finally {
            if (connection != null){
                GeoApplication.getInstance().returnGeoMySQLConnection(connection);
            }
        }

        Main.SetOperCalcStatus(aTaxiApplication.getDataBase(), order.getDirectionsStatus());
        appServerResponse.setStatus(200, order.toJSON());
    }

    private void DriversMonitor() throws CheckDataException, CacheException {
        String callSign = paramString("callsign", false);
        JSONArray result;
        if (callSign.equals("")){
            String cacheData = Main.DriversGPSMonitor(aTaxiApplication.getDataBase(), 1);
            String[] locations = cacheData.split("\\|");
            result = new JSONArray();
            for (String s : locations) {
                JSONObject location = Location.fromCache(s).toJSON();
                String[] data = s.split("\\^");
                location.put("callsign", data[5]);
                location.put("status", DriverStatus.getString(Integer.parseInt(data[6])));
                result.put(location);
            }
        }
        else {
            String cacheData = Main.DriverGPSMonitor(aTaxiApplication.getDataBase(), paramString("callsign"), paramString("date"));
            String[] locations = cacheData.split("\\|");
            result = new JSONArray();
            for (String s : locations) {
                result.put(Location.fromCache(s).toJSON());
            }
        }

        appServerResponse.setStatus(200, result);
    }
}
