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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreGamesListActivity extends HomeBaseActivity {

    private RecyclerView gamelistRecyclerview;
    private GameListAdapter listAdapter;
    private ContentTypeCategoryBean mContentTypeCategoryBean;
    private RelativeLayout rlProgressContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_more_games_list);
        String groupSlug, slug;
        mContentTypeCategoryBean = getIntent().getParcelableExtra(AppConstants.CONTENT_BEAN);
        groupSlug = getIntent().getStringExtra(AppConstants.KEY_GROUP_SLUG);
        slug = getIntent().getStringExtra(AppConstants.KEY_SLUG);
        initView();
        if (mContentTypeCategoryBean != null) {
            callMoreGameListApi(groupSlug, slug, mContentTypeCategoryBean.getContentType());
        }
    }

    private void initView() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.more_progressBarSignInContainer);

        int numberOfColumns = 3;
        if (getResources().getBoolean(R.bool.isTablet)) {
            numberOfColumns = 3;
        } else {
            numberOfColumns = 2;
        }
        gamelistRecyclerview = (RecyclerView) findViewById(R.id.more_gamelist_recyclerview);
        gamelistRecyclerview.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(this, R.dimen.dp_20);
        gamelistRecyclerview.addItemDecoration(itemDecoration);
        super.rlTopBar.setVisibility(View.VISIBLE);
        super.tvTopBarHeader.setText(mContentTypeCategoryBean.getContentName());


    }

    private void callMoreGameListApi(String groupSlug, String slug, String contentType) {

        showProgressBar(true);


        Map<String, String> apiParamMap = new HashMap<>();
        apiParamMap = GameRequestParam.getParams(MoreGamesListActivity.this, groupSlug, slug, contentType);
        Log.e("MORE_GAMES gp slug", groupSlug);
        Log.e("MORE_GAMES slug", slug);
        Log.e("MORE_GAMES contentType", contentType);
        GameListRequest.gameList(MoreGamesListActivity.this, new OnGameListCompleteListener() {
            @Override
            public void requestSuccess(GameListBean gameListBean) {
                showProgressBar(false);
                List<GameListModel> gameListModels = gameListBean.getGame();
               /* final List<GameListModel> gameListModelsSorted = new ArrayList<GameListModel>();
                for (int i = 0; i < gameListModels.size(); i++) {
                    if (gameListModels.get(i).getIsView().equalsIgnoreCase(AppConstants.WEB) || gameListModels.get(i).getIsGroup()) {
                        gameListModelsSorted.add(gameListModels.get(i));
                    }
                }*/
                listAdapter = new GameListAdapter(MoreGamesListActivity.this, gameListModels, new GameItemClickListner() {
                    @Override
                    public void openGame(GameListModel gameListModel) {

                        Util.setGoogleAnayticsData(MoreGamesListActivity.this, mContentTypeCategoryBean.getContentName(), gameListModel.getGameName());
                        Util.setFirebaseAnalyticsLog(MoreGamesListActivity.this, mContentTypeCategoryBean.getContentName(), gameListModel.getGameName());
                        if (Preferences.getEffectSoundEnability(getApplicationContext()))
                            SoundEffect.getInstance().playSound(MoreGamesListActivity.this, false); // true bcz , it is  a home/back button is not clicked
                            if (!Util.isPremeiumMember(MoreGamesListActivity.this)) {//if user is not logged in , we wil show only those items that are unlocked
                               /* if (gameListModel.getIsBlock()) {
                                    CustomDialog.loginViewDialog(MoreGamesListActivity.this);
                                } else {//if user is not logged in , we wil show only all the items which are noy bliocked
                                    if (gameListModel.getViewUrl() != null && !TextUtils.isEmpty(gameListModel.getViewUrl())) {
                                        Intent intent = new Intent(MoreGamesListActivity.this, ContentWebviewActivity.class);
                                        intent.putExtra(AppConstants.URL_TO_LOAD, gameListModel.getViewUrl());
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();

                                    }
                                }*/
                                CustomDialog.loginViewDialog(MoreGamesListActivity.this);

                            } else {
                                if (gameListModel.getViewUrl() != null && !TextUtils.isEmpty(gameListModel.getViewUrl())) {
                                    Intent intent = new Intent(MoreGamesListActivity.this, ContentWebviewActivity.class);
                                    intent.putExtra(AppConstants.URL_TO_LOAD, gameListModel.getViewUrl());
                                    startActivity(intent);
                                } else {
                                    //Toast.makeText(getApplicationContext(), "URL is null", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                });
                gamelistRecyclerview.setAdapter(listAdapter);
            }

            @Override
            public void requestFailed(String error) {
                showProgressBar(false);
                Util.showToast(MoreGamesListActivity.this,error);
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