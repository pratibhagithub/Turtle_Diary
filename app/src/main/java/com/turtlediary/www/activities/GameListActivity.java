package com.turtlediary.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.turtlediary.www.R;
import com.turtlediary.www.adapter.GameListAdapter;
import com.turtlediary.www.beans.ContentTypeCategoryBean;
import com.turtlediary.www.beans.GameListBean;
import com.turtlediary.www.beans.GameListModel;
import com.turtlediary.www.custonViews.CustomDialog;
import com.turtlediary.www.custonViews.SpacesItemDecoration;
import com.turtlediary.www.httpConnection.httpRequest.GameListRequest;
import com.turtlediary.www.httpConnection.requestParam.GameRequestParam;
import com.turtlediary.www.interfaces.GameItemClickListner;
import com.turtlediary.www.interfaces.OnGameListCompleteListener;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameListActivity extends HomeBaseActivity {

    private RecyclerView gamelistRecyclerview;
    private GameListAdapter listAdapter;
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private RelativeLayout rlProgressContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_gamelist);
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        initView();
        if (mContentTypeCategoryBean != null) {
            callGameListApi(mContentTypeCategoryBean.getContentType(), mContentTypeCategoryBean.getContentType());
        }
    }

    private void initView() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);

        int numberOfColumns = 3;
        if (getResources().getBoolean(R.bool.isTablet)) {
            numberOfColumns = 3;
        } else {
            numberOfColumns = 2;
        }
        gamelistRecyclerview = (RecyclerView) findViewById(R.id.gamelist_recyclerview);
        gamelistRecyclerview.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(this, R.dimen.dp_20);
        gamelistRecyclerview.addItemDecoration(itemDecoration);
        super.rlTopBar.setVisibility(View.VISIBLE);
        super.tvTopBarHeader.setText(mContentTypeCategoryBean.getContentName());


    }

    private void callGameListApi(String slug, String contentType) {

        showProgressBar(true);

        Map<String, String> apiParamMap = new HashMap<>();
        String groupSlug = ""; //Here we dont need groupSlug
        apiParamMap = GameRequestParam.getParams(GameListActivity.this, groupSlug, slug, contentType);
        GameListRequest.gameList(GameListActivity.this, new OnGameListCompleteListener() {
            @Override
            public void requestSuccess(GameListBean gameListBean) {
                showProgressBar(false);
                List<GameListModel> gameListModels = gameListBean.getGame();
                final List<GameListModel> gameListModelsSorted = new ArrayList<GameListModel>();
                for (int i = 0; i < gameListModels.size(); i++) {
                    if (gameListModels.get(i).getIsView().equalsIgnoreCase(AppConstants.WEB) || gameListModels.get(i).getIsGroup()) {
                        gameListModelsSorted.add(gameListModels.get(i));
                    }
                }
                listAdapter = new GameListAdapter(GameListActivity.this, gameListModelsSorted, new GameItemClickListner() {
                    @Override
                    public void openGame(GameListModel gameListModel) {

                        Util.setGoogleAnayticsData(GameListActivity.this, mContentTypeCategoryBean.getContentName(), gameListModel.getGameName());
                        Util.setFirebaseAnalyticsLog(GameListActivity.this, mContentTypeCategoryBean.getContentName(), gameListModel.getGameName());
                        if (Preferences.getEffectSoundEnability(getApplicationContext()))
                            SoundEffect.getInstance().playSound(GameListActivity.this, false); // true bcz , it is  a home/back button is not clicked
                        if (gameListModel.getIsGroup()) {
                            //this check is implemented bcz if isGroup is true , then we need to hit api for more items of particular games
                            //Toast.makeText(GameListActivity.this, "IS Group is + " + gameListModel.getIsGroup(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(GameListActivity.this, MoreGamesListActivity.class);
                            intent.putExtra(AppConstants.CONTENT_BEAN, mContentTypeCategoryBean);
                            intent.putExtra(AppConstants.KEY_SLUG, mContentTypeCategoryBean.getContentType());
                            intent.putExtra(AppConstants.KEY_GROUP_SLUG, gameListModel.getGroupSlug());
                            startActivity(intent);
                        } else {
                            if (!Util.isPremeiumMember(GameListActivity.this)) {//if user is not logged in , we wil show only those items that are unlocked
                           /*     if (gameListModel.getIsBlock()) {
                                    CustomDialog.loginViewDialog(GameListActivity.this);
                                } else {//if user is not logged in , we wil show only all the items
                                    if (gameListModel.getViewUrl() != null && !TextUtils.isEmpty(gameListModel.getViewUrl())) {
                                        Intent intent = new Intent(GameListActivity.this, ContentWebviewActivity.class);
                                        intent.putExtra(AppConstants.URL_TO_LOAD, gameListModel.getViewUrl());
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();
                                    }
                                }*/
                                CustomDialog.loginViewDialog(GameListActivity.this);

                            } else {
                                if (gameListModel.getViewUrl() != null && !TextUtils.isEmpty(gameListModel.getViewUrl())) {
                                    Intent intent = new Intent(GameListActivity.this, ContentWebviewActivity.class);
                                    intent.putExtra(AppConstants.URL_TO_LOAD, gameListModel.getViewUrl());
                                    startActivity(intent);
                                } else {
                                  //  Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();

                                }
                            }

                        }

                    }
                });
                gamelistRecyclerview.setAdapter(listAdapter);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(GameListActivity.this,error);
                Log.e("request failed ", "######");
            }
        }, apiParamMap);

    }

    private void showProgressBar(boolean isShow) {
        // if we waant show progress, then bottom bar will be showProgressBarPayment i.e
        // if isShow=true ; then isDisable=true
        disableBottomBarView(isShow);
        if (isShow) {
            gamelistRecyclerview.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            gamelistRecyclerview.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }

    }

}
