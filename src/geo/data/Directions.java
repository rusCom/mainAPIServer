package geo.data;

import aTaxi.ATaxiApplication;
import aTaxiAPI.GEO;
import com.intersys.objects.CacheException;
import geo.GeoApplication;
import geo.services.GeoSQLThread;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.MainUtils;
import tools.MySQLTools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static tools.MainUtils.JSONGetInteger;
import static tools.MainUtils.JSONGetString;

public class Directions {
    public Location begin;
    public Location end;
    double blunder;
    int distance;
    int duration;
    int ID;
    String status = "";
    String source = "";
    Bounds bounds;
    String polyline;

    public Directions() {
    }

    public Directions(Location begin, Location end) throws SQLException, IOException, CacheException {
        this.begin = begin;
        this.end = end;
        calc();
    }

    public static Directions fromATaxiCache(JSONObject data, boolean needCalc) throws SQLException, CacheException, IOException {
        if (data.getString("blt").equals("")) return null;
        if (data.getString("bln").equals("")) return null;
        if (data.getString("elt").equals("")) return null;
        if (data.getString("eln").equals("")) return null;
        Directions direction = new Directions();
        direction.begin = new Location(data.getDouble("blt"), data.getDouble("bln"));
        direction.end = new Location(data.getDouble("elt"), data.getDouble("eln"));
        direction.source = JSONGetString(data, "source");
        if (needCalc) {
            direction.calc();
        }
        direction.status = "need";
        return direction;
    }

    public static Directions toLocationsCache(Location from, Location to, double blunder) {
        Directions directions = new Directions();
        directions.begin = new Location(from.latitude, from.longitude);
        directions.end = new Location(to.latitude, to.longitude);
        directions.blunder = blunder;
        directions.begin.blunder(blunder);
        directions.end.blunder(blunder);
        return directions;
    }

    public Boolean isSameLocation() {
        if ((begin.latitude.equals(end.latitude)) && (begin.longitude.equals(end.longitude))) {
            this.distance = 0;
            this.duration = 0;
            this.status = "OK";
            this.source = "same";
            return true;
        }
        if (begin.distanceTo(end) < 500) {
            this.distance = begin.distanceTo(end);
            this.duration = 0;
            this.status = "OK";
            this.source = "same";
            return true;
        }
        return false;
    }

    public String getSource() {
        return source;
    }

    public void setFromResultSet(ResultSet resultSet) throws SQLException {
        this.distance = resultSet.getInt("distance");
        this.duration = resultSet.getInt("duration");
        this.source = resultSet.getString("source");
        this.status = resultSet.getString("status");
        this.ID = resultSet.getInt("id");
    }

    public String getLocationsCacheString() {
        String result = "";
        result += begin.getLatitudeStr() + ";";
        result += begin.getLongitudeStr() + ";";
        result += end.getLatitudeStr() + ";";
        result += end.getLongitudeStr() + ";";
        return result;
    }

    private void calc() throws SQLException, IOException, CacheException {
        if (begin.isCheck() && end.isCheck() && !isSameLocation()) {
            this.status = "NotFound";
            getFromMySQL();
            // Если из базы мы не смогли достать, то делаем запрос к нашему ORS северу
            if (!status.equals("OK")) {
                getFromGraphhopper();
            }
            if (!status.equals("OK")) {
                getFromCache();
            }
        } else {
            status = "null point";
        }
    }

