package com.turtlediary.www.httpConnection;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pratibha on 26/10/17.
 */

public class ApiBuilder {
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private static Activity activity;

    public static ApiInterface createretrofit(Activity act) {
        String baseUrl = getBAseUrl(act);
        activity = act;
        setLogInterceptor();
   /*     if (retrofit != null) {

            return apiInterface = retrofit.create(ApiInterface.class);
        }*/

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)

                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .client(okHttpClient.build())
                .build();
        return retrofit.create(ApiInterface.class);

    }

    /**
     * set log interceptor for logging the network response
     */
    private static void setLogInterceptor() {

        //if (interceptor == null) {
        interceptor = new HttpLoggingInterceptor();
        //}
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(interceptor);
        okHttpClient.addNetworkInterceptor(new AddHeaderInterceptor());


      /*  if (ApiKeys.SERVER_TYPE == ApiKeys.DEV_SERVER) {
            okHttpClient.addNetworkInterceptor(new AddHeaderInterceptor());
        } else {
            if (!TextUtils.isEmpty(Preferences.getUserToken(activity))) {
                okHttpClient.addNetworkInterceptor(new AddHeaderInterceptor());
            } else {
                okHttpClient.addNetworkInterceptor(new AddHeaderInterceptor());
            }

        }*/

    }


    private static class AddHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            String token = "";
            String basic = "";
            Request request = null;

            if (ApiKeys.SERVER_TYPE == ApiKeys.DEV_SERVER) {
                if (!TextUtils.isEmpty(Preferences.getUserToken(activity))) {
                    token = AppConstants.USER_NAME + ":" + AppConstants.PASSWORD;
                    basic = "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
                    Request original = null;
                    original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Authorization-app", "td_app")
                            .header("Authorization-token", "bearer " + Preferences.getUserToken(activity));
                    request = requestBuilder.build();
                }else
                {
                    Log.e("Check", "-------------------------------------------------------------------user tokem  empty");
                    token = AppConstants.USER_NAME + ":" + AppConstants.PASSWORD;
                    basic = "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
                    Request original = null;
                    original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Authorization-app", "td_app");
                    request = requestBuilder.build();

                }

            } else {
                if (!TextUtils.isEmpty(Preferences.getUserToken(activity))) {
                    Log.e("Check", "-------------------------------------------------------------------user tokem not  empty");
                    basic = "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
                    Request original = null;
                    original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Authorization-app", "td_app")
                            .header("Authorization-token", "bearer " + Preferences.getUserToken(activity));
                    request = requestBuilder.build();
                } else {
                    Log.e("Check", "-------------------------------------------------------------------user tokem  empty");
                    token = AppConstants.USER_NAME + ":" + AppConstants.PASSWORD;
                    basic = "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
                    Request original = null;
                    original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                            .header("Authorization-app", "td_app");
                    request = requestBuilder.build();

                }

            }

            return chain.proceed(request);

        }
    }

    public static String getBAseUrl(Activity act) {
        String baseUrl = "";
        if (ApiKeys.SERVER_TYPE == ApiKeys.DEV_SERVER)
            baseUrl = ApiKeys.BASE_URL_DEV;
        else if (ApiKeys.SERVER_TYPE == ApiKeys.LIVE_SERVER)
            baseUrl = ApiKeys.BASE_URL_LIVE;
        else if (ApiKeys.SERVER_TYPE == ApiKeys.CHINESE_SERVER)
            baseUrl = ApiKeys.BASE_URL_CHINESE;

        return baseUrl;
    }

}
