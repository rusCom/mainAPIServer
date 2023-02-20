package aTaxi.data;

public class DriverStatus {
    public static final int Busy = 0;
    public static final int Free = 2;
    public static final int OnOrder = 3;

    public static String getString(int status){
        if (status == DriverStatus.Busy)return "busy";
        if (status == DriverStatus.Free)return "free";
        if (status == DriverStatus.OnOrder)return "on_order";
        return "null";
    }

}
