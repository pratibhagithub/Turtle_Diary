package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.LessonResponseBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnLessonContentCompleteListner;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 7/11/17.
 */

public class LessonContentRequest {
    public static void getLessonCotent(final Activity activity,Map<String, String> valueMap, final OnLessonContentCompleteListner lessonContentCompleteListner) {
        ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callLogin = apiInterface.callLessonContentRequest(valueMap);
        ConnectionManager.callApi(callLogin, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");
                lessonContentCompleteListner.onFailed(Util.serverErrorMessageSet(activity,msg));
            }
            @Override
            public void onWebServiceComplete(String response) {
                Log.d("TAG", "Success" + response);

              Gson gson = new Gson();
                LessonResponseBean lessonResponseBean = gson.fromJson(response, LessonResponseBean.class);
                lessonContentCompleteListner.onSucces(lessonResponseBean);
            }
        });
    }
}
