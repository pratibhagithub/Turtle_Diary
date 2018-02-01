package com.turtlediary.www.httpConnection.requestParam;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.requestModel.ContentTypeRequestModel;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pratibha on 18/1/18.
 */

public class ConfigRequestParam
{

    public static Map<String, String> getParams(Activity activity)
    {
        Gson gson = new Gson();
        ContentTypeRequestModel contentTypeRequest = new ContentTypeRequestModel();
        contentTypeRequest.setSlug(ApiKeys.sPreKVideoApp);
        // if user is logged in then their password otherwise default blank
        contentTypeRequest.setPassword(Preferences.getUserPassword(getApplicationContext()));
        // if user is logged in then select their role otherwise default teacher
        contentTypeRequest.setUsertype(ApiKeys.sTeacher);
        contentTypeRequest.setUid(Util.getUniqueDeviceID(activity));
        contentTypeRequest.setAction(ApiKeys.sLogin);
        contentTypeRequest.setAppVersion(Util.getApplicationVersion(activity));
        contentTypeRequest.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        contentTypeRequest.setLanguage(ApiKeys.sEnglish);
        // if user is logged in then their username otherwise default blank
        contentTypeRequest.setUsername(Preferences.getUserNAme(getApplicationContext()));
        contentTypeRequest.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (activity.getResources().getBoolean(R.bool.isTablet)) {
            contentTypeRequest.setUserDevice(ApiKeys.sUserDevice);
        } else {
            contentTypeRequest.setUserDevice(activity.getString(R.string.phone));
        }
        String data = gson.toJson(contentTypeRequest);

        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_ContentType);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("data",apiParamMap.toString());
        return apiParamMap;
    }
}
