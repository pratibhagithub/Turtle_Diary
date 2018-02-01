package com.turtlediary.www.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.beans.PrintableCategoryBean;
import com.turtlediary.www.custonViews.CustomDialog;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.PrintableRequest;
import com.turtlediary.www.httpConnection.requestParam.PrintableRequestParam;
import com.turtlediary.www.interfaces.OnPrintableRequestCompleteListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import java.util.Map;

public class PrintableCategoriesActivity extends HomeBaseActivity {
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private int mCAlledFor;
    private ImageView imgColorSheets, imgCraft, imgWorkSheet, imgWorkSheetGenerator;
    private PrintableCategoryBean printableCategoryBean;
    private RelativeLayout rlProgressContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_printable_sub_categories);
        initView();
        setListners();
        getIntentData();
    }

    private void getIntentData() {
        mCAlledFor = getIntent().getIntExtra(AppConstants.CALLED_FOR, 0);
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        if (mContentTypeCategoryBean != null) {
            callPrintableApi();
        }
    }

    private void initView() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.more_progressBarSignInContainer);
        imgColorSheets = (ImageView) findViewById(R.id.iv_color_sheets);
        imgCraft = (ImageView) findViewById(R.id.iv_art_craft);
        imgWorkSheet = (ImageView) findViewById(R.id.iv_worksheet);
        imgWorkSheetGenerator = (ImageView) findViewById(R.id.iv_worksheet_generator);

    }

    private void callPrintableApi() {
        showProgressBar(true);
        Map<String, String> requestParam = PrintableRequestParam.getParams(this, mContentTypeCategoryBean.getContentType(), mContentTypeCategoryBean.getContentType());
        PrintableRequest.printableRequest(PrintableCategoriesActivity.this, new OnPrintableRequestCompleteListner() {
            @Override
            public void requestSuccess(PrintableCategoryBean bean) {
                showProgressBar(false);
                printableCategoryBean = bean;
                loadImages(printableCategoryBean);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(PrintableCategoriesActivity.this,error);
                Log.e("request failed ", "######");
            }
        }, requestParam);

    }

    private void loadImages(PrintableCategoryBean printableCategoryBean) {
        Glide.with(this).load(printableCategoryBean.getContentList().getColoring_sheets().getContentImage()).into(imgColorSheets);
        Glide.with(this).load(printableCategoryBean.getContentList().getKids_arts_and_crafts().getContentImage()).into(imgCraft);
        Glide.with(this).load(printableCategoryBean.getContentList().getWorksheets().getContentImage()).into(imgWorkSheet);
        Glide.with(this).load(printableCategoryBean.getContentList().getWorksheet_generator().getContentImage()).into(imgWorkSheetGenerator);
        super.tvTopBarHeader.setText(printableCategoryBean.getPageHeading());
    }


    public  void callWebview(Activity activity, String url) {
        if (!Util.isPremeiumMember(PrintableCategoriesActivity.this))
        {
            CustomDialog.loginViewDialog(PrintableCategoriesActivity.this);
        }else {
            if(url!=null && !TextUtils.isEmpty(url)) {
                Intent intent = new Intent(activity, ContentWebviewActivity.class);
                intent.putExtra(AppConstants.URL_TO_LOAD, url);
                intent.putExtra(AppConstants.IS_PRINTABLE, true);
                activity.startActivity(intent);
            }else
            {
              //  Toast.makeText(getApplicationContext(),"URL is null",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setListners()
    {
        imgColorSheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(printableCategoryBean.getContentList().getColoring_sheets().getNextPageType().equalsIgnoreCase(ApiKeys.WEB))
                callWebview(PrintableCategoriesActivity.this,printableCategoryBean.getContentList().getColoring_sheets().getContentUrl());
            }
        });
        imgCraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(printableCategoryBean.getContentList().getKids_arts_and_crafts().getNextPageType().equalsIgnoreCase(ApiKeys.WEB))
                callWebview(PrintableCategoriesActivity.this,printableCategoryBean.getContentList().getKids_arts_and_crafts().getContentUrl());
            }
        });
        imgWorkSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(printableCategoryBean.getContentList().getWorksheets().getNextPageType().equalsIgnoreCase(ApiKeys.WEB))
                callWebview(PrintableCategoriesActivity.this,printableCategoryBean.getContentList().getWorksheets().getContentUrl());
            }
        });
        imgWorkSheetGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (printableCategoryBean.getContentList().getWorksheet_generator().getNextPageType().equalsIgnoreCase(ApiKeys.WEB))
                    callWebview(PrintableCategoriesActivity.this, printableCategoryBean.getContentList().getWorksheet_generator().getContentUrl());
            }
        });

    }
    private void showProgressBar(boolean isShow) {
        // if we waant show progress, then bottom bar will be showProgressBarPayment i.e
        // if isShow=true ; then isDisable=true
        disableBottomBarView(isShow);
        if (isShow) {
            imgColorSheets.setEnabled(false);;
            imgCraft.setEnabled(false);
            imgWorkSheet.setEnabled(false);
            imgWorkSheetGenerator.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            rlProgressContainer.setVisibility(View.GONE);
            imgColorSheets.setEnabled(true);;
            imgCraft.setEnabled(true);
            imgWorkSheet.setEnabled(true);
            imgWorkSheetGenerator.setEnabled(true);
        }

    }

}
