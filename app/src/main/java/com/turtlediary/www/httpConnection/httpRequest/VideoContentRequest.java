package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.VideoResponseBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.interfaces.VideoContentCompleteListner;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pratibha on 8/11/17.
 */

public class VideoContentRequest {public static void getVideoCotent(final Activity activity ,Map<String, String> valueMap, final VideoContentCompleteListner videoContentCompleteListner) {
    ApiInterface apiInterface = ApiBuilder.createretrofit(activity);
    Call<ResponseBody> callLogin = apiInterface.callVideoContentRequest(valueMap);
    ConnectionManager.callApi(callLogin, new OnWebServiceCompleteListener() {
        @Override
        public void onWebStatusFalse(String msg) {
            Log.d("TAG", "Error");
            videoContentCompleteListner.onFailed(Util.serverErrorMessageSet(activity,msg));
        }
        @Override
        public void onWebServiceComplete(String response) {
            Log.d("TAG", "Success" + response);

            Gson gson = new Gson();
           VideoResponseBean videoResponseBean = gson.fromJson(response, VideoResponseBean.class);
            videoContentCompleteListner.onSucces(videoResponseBean);
        }
    });
}
}
