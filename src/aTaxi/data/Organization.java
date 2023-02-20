package aTaxi.data;

import org.json.JSONObject;
import static tools.MainUtils.JSONGetInteger;
import static tools.MainUtils.JSONGetString;

public class Organization {
    public Integer ID;
    public String driverSupportPhone;


    public Organization(JSONObject data) {
        this.ID = JSONGetInteger(data, "id");
        this.driverSupportPhone = JSONGetString(data, "driver_support_phone");
    }

    public JSONObject debugJSON(){
        JSONObject  result = new JSONObject();
        result.put("id", ID);
        result.put("driver_support_phone", driverSupportPhone);
        return result;
    }
}
