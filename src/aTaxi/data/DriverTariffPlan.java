package aTaxi.data;

import org.json.JSONObject;
import static tools.MainUtils.JSONGetInteger;
import static tools.MainUtils.JSONGetString;

public class DriverTariffPlan {
    public Integer ID;
    String Name;
    public Integer Cost;
    Integer lastID;
    public Integer CityID;

    public DriverTariffPlan(JSONObject data)  {
        ID = JSONGetInteger(data, "id");
        Name = JSONGetString(data, "name");
        Cost = JSONGetInteger(data, "cost");
        CityID = JSONGetInteger(data, "city_id");
        lastID = JSONGetInteger(data, "last_id");
    }

    public JSONObject taximeterJSON(){
        return debugJSON();
    }

    public JSONObject debugJSON(){
        JSONObject result = new JSONObject();
        result.put("id", ID);
        result.put("name", Name);
        result.put("cost", Cost);
        result.put("last_id", lastID);
        return result;
    }
}
