package com.turtlediary.www.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.ContentTypeRequest;
import com.turtlediary.www.httpConnection.httpRequest.PhoneDetailRequest;
import com.turtlediary.www.httpConnection.requestModel.PhoneDetailRequestModel;
import com.turtlediary.www.httpConnection.requestParam.ConfigRequestParam;
import com.turtlediary.www.interfaces.OnHttpRequestCompleteListner;
import com.turtlediary.www.interfaces.OnPhoneDetailRequestCompleteListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

import static android.util.Log.d;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
     //   Preferences.saveBaseUrl(getApplicationContext(),ApiKeys.BASE_URL); ;
        /**
         *  We are saving server type so that we can add/remove Authorisation header in APi calls
         * */
        Util.getKeyHash(SplashActivity.this);
      // Preferences.saveServerType(getApplicationContext(),ApiKeys.DEV_SERVER);
        Fabric.with(this, new Crashlytics());

        setAppVersion();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getContentTypeForUser();
                callPhoneDetailAPI();
            }
        }, 1000);

    }

    private void setAppVersion() {
        TextView versionTextView = (TextView) findViewById(R.id.et_version);
        versionTextView.setText(Util.getApplicationVersion(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getContentTypeForUser() {
        ContentTypeRequest.contentTypenUser(SplashActivity.this, ConfigRequestParam.getParams(this), new OnHttpRequestCompleteListner() {
            @Override
            public void requestSuccess(BaseBean contentTypeBean) {
                MyApplication myApplication = MyApplication.getInstance();
                myApplication.setBaseBean(contentTypeBean);
                Preferences.saveUserStatus(getApplicationContext(),contentTypeBean.getUserStatus()); ;
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                intent.putExtra(AppConstants.KEY_BASE_BEAN, contentTypeBean);
                startActivity(intent);
                finish();
            }
            @Override
            public void requestFailed(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void callPhoneDetailAPI() {
        Gson gson = new Gson();
        PhoneDetailRequestModel phoneDetailRequestModel = new PhoneDetailRequestModel();
        phoneDetailRequestModel.setUuid(Util.getUniqueDeviceID(this));
        phoneDetailRequestModel.setAppName(getString(R.string.app_name));
        phoneDetailRequestModel.setOs(ApiKeys.OS_ANDROID);
        phoneDetailRequestModel.setDevicetoken("sfsdgtegfdgdsfyggg");
        phoneDetailRequestModel.setAppversion(Util.getApplicationVersion(this));

        String data = gson.toJson(phoneDetailRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_PhoneDetail);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);

        PhoneDetailRequest.callPhoneDetailApi(SplashActivity.this,apiParamMap, new OnPhoneDetailRequestCompleteListener() {
            @Override
            public void onSuccess() {
                d(TAG, "Phone Detail Api Calls Successfully");
            }
            @Override
            public void onFailure(String message) {
                //Toast.makeText(SplashActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
