package aTaxi;

import aTaxi.data.City;
import aTaxi.data.Organization;
import aTaxi.data.Parking;
import aTaxi.taximeter.TaximeterApplication;
import aTaxiAPI.Data;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import org.json.JSONArray;
import org.json.JSONObject;
import tools.MainUtils;

import java.util.ArrayList;
import java.util.List;

public class ATaxiApplication {
    private static volatile ATaxiApplication aTaxiApplicationInstance;
    private long lastLoadPreferences = 0;
    private final Database aTaxiDataBase;
    private final List<Organization> organizations;
    private final List<City> cities;
    private final List<Parking> parkings;
    private final TaximeterApplication taximeterApplication;

    private final String tinkoffTerminalKey;
    private final String tinkoffPublicKey;

    public String dataBaseURL, dataBaseUser, dataBasePassword;

    public static ATaxiApplication getInstance() throws CacheException {
        ATaxiApplication localInstance = aTaxiApplicationInstance;
        if (localInstance == null) {
            synchronized (ATaxiApplication.class) {
                localInstance = aTaxiApplicationInstance;
                if (localInstance == null) {
                    aTaxiApplicationInstance = localInstance = new ATaxiApplication();
                }
            }
        }
        return localInstance;
    }

    public ATaxiApplication() throws CacheException {

        MainUtils.getInstance().printLog("main","crate aTaxiApplication class");

        dataBaseURL = "jdbc:Cache://" + MainUtils.getInstance().getPropertyString("ataxi.address") + ":"
                + MainUtils.getInstance().getPropertyString("ataxi.port") + "/"
                + MainUtils.getInstance().getPropertyString("ataxi.namespace");
        dataBaseUser = MainUtils.getInstance().getPropertyString("ataxi.username");
        dataBasePassword = MainUtils.getInstance().getPropertyString("ataxi.password");

        MainUtils.getInstance().printLog("main","Connection to Cache Database " + dataBaseURL);

        aTaxiDataBase = CacheDatabase.getDatabase(dataBaseURL, dataBaseUser, dataBasePassword); // aTaxiConnectionPool.getConnection();

        MainUtils.getInstance().printLog("main","Connection to Cache Database success");

        organizations = new ArrayList<>();
        cities = new ArrayList<>();
        parkings = new ArrayList<>();

        taximeterApplication = new TaximeterApplication();

        tinkoffTerminalKey = MainUtils.getInstance().getPropertyString("tinkoff.terminalKey");
        tinkoffPublicKey = MainUtils.getInstance().getPropertyString("tinkoff.publicKey");



        this.loadApplicationPreferences();
    }

    public Database getDataBase() {
        return aTaxiDataBase;
    }

    public void loadApplicationPreferences() throws CacheException{
        if (MainUtils.passedTimeMin(this.lastLoadPreferences) > 10){
            cities.clear();
            organizations.clear();
            parkings.clear();
            JSONObject data = new JSONObject(Data.ApplicationPreferences(aTaxiDataBase));


            JSONArray citiesArray = data.getJSONArray("cities");
            for (int itemID = 0; itemID < citiesArray.length(); itemID++){
                City city = new City(citiesArray.getJSONObject(itemID));
                cities.add(city);
            }

            JSONArray organizationsArray = data.getJSONArray("organizations");
            for (int itemID = 0; itemID < organizationsArray.length(); itemID++){
                Organization organization = new Organization(organizationsArray.getJSONObject(itemID));
                organizations.add(organization);
            }

            JSONArray parkingsArray = data.getJSONArray("parkings");
            for (int itemID = 0; itemID < parkingsArray.length(); itemID++){
                Parking parking = new Parking(parkingsArray.getJSONObject(itemID));
                parkings.add(parking);
            }


            taximeterApplication.loadApplicationPreferences(data.getJSONObject("taximeter"));

            this.lastLoadPreferences = System.currentTimeMillis();
        }
    }

    public City getCity(Integer ID) throws CacheException {
        City result = null;

        for (City city : cities) {
            if (city.ID.equals(ID)) {
                result = city;
            }
        }

        if (result == null) {
            JSONObject cityJSON = new JSONObject(Data.City(aTaxiDataBase, String.valueOf(ID)));
            City city = new City(cityJSON);
            cities.add(city);
            result = city;
        }

        return result;
    }

    public Organization getOrganization(Integer ID) throws CacheException {
        if (ID == null){return null;}
        if (ID == 0){return null;}
        Organization result = null;

        for (Organization organization : organizations) {
            if (organization.ID.equals(ID)) {
                result = organization;
            }
        }
        if (result == null) {
            JSONObject organizationJSON = new JSONObject(Data.Organization(aTaxiDataBase, String.valueOf(ID)));
            Organization organization = new Organization(organizationJSON);
            organizations.add(organization);
            result = organization;
        }

        return result;
    }

    public String getTinkoffTerminalKey() {
        return tinkoffTerminalKey;
    }

    public String getTinkoffPublicKey() {
        return tinkoffPublicKey;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public TaximeterApplication getTaximeterApplication() {
        return taximeterApplication;
    }
}

