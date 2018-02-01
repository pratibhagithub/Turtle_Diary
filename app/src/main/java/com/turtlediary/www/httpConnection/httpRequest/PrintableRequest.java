package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.PrintableCategoryBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnPrintableRequestCompleteListner;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 12/12/17.
 */

public class PrintableRequest {

    public static void  printableRequest(final Activity activity, final OnPrintableRequestCompleteListner onPrintableRequestCompleteListner, Map<String,String> paramsMap)
{
    ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
    Call<ResponseBody> callPrintableList = apiInterface.callPrintableRequest(paramsMap);
    ConnectionManager.callApi(callPrintableList, new OnWebServiceCompleteListener() {
        @Override
        public void onWebStatusFalse(String msg) {

            onPrintableRequestCompleteListner.requestFailed(Util.serverErrorMessageSet(activity,msg));
        }

        @Override
        public void onWebServiceComplete(String response) {

            Log.e("response....",response);
            Gson gson = new Gson();
            PrintableCategoryBean printableCategoryBean = gson.fromJson(response, PrintableCategoryBean.class);
            onPrintableRequestCompleteListner.requestSuccess(printableCategoryBean);
        }
    });    }



}
