package com.limitless.haulified.Haulifier.common;


import java.util.ArrayList;

public class AppConstants {


//live
   // public static String BASE_URL = "http://139.59.1.185:8080/";
    //test
    public static String BASE_URL = "http://139.59.24.46:8080";

    public static String token = "";
    //App font
    public static String DELIVERY = "/deliveries";

    public  static  String tokenExpMsg="Token Expire Please SignIn again";
    public  static  String ServerErrorMsg="Token Expire Please SignIn again";
    public static String FONT_ROBOTO_BOLD = "fonts/Roboto-Bold.ttf";
    public static String FONT_ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";
    public static String FONT_ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static String FONT_ROBOTO_LIGHT = "fonts/Roboto-Light.ttf";

    public static String Available_Vehicle = "Available";
    public static String Assigned_Vehicle = "Assigned";
    public static ArrayList<String> Trucker() {
        ArrayList<String> ms = new ArrayList<>();
        ms.add("Trucker");
        ms.add("Trucker1");
        ms.add("Trucker2");
        return ms;
    }
    public static ArrayList<String> Quantity() {
        ArrayList<String> ms = new ArrayList<>();
        ms.add("cbm");
        ms.add("cb ft");
        return ms;
    }
    public static ArrayList<String> Measurement() {
        ArrayList<String> ms = new ArrayList<>();
        ms.add("kg");
        ms.add("tons");
        return ms;
    }
    public static String Add_truck = "Add Truck";
    public static String  Edit_truck = "Edit Truck";

    public static String Add_driver = "Add Driver";
    public static String  Edit_driver = "Edit Driver";
}
