package com.turtlediary.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.adapter.SubjectContentListAdapter;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.beans.SubjectContentListBean;
import com.turtlediary.www.beans.SubjectContentListItemBean;
import com.turtlediary.www.beans.SubjectContentRequestModel;
import com.turtlediary.www.custonViews.SpacesItemDecoration;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.SubjectContentRequest;
import com.turtlediary.www.interfaces.OnLessonTopicClickListner;
import com.turtlediary.www.interfaces.OnSubjectCotentCompleteListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentListActivity extends HomeBaseActivity {

    private RecyclerView contentListRecyclerview;
    private SubjectContentListAdapter listAdapter;
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private String subjectSlug;
    private String subjectName, contentType;
    private RelativeLayout rlProgressContainer;
    private int mCAlledFor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_subject_content);
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        mCAlledFor = getIntent().getIntExtra(AppConstants.CALLED_FOR, 0);

        initView();
        callContentApi();
    }

    private void initView() {
        int numberOfColumns = 4;
        int spaceWidth;

        if (getResources().getBoolean(R.bool.isTablet)) {
            numberOfColumns = 4;
            spaceWidth = R.dimen.dp_5;
        } else {
            numberOfColumns = 3;
            spaceWidth = R.dimen.dp_5;

        }
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);
        contentListRecyclerview = (RecyclerView) findViewById(R.id.gamelist_recyclerview);
        contentListRecyclerview.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(this, spaceWidth);
        contentListRecyclerview.addItemDecoration(itemDecoration);
    }

    private void callContentApi() {
        showProgressBar(true);
        //  contentType = getIntent().getStringExtra(AppConstants.KEY_SUBJECT_CONTENT_TYPE);
        subjectSlug = getIntent().getStringExtra(AppConstants.KEY_SUBJECT_SLUG);
        subjectName = getIntent().getStringExtra(AppConstants.KEY_SUBJECT_NAME);
        super.tvTopBarHeader.setText(subjectName);
        Gson gson = new Gson();
        SubjectContentRequestModel subjectContentRequestModel = new SubjectContentRequestModel();
        subjectContentRequestModel.setSlug(subjectSlug);
        subjectContentRequestModel.setAppVersion(Util.getApplicationVersion(this));
        subjectContentRequestModel.setGroup(false);
        subjectContentRequestModel.setEnvir(ApiKeys.ENVIR);
        Log.e("setContentType", mContentTypeCategoryBean.getContentType() + "");
        subjectContentRequestModel.setContentType(mContentTypeCategoryBean.getContentType() + "");
        subjectContentRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        subjectContentRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (getResources().getBoolean(R.bool.isTablet)) {
            subjectContentRequestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            subjectContentRequestModel.setUserDevice(getString(R.string.phone));
        }
        subjectContentRequestModel.setApiVersion(ApiKeys.API_VERSION);
        subjectContentRequestModel.setGroupSlug("");
        String data = gson.toJson(subjectContentRequestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_SUBJECT_CONTENT_LIST);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("subjectContentRequest", apiParamMap + "");
        SubjectContentRequest.subjectContentList(SubjectContentListActivity.this, new OnSubjectCotentCompleteListener() {
            @Override
            public void requestSuccess(SubjectContentListBean subjectContentListBean) {
                showProgressBar(false);
                List<SubjectContentListItemBean> contentListItemBeen = subjectContentListBean.getTopicList();
                listAdapter = new SubjectContentListAdapter(SubjectContentListActivity.this, contentListItemBeen, new OnLessonTopicClickListner() {
                    @Override
                    public void onLessonTopicClicked(SubjectContentListItemBean subjectContentListItemBean) {
                        Util.setGoogleAnayticsData(SubjectContentListActivity.this, mContentTypeCategoryBean.getContentName(), subjectContentListItemBean.getTopicName());
                        Util.setFirebaseAnalyticsLog(SubjectContentListActivity.this, mContentTypeCategoryBean.getContentName(), subjectContentListItemBean.getTopicName());

                        if (Preferences.getEffectSoundEnability(getApplicationContext()))
                            SoundEffect.getInstance().playSound(SubjectContentListActivity.this, false); // true bcz , it is not  a home/back button is not clicked
                        Intent intent = new Intent(SubjectContentListActivity.this, SubTopicsActivity.class);
                        intent.putExtra(AppConstants.CALLED_FOR, mCAlledFor);
                        intent.putExtra(AppConstants.CONTENT_BEAN, mContentTypeCategoryBean);
                        intent.putExtra(AppConstants.SUBJECTCONTENTLISTITEMBEAN, subjectContentListItemBean);
                        startActivity(intent);
                    }
                });
                contentListRecyclerview.setAdapter(listAdapter);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(SubjectContentListActivity.this,error);
                Log.e("request failed ", "######");
            }
        }, apiParamMap);

    }

    private void showProgressBar(boolean isShow) {
        // if we waant show progress, then bottom bar will be showProgressBarPayment i.e
        // if isShow=true ; then isDisable=true
        disableBottomBarView(isShow);

        if (isShow) {
            contentListRecyclerview.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            contentListRecyclerview.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }

    }
}
