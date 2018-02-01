package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnHttpRequestCompleteListner;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 26/10/17.
 */

public class ContentTypeRequest {
    public static void contentTypenUser(final Activity activity, Map<String, String> valueMap, final OnHttpRequestCompleteListner onHttpRequestCompleteListner) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callLogin = apiInterface.callContentType(valueMap);
        ConnectionManager.callApi(callLogin, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");
                onHttpRequestCompleteListner.requestFailed(msg);
            }

            @Override
            public void onWebServiceComplete(String response) {
                Log.d("TAG", "Success" + response);

                Gson gson = new Gson();
                BaseBean contentTypeBean = gson.fromJson(response, BaseBean.class);
                onHttpRequestCompleteListner.requestSuccess(contentTypeBean);


            }
        });
    }
}
