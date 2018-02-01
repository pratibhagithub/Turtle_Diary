package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.RegistrationResultBean;
import com.turtlediary.www.beans.SignUpPageBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.interfaces.RegistrationCompleteInterface;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 13/11/17.
 */

public class RegistrationRequest {
    public static void userRegistration(final Activity activity,final RegistrationCompleteInterface registrationCompleteInterface, Map<String, String> paramsMap) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callRegistration = apiInterface.callRegistrationRequest(paramsMap);
        ConnectionManager.callApi(callRegistration, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error-" + msg);
                registrationCompleteInterface.onRegistrationFailed(Util.serverErrorMessageSet(activity,msg));
            }
            @Override
            public void onWebServiceComplete(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    RegistrationResultBean registrationResultBean = gson.fromJson(response, RegistrationResultBean.class);
                    if(jsonObject.has("result")) {
                        if (registrationResultBean.getResult().equalsIgnoreCase(AppConstants.TRUE)) {
                            registrationCompleteInterface.onRegistrationSucess(registrationResultBean);
                        } else {
                            MyApplication myApplication = MyApplication.getInstance();
                             SignUpPageBean signUpPageBean=myApplication.getBaseBean().getConfig().getNewLoginScreen().getSignupPage();

                            // we are getting the msg code from bean
                            if (registrationResultBean.getEmail().equalsIgnoreCase(AppConstants.TRUE) && registrationResultBean.getUsername().equalsIgnoreCase(AppConstants.TRUE)) {
                                registrationCompleteInterface.onRegistrationFailed(signUpPageBean.getUserAlreayFound());
                            }
                            else if (registrationResultBean.getEmail().equalsIgnoreCase(AppConstants.TRUE)) {
                                registrationCompleteInterface.onRegistrationFailed(signUpPageBean.getEmailAlreayFound());
                            }else if(registrationResultBean.getUsername().equalsIgnoreCase(AppConstants.TRUE)) {
                                registrationCompleteInterface.onRegistrationFailed(signUpPageBean.getUserAlreayFound());
                            }
                        }
                    }
                } catch (Exception ex) {

                }

            }
        });
    }
}