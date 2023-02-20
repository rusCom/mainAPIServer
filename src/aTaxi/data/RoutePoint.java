package aTaxi.data;

import geo.data.GeoObject;
import org.json.JSONObject;

public class RoutePoint extends GeoObject {
    private Parking parking;
    private int parkingDistance;

    public static RoutePoint fromGeoObject(GeoObject geoObject){
        RoutePoint routePoint = new RoutePoint();
        routePoint.placeId = geoObject.getPlaceId();
        routePoint.name = geoObject.getName();
        routePoint.description = geoObject.getDescription();
        routePoint.latitude = geoObject.getLatitude();
        routePoint.longitude = geoObject.getLongitude();
        routePoint.type = geoObject.getType();
        return routePoint;
    }
    public void setParking(Parking parking, int parkingDistance) {
        this.parking = parking;
        this.parkingDistance = parkingDistance;
    }

    public Parking getParking() {
        return parking;
    }

    public int getParkingDistance() {
        return parkingDistance;
    }

    public boolean isAirport(){
        return (parking.getAirport() && parkingDistance < 1500);
    }

    public boolean isTransCity(){
        return parkingDistance > 30000;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject result = super.toJSON();
        if (parking != null){
            JSONObject parkingJSON = parking.toJSON();
            parkingJSON.put("distance", parkingDistance);
            if (parking.getAirport()){
                parkingJSON.remove("is_airport");
                if (isAirport()){
                    parkingJSON.put("is_airport", true);
                }
            }
            result.put("parking", parkingJSON);
        }
        return result;
    }
}
