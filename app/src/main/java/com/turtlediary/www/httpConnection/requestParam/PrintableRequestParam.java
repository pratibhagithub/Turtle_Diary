package com.turtlediary.www.httpConnection.requestParam;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.requestModel.QuizRequestModel;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pratibha on 12/12/17.
 */

public class PrintableRequestParam
{
 public static  Map<String, String> getParams(Activity activity,String slug,String contentype)
 {
     Gson gson = new Gson();
     QuizRequestModel quizRequestModel = new QuizRequestModel();

     quizRequestModel.setSlug(slug);
     quizRequestModel.setAppVersion(Util.getApplicationVersion(activity));

     quizRequestModel.setEnvir(ApiKeys.ENVIR);
     quizRequestModel.setContentType(contentype);
     quizRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
     if (activity.getResources().getBoolean(R.bool.isTablet)) {
         quizRequestModel.setUserDevice(ApiKeys.sUserDevice);
     } else {
         quizRequestModel.setUserDevice(activity.getString(R.string.phone));
     }
     quizRequestModel.setApiVersion(ApiKeys.API_VERSION);

     String data = gson.toJson(quizRequestModel);
     Map<String, String> apiParamMap = new HashMap<>();
     apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_QUIZ);
     apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
     apiParamMap.put(ApiKeys.KEY_DATA, data);
     Log.e("quizRequestModel",apiParamMap+"");
     Log.e("setContentType",contentype+"");
     return apiParamMap;
 }
}
