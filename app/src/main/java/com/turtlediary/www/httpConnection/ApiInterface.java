package com.turtlediary.www.httpConnection;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by pratibha on 26/10/17.
 */

public interface ApiInterface {

    @GET("index.php")
    Call<ResponseBody> callContentType(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callLogin(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callPhoneDetail(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callLogout(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callGameList(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callQuizRequest(@QueryMap(encoded = true) Map<String, String> params);@GET("index.php")
    Call<ResponseBody> callPrintableRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callSubjectContentListRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callSubTopicRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callLessonContentRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callVideoContentRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callRegistrationRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callProductIdRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callReceiptRequest(@QueryMap(encoded = true) Map<String, String> params);

    @GET("index.php")
    Call<ResponseBody> callRestoreRequest(@QueryMap(encoded = true) Map<String, String> params);
}
