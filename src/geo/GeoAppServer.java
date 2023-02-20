package geo;

import aTaxi.ATaxiApplication;
import aTaxiAPI.GEO;
import com.intersys.objects.CacheException;
import geo.data.Directions;
import geo.data.Location;
import org.eclipse.jetty.server.Request;
import tools.AppServer;
import tools.AppServerResponse;
import tools.CheckDataException;

import java.io.IOException;
import java.sql.SQLException;


public class GeoAppServer extends AppServer {
    private final ATaxiApplication aTaxiApplication;

    public GeoAppServer(ATaxiApplication aTaxiApplication) {
        this.aTaxiApplication = aTaxiApplication;
    }



    @Override
    public AppServerResponse response(String target, Request baseRequest) throws CacheException, CheckDataException, SQLException, IOException {
        super.response(target, baseRequest);
        if (GEO.Auth(aTaxiApplication.getDataBase(), paramString("token", false)).equals("0")) {
            appServerResponse.setStatus(401);
        } else if (target.equals("/geo/directions")) {
            Directions();
        }
        return appServerResponse;
    }

    private void Directions() throws CheckDataException, SQLException, IOException, CacheException {
        String begin = paramString("begin", false);
        String endStr = paramString("end", false);
        Location from, end;
        if (!begin.equals("")){
            from = new Location(Double.parseDouble(begin.split(",")[0]), Double.parseDouble(begin.split(",")[1]));
        }
        else {
            from = new Location(paramDouble("blt"), paramDouble("bln"));
        }
        if (!endStr.equals("")){
            end = new Location(Double.parseDouble(endStr.split(",")[0]), Double.parseDouble(endStr.split(",")[1]));
        }
        else {
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
