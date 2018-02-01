package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.SubjectContentListBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnSubjectCotentCompleteListener;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentRequest {

    public static void  subjectContentList(final Activity activity,final OnSubjectCotentCompleteListener onSubjectCotentCompleteListener, Map<String,String> paramsMap)
    {
        ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callGameList = apiInterface.callSubjectContentListRequest(paramsMap);

        ConnectionManager.callApi(callGameList, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");

                onSubjectCotentCompleteListener.requestFailed(Util.serverErrorMessageSet(activity,msg));
                // onHttpRequestCompleteListner.requestFailed();
            }

            @Override
            public void onWebServiceComplete(String response) {

                Gson gson = new Gson();
                SubjectContentListBean subjectContentListBean=gson.fromJson(response, SubjectContentListBean.class);
                onSubjectCotentCompleteListener.requestSuccess(subjectContentListBean);


            }
        });    }



}
