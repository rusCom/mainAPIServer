package geo.tools;

import geo.data.GeoObject;
import geo.services.GeoSQLThread;
import org.apache.commons.lang3.StringUtils;
import tools.MySQLTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GeoCode {
    private final Map<String, GeoObject> btsGeocodeStreets;

    public GeoCode() {
        btsGeocodeStreets = new ConcurrentHashMap<>();
    }

    public GeoObject geoCodeDispatcher(String searchString, Connection connection) throws SQLException {
        GeoObject geoObject = null;
        searchString = searchString.split(" - ")[0];
        String clearSearchString = clearSearchString(searchString);
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + clearSearchString + "';");

            if (resultSet.next()) {
                geoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                GeoSQLThread.getInstance().addQuery("UPDATE `geocode_str` set `check_count` = `check_count` + 1 where `name` = '" + clearSearchString + "';");
            } else {
                GeoSQLThread.getInstance().addQuery("INSERT INTO `geocode_str` (`name`) values ('" + clearSearchString + "');");
            }

            if (geoObject == null) {
                geoObject = new GeoObject();
            }
            geoObject.searchString = searchString;
            geoObject.clearSearchString = clearSearchString;

            if (geoObject.getType().equals("street_address")) { // Если найден конкретный адрес, то увеличиваем счетчик для улицы по адресу
                GetStreet(clearSearchString, connection);
            }

            // System.out.println(geoObject);


            if (geoObject.getType().equals("null")) { // Если ничего не найдено// Пока проставялем улицу, если получилось ее найти
                geoObject = GetStreet(clearSearchString, connection);
                // System.out.println(geoObject);

                // Если получилось найти улицу, то пробуем найти дом, если адрес задан с дробью
                if (geoObject.getType().equals("route")) {
                    String streetAddressNumber = clearSearchString.split(" ")[clearSearchString.split(" ").length - 1];
                    String streetName = clearSearchString.replace(streetAddressNumber, "").trim();
                    // System.out.println(streetName);
                    String newSearchString = null;
                    // Если в номере есть знак / означающий дробь или указан номер корпуса
                    if (streetAddressNumber.contains("/")) {
                        newSearchString = streetName + " " + streetAddressNumber.split("/")[0];
                    } else if (streetAddressNumber.endsWith("к1")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к1", "");
                    } else if (streetAddressNumber.endsWith("к2")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к2", "");
                    } else if (streetAddressNumber.endsWith("к3")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к3", "");
                    } else if (streetAddressNumber.endsWith("к4")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к4", "");
                    } else if (streetAddressNumber.endsWith("к5")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к5", "");
                    } else if (streetAddressNumber.endsWith("к6")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к6", "");
                    } else if (streetAddressNumber.endsWith("к7")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к7", "");
                    } else if (streetAddressNumber.endsWith("к8")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к8", "");
                    } else if (streetAddressNumber.endsWith("к9")) {
                        newSearchString = streetName + " " + streetAddressNumber.replace("к9", "");
                    }
                    if (newSearchString != null) {
                        resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                        if (resultSet.next()){
                            GeoObject searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                            if (!searchGeoObject.getType().equals("null")) {
                                geoObject = searchGeoObject;
                            }
                        }
                    }

                    // Если не нашли с дробью, то можно попробоывать поискать +- два адреса
                    if (geoObject.getType().equals("route")) {
                        try {
                            String newStreetAddressNumber = NumberFormat.getIntegerInstance().parse(streetAddressNumber).toString();
                            newSearchString = streetName + " " + newStreetAddressNumber;
                            GeoObject searchGeoObject;
                            if (!newSearchString.equals(clearSearchString)) {
                                resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                                if (resultSet.next()){
                                    searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                                    if (searchGeoObject.getType().equals("street_address")) {
                                        geoObject = searchGeoObject;
                                    }
                                }
                            } else {

                                if (geoObject.getType().equals("route")) {
                                    newSearchString = streetName + " " + (Integer.parseInt(newStreetAddressNumber) + 2);
                                    // System.out.println(newSearchString);
                                    resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                                    if (resultSet.next()){
                                        searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                                        if (searchGeoObject.getType().equals("street_address")) {
                                            geoObject = searchGeoObject;
                                        }
                                    }
                                }
                                if (geoObject.getType().equals("route")) {
                                    newSearchString = streetName + " " + (Integer.parseInt(newStreetAddressNumber) - 2);
                                    // System.out.println(newSearchString);
                                    resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                                    if (resultSet.next()){
                                        searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                                        if (searchGeoObject.getType().equals("street_address")) {
                                            geoObject = searchGeoObject;
                                        }
                                    }
                                }
                                if (geoObject.getType().equals("route")) {
                                    newSearchString = streetName + " " + (Integer.parseInt(newStreetAddressNumber) + 4);
                                    resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                                    if (resultSet.next()){
                                        searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                                        if (searchGeoObject.getType().equals("street_address")) {
                                            geoObject = searchGeoObject;
                                        }
                                    }
                                }
                                if (geoObject.getType().equals("route")) {
                                    newSearchString = streetName + " " + (Integer.parseInt(newStreetAddressNumber) - 4);
                                    resultSet = statement.executeQuery("SELECT * FROM `crm_geocode_str` where `name` = '" + newSearchString + "';");
                                    if (resultSet.next()){
                                        searchGeoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                                        if (searchGeoObject.getType().equals("street_address")) {
                                            geoObject = searchGeoObject;
                                        }
                                    }
                                }

                            }


                        } catch (ParseException ignored) {
                        }
                    }
                }
            }

        } finally {
            MySQLTools.closeStatement(statement);
            MySQLTools.closeResultSet(resultSet);
        }
        return geoObject;
    }

    public GeoObject GetStreet(String searchString, Connection connection) throws SQLException {
        GeoObject geoObject;
        String[] searchStringSplit = searchString.split(" ");
        StringBuilder streetName = new StringBuilder();
        for (int itemID = 0; itemID < (searchStringSplit.length - 1); itemID++) {
            streetName.append(searchStringSplit[itemID]);
            if (itemID < (searchStringSplit.length - 1)) streetName.append(" ");
        }
        streetName = new StringBuilder(streetName.toString().trim());
        if (btsGeocodeStreets.containsKey(streetName.toString())) {
            geoObject = btsGeocodeStreets.get(streetName.toString());
        } else {
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                String SQL = "SELECT * FROM `crm_geocode_str` where `name` = '" + streetName + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(SQL);
                // System.out.println(SQL);
                if (resultSet.next()) {
                    geoObject = GeoObject.fromGeoMySQLDatabase(resultSet);
                    if (!geoObject.getType().equals("null")) {
                        geoObject.searchString = streetName.toString();
                        btsGeocodeStreets.put(streetName.toString(), geoObject);
                    } else {
                        geoObject = new GeoObject();
                        GeoSQLThread.getInstance().addQuery("UPDATE `geocode_str` set `check_count` = `check_count` + 1 where `name` = '" + streetName + "';");
                    }
                    // btsGeocodeStreets.put(streetName.toString(), geoObject);
                } else {
                    geoObject = new GeoObject();
                    // btsGeocodeStreets.put(streetName.toString(), geoObject);
                }

            } finally {
                MySQLTools.closeStatement(statement);
                MySQLTools.closeResultSet(resultSet);
            }
        }

        if (geoObject != null) {
            if (!geoObject.getType().equals("null")) {
                GeoSQLThread.getInstance().addQuery("UPDATE `geocode_str` set `check_count` = `check_count` + 1 where `name` = '" + streetName + "';");
            }
        }
        return geoObject;
    }

    public int getBTSStreetsCount() {
        return btsGeocodeStreets.size();
    }

    String clearSearchString(String searchString) {
        searchString = searchString.toLowerCase();
        searchString = StringUtils.normalizeSpace(searchString);
        searchString = searchString.replace(" улица ", " ");
        searchString = searchString.replace(" улица", "");
        searchString = searchString.replace("улица ", "");
        searchString = searchString.replace("улица", "");
        searchString = searchString.replace("/\\", "");
        searchString = searchString.replace("/ \\", "");
        searchString = searchString.replace("\\", "");
        searchString = searchString.replace("-", " ");
        searchString = searchString.replace(",", " ");
        searchString = searchString.replace(".", " ");
        searchString = searchString.replace("/ ", "/");
        searchString = searchString.replace(" /", "/");
        searchString = searchString.replace(" корп ", "к");
        searchString = searchString.replace("стр ", "стр");
        searchString = searchString.replace("/чд", "");
        searchString = searchString.replace(" )", ")");

        searchString = replaceSearchStringLast(searchString, " стадион", "");


        searchString = searchString.replace("  ", " ");
        searchString = searchString.replace("  ", " ");
        searchString = searchString.replace("  ", " ");

        searchString = replaceSearchStringLast(searchString, "/а", "а");
        searchString = replaceSearchStringLast(searchString, "/б", "б");
        searchString = replaceSearchStringLast(searchString, "/в", "в");
        searchString = replaceSearchStringLast(searchString, "/г", "г");
        searchString = replaceSearchStringLast(searchString, "/д", "д");
        searchString = replaceSearchStringLast(searchString, "/е", "е");
        searchString = replaceSearchStringLast(searchString, "/ж", "ж");
        searchString = replaceSearchStringLast(searchString, "/з", "з");
        searchString = replaceSearchStringLast(searchString, "/и", "и");
        searchString = replaceSearchStringLast(searchString, "/к", "к");
        searchString = replaceSearchStringLast(searchString, "/л", "л");
        searchString = replaceSearchStringLast(searchString, "/м", "м");
        searchString = replaceSearchStringLast(searchString, "/н", "н");

        searchString = replaceSearchStringLast(searchString, " а", "а");
        searchString = replaceSearchStringLast(searchString, " б", "б");
        searchString = replaceSearchStringLast(searchString, " в", "в");
        searchString = replaceSearchStringLast(searchString, " г", "г");
        searchString = replaceSearchStringLast(searchString, " д", "д");
        searchString = replaceSearchStringLast(searchString, " е", "е");
        searchString = replaceSearchStringLast(searchString, " ж", "ж");
        searchString = replaceSearchStringLast(searchString, " з", "з");
        searchString = replaceSearchStringLast(searchString, " и", "и");
        searchString = replaceSearchStringLast(searchString, " к", "к");
        searchString = replaceSearchStringLast(searchString, " л", "л");
        searchString = replaceSearchStringLast(searchString, " м", "м");
        searchString = replaceSearchStringLast(searchString, " н", "н");

        searchString = replaceSearchStringLast(searchString, "/а)", "а)");
        searchString = replaceSearchStringLast(searchString, "/б)", "б)");
        searchString = replaceSearchStringLast(searchString, "/в)", "в)");
        searchString = replaceSearchStringLast(searchString, "/г)", "г)");
        searchString = replaceSearchStringLast(searchString, "/д)", "д)");
        searchString = replaceSearchStringLast(searchString, "/е)", "е)");
        searchString = replaceSearchStringLast(searchString, "/ж)", "ж)");
        searchString = replaceSearchStringLast(searchString, "/з)", "з)");
        searchString = replaceSearchStringLast(searchString, "/и)", "и)");
        searchString = replaceSearchStringLast(searchString, "/к)", "к)");
        searchString = replaceSearchStringLast(searchString, "/л)", "л)");
        searchString = replaceSearchStringLast(searchString, "/м)", "м)");
        searchString = replaceSearchStringLast(searchString, "/н)", "н)");

        searchString = replaceSearchStringLast(searchString, "/к1", "к1");
        searchString = replaceSearchStringLast(searchString, "/к2", "к2");
        searchString = replaceSearchStringLast(searchString, "/к3", "к3");
        searchString = replaceSearchStringLast(searchString, "/к4", "к4");
        searchString = replaceSearchStringLast(searchString, "/к5", "к5");
        searchString = replaceSearchStringLast(searchString, "/к6", "к6");
        searchString = replaceSearchStringLast(searchString, "/к7", "к7");
        searchString = replaceSearchStringLast(searchString, "/к8", "к8");
        searchString = replaceSearchStringLast(searchString, "/к9", "к9");

        searchString = replaceSearchStringLast(searchString, "/к1)", "к1)");
        searchString = replaceSearchStringLast(searchString, "/к2)", "к2)");
        searchString = replaceSearchStringLast(searchString, "/к3)", "к3)");
        searchString = replaceSearchStringLast(searchString, "/к4)", "к4)");
        searchString = replaceSearchStringLast(searchString, "/к5)", "к5)");
        searchString = replaceSearchStringLast(searchString, "/к6)", "к6)");
        searchString = replaceSearchStringLast(searchString, "/к7)", "к7)");
        searchString = replaceSearchStringLast(searchString, "/к8)", "к8)");
        searchString = replaceSearchStringLast(searchString, "/к9)", "к9)");

        return searchString;
    }

    String replaceSearchStringLast(String searchString, String target, String replacement) {
        if (searchString.endsWith(target)) {
            searchString = searchString.substring(0, searchString.length() - target.length()) + replacement;
        }
        return searchString;
    }
}
