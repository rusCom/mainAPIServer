package geo.data;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bounds {
    Double southwestLatitude;
    Double southwestLongitude;
    Double northeastLatitude;
    Double northeastLongitude;

    public static Bounds fromGoogleJSON(JSONObject boundsJSON){
        Bounds bounds = new Bounds();
        JSONObject southwestObject = boundsJSON.getJSONObject("southwest");
        JSONObject northeastObject = boundsJSON.getJSONObject("northeast");
        bounds.southwestLatitude = southwestObject.getDouble("lat");
        bounds.southwestLongitude = southwestObject.getDouble("lng");
        bounds.northeastLatitude = northeastObject.getDouble("lat");
        bounds.northeastLongitude = northeastObject.getDouble("lng");
        return bounds;
    }

    public static Bounds fromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.getObject("bound_southwest_lt") == null)return null;
        if (resultSet.getObject("bound_southwest_ln") == null)return null;
        if (resultSet.getObject("bound_northeast_lt") == null)return null;
        if (resultSet.getObject("bound_northeast_ln") == null)return null;
        Bounds bounds = new Bounds();
        bounds.southwestLatitude = resultSet.getDouble("bound_southwest_lt");
        bounds.southwestLongitude = resultSet.getDouble("bound_southwest_ln");
        bounds.northeastLatitude = resultSet.getDouble("bound_northeast_lt");
        bounds.northeastLongitude = resultSet.getDouble("bound_northeast_ln");
        return bounds;
    }

    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        JSONObject southwest = new JSONObject();
        southwest.put("lt", southwestLatitude);
        southwest.put("ln", southwestLongitude);
        JSONObject northeast = new JSONObject();
        northeast.put("lt", northeastLatitude);
        northeast.put("ln", northeastLongitude);
        result.put("southwest", southwest);
        result.put("northeast", northeast);
        return result;
    }
}
