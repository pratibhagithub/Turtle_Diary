package com.turtlediary.www.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.VideoResponseBean;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.VideoContentRequest;
import com.turtlediary.www.httpConnection.requestModel.LessonContentRequestModel;
import com.turtlediary.www.interfaces.VideoContentCompleteListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

public class VideoContentActivity extends HomeBaseActivity {
    private VideoView videoView;
    private TextView textViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_video_content);
        initWidgets();

        callVideoContent(getIntent().getStringExtra(AppConstants.KEY_SLUG));
    }

    private void initWidgets() {
        super.rlBottomBar.setVisibility(View.GONE);
        super.rlTopBar.setVisibility(View.VISIBLE);
        super.rlTopBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        super.ivHomeAtTop.setVisibility(View.GONE);
        textViewHeader = super.tvTopBarHeader;
        textViewHeader.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
    }

    private void callVideoContent(String slug) {

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
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_VIDEO_CONTENT);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("lessonContentModel", "--" + apiParamMap);
        Log.e("Slug", slug + "");
        VideoContentRequest.getVideoCotent(VideoContentActivity.this, apiParamMap, new VideoContentCompleteListner() {
            @Override
            public void onSucces(VideoResponseBean videoResponseBean) {
                MyApplication myApplication = (MyApplication)getApplicationContext();
                myApplication.stopPlayer((VideoContentActivity.this)) ;
                MediaController mediaController = new MediaController(VideoContentActivity.this);
                textViewHeader.setText(videoResponseBean.getContentList().get(0).getVideoTitle().toString());
                videoView.setVideoPath(videoResponseBean.getContentList().get(0).getVideoUrl());
                videoView.setMediaController(mediaController);
                videoView.start();
            }

            @Override
            public void onFailed(String msg) {
                Util.showToast(VideoContentActivity.this,msg);

            }
        });

    }
    /*@Override
    public void onSucces(VideoResponseBean videoResponseBean) {

    }

    @Override
    public void onFailed() {
        Toast.makeText(VideoContentActivity.this, "FAILED ", Toast.LENGTH_SHORT).show();
    }
*/
}
