package com.turtlediary.www.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Logg;

public class WebViewActivity extends HomeBaseActivity {
    private WebView webView;
    private TextView textViewHeader;
    private String urlToLoad;
    private RelativeLayout rlProgressContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_web_view);
        urlToLoad=getIntent().getStringExtra(AppConstants.URL_TO_LOAD);
        Logg.p("", "urlToLoad to load::" + urlToLoad);

        initWidgets();
        setData();
    }
    private void initWidgets() {
        super.rlBottomBar.setVisibility(View.GONE);
        super.rlTopBar.setVisibility(View.VISIBLE);
        super.rlTopBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        super.ivHomeAtTop.setVisibility(View.GONE);
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);

        textViewHeader = super.tvTopBarHeader;
        textViewHeader.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        webView = (WebView) findViewById(R.id.webview);
    }
    private void setData() {
        rlProgressContainer.setVisibility(View.VISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(urlToLoad);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(AppConstants.USER_NAME, AppConstants.PASSWORD);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Logg.p("", "error::" + error.toString());
                rlProgressContainer.setVisibility(View.GONE);

            }

            @SuppressLint("NewApi")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Logg.p("", "request.getRequestHeaders()::" + request.getRequestHeaders());
                return null;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                rlProgressContainer.setVisibility(View.GONE);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }



}
