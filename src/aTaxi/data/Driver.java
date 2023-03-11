package aTaxi.data;

import aTaxi.ATaxiApplication;
import aTaxiAPI.Taximeter;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import geo.data.Location;
import org.json.JSONObject;
import tools.DateTimeUtils;

import java.time.LocalDateTime;

import static tools.MainUtils.*;

public class Driver extends data.driver.Driver {
    Database database;
    public boolean isBlocked;
    private String callSign;
    public Organization organization;
    public City city;
    public String inviteCode;
    public String tariff;
    private LocalDateTime licenseAgreementDate;
    public boolean pushNotificationActive;
    public Integer taximeterVersion;
    public Integer taximeterFreeOrderCount;
    public String fullName;
    public String GUID;
    public String carName;
    public Integer status;
    public Float balance;
    public Integer driverTariffPlanID;
    public LocalDateTime driverTariffPlanEndDate;
    private String phone;
    public Driver() {
    }

    public Driver(Database database, String token, Location location) throws CacheException {
        this.database = database;
        this.Token = token;

        String authString = this.Token + "^";
        if (location != null) {
            authString += location.getLatitudeStr() + "^";
            authString += location.getLongitudeStr() + "^";
            authString += location.speed.toString() + "^";
            authString += location.bearing.toString() + "^";
        } else {
            authString += "^";
            authString += "^";
            authString += "^";
            authString += "^";
        }
        this.ID = Integer.parseInt(Taximeter.Auth(database, authString));
        this.loadData();
    }

    public Driver(Database database, String token) throws CacheException {
        this.database = database;
        this.Token = token;
        this.ID = Integer.parseInt(Taximeter.Auth(database, token));
        this.loadData();
    }

    private void loadData() throws CacheException {

        if (this.isAuth()) {
            JSONObject data = new JSONObject(Taximeter.GetDriver(database, String.valueOf(this.ID)));
            Phone = JSONGetString(data, "phone");
            Code = JSONGetString(data, "code");
            isBlocked = JSONGetBool(data, "is_blocked");
            callSign = JSONGetString(data, "callsign");
            phone = JSONGetString(data, "phone");
            organization = ATaxiApplication.getInstance().getOrganization(JSONGetInteger(data, "organization_id"));
            city = ATaxiApplication.getInstance().getCity(JSONGetInteger(data, "city_id"));
            inviteCode = JSONGetString(data, "invite_code");
            tariff = JSONGetString(data, "tariff");
            pushNotificationActive = JSONGetBool(data, "push_notification_active");
            taximeterVersion = JSONGetInteger(data, "taximeter_version");
            taximeterFreeOrderCount = JSONGetInteger(data, "taximeter_free_order_count");
            status = JSONGetInteger(data, "status");
            balance = JSONGetFloat(data, "balance");
            driverTariffPlanID = JSONGetInteger(data, "tariff_plan_id");
            driverTariffPlanEndDate = DateTimeUtils.convertFromCache(JSONGetString(data, "tariff_plan_end_date"));

            licenseAgreementDate = DateTimeUtils.convertFromCache(JSONGetString(data, "license_agreement_date"));
        }

    }

    public String getCallSign() {
        return callSign;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public boolean checkLicenseAgreementDate(int documentsTimeout){
        return licenseAgreementDate.isBefore(LocalDateTime.now().minusDays(documentsTimeout));
    }

    public JSONObject debugJSON() throws CacheException {
        JSONObject result = super.toJson();

        result.put("is_blocked", isBlocked);
        result.put("organization", organization.debugJSON());
        result.put("city", city.debugJSON());
        result.put("invite_code", inviteCode);

        return result;
    }

    public static Driver byPhone(Database database, String phone) throws CacheException {
        String token = Taximeter.Auth(database, "phone^" + phone);
        return new Driver(database, token);
    }

    public static Driver fromJSON(JSONObject data) {
        if (data == null) return null;
        if (data.toString().equals("")) return null;
        if (data.toString().equals("{}")) return null;
        Driver result = new Driver();
        result.fullName = JSONGetString(data, "full_name");
        result.GUID = JSONGetString(data, "guid");
        result.carName = JSONGetString(data, "car_name");
        return result;
    }


}
