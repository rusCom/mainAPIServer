package geo.data;

import geo.services.GeoSQLThread;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeoObject extends Location {
    protected String placeId;
    protected String name;
    protected String description;
    protected Integer detail;
    protected Integer manualCheck;
    protected String type;
    public String searchString;
    public String clearSearchString;

    public static GeoObject fromGeoMySQLDatabase(ResultSet resultSet) throws SQLException {
        GeoObject geoObject = new GeoObject();
        if (resultSet.getString("place_id") == null) {
            return geoObject;
        }
        geoObject.placeId = resultSet.getString("place_id");
        geoObject.name = resultSet.getString("place_name");
        geoObject.description = resultSet.getString("place_dsc");
        geoObject.latitude = resultSet.getDouble("place_lt");
        geoObject.longitude = resultSet.getDouble("place_ln");
        geoObject.type = resultSet.getString("place_type");
        geoObject.detail = resultSet.getInt("detail");
        geoObject.manualCheck = resultSet.getInt("manual_check");

        geoObject.roundLocation();
        geoObject.clearStreetsData();
        return geoObject;
    }

    private void clearStreetsData() throws SQLException {
        if (isCheck()) {
            String newName = null;
            if (name.contains("улица ")) {
                newName = name.replace("улица ", "");
            }
            if (name.contains(" улица")) {
                newName = name.replace(" улица", "");
            }

            if (newName != null) {
                String SQL = "update `objects_google` set `name` = '" + newName + "' where `place_id` = '" + placeId + "'";
                GeoSQLThread.getInstance().addQuery(SQL);
                name = newName;
            }


            if (name.contains("ул. ")) {
                name = name.replace("ул. ", "");
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `name` = '" + name + "' where `place_id` = '" + placeId + "'");
            }

            if (type.equals("premise")) {
                type = "street_address";
                String SQL = "update `objects_google` set `type` = 'street_address' where `place_id` = '" + placeId + "'";
                GeoSQLThread.getInstance().addQuery(SQL);
            }

            if (description.contains(" улица")) {
                description = description.replace(" улица", "");
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `dsc` = '" + description + "' where `place_id` = '" + placeId + "'");
            }

            if (description.equals("село Зубово, Зубовский сельсовет, Уфимский район, Республика Башкортостан")) {
                description = "село Зубово, Уфимский район, Республика Башкортостан";
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `dsc` = '" + description + "' where `place_id` = '" + placeId + "'");
            }
            if (description.equals("деревня Дорогино, Кирилловский сельсовет, Уфимский район, Республика Башкортостан")) {
                description = "деревня Дорогино, Уфимский район, Республика Башкортостан";
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `dsc` = '" + description + "' where `place_id` = '" + placeId + "'");
            }
            if (description.equals("село санатория Юматово имени 15-летия БАССР, Юматовский сельсовет, Уфимский район, Республика Башкортостан, Россия")) {
                description = "село санатория Юматово имени 15-летия БАССР, Уфимский район, Республика Башкортостан, Россия";
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `dsc` = '" + description + "' where `place_id` = '" + placeId + "'");
            }
            if (description.equals("ст Цветы Башкирии, Уфа, Республика Башкортостан")) {
                description = "Цветы Башкирии, Уфа, Республика Башкортостан";
                GeoSQLThread.getInstance().addQuery("update `objects_google` set `dsc` = '" + description + "' where `place_id` = '" + placeId + "'");
            }


        }
    }

    public String getType() {
        if (type == null) return "null";
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean isCheck() {
        if (!super.isCheck()) return false;
        if (this.name == null) return false;
        if (this.description == null) return false;
        if (this.placeId == null) return false;
        if (this.name.equals("")) return false;
        if (this.placeId.equals("")) return false;
        return true;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTaxiGeoCodeData() {
        String result = "";
        result += this.name + "^";
        result += this.description + "^";
        result += this.latitude + "^";
        result += this.longitude + "^";
        result += this.placeId + "^";
        return result;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("place_id", placeId);
        jsonObject.put("name", name);
        jsonObject.put("dsc", description);
        jsonObject.put("lt", latitude);
        jsonObject.put("ln", longitude);
        jsonObject.put("type", getType());
        jsonObject.put("detail", detail);
        jsonObject.put("manual_check", manualCheck);
        jsonObject.put("clear_search_string", clearSearchString);
        jsonObject.put("search_string", searchString);

        return jsonObject;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }
}
