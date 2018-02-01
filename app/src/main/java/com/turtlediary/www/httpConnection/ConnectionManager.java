package com.turtlediary.www.httpConnection;

import android.util.Log;

import com.turtlediary.www.interfaces.OnWebServiceCompleteListener;
import com.turtlediary.www.utilities.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pratibha on 26/10/17.
 */
public class ConnectionManager {
    private Call<ResponseBody> enqueueCall;
    private static ConnectionManager ourInstance = new ConnectionManager();
    private static final String TAG = ConnectionManager.class.getSimpleName();
    public static ConnectionManager getInstance() {
        return ourInstance;
    }
    private ConnectionManager() {
    }
    public static void callApi(final Call<ResponseBody> mCall, final OnWebServiceCompleteListener mListener){
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "Inside Response ConnectionMAanager");
                if(response.isSuccessful()){
                    Log.d(TAG,response+"");

                    try {
                        String resp=response.body().string();
                        Log.d(TAG,resp);

                        JSONObject jsonObject = new JSONObject(resp);
                        mListener.onWebServiceComplete(jsonObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                        mListener.onWebStatusFalse(AppConstants.NETWORK_EXCEPTION);
                    } catch (IOException e) {
                        e.printStackTrace();
                        mListener.onWebStatusFalse(AppConstants.NETWORK_EXCEPTION);
                    }
                }else{
                    if(response.message().equals("Unauthorized") || response.message().equals("Authorization header not valid")){
                        mListener.onWebStatusFalse(AppConstants.NETWORK_ERROR);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(t instanceof IOException){
                    mListener.onWebStatusFalse(AppConstants.NETWORK_NOT_AVAILABLE);
                    return;
                }
                mListener.onWebStatusFalse(AppConstants.NETWORK_NOT_AVAILABLE);
            }
        });
    }
}
