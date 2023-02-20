package geo.tools;

import com.intersys.objects.CacheException;
import geo.GeoApplication;
import geo.data.Directions;
import geo.data.Location;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class LocationsCache {
    Map<String, Directions> locationsCache;

    public LocationsCache() {
        locationsCache = new HashMap<>();
    }

    public void addToCalc(Location from, Location to, double blunder) {
        Directions directions = Directions.toLocationsCache(from, to, blunder);
        if (!directions.isSameLocation()) {
            locationsCache.putIfAbsent(directions.getLocationsCacheString(), directions);
        }

    }

    public Directions getDirections(Location from, Location to, double blunder) {
        Directions directions = Directions.toLocationsCache(from, to, blunder);
        if (directions.isSameLocation()) {
            return directions;
        }
        if (locationsCache.get(directions.getLocationsCacheString()) != null) {
            return locationsCache.get(directions.getLocationsCacheString());
        }
        return directions;
    }

    public void calcAll() throws SQLException {
        final String[] SQL = {""};
        locationsCache.forEach((locationsCacheString, direction) ->
                SQL[0] += "SELECT * FROM `distance` where `blt` = " + direction.begin.getLatitudeStr() +
                        " and `bln` = " + direction.begin.getLongitudeStr() +
                        " and `elt` = " + direction.end.getLatitudeStr() +
                        " and `eln` = " + direction.end.getLongitudeStr() + " UNION ALL "
        );
        SQL[0] = SQL[0].substring(0, SQL[0].length() - 11) + ";";

        Connection connection = null;
        try {
            connection = GeoApplication.getInstance().getGeoMySQLConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL[0]);
            while (resultSet.next()) {
                if (resultSet.getString("status").equals("OK")) {
                    Directions directions = locationsCache.get(getLocationsCacheString(resultSet));
                    if (directions != null) {
                        directions.setFromResultSet(resultSet);
                    }
                }
            }
        } finally {
            if (connection != null) {
                GeoApplication.getInstance().returnGeoMySQLConnection(connection);
            }
        }

        locationsCache.forEach((locationsCacheString, direction) -> {
            if (!direction.getStatus().equals("OK")) {
                try {
                    direction.getFromGraphhopper();
                } catch (IOException | SQLException | CacheException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!direction.getStatus().equals("OK")) {
                try {
                    direction.getFromORSRemote(GeoApplication.getInstance().getRemoteORSToken());
                } catch (IOException | SQLException | CacheException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!direction.getStatus().equals("OK")) {
                try {
                    direction.getFromGoogle(GeoApplication.getInstance().getGoogleKey());
                } catch (IOException | SQLException | CacheException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!direction.getStatus().equals("OK")) {
                try {
                    direction.getFromCache();
                } catch (CacheException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private String getLocationsCacheString(ResultSet resultSet) throws SQLException {
        String result = "";
        result += resultSet.getString("blt") + ";";
        result += resultSet.getString("bln") + ";";
        result += resultSet.getString("elt") + ";";
        result += resultSet.getString("eln") + ";";
        return result;
    }

}
