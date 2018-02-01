package com.turtlediary.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.beans.QuizBean;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.QuizRequest;
import com.turtlediary.www.httpConnection.requestModel.QuizRequestModel;
import com.turtlediary.www.interfaces.OnQuizRequestCompleteListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizActivity extends HomeBaseActivity implements View.OnClickListener {

    private ImageView imgSubMath;
    private ImageView imgSubEla;
    private ImageView imgSubScience;
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private int mCAlledFor;
    private RelativeLayout rlProgressContainer;


    private QuizBean quizBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_quiz);


        initView();
        setViewListeners();
        mCAlledFor = getIntent().getIntExtra(AppConstants.CALLED_FOR, 0);
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        if (mContentTypeCategoryBean != null) {

            callQuizApi();
        }
    }


    private void initView() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);
        imgSubMath = (ImageView) findViewById(R.id.img_sub_math);
        imgSubEla = (ImageView) findViewById(R.id.img_sub_ela);
        imgSubScience = (ImageView) findViewById(R.id.img_sub_science);

    }

    private void setViewListeners() {
        imgSubEla.setOnClickListener(this);
        imgSubMath.setOnClickListener(this);
        imgSubScience.setOnClickListener(this);
        Util.setEffect(imgSubEla);
        Util.setEffect(imgSubMath);
        Util.setEffect(imgSubScience);
    }


    private void callQuizApi() {
        showProgressBar(true);
        Gson gson = new Gson();
        QuizRequestModel quizRequestModel = new QuizRequestModel();

        quizRequestModel.setSlug(mContentTypeCategoryBean.getContentType());
        quizRequestModel.setAppVersion(Util.getApplicationVersion(this));

        quizRequestModel.setEnvir(ApiKeys.ENVIR);
        quizRequestModel.setContentType(mContentTypeCategoryBean.getContentType());
        quizRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        if (getResources().getBoolean(R.bool.isTablet)) {
            quizRequestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            quizRequestModel.setUserDevice(getString(R.string.phone));
        }
        quizRequestModel.setApiVersion(ApiKeys.API_VERSION);

        String data = gson.toJson(quizRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_QUIZ);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("quizRequestModel", apiParamMap + "");
        Log.e("setContentType", mContentTypeCategoryBean.getContentType() + "");
        QuizRequest.quizRequest(QuizActivity.this, new OnQuizRequestCompleteListener() {

            @Override
            public void requestSuccess(QuizBean bean) {
                quizBean = bean;
                // load data to layout's view
                loadImages(quizBean);
                showProgressBar(false);


            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(QuizActivity.this, error);
            }
        }, apiParamMap);

    }

    private void loadImages(QuizBean quizBean) {

        Glide.with(this).load(quizBean.getSubjectList().getMath().getSubjectImage()).into(imgSubMath);
        Glide.with(this).load(quizBean.getSubjectList().getEla().getSubjectImage()).into(imgSubEla);
        Glide.with(this).load(quizBean.getSubjectList().getScience().getSubjectImage()).into(imgSubScience);
        super.tvTopBarHeader.setText(quizBean.getPageHeading());
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.img_sub_math:
                callSubjectContentActivity(quizBean.getSubjectList().getMath().getSubjectSlug(), quizBean.getSubjectList().getMath().getSubjectName());
                break;
            case R.id.img_sub_ela:
                callSubjectContentActivity(quizBean.getSubjectList().getEla().getSubjectSlug(), quizBean.getSubjectList().getEla().getSubjectName());
                break;
            case R.id.img_sub_science:
                callSubjectContentActivity(quizBean.getSubjectList().getScience().getSubjectSlug(), quizBean.getSubjectList().getScience().getSubjectName());
                break;
        }
    }

    private void callSubjectContentActivity(String subjectSlug, String subjectName) {
        if (Preferences.getEffectSoundEnability(getApplicationContext()))
            SoundEffect.getInstance().playSound(QuizActivity.this, false); // true bcz , it is  not  a home/back button is not clicked
        if (Util.isNetworkAvailable(QuizActivity.this)) {
            Intent intent = new Intent(QuizActivity.this, SubjectContentListActivity.class);
            intent.putExtra(AppConstants.CONTENT_BEAN, mContentTypeCategoryBean);
            intent.putExtra(AppConstants.KEY_SUBJECT_SLUG, subjectSlug);
            intent.putExtra(AppConstants.KEY_SUBJECT_NAME, subjectName);
            intent.putExtra(AppConstants.CALLED_FOR, mCAlledFor);
            startActivity(intent);
        }
    }

    private void showProgressBar(boolean isShow) {
        // if we waant show progress, then bottom bar will be showProgressBarPayment i.e
        // if isShow=true ; then isDisable=true
        disableBottomBarView(isShow);
        if (isShow) {
            imgSubMath.setEnabled(false);
            imgSubEla.setEnabled(false);
            imgSubScience.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            imgSubMath.setEnabled(true);
            imgSubEla.setEnabled(true);
            imgSubScience.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }

    }
}
