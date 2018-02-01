package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.RegistrationResponseBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnPaymentConfirmation;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 5/12/17.
 */

public class PaymentReciepRequest {

    public static void getPaymentReceiptFromServer(final  Activity activity, Map<String, String> valueMap, final OnPaymentConfirmation onPaymentConfirmation) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callLogin = apiInterface.callReceiptRequest(valueMap);
        ConnectionManager.callApi(callLogin, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");
                onPaymentConfirmation.paymentFailed(Util.serverErrorMessageSet(activity,msg));            }

            @Override
            public void onWebServiceComplete(String response) {
                Log.d("TAG", "Success" + response);

                Gson gson = new Gson();
                RegistrationResponseBean registrationResponseBean=gson.fromJson(response, RegistrationResponseBean.class);

                onPaymentConfirmation.paymentSuccess(registrationResponseBean);



            }
        });
    }
}
