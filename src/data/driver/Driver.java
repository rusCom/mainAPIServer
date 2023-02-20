package data.driver;

import org.json.JSONObject;

public class Driver {
    protected Integer ID;
    protected String Token;
    protected String Phone;
    protected String Code;

    public boolean isAuth(){
        if (ID == null)return false;
        if (ID == 0)return false;
        if (Token == null)return false;
        return !Token.equals("");
    }

    public Integer getID() {
        return ID;
    }

    public String getPhone() {
        return Phone;
    }

    public String getCode() {
        return Code;
    }

    public String getToken() {
        return Token;
    }

    public JSONObject toJson(){
        JSONObject result = new JSONObject();

        result.put("id", this.ID);
        result.put("token", this.Token);
        result.put("phone", this.Phone);
        result.put("code", this.Code);

        return result;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    public static aTaxi.data.Driver fromJSON(JSONObject data){return null;}
}
