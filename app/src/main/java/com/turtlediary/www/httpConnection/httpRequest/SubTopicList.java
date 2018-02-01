package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.SubTopicBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnSubTopicCompleteListener;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 2/11/17.
 */

public class SubTopicList {
    public static void  getTopicList(final Activity activity,final OnSubTopicCompleteListener onSubTopicCompleteListener, Map<String,String> paramsMap)
    {
        ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callSubTopicListList = apiInterface.callSubTopicRequest(paramsMap);

        ConnectionManager.callApi(callSubTopicListList, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");

                onSubTopicCompleteListener.requestFailed(Util.serverErrorMessageSet(activity,msg));
            }

            @Override
            public void onWebServiceComplete(String response) {

                Gson gson = new Gson();
                SubTopicBean topicbean=gson.fromJson(response, SubTopicBean.class);
                onSubTopicCompleteListener.requestSuccess(topicbean);


            }
        });    }


}
