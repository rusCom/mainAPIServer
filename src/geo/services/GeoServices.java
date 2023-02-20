package geo.services;

import aTaxi.ATaxiApplication;
import aTaxiAPI.GEO;
import geo.GeoApplication;
import geo.data.Directions;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import tools.MainUtils;

public class GeoServices extends Thread {
    boolean isRun = true;

    @Override
    public void run() {
        while (isRun) {
            try {
                JSONObject direction = new JSONObject(GEO.DistanceToCalc(ATaxiApplication.getInstance().getDataBase(), "GraphHopper"));
                Directions directions = Directions.fromATaxiCache(direction, false);
                if (directions != null){
                    if (!directions.isSameLocation()){
                        if (directions.getSource().equals("api")){
                            directions.getFromORSRemote(GeoApplication.getInstance().getRemoteORSToken());
                        }
                        else {
                            directions.getFromGraphhopper();
                        }
                        StringBuilder logText = new StringBuilder();
                        logText.append("date").append("\t").append("= ").append(MainUtils.getCurDateTime()).append("\n");
                        logText.append("source").append("\t").append("= ").append(direction).append("\n");
                        logText.append("result").append("\t").append("= ").append(directions.toJSON()).append("\n");
                        MainUtils.getInstance().printLog("service_geo", logText.toString());
                        if (directions.getStatus().equals("need")){
                            GEO.DirectionsNeedClear(ATaxiApplication.getInstance().getDataBase(),
                                    directions.begin.getLatitudeStr(),
                                    directions.begin.getLongitudeStr(),
                                    directions.end.getLatitudeStr(),
                                    directions.end.getLongitudeStr());
                        }
                    }
                    else {
                        GEO.DirectionsNeedClear(ATaxiApplication.getInstance().getDataBase(),
                                directions.begin.getLatitudeStr(),
                                directions.begin.getLongitudeStr(),
                                directions.end.getLatitudeStr(),
                                directions.end.getLongitudeStr());
                    }
                    // System.out.println(directions);
                }
                else {
                    // System.out.println("directions is null");
                    // System.out.println(direction.toString());
                }
                Thread.sleep(1000);
            } catch (Exception exception) {
                MainUtils.getInstance().printLog("service_geo", "error" + ExceptionUtils.getStackTrace(exception));
            }

        } // while (isRun)
    }

}
