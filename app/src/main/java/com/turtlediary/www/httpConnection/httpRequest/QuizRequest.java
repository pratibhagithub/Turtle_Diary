package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.QuizBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnQuizRequestCompleteListener;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizRequest {

    public static void  quizRequest(final Activity activity,final OnQuizRequestCompleteListener onQuizRequestCompleteListener, Map<String,String> paramsMap)
    {
        ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callGameList = apiInterface.callQuizRequest(paramsMap);
        ConnectionManager.callApi(callGameList, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {


                onQuizRequestCompleteListener.requestFailed(Util.serverErrorMessageSet(activity,msg));
                // onHttpRequestCompleteListner.requestFailed();
            }

            @Override
            public void onWebServiceComplete(String response) {

                Log.e("response....",response);
                Gson gson = new Gson();
                QuizBean quizBean = gson.fromJson(response, QuizBean.class);
                onQuizRequestCompleteListener.requestSuccess(quizBean);


            }
        });    }

}
