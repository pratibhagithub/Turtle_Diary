package com.turtlediary.www.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
    private static final String USERSTATUS = "username";
    private static final String COUNTRY_CODE = "countryCODE";
    private static final String USERNAME = "userstatus";
    private static final String USEREMAIL = "userEmail";
    private static final String USERID = "userId";
    private static final String USERPASSWORD = "userpwd";
    private static final String USERTYPE = "userType";
    private static final String USERTOKEN = "userToken";
    private static final String BACKGROUND_SOUND = "BackgroundSound";
    private static final String PURCHASE_ITEM_ID = "skuID";
    private static final String EFFECT_SOUND = "EffectSound";
    // private static final String BASEURL = "baseurl";
    // private static final String SERVERTYPE = "servertype";


    public static void saveBackgroundSoundEnability(Context context, boolean isEnable) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(BACKGROUND_SOUND, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putBoolean(BACKGROUND_SOUND, isEnable);
        accessTokenEditor.commit();
    }


    public static boolean getBackgroundSoundEnability(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(BACKGROUND_SOUND, 0);
        return accessTokenSharedPref.getBoolean(BACKGROUND_SOUND, true);
    }

    public static void saveEffectSoundEnability(Context context, boolean isEnable) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(EFFECT_SOUND, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putBoolean(EFFECT_SOUND, isEnable);
        accessTokenEditor.commit();
    }


    public static boolean getEffectSoundEnability(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(EFFECT_SOUND, 0);
        return accessTokenSharedPref.getBoolean(EFFECT_SOUND, true);
    }


    public static void saveCountryCode(Context context, String countryCode) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(COUNTRY_CODE, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(COUNTRY_CODE, countryCode);
        accessTokenEditor.commit();
    }

    public static String getCountryCode(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(COUNTRY_CODE, 0);
        return accessTokenSharedPref.getString(COUNTRY_CODE, "");
    }




 public static void saveUserId(Context context, String uID) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERID, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USERID, uID);
        accessTokenEditor.commit();
    }

    public static String getUserId(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERID, 0);
        return accessTokenSharedPref.getString(USERID, "");
    }

    public static void saveUserName(Context context, String uName) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERNAME, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USERNAME, uName);
        accessTokenEditor.commit();
    }

    public static String getUserNAme(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERNAME, 0);
        return accessTokenSharedPref.getString(USERNAME, "");
    }

  public static void saveUserEmail(Context context, String uEMAIL) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USEREMAIL, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USEREMAIL, uEMAIL);
        accessTokenEditor.commit();
    }

    public static String getUserEmail(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USEREMAIL, 0);
        return accessTokenSharedPref.getString(USEREMAIL, "");
    }


    public static void saveUserPassword(Context context, String uPwd) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERPASSWORD, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USERPASSWORD, uPwd);
        accessTokenEditor.commit();
    }

    public static String getUserPassword(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERPASSWORD, 0);
        return accessTokenSharedPref.getString(USERPASSWORD, "");
    }


    public static void saveUserType(Context context, String uType) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERTYPE, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USERTYPE, uType);
        accessTokenEditor.commit();
    }

    public static String getUserType(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERTYPE, 0);
        return accessTokenSharedPref.getString(USERTYPE, "");
    }

    public static void saveUserToken(Context context, String userTokenn) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERTOKEN, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(USERTOKEN, userTokenn);
        accessTokenEditor.commit();
    }

    public static String getUserToken(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERTOKEN, 0);
        return accessTokenSharedPref.getString(USERTOKEN, "");
    }


    public static void getSavePurchaseID(Context context, String purchaded_sku_id) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(PURCHASE_ITEM_ID, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putString(PURCHASE_ITEM_ID, purchaded_sku_id);
        accessTokenEditor.commit();
    }

    public static String getPurchasedItemId(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(PURCHASE_ITEM_ID, 0);
        return accessTokenSharedPref.getString(PURCHASE_ITEM_ID, "");
    }

    public static void saveUserStatus(Context context, boolean userStatus) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(USERSTATUS, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putBoolean(USERSTATUS, userStatus);
        accessTokenEditor.commit();
    }

    public static boolean getUserStatus(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(USERSTATUS, 0);
        return accessTokenSharedPref.getBoolean(USERSTATUS, false);
    }

   /* public static void saveServerType(Context context, int serverType) {
        SharedPreferences accessTokenSharedPref = context.getSharedPreferences(SERVERTYPE, 0);
        Editor accessTokenEditor = accessTokenSharedPref.edit();
        accessTokenEditor.putInt(SERVERTYPE, serverType);
        accessTokenEditor.commit();
    }

    public static int getServerType(Context con) {
        SharedPreferences accessTokenSharedPref = con.getSharedPreferences(SERVERTYPE, 0);
        return accessTokenSharedPref.getInt(SERVERTYPE, 0);
    }*/


}
