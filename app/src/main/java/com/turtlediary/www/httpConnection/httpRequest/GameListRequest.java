package com.turtlediary.www.httpConnection.httpRequest;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.beans.GameListBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiInterface;
import com.turtlediary.www.httpConnection.ConnectionManager;
import com.turtlediary.www.interfaces.OnGameListCompleteListener;
import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by arvind on 31/10/17.
 */

public class GameListRequest {

    public static void  gameList(final Activity activity, final OnGameListCompleteListener onGameListCompleteListener, Map<String,String> paramsMap)
    {
        ApiInterface apiInterface= ApiBuilder.createretrofit(activity);
        Call<ResponseBody> callGameList = apiInterface.callGameList(paramsMap);
        ConnectionManager.callApi(callGameList, new OnWebServiceCompleteListener() {
            @Override
            public void onWebStatusFalse(String msg) {
                Log.d("TAG", "Error");

                onGameListCompleteListener.requestFailed(Util.serverErrorMessageSet(activity,msg));
            }

            @Override
            public void onWebServiceComplete(String response) {
                Log.e("response....",response);
                Gson gson = new Gson();
                GameListBean gameListBean=gson.fromJson(response, GameListBean.class);
                onGameListCompleteListener.requestSuccess(gameListBean);


            }
        });    }


}
