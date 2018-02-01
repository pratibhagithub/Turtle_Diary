package com.turtlediary.www.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.AssesmentBean;
import com.turtlediary.www.beans.AssessmentContentBean;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.custonViews.CustomDialog;
import com.turtlediary.www.httpConnection.httpRequest.AssessmentRequest;
import com.turtlediary.www.httpConnection.requestParam.PrintableRequestParam;
import com.turtlediary.www.interfaces.AssessmentComplete;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import java.util.List;
import java.util.Map;

public class AssessmentsActivity extends HomeBaseActivity {
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private int mCAlledFor;
    private ImageView imgassementSheets;
    private RelativeLayout rlProgressContainer;
    private AssesmentBean mAssesmentBeaan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_assessments);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        mCAlledFor = getIntent().getIntExtra(AppConstants.CALLED_FOR, 0);
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        if (mContentTypeCategoryBean != null) {
            callAssesmentApi();

        }
    }

    private void initView() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.more_progressBarSignInContainer);
        imgassementSheets = (ImageView) findViewById(R.id.iv_assesment_sheets);
        imgassementSheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AssessmentContentBean> assessmentContentBeanList = mAssesmentBeaan.getContentList();
                callWebview(AssessmentsActivity.this, assessmentContentBeanList.get(0).getContentUrl());
            }
        });

    }

    private void loadImages(List<AssessmentContentBean> assessmentContentBeanList) {
        Glide.with(this).load(assessmentContentBeanList.get(0).getContentImage());

        super.tvTopBarHeader.setText(mAssesmentBeaan.getPageHeading());
    }


    public void callWebview(Activity activity, String url) {
        if (Util.isPremeiumMember(activity)) {
            if (url!=null && !TextUtils.isEmpty(url)) {      //to call loginDialog
                Intent intent = new Intent(activity, ContentWebviewActivity.class);
                intent.putExtra(AppConstants.URL_TO_LOAD, url);
                intent.putExtra(AppConstants.IS_PRINTABLE, false);
                activity.startActivity(intent);
            }
        } else {
            CustomDialog.loginViewDialog(AssessmentsActivity.this);

        }

    }

    private void showProgressBar(boolean isShow) {
        disableBottomBarView(isShow);
        if (isShow) {
            imgassementSheets.setEnabled(false);
            ;

            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            rlProgressContainer.setVisibility(View.GONE);
            imgassementSheets.setEnabled(true);
            ;

        }

    }

    private void callAssesmentApi() {
        showProgressBar(true);
        Map<String, String> requestParam = PrintableRequestParam.getParams(this, mContentTypeCategoryBean.getContentType(), mContentTypeCategoryBean.getContentType());
        AssessmentRequest.assessmentRequest(AssessmentsActivity.this, new AssessmentComplete() {
            @Override
            public void requestSuccess(AssesmentBean assesmentBean) {
                showProgressBar(false);
                mAssesmentBeaan = assesmentBean;
                loadImages(mAssesmentBeaan.getContentList());
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(AssessmentsActivity.this,error);
                finish();
            }
        }, requestParam);

    }


}
