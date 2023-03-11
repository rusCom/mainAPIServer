package aTaxi.data;

import org.json.JSONObject;

import static tools.MainUtils.JSONGetInteger;
import static tools.MainUtils.JSONGetString;

public class Client {
    private Integer id;
    private String name;
    private Integer type;
    private Integer dispatchingID;
    private Integer calcDiscountPercent;
    private Integer calcMarginPercent;

    public static Client fromJSON(JSONObject data){
        Client client = new Client();
        client.id = JSONGetInteger(data, "id");
        client.name = JSONGetString(data, "name");
        client.type = JSONGetInteger(data, "type");
        client.dispatchingID = JSONGetInteger(data, "dispatching_id");
        client.calcDiscountPercent = JSONGetInteger(data, "calc_discount_percent");
        client.calcMarginPercent = JSONGetInteger(data, "calc_margin_percent");
        return client;
    }

    public JSONObject toJSON(){
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        result.put("type", type);
        result.put("dispatching_id", dispatchingID);
        result.put("calc_discount_percent", calcDiscountPercent);
        result.put("calc_margin_percent", calcMarginPercent);
        return result;
    }
}
