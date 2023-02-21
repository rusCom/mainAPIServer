package aTaxi.data;

import aTaxi.ATaxiApplication;
import com.intersys.objects.CacheException;
import geo.data.Directions;
import geo.data.GeoObject;
import geo.tools.LocationsCache;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static tools.MainUtils.boolToInt;

public class Order {
    private final List<RoutePoint> route;
    private final LocationsCache locationsCache;
    int distance;
    int duration;
    int pickDistance;
    int returnDistance;
    private String directionsStatus;
    City city;
    boolean isAirport = false;
    boolean isTransCity = false;
    private boolean isHour;
    private boolean isPrior;
    private LocalDateTime regDate;
    private LocalDateTime workDate;
    private int clientID;
    private String calledID;
    private String callerID;

    public Order() {
        route = new ArrayList<>();
        distance = 0;
        duration = 0;
        locationsCache = new LocationsCache();
    }

    public void addRoutePoint(RoutePoint routePoint)  {

        route.add(routePoint);
    }

    public void calcOrder() throws CacheException, SQLException {

        if (!route.isEmpty()) {
            for (int itemID = 0; itemID < route.size(); itemID++) {
                if (!route.get(itemID).isCheck()){
                    this.directionsStatus = "RoutePoint null status";
                    return;
                }
                for (Parking parking : ATaxiApplication.getInstance().getParkings()) {
                    locationsCache.addToCalc(parking, route.get(itemID), 0.5);
                }
                if (itemID > 0) {
                    locationsCache.addToCalc(route.get(itemID - 1), route.get(itemID), 0);
                }
            }


            locationsCache.calcAll();

            for (int itemID = 0; itemID < route.size(); itemID++) {
                RoutePoint routePoint = route.get(itemID);
                int minDistance = 999999999;
                for (Parking parking : ATaxiApplication.getInstance().getParkings()) {
                    Directions direction = locationsCache.getDirections(parking, routePoint, 0.5);
                    // System.out.println(direction);
                    if (direction.getStatus().equals("OK")) {
                        if (minDistance > direction.getDistance()) {
                            minDistance = direction.getDistance();
                            routePoint.setParking(parking, direction.getDistance());
                        }
                    }
                    else {
                        // System.out.println(parking);
                        routePoint.setParking(null, 0);
                        this.directionsStatus = "Parking not found";
                        return;
                    }
                } // for (Parking parking : ATaxiApplication.getInstance().getParkings()){

                if (routePoint.isAirport()){
                    isAirport = true;
                }
                if (routePoint.isTransCity()){
                    isTransCity = true;
                }
                if (itemID == 0){
                    pickDistance = routePoint.getParkingDistance();
                }
                else if (itemID == route.size() - 1){
                    returnDistance = routePoint.getParkingDistance();
                }

                directionsStatus = "OK";
                if (itemID > 0) {
                    // расстояние между точками - округление до трех знаков
                    Directions direction = locationsCache.getDirections(route.get(itemID - 1), route.get(itemID), 0);
                    // System.out.println(direction);
                    if (direction.getStatus().equals("OK")) {
                        this.distance += direction.getDistance();
                        this.duration += direction.getDuration();
                    } else {
                        this.directionsStatus = direction.getStatus();
                    }
                }
            }

        } // if (!route.isEmpty()) {
    }

    public boolean isHour() {
        return isHour;
    }

    public void setHour(boolean hour) {
        isHour = hour;
    }

    public boolean isPrior() {
        return isPrior;
    }

    public void setPrior(boolean prior) {
        isPrior = prior;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDateTime workDate) {
        this.workDate = workDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getCalledID() {
        return calledID;
    }

    public void setCalledID(String calledID) {
        this.calledID = calledID;
    }

    public String getCallerID() {
        return callerID;
    }

    public void setCallerID(String callerID) {
        this.callerID = callerID;
    }

    public void setCityByID(Integer cityID) throws CacheException {
        this.city = ATaxiApplication.getInstance().getCity(cityID);
    }

    public String getDirectionsStatus() {
        return directionsStatus;
    }

    private String getATaxiData(){
        String result = "$$$$$";
        result += directionsStatus + "^";
        result += distance + "^";
        result += pickDistance + "^";
        result += returnDistance +"^";
        result += route.size() + "^";
        result += boolToInt(isAirport) + "^";
        result += boolToInt(isTransCity) + "^";

        result += "$$$$$";
        return result;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        if (!route.isEmpty()) {
            JSONArray routeJSON = new JSONArray();
            route.forEach((GeoObject routePoint) -> routeJSON.put(routePoint.toJSON()));
            result.put("route", routeJSON);
        }
        result.put("city", city.toJSON());

        JSONObject directions = new JSONObject();
        directions.put("distance", distance);
        directions.put("duration", duration);
        directions.put("status", directionsStatus);
        directions.put("pick_distance", pickDistance);
        directions.put("return_distance", returnDistance);
        if (isAirport){directions.put("is_airport", true);}
        if (isTransCity){directions.put("is_trans_city", true);}
        result.put("directions", directions);

        result.put("calledID", calledID);
        result.put("callerID", callerID);

        result.put("ataxi_data", getATaxiData());

        return result;
    }
}
