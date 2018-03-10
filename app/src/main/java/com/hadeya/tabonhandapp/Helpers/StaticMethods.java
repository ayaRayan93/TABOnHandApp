package com.hadeya.tabonhandapp.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class StaticMethods
{
    public static String App_URL ="http://toh.hadeya.net/";
    public static String API_SERVICE_URL ="http://toh.hadeya.net/api/";
    public static final String NoPREFERENCES = "noPrefs";
    public static final String shared_NOT = "not";
    public static SharedPreferences sharedpreferences;
    public static String langAPIs;

    public static int getScreenWidth(Context ctx) {

        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        return width;
    }

    public static int getScreenHight(Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();

        int height = metrics.heightPixels;
        return height;
    }

    public static boolean HaveNetworkConnection(Context context) {
        boolean HaveConnectedWifi = false;
        boolean HaveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    HaveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    HaveConnectedMobile = true;
        }
        return HaveConnectedWifi || HaveConnectedMobile;
    }


    public static void ChangeLangEN(Context ctx) {

        sharedpreferences = ctx.getSharedPreferences(NoPREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(shared_NOT, "en");
        editor.commit();
    }

    public static void ChangeLangAR(Context ctx) {
        sharedpreferences = ctx.getSharedPreferences(NoPREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(shared_NOT, "ar");
        editor.commit();
    }

    public static boolean isLangEng(Context ctx) {
        boolean isOn = false;
        String not;
        sharedpreferences = ctx.getSharedPreferences(NoPREFERENCES,
                Context.MODE_PRIVATE);

        not = sharedpreferences.getString(shared_NOT, "");
        langAPIs = not;

        if (not.equals("en")) {
            isOn = true;
        } else if (not.equals("ar")) {
            isOn = false;
        } else {
            isOn = true;
        }
        return isOn;
    }

    public static String getlangAPIs() {
        //this is defualt lang for APIs
        if (langAPIs.equals("") || langAPIs.equals("en")) {
            return "en";
        } else {
            return "ar";
        }

    }
}
