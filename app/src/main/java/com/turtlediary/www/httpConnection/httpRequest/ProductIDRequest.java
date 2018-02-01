package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.ProductPriceBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.interfaces.ProductIdCompleteInterface;
import com.turtlediary.www.utilities.Util;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 13/11/17.
 */

public class ProductIDRequest {
    public static void getProductId(final  Activity activity,final ProductIdCompleteInterface productIdCompleteInterface, Map<String, String> paramsMap) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callApi = apiInterface.callProductIdRequest(paramsMap);
        ConnectionManager.callApi(callApi, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error-" + msg);

                productIdCompleteInterface.productIDFailed(Util.serverErrorMessageSet(activity,msg));
            }

            @Override
            public void onWebServiceComplete(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Gson gson = new Gson();
                    ProductPriceBean productPriceBean = gson.fromJson(response, ProductPriceBean.class);
                    if(jsonObject.has("status")) {
                        if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                            productIdCompleteInterface.productIDSucess(productPriceBean);
                        } else {
                            if (jsonObject.has("message")) {
                                productIdCompleteInterface.productIDFailed(jsonObject.getString("message"));
                            }
                        }
                    }

                } catch (Exception ex) {

                }
               /* if (jsonObject.has("message")) {
                    if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                        mListener.onWebServiceComplete(jsonObject.toString());
                    } else {
                        mListener.onWebStatusFalse("Getting status as false");
                    }
                }*/


            }
        });
    }
}
