package aTaxi.data;

import geo.data.Location;
import org.json.JSONObject;

import static tools.MainUtils.*;

public class Parking extends Location {
    private final String name;
    private final Boolean isAirport;


    public Parking(JSONObject jsonObject) {
        this.name = JSONGetString(jsonObject, "name");
        this.isAirport = JSONGetBool(jsonObject, "is_airport");
        this.latitude = JSONGetDouble(jsonObject, "latitude");
        this.longitude = JSONGetDouble(jsonObject, "longitude");
    }


    public Boolean getAirport() {
        return isAirport;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject result = super.toJSON();
        result.put("name", this.name);
        if (isAirport){result.put("is_airport", true);}
        return result;
    }
}
