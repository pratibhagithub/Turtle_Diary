package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;

import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnLogOutComplete;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 31/10/17.
 */

public class LogoutRequest {
    public static void callLogOutApi(final Activity activity,final OnLogOutComplete onLogOutComplete, Map<String,String> paramsMap) {


    ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
    Call<ResponseBody> calllogoutApi = apiInterface.callLogout(paramsMap);
        ConnectionManager.callApi(calllogoutApi, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                onLogOutComplete.logoutFailed(Util.serverErrorMessageSet(activity,msg));
            }

            @Override
            public void onWebServiceComplete(String jsonResponse) {
                onLogOutComplete.logoutSuccess();

            }
        });
}
}