    void getFromMySQL() throws SQLException {
        Connection connection = null;
        try {
            connection = GeoApplication.getInstance().getGeoMySQLConnection();
            String SQL = "SELECT * FROM `distance` where `blt` = " + begin.getLatitudeStr() +
                    " and `bln` = " + begin.getLongitudeStr() +
                    " and `elt` = " + end.getLatitudeStr() +
                    " and `eln` = " + end.getLongitudeStr() + ";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()){
                if (resultSet.getString("status").equals("OK")) {
                    this.distance = resultSet.getInt("distance");
                    this.duration = resultSet.getInt("duration");
                    this.source = resultSet.getString("source");
                    this.polyline = resultSet.getString("polyline");
                    this.bounds = Bounds.fromResultSet(resultSet);
                    this.ID = resultSet.getInt("ID");
                    this.status = "OK";
                } else {
                    this.status = resultSet.getString("status");
                    this.source = resultSet.getString("source");
                }
                resultSet.close();
                statement.close();
            }
        } finally {
            if (connection != null) {
                GeoApplication.getInstance().returnGeoMySQLConnection(connection);
            }
        }
    }

    public void saveToMySQL() throws CacheException, SQLException {
        Connection connection = null;

        try {
            connection = GeoApplication.getInstance().getGeoMySQLConnection();
            String SQL = "SELECT * FROM `distance` where `blt` = " + begin.getLatitudeStr() +
                    " and `bln` = " + begin.getLongitudeStr() +
                    " and `elt` = " + end.getLatitudeStr() +
                    " and `eln` = " + end.getLongitudeStr() + ";";
            if (MySQLTools.isQueryResult(connection, SQL)) {
                SQL = "UPDATE `distance` set `distance` = " + this.distance + ", `duration` = " + this.duration
                        + ", `status` = '" + this.status + "', `source` = '" + this.source + "' ";
                SQL += " where `blt` = " + begin.getLatitudeStr() + " and `bln` = " + begin.getLongitudeStr()
                        + " and `elt` = " + end.getLatitudeStr() + " and `eln` = " + end.getLongitudeStr();
                GeoSQLThread.getInstance().addQuery(SQL);
            } else {
                SQL = "INSERT INTO `distance` (`blt`, `bln`, `elt`, `eln`, `distance`, `duration`, `status`, `source`) VALUES ("
                        + begin.getLatitudeStr() + ", " + begin.getLongitudeStr() + ", " + end.getLatitudeStr() + ", " + end.getLongitudeStr() + ","
                        + this.distance + ", " + this.duration + ", '" + this.status + "', '" + this.source + "')"
                        + " ON DUPLICATE KEY UPDATE `distance` = " + this.distance + ", `duration` = " + this.duration + ";";
                GeoSQLThread.getInstance().addQuery(SQL);
            }
            if (polyline != null && !polyline.equals("") && bounds != null) {
                SQL = "UPDATE `distance` set `distance` = " + this.distance + ", `duration` = " + this.duration
                        + ", `status` = '" + this.status + "', `source` = '" + this.source + "' ";
                SQL += ", `polyline` = '" + polyline + "' ";
                SQL += ", `bound_northeast_lt` = " + bounds.northeastLatitude + " ";
                SQL += ", `bound_northeast_ln` = " + bounds.northeastLongitude + " ";
                SQL += ", `bound_southwest_lt` = " + bounds.southwestLatitude + " ";
                SQL += ", `bound_southwest_ln` = " + bounds.southwestLongitude + " ";

                SQL += " where `blt` = " + begin.getLatitudeStr() + " and `bln` = " + begin.getLongitudeStr()
                        + " and `elt` = " + end.getLatitudeStr() + " and `eln` = " + end.getLongitudeStr();
                GeoSQLThread.getInstance().addQuery(SQL);

            }
        } finally {
            if (connection != null) {
                GeoApplication.getInstance().returnGeoMySQLConnection(connection);
            }
        }

        GEO.DistanceSet(ATaxiApplication.getInstance().getDataBase(),
                begin.getLatitudeStr(),
                begin.getLongitudeStr(),
                end.getLatitudeStr(),
                end.getLongitudeStr(),
                String.valueOf(distance),
                String.valueOf(duration),
                status, source
        );
    }

    public void getFromCache() throws CacheException {
        JSONObject data = new JSONObject(
                GEO.DistanceGet(ATaxiApplication.getInstance().getDataBase(),
                        begin.getLatitudeStr(),
                        begin.getLongitudeStr(),
                        end.getLatitudeStr(),
                        end.getLongitudeStr(),
                        "api"
                )
        );
        if (JSONGetString(data, "status").equals("OK")) {
            this.distance = JSONGetInteger(data, "distance");
            this.duration = JSONGetInteger(data, "duration");
            this.source = "cache";
            this.status = "OK";
        }
    }

    public void getFromGraphhopper() throws IOException, SQLException, CacheException {
        if (isSameLocation()) {
            return;
        }
        String url = "http://lk.toptaxi.org:8989/route?point="
                + begin.getLatitudeStr() + "," + begin.getLongitudeStr()
                + "&point=" + end.getLatitudeStr() + "," + end.getLongitudeStr();
        // System.out.println(url);
        JSONObject response = MainUtils.getInstance().httpGet(url);
        if (response != null) {
            JSONObject paths = response.getJSONArray("paths").getJSONObject(0);
            this.distance = JSONGetInteger(paths, "distance");
            this.duration = JSONGetInteger(paths, "weight");
            this.status = "OK";
            this.source = "graphhopper";
            saveToMySQL();
        }
    }

    public void getFromORSRemote(String token) throws IOException, SQLException, CacheException {
        if (isSameLocation()) {
            return;
        }
        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + token;


        JSONObject postBody = new JSONObject();
        JSONArray postBodyCoordinates = new JSONArray();
        postBodyCoordinates.put(begin.toORS());
        postBodyCoordinates.put(end.toORS());
        postBody.put("coordinates", postBodyCoordinates);

        JSONObject postBodyOptions = new JSONObject();
        JSONArray postBodyOptionsAvoidFeatures = new JSONArray();
        postBodyOptionsAvoidFeatures.put("ferries");
        postBodyOptions.put("avoid_features", postBodyOptionsAvoidFeatures);
        postBody.put("options", postBodyOptions);
        postBody.put("language", "ru");

        JSONObject response = MainUtils.getInstance().httpPost(url, postBody);
        if (response != null) {
            if (!response.has("error")) {
                JSONObject routes = response.getJSONArray("routes").getJSONObject(0);
                JSONObject summary = routes.getJSONObject("summary");
                this.distance = JSONGetInteger(summary, "distance");
                this.duration = JSONGetInteger(summary, "duration");
                this.status = "OK";
                this.source = "ors";
                saveToMySQL();
            } else {
                this.status = "ZERO";
                this.source = "ors";
                this.distance = 0;
                this.duration = 0;
                saveToMySQL();
            }
        } else {
            this.status = "ZERO";
            this.source = "ors";
            this.distance = 0;
            this.duration = 0;
            saveToMySQL();
        }
    }

    public void getFromGoogle(String googleKey) throws IOException, SQLException, CacheException {
        if (isSameLocation()) {
            return;
        }
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + begin.getLatitudeStr() + "," + begin.getLongitude()
                + "&destination=" + end.getLatitudeStr() + "," + end.getLongitudeStr() + "&key=" + googleKey;
        JSONObject response = MainUtils.getInstance().httpGet(url);
        if (response.getString("status").equals("OK")) {
            JSONArray routes = response.getJSONArray("routes");
            JSONObject route = routes.getJSONObject(0);
            JSONObject leg = route.getJSONArray("legs").getJSONObject(0);
            this.distance = leg.getJSONObject("distance").getInt("value");
            this.duration = leg.getJSONObject("duration").getInt("value");

            this.bounds = Bounds.fromGoogleJSON(route.getJSONObject("bounds"));
            this.polyline = route.getJSONObject("overview_polyline").getString("points");
            this.status = "OK";
            this.source = "google";
            saveToMySQL();

        } else if (response.getString("status").equals("ZERO_RESULTS")) {
            this.status = "ZERO";
            this.source = "google";
            this.distance = 0;
            this.duration = 0;
            saveToMySQL();
        }

    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("uid", ID);
        result.put("begin", begin.toJSON());
        result.put("end", end.toJSON());
        result.put("distance", distance);
        result.put("duration", duration);
        result.put("status", status);
        result.put("source", source);
        result.put("polyline", polyline);
        if (bounds != null) {
            result.put("bounds", bounds.toJSON());
        }
        return result;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public String getStatus() {
        if (status.equals("")) {
            status = "NotFound";
        }
        return status;
    }
}
