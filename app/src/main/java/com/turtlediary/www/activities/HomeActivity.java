package com.turtlediary.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.AssesmentBean;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.custonViews.CustomDialog;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.AssessmentRequest;
import com.turtlediary.www.httpConnection.requestParam.PrintableRequestParam;
import com.turtlediary.www.interfaces.AssessmentComplete;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

//import android.content.ServiceConnection;

public class HomeActivity extends HomeBaseActivity implements View.OnClickListener {
    private ImageView mGameImageView, mVideoImageView, mQuizImageView, mLessonImageView, mPrintablesImageView, mAssesmentsImageView;
    private BaseBean baseBean;
    private TextView tvBestView;
    CallbackManager callbackManager = CallbackManager.Factory.create();
    private static final String TAG = HomeActivity.class.getSimpleName();
    IInAppBillingService mService;
    private boolean blnBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_home);

        baseBean = getIntent().getParcelableExtra(AppConstants.KEY_BASE_BEAN);
        Log.d(TAG, baseBean.getContentType().getGame().getContentName());
        initView();
        if (getResources().getBoolean(R.bool.isTablet)) {
            tvBestView.setVisibility(View.GONE);
        } else {
            tvBestView.setVisibility(View.VISIBLE);
            tvBestView.setText(baseBean.getConfig().getUpdateAlertMsg().getBestViewText());
        }
        if (baseBean != null) {
            loadImages();
        }
        checkForUpdateDialog();


    }

    private void checkForUpdateDialog() {
        boolean forceUpdate = baseBean.isForceupdateApp();
        boolean normalUpdate = baseBean.isUpdateApp();
        if (forceUpdate) {
            CustomDialog.appUpdateDialog(HomeActivity.this, true);
        } else {
            if(normalUpdate)
            CustomDialog.appUpdateDialog(HomeActivity.this, false);
            else {
                //We do not need to show the dialog if no apdate is available
            }

        }
    }

    private void initView() {
        mGameImageView = (ImageView) findViewById(R.id.iv_game);
        mVideoImageView = (ImageView) findViewById(R.id.iv_video);
        mPrintablesImageView = (ImageView) findViewById(R.id.iv_printables);
        mAssesmentsImageView = (ImageView) findViewById(R.id.iv_assesments);
        mLessonImageView = (ImageView) findViewById(R.id.iv_lesson);
        mQuizImageView = (ImageView) findViewById(R.id.iv_quizzes);
        tvBestView = (TextView) findViewById(R.id.tv_best_view_txt);
        mGameImageView.setOnClickListener(this);
        mVideoImageView.setOnClickListener(this);
        mQuizImageView.setOnClickListener(this);
        mLessonImageView.setOnClickListener(this);
        mAssesmentsImageView.setOnClickListener(this);
        mPrintablesImageView.setOnClickListener(this);
        super.rlTopBar.setVisibility(View.GONE);
    }

    private void loadImages() {
        Glide.with(HomeActivity.this).load(baseBean.getContentType().getGame().getContentImage()).into(mGameImageView);
        Glide.with(this).load(baseBean.getContentType().getVideo().getContentImage()).into(mVideoImageView);
        Glide.with(this).load(baseBean.getContentType().getLesson().getContentImage()).into(mLessonImageView);
        Glide.with(this).load(baseBean.getContentType().getQuiz().getContentImage()).into(mQuizImageView);
        if (ApiKeys.SERVER_TYPE == ApiKeys.DEV_SERVER) {
            mAssesmentsImageView.setVisibility(View.VISIBLE);
            mPrintablesImageView.setVisibility(View.VISIBLE);
            Glide.with(this).load(baseBean.getContentType().getAssessment_test().getContentImage()).into(mAssesmentsImageView);
            Glide.with(this).load(baseBean.getContentType().getPrintable().getContentImage()).into(mPrintablesImageView);
        } else {
            mAssesmentsImageView.setVisibility(View.GONE);
            mPrintablesImageView.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mAssesmentsImageView.setEnabled(true);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mGameImageView.getId()) {
            Util.setEffect(v);
            if (Util.isNetworkAvailable(HomeActivity.this)) {
                Intent intent = new Intent(HomeActivity.this, GameListActivity.class);
                intent.putExtra(AppConstants.CONTENT_BEAN, baseBean.getContentType().getGame());
                startActivity(intent);
            }
            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked
        }
        if (v.getId() == mQuizImageView.getId()) {
            Util.setEffect(v);
            if (Util.isNetworkAvailable(HomeActivity.this)) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra(AppConstants.CONTENT_BEAN, baseBean.getContentType().getQuiz());
                intent.putExtra(AppConstants.CALLED_FOR, AppConstants.QUIZ);
                startActivity(intent);
            }
            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked

        }
        if (v.getId() == mLessonImageView.getId()) {
            Util.setEffect(v);
            if (Util.isNetworkAvailable(HomeActivity.this)) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra(AppConstants.CONTENT_BEAN, baseBean.getContentType().getLesson());
                intent.putExtra(AppConstants.CALLED_FOR, AppConstants.LESSON);
                startActivity(intent);
            }
            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked
        }
        if (v.getId() == mVideoImageView.getId()) {
            Util.setEffect(v);
            if (Util.isNetworkAvailable(HomeActivity.this)) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                intent.putExtra(AppConstants.CONTENT_BEAN, baseBean.getContentType().getVideo());
                intent.putExtra(AppConstants.CALLED_FOR, AppConstants.VIDEO);
                startActivity(intent);
            }
            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked
        }
        if (v.getId() == mPrintablesImageView.getId()) {
            Util.setEffect(v);
            if (!Util.isPremeiumMember(HomeActivity.this)) {
                CustomDialog.loginViewDialog(HomeActivity.this);
            } else {
                if (Util.isNetworkAvailable(HomeActivity.this)) {
                    if (ApiKeys.SERVER_TYPE == ApiKeys.DEV_SERVER) {
                        Intent intent = new Intent(HomeActivity.this, PrintableCategoriesActivity.class);
                        intent.putExtra(AppConstants.CONTENT_BEAN, baseBean.getContentType().getPrintable());
                        intent.putExtra(AppConstants.CALLED_FOR, AppConstants.PRINTABLES);
                        startActivity(intent);
                    } else {
                    }
                }

            }
            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked
        }
        if (v.getId() == mAssesmentsImageView.getId()) {
            Util.setEffect(v);
            if (!Util.isPremeiumMember(HomeActivity.this)) {
                CustomDialog.loginViewDialog(HomeActivity.this);
            } else {
                if(mAssesmentsImageView.isEnabled()) {
                    mAssesmentsImageView.setEnabled(false);
                    callAssesmentApi();
                }
            }

            if (Preferences.getEffectSoundEnability(getApplicationContext()))
                SoundEffect.getInstance().playSound(HomeActivity.this, false); // false bcz , it is not a home/back button is not clicked
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void callAssesmentApi() {
        Map<String, String> requestParam = PrintableRequestParam.getParams(this, baseBean.getContentType().getAssessment_test().getContentType(), baseBean.getContentType().getAssessment_test().getContentType());
        AssessmentRequest.assessmentRequest(HomeActivity.this, new AssessmentComplete() {
            @Override
            public void requestSuccess(AssesmentBean assesmentBean) {

                String url = assesmentBean.getContentList().get(0).getContentUrl();
                {
                    if (url != null && !TextUtils.isEmpty(url)) {      //to call loginDialog
                        Intent intent = new Intent(HomeActivity.this, ContentWebviewActivity.class);
                        intent.putExtra(AppConstants.URL_TO_LOAD, url);
                        intent.putExtra(AppConstants.IS_PRINTABLE, false);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void requestFailed(String error) {
                Util.showToast(HomeActivity.this, error);
                Log.e("request failed ", "######");
            }
        }, requestParam);

    }


}
