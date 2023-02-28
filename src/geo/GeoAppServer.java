package geo;

import aTaxi.ATaxiApplication;
import aTaxiAPI.GEO;
import com.intersys.objects.CacheException;
import geo.data.Directions;
import geo.data.GeoObject;
import geo.data.Location;
import geo.services.GeoSQLThread;
import geo.tools.GeoCode;
import org.eclipse.jetty.server.Request;
import tools.AppServer;
import tools.AppServerResponse;
import tools.CheckDataException;
import tools.MySQLTools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GeoAppServer extends AppServer {
    private final ATaxiApplication aTaxiApplication;
    private final GeoApplication geoApplication;

    public GeoAppServer(ATaxiApplication aTaxiApplication, GeoApplication geoApplication) {
        this.aTaxiApplication = aTaxiApplication;
        this.geoApplication = geoApplication;
    }


    @Override
    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        super.response(target, baseRequest);
        if (GEO.Auth(aTaxiApplication.getDataBase(), paramString("token", false)).equals("0")) {
            appServerResponse.setStatus(401);
        } else {
            switch (target) {
                case "/geo/directions" -> Directions();
                case "/geo/bts/geocode" -> BTSGeocode();
            }

        }
        return appServerResponse;
    }

    private void BTSGeocode() throws CheckDataException, SQLException {
        String searchString = paramString("geocode").split(" - ")[0];
        String clearSearchString = GeoCode.clearSearchString(searchString);
        Connection connection = geoApplication.getGeoMySQLConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        GeoObject geoObject = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + clearSearchString + "';");

            if (resultSet.next()) {
                geoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                GeoSQLThread.getInstance().addQuery("UPDATE `geocode_str` set `check_count` = `check_count` + 1 where `name` = '" + clearSearchString + "';");
            } else {
                GeoSQLThread.getInstance().addQuery("INSERT INTO `geocode_str` (`name`, `check_count`) values ('" + clearSearchString + "', 10);");
            }
        } finally {
            if (connection != null) {
                geoApplication.returnGeoMySQLConnection(connection);
            }
            MySQLTools.closeStatement(statement);
            MySQLTools.closeResultSet(resultSet);
        }

        if (geoObject == null) {
            geoObject = new GeoObject();
        }
        geoObject.searchString = searchString;
        geoObject.clearSearchString = clearSearchString;

        if (!geoObject.getType().equals("null")){
            appServerResponse.setStatus(200, geoObject.toJSON());
        }
        else {
            appServerResponse.setStatus(404, geoObject.toJSON());
        }

    }

    private void Directions() throws CheckDataException, SQLException, IOException, CacheException {
        String begin = paramString("begin", false);
        String endStr = paramString("end", false);
        Location from, end;
        if (!begin.equals("")) {
            from = new Location(Double.parseDouble(begin.split(",")[0]), Double.parseDouble(begin.split(",")[1]));
        } else {
            from = new Location(paramDouble("blt"), paramDouble("bln"));
        }
        if (!endStr.equals("")) {
            end = new Location(Double.parseDouble(endStr.split(",")[0]), Double.parseDouble(endStr.split(",")[1]));
        } else {
            end = new Location(paramDouble("elt"), paramDouble("eln"));
        }


        Directions directions = new Directions(from, end);

        if (directions.getStatus().equals("NotFound") && (!paramString("ors_key", false).equals(""))) {
            directions.getFromORSRemote(paramString("ors_key"));
        }

        if (directions.getStatus().equals("NotFound") && (!paramString("google_key", false).equals(""))) {
            directions.getFromGoogle(paramString("google_key"));
        }
        appServerResponse.setStatus(200, directions.toJSON());
    }
}
