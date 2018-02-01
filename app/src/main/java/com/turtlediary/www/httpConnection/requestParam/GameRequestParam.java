package com.turtlediary.www.httpConnection.requestParam;

import android.app.Activity;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.requestModel.GameListRequestModel;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pratibha on 19/12/17.
 */

public class GameRequestParam {

    public static Map<String, String> getParams(Activity activity, String groupSlug,String slug,String contentType)
    {
        Gson gson = new Gson();
        GameListRequestModel gameListRequestModel = new GameListRequestModel();
        gameListRequestModel.setSlug(slug);
        gameListRequestModel.setAppVersion(Util.getApplicationVersion(activity));
        gameListRequestModel.setGroup(false);
        gameListRequestModel.setEnvir(ApiKeys.ENVIR);
        gameListRequestModel.setContentType(contentType);
        gameListRequestModel.setForceUpdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        gameListRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (activity.getResources().getBoolean(R.bool.isTablet)) {
            gameListRequestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            gameListRequestModel.setUserDevice(activity.getString(R.string.phone));
        }
        gameListRequestModel.setApiVersion(ApiKeys.API_VERSION);
        gameListRequestModel.setGroupSlug(groupSlug);
        String data = gson.toJson(gameListRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sApiGameList);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        return apiParamMap;
    }
}
