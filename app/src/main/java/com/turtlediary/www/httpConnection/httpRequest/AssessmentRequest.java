package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.AssesmentBean;
import com.turtlediary.www.beans.PrintableCategoryBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.AssessmentComplete;
import com.turtlediary.www.interfaces.OnPrintableRequestCompleteListner;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 27/12/17.
 */

public class AssessmentRequest {
    public static void  assessmentRequest(final Activity activity, final AssessmentComplete assessmentComplete, Map<String,String> paramsMap)
{
    ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
    Call<ResponseBody> callPrintableList = apiInterface.callPrintableRequest(paramsMap);
    ConnectionManager.callApi(callPrintableList, new OnWebServiceCompleteListener() {
        @Override
        public void onWebStatusFalse(String msg) {

            assessmentComplete.requestFailed(Util.serverErrorMessageSet(activity,msg));
        }

        @Override
        public void onWebServiceComplete(String response) {

            Log.e("response....",response);
            Gson gson = new Gson();
            AssesmentBean assesmentBean=gson.fromJson(response, AssesmentBean.class);
          //  PrintableCategoryBean printableCategoryBean = gson.fromJson(response, PrintableCategoryBean.class);
            assessmentComplete.requestSuccess(assesmentBean);
        }
    });    }



}