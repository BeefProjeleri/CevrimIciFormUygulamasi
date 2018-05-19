package com.tasarm.proje.prjtsrm.classs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 07.02.2018.
 */

public class SharedPreferencesDurum {


    private static String MY_STRING_PREF = "mystringpref";

    private static String MY_BOOL_PREF = "shareduserid";


    public static SharedPreferences getPrefs(Context context) {

        return context.getSharedPreferences("UserNameAcrossApplication", context.MODE_PRIVATE);

    }

    public static String getMyStringPref(Context context) {

        return getPrefs(context).getString(MY_STRING_PREF, "burhan");
    }

    public static boolean getMyBoolPref(Context context) {

        return getPrefs(context).getBoolean(MY_BOOL_PREF, false);
    }

    public static void setMyStringPref(Context context, String value) {
        // perform validation etc..
        getPrefs(context).edit().putString(MY_STRING_PREF, value).commit();
    }

    public static void setMyBoolPref(Context context, Boolean value) {
        // perform validation etc..
        getPrefs(context).edit().putBoolean(MY_BOOL_PREF, value).commit();
    }
        static  boolean bool=false;
    public static void setbool() {
        // perform validation etc..
        bool=true;

    }

    public static boolean getbool() {
        // perform validation etc..
        return bool;

    }

}
