package com.turtlediary.www.httpConnection.requestParam;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.requestModel.RegistrationForPaymentReceiptModel;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pratibha on 18/1/18.
 */

public class RegistrationRequestParam {
    public static Map<String, String> getParam(HashMap<String,String> hasmapUserAddedData, String purchaseToken, String productId, Activity activity)
    {
        String email=hasmapUserAddedData.get(AppConstants.EMAIL);
        String pwd=hasmapUserAddedData.get(AppConstants.PASSWORD);
        String userName=hasmapUserAddedData.get(AppConstants.UNAME);

        Gson gson = new Gson();
        RegistrationForPaymentReceiptModel registrationForPaymentReceiptModel = new RegistrationForPaymentReceiptModel();
        registrationForPaymentReceiptModel.setEmail(email);
        registrationForPaymentReceiptModel.setProductId(productId);
        registrationForPaymentReceiptModel.setPassword(pwd);
        registrationForPaymentReceiptModel.setUsername(userName);
        registrationForPaymentReceiptModel.setReceipt(purchaseToken);
        registrationForPaymentReceiptModel.setRegBy(ApiKeys.TD_ANDROID);
        registrationForPaymentReceiptModel.setEnvir(ApiKeys.ENVIR);
        registrationForPaymentReceiptModel.setAppVersion(Util.getApplicationVersion(activity));
        registrationForPaymentReceiptModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        registrationForPaymentReceiptModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (activity.getResources().getBoolean(R.bool.isTablet)) {
            registrationForPaymentReceiptModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            registrationForPaymentReceiptModel.setUserDevice(activity.getString(R.string.phone));
        }
        if(TextUtils.isEmpty(Preferences.getUserId(activity.getApplicationContext())))
        {

        }else
        {
            registrationForPaymentReceiptModel.setUserId(Preferences.getUserId(activity.getApplicationContext()));
        }
        registrationForPaymentReceiptModel.setApiVersion(ApiKeys.API_VERSION);
        String data = gson.toJson(registrationForPaymentReceiptModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sREGSTATION_API_AFTER_PAYMENT);//turtleapp.TdAppRegistration
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);//
        return apiParamMap;
    }
}
