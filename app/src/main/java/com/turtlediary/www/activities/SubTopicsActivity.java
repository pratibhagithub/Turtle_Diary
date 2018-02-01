package com.turtlediary.www.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.beans.SubTopicBean;
import com.turtlediary.www.beans.SubTopicListBean;
import com.turtlediary.www.beans.SubjectContentListItemBean;
import com.turtlediary.www.fragments.SubTopicDetailFragment;
import com.turtlediary.www.fragments.SubTopicNameFragment;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.SubTopicList;
import com.turtlediary.www.httpConnection.requestModel.SubTopicRquestModel;
import com.turtlediary.www.interfaces.OnSubTopicCompleteListener;
import com.turtlediary.www.interfaces.OnSubTopicEventListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.Map;

public class SubTopicsActivity extends HomeBaseActivity implements OnSubTopicEventListener {

    FrameLayout frameLayoutName, frameLayoutDetail;
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private SubjectContentListItemBean msubjectContentListItemBean;
    private int mCAlledFor;
    private RelativeLayout rlProgressContainer;
    private String headerame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_lesson_topics);
        mCAlledFor = getIntent().getIntExtra(AppConstants.CALLED_FOR,0);
        Log.e("LOGe", mCAlledFor+"");

        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        msubjectContentListItemBean = getIntent().getParcelableExtra(AppConstants.SUBJECTCONTENTLISTITEMBEAN);
        initWidgets();
        super.rlTopBar.setVisibility(View.VISIBLE);
        headerame=msubjectContentListItemBean.getTopicName();
        super.tvTopBarHeader.setText(msubjectContentListItemBean.getTopicName());
        callSubTopicListApi();
    }


    private void initWidgets() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);
        frameLayoutName = (FrameLayout) findViewById(R.id.fragment_left);
        frameLayoutDetail = (FrameLayout) findViewById(R.id.fragment_right);
    }

    private void setNameFragment(SubTopicBean topicbean) {
        SubTopicNameFragment topicNameFragment = new SubTopicNameFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.SUBTOPIC_BEAN, topicbean);
        Log.e("SubTopicListBean Size", "" + topicbean.getSubtopic().size());

        topicNameFragment.setArguments(bundle);
        Util.fragmentTransec(topicNameFragment, SubTopicsActivity.this, R.id.fragment_left);
    }

    private void setDetailFragment(SubTopicBean topicbean) {
        SubTopicDetailFragment topicDetailFragment = new SubTopicDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.SUBTOPIC_BEAN, topicbean);
        bundle.putInt(AppConstants.CALLED_FOR, mCAlledFor);
        bundle.putString(AppConstants.CONTENT_TYPE, mContentTypeCategoryBean.getContentName());
        bundle.putString(AppConstants.HEADER, headerame);
        topicDetailFragment.setArguments(bundle);
        Util.fragmentTransec(topicDetailFragment, SubTopicsActivity.this, R.id.fragment_right);
    }

    @Override
    public void onSubTopicNameClicked(SubTopicListBean subTopicListBean) {
        super.tvTopBarHeader.setText(subTopicListBean.getTopicName());
        headerame=subTopicListBean.getTopicName();

        callRightData(subTopicListBean.getTopicSlug());
    }

    private void callSubTopicListApi() {
        showProgressBar(true);

        Gson gson = new Gson();
        SubTopicRquestModel subTopicRquestModel = new SubTopicRquestModel();
        subTopicRquestModel.setSlug(msubjectContentListItemBean.getTopicSlug());
        subTopicRquestModel.setAppVersion(Util.getApplicationVersion(this));
        subTopicRquestModel.setIsGroup("false");
        subTopicRquestModel.setEnvir(ApiKeys.ENVIR);
        subTopicRquestModel.setContentType(mContentTypeCategoryBean.getContentType());
        subTopicRquestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        subTopicRquestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (getResources().getBoolean(R.bool.isTablet)) {
            subTopicRquestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            subTopicRquestModel.setUserDevice(getString(R.string.phone));
        }
        subTopicRquestModel.setApiVersion(ApiKeys.API_VERSION);
        subTopicRquestModel.setGroupSlug("");
        String data = gson.toJson(subTopicRquestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_SUBTOPICLIST);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.e("subTopicRquestModel", "--" + apiParamMap);
        Log.e("Content_type",mContentTypeCategoryBean.getContentType()+"");
        Log.e("Slug",msubjectContentListItemBean.getTopicSlug()+"");

        SubTopicList.getTopicList(SubTopicsActivity.this,new OnSubTopicCompleteListener() {
            @Override
            public void requestSuccess(SubTopicBean topicbean) {
                showProgressBar(false);
                Log.d("REQUEST SUCCESS", "DONE");
                setNameFragment(topicbean);
                setDetailFragment(topicbean);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(SubTopicsActivity.this,error);
                Log.d("REQUEST FAILED", "--" + error);

            }
        }, apiParamMap);

    }

    private void callRightData(String topicSlug) {
        showProgressBar(true);

        Gson gson = new Gson();
        SubTopicRquestModel subTopicRquestModel = new SubTopicRquestModel();
        subTopicRquestModel.setSlug(topicSlug);
        subTopicRquestModel.setAppVersion(Util.getApplicationVersion(this));
        subTopicRquestModel.setIsGroup("false");
        subTopicRquestModel.setEnvir(ApiKeys.ENVIR);
        subTopicRquestModel.setContentType(mContentTypeCategoryBean.getContentType());
        subTopicRquestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
        subTopicRquestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
        if (getResources().getBoolean(R.bool.isTablet)) {
            subTopicRquestModel.setUserDevice(ApiKeys.sUserDevice);
        } else {
            subTopicRquestModel.setUserDevice(getString(R.string.phone));
        }
        subTopicRquestModel.setApiVersion(ApiKeys.API_VERSION);
        subTopicRquestModel.setGroupSlug("");
        String data = gson.toJson(subTopicRquestModel);
        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_SUBTOPICLIST);
        apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
        apiParamMap.put(ApiKeys.KEY_DATA, data);
        Log.d("subTopic Right", "--" + apiParamMap);
        Log.e("Content_type Right",mContentTypeCategoryBean.getContentType()+"");
        Log.e("Slug Right",topicSlug+"");
        SubTopicList.getTopicList(SubTopicsActivity.this,new OnSubTopicCompleteListener() {
            @Override
            public void requestSuccess(SubTopicBean topicbean) {
                showProgressBar(false);
                setDetailFragment(topicbean);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(SubTopicsActivity.this,error);

                Log.d("REQUEST FAILED", "--" + error);

            }
        }, apiParamMap);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void showProgressBar(boolean isShow) {

        disableBottomBarView(isShow);
        if(isShow)
        {
            rlProgressContainer.setVisibility(View.VISIBLE);
        }else
        {
            rlProgressContainer.setVisibility(View.GONE);
        }

    }
}
