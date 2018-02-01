package com.turtlediary.www.httpConnection.requestParam;

import android.app.Activity;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.requestModel.ProductRequestModel;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pratibha on 18/1/18.
 */

public class ProductIDfromServerParam {
    public static Map<String, String> getParam(Activity activity) {
        Gson gson = new Gson();
        ProductRequestModel productRequestModel = new ProductRequestModel();
        productRequestModel.setEnvir(ApiKeys.ENVIR);
        productRequestModel.setAppVersion(Util.getApplicationVersion(activity));
        productRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        productRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (activity.getResources().getBoolean(R.bool.isTablet)) {
            productRequestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            productRequestModel.setUserDevice(activity.getString(R.string.phone));
        }
        productRequestModel.setApiVersion(ApiKeys.API_VERSION);


        String data = gson.toJson(productRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sPRODUCT_ID);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        return apiParamMap;
    }
}
