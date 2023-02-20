package geo.data;

import org.json.JSONArray;
import org.json.JSONObject;

public class Location {
    protected Double latitude;
    protected Double longitude;
    public Double accuracy;
    public Double bearing;
    public Double speed;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = round6(latitude);
        this.longitude = round6(longitude);
    }

    public Location(JSONObject location) {
        this.latitude = round6(location.getDouble("lt"));
        this.longitude = round6(location.getDouble("ln"));

        if (location.has("accuracy")) {
            this.accuracy = location.getDouble("accuracy");
        }
        if (location.has("bearing")) {
            this.bearing = location.getDouble("bearing");
        }
        if (location.has("speed")) {
            this.speed = location.getDouble("speed");
        }
    }

    public static Location fromJSONArray(JSONArray array){
        Location location = new Location();
        location.longitude = array.getDouble(0);
        location.latitude = array.getDouble(1);
        return location;
    }

    public static Location fromCache(String dataStr){
        String[] data = dataStr.split("\\^");
        Location location = new Location();
        location.latitude = Double.parseDouble(data[0]);
        location.longitude = Double.parseDouble(data[1]);
        if (!data[2].equals("")){
            location.accuracy = Double.parseDouble(data[2]);
        }
        if (!data[3].equals("")){
            location.bearing = Double.parseDouble(data[3]);
        }
        if (!data[4].equals("")){
            location.speed = Double.parseDouble(data[4]);
        }

        return location;
    }

    public static Location fromLocation(Location location, int round){
        Location newLocation = new Location();
        newLocation.longitude = location.longitude;
        newLocation.latitude = location.latitude;
        newLocation.roundLocation(round);
        return newLocation;
    }

    public static Location fromLocation(Location location, double blunder){
        Location newLocation = new Location();
        newLocation.longitude = location.longitude;
        newLocation.latitude = location.latitude;
        newLocation.blunder(blunder);
        return newLocation;
    }

    public boolean isCheck(){
        if (this.latitude == null)return false;
        if (this.longitude == null)return false;
        return true;
    }

    public int distanceTo(Location to){
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(to.getLatitude() - getLatitude());
        double lonDistance = Math.toRadians(to.getLongitude() - getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(getLatitude())) * Math.cos(Math.toRadians(to.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        // double height = el1 - el2;

        distance = Math.pow(distance, 2); // + Math.pow(height, 2);

        return Math.toIntExact(Math.round(Math.sqrt(distance)));
    }


    public void blunder(Double blunder){
        if (blunder == 0){return;}
        double dLatitude = 0.009;
        double dLongitude = 0.0156;

        dLatitude = dLatitude * blunder;
        dLongitude = dLongitude * blunder;

        this.latitude = Math.round(round6(this.latitude)/dLatitude) * dLatitude;
        this.longitude = Math.round(round6(this.longitude)/dLongitude) * dLongitude;
        roundLocation();
    }

    public void roundLocation(){
        this.latitude = this.round6(this.latitude);
        this.longitude = this.round6(this.longitude);
    }

    public void roundLocation(int digits){
        switch (digits) {
            case 6 -> {
                this.latitude = this.round6(this.latitude);
                this.longitude = this.round6(this.longitude);
            }
            case 5 -> {
                this.latitude = this.round5(this.latitude);
                this.longitude = this.round5(this.longitude);
            }
            case 4 -> {
                this.latitude = this.round4(this.latitude);
                this.longitude = this.round4(this.longitude);
            }
            case 3 -> {
                this.latitude = this.round3(this.latitude);
                this.longitude = this.round3(this.longitude);
            }
            case 2 -> {
                this.latitude = this.round2(this.latitude);
                this.longitude = this.round2(this.longitude);
            }
        }

    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    private Double round6(Double in){
        return Math.round(in*1000000.0)/1000000.0;
    }

    private Double round5(Double in){
        return Math.round(in*100000.0)/100000.0;
    }

    private Double round4(Double in){
        return Math.round(in*10000.0)/10000.0;
    }

    private Double round3(Double in){
        return Math.round(in*1000.0)/1000.0;
    }
    private Double round2(Double in){
        return Math.round(in*100.0)/100.0;
    }

    public String getLatitudeStr(){
        return this.latitude.toString();
    }

    public String getLongitudeStr(){
        return this.longitude.toString();
    }
    public String toCacheLocationData() {
        String res = "";
        res += this.latitude.toString() + ";";
        res += this.longitude.toString() + ";";
        if (this.accuracy != null) {
            res += this.accuracy.toString() + ";";
        } else {
            res += ";";
        }
        if (this.speed != null) {
            res += this.speed.toString() + ";";
        } else {
            res += ";";
        }
        if (this.bearing != null) {
            res += this.bearing.toString() + ";";
        } else {
            res += ";";
        }
        return res;
    }

    JSONArray toORS(){
        JSONArray result = new JSONArray();
        result.put(longitude);
        result.put(latitude);
        return result;
    }


    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("lt", this.latitude);
        result.put("ln", this.longitude);
        if (this.accuracy != null) {
            result.put("accuracy", this.accuracy);
        }
        if (this.bearing != null) {
            result.put("bearing", this.bearing);
        }
        if (this.speed != null) {
            result.put("speed", this.speed);
        }
        return result;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }
}
