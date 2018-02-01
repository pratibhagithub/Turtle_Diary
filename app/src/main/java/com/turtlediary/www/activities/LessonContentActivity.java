package com.turtlediary.www.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.LessonResponseBean;
import com.turtlediary.www.httpConnection.ApiBuilder;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.LessonContentRequest;
import com.turtlediary.www.httpConnection.requestModel.LessonContentRequestModel;
import com.turtlediary.www.interfaces.OnLessonContentCompleteListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

public class LessonContentActivity extends HomeBaseActivity {
private WebView webView;
    private TextView textViewHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_lesson_content);

        initWidgets();
        callLessonContent(getIntent().getStringExtra(AppConstants.KEY_SLUG));
    }
    private void initWidgets()
    {
        super.rlBottomBar.setVisibility(View.GONE);
        super.rlTopBar.setVisibility(View.VISIBLE);
        super.rlTopBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.parent_green));
        super.ivHomeAtTop.setVisibility(View.GONE);
        textViewHeader=   super.tvTopBarHeader;
        webView=(WebView) findViewById(R.id.wb_content);
    }

    private void callLessonContent(String slug) {

        Gson gson = new Gson();
        LessonContentRequestModel lessonContentRequestModel = new LessonContentRequestModel();
        lessonContentRequestModel.setLevel(ApiKeys.LEVEL);
        lessonContentRequestModel.setEnvir(ApiKeys.ENVIR);
        lessonContentRequestModel.setApiVersion(ApiKeys.API_VERSION);
        lessonContentRequestModel.setAppVersion(Util.getApplicationVersion(this));
        lessonContentRequestModel.setSlug(slug);
        if (getResources().getBoolean(R.bool.isTablet)) {
            lessonContentRequestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            lessonContentRequestModel.setUserDevice(getString(R.string.phone));
        }
        lessonContentRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        lessonContentRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");


        String data = gson.toJson(lessonContentRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_LESSON_CONTENT);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("lessonContentModel", "--" + apiParamMap);
        Log.e("Slug",slug+"");
        LessonContentRequest.getLessonCotent(LessonContentActivity.this,apiParamMap, new OnLessonContentCompleteListner() {
            @Override
            public void onSucces(LessonResponseBean lessonResponseBean ) {
                textViewHeader.setText(lessonResponseBean.getContent().getLessonTitle().toString());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                String cssLink1=lessonResponseBean.getCssLink1();
                String cssLink2=lessonResponseBean.getCssLink2();
                String dataToLoad=lessonResponseBean.getContent().getLessonPlan();
                String baseUrlForCSS= ApiBuilder.getBAseUrl(LessonContentActivity.this);
               webView.loadDataWithBaseURL(baseUrlForCSS,cssLink1+dataToLoad, "text/html", "UTF-8", null);
              //webView.loadData(dataToLoad, "text/html", "UTF-8");
            }

            @Override
            public void onFailed(String msg) {
                Toast.makeText(LessonContentActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
