package aTaxi.data;

import aTaxi.ATaxiApplication;
import com.intersys.objects.CacheException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static tools.MainUtils.*;

public class City {
    public Integer ID;
    String name;
    public String driverDispatcherPhone;
    public Boolean driverDispatcherMessages;

    public City(JSONObject data){
        this.ID = JSONGetInteger(data, "id");
        this.name = JSONGetString(data, "name");
        this.driverDispatcherPhone = JSONGetString(data, "driver_dispatcher_phone");
        this.driverDispatcherMessages = JSONGetBool(data, "driver_dispatcher_messages");
    }

    public List<DriverTariffPlan> getUnlimitedDriverTariffPlans() throws CacheException {
        List<DriverTariffPlan> result = new ArrayList<>();
        for (int itemID = 0; itemID < ATaxiApplication.getInstance().getTaximeterApplication().driverTariffPlans.size(); itemID++){
            DriverTariffPlan driverTariffPlan = ATaxiApplication.getInstance().getTaximeterApplication().driverTariffPlans.get(itemID);
            if (driverTariffPlan.CityID.equals(this.ID)){
                result.add(driverTariffPlan);
            }

        }
        return result;
    }

    public JSONObject toJSON(){
        JSONObject  result = new JSONObject();
        result.put("name", this.name);
        return result;
    }


    public JSONObject debugJSON() throws CacheException {
        JSONObject  result = new JSONObject();
        result.put("id", ID);
        result.put("driver_dispatcher_phone", driverDispatcherPhone);
        if (!getUnlimitedDriverTariffPlans().isEmpty()){
            JSONArray unlimitedDriversTariffPlanArray = new JSONArray();
            for (int itemID = 0; itemID < getUnlimitedDriverTariffPlans().size(); itemID++){
                unlimitedDriversTariffPlanArray.put(getUnlimitedDriverTariffPlans().get(itemID).debugJSON());
            }
            result.put("unlimited_drivers_tariff_plan", unlimitedDriversTariffPlanArray);
        }

        return result;
    }
}
