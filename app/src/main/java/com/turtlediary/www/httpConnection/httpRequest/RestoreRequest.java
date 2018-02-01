package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnRestoreCompletion;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 5/12/17.
 */

public class RestoreRequest {

    /*  dictionary["userId"]=TDBridgingObjCSwiftData.TDuserId
    //others vars
    dictionary["upgrade"]="yes"
    dictionary["action"]="registration"
    dictionary["regBy"]="td_ios"
    //username
    dictionary["username"] = TDBridgingObjCSwiftData.TDuserName
    //password
    dictionary["password"] = TDBridgingObjCSwiftData.TDpassword
    //email
    //receipt
    dictionary["receipt"] = TDpurchase.sharedInstance.receiptData
*/


    public static void getRestore(final Activity activity, Map<String, String> valueMap, final OnRestoreCompletion onRestoreCompletion) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callRestore = apiInterface.callRestoreRequest(valueMap);
        ConnectionManager.callApi(callRestore, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");
                onRestoreCompletion.restorFailed(Util.serverErrorMessageSet(activity,msg));            }

            @Override
            public void onWebServiceComplete(String response) {
                Log.d("TAG", "Success" + response);

                Gson gson = new Gson();
/*
                RegistrationResponseBean registrationResponseBean=gson.fromJson(response, RegistrationResponseBean.class);
*/

                onRestoreCompletion.restorSuccess();



            }
        });
    }
}
