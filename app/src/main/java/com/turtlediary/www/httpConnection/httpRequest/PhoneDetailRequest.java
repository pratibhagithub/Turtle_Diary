package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnPhoneDetailRequestCompleteListener;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by tarun on 30/10/17.
 */

public class PhoneDetailRequest {
    public static void callPhoneDetailApi(Activity activity,Map<String, String> valueMap, final OnPhoneDetailRequestCompleteListener onPhoneDetailRequestCompleteListener) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callPhoneDetail = apiInterface.callPhoneDetail(valueMap);
        ConnectionManager.callApi(callPhoneDetail, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");
                onPhoneDetailRequestCompleteListener.onFailure(msg);
            }

            @Override
            public void onWebServiceComplete(String response) {
                Log.d("TAG", "Success" + response);

              onPhoneDetailRequestCompleteListener.onSuccess();


            }
        });
    }
}
