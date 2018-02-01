package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.LoginBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnLoginInterface;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static com.turtlediary.www.utilities.AppConstants.EXCEED_MSG;

/**
 * Created by pratibha on 27/10/17.
 */

public class LoginRequest {

    public static void userLogin(final Activity activity, final OnLoginInterface onLoginInterface, Map<String, String> paramsMap) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callLogin = apiInterface.callLogin(paramsMap);
        ConnectionManager.callApi(callLogin, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error-" + msg);
                onLoginInterface.onLoginFailed(Util.serverErrorMessageSet(activity, msg));
            }

            @Override
            public void onWebServiceComplete(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                    if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                        onLoginInterface.onLoginSuccess(loginBean);
                    } else {
                        MyApplication myApplication = MyApplication.getInstance();
                        if (jsonObject.has("message")) {
                            String message = jsonObject.getString("message");
                            if (message.equalsIgnoreCase(AppConstants.INVALID_USER_US)||message.equalsIgnoreCase(AppConstants.INVALID_USER_China))
                                onLoginInterface.onLoginFailed(myApplication.getBaseBean().getConfig().getLogin().getInvalidText());
                            else if (message.equalsIgnoreCase(EXCEED_MSG))
                                onLoginInterface.onLoginFailed(message);
                            else
                                onLoginInterface.onLoginFailed(message);
                        }
                    }

                } catch (Exception ex) {

                }


            }
        });
    }
}
