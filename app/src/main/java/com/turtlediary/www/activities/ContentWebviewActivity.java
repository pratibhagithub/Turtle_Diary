package com.turtlediary.www.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turtlediary.www.R;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.PrintFinishListener;
import com.turtlediary.www.utilities.PrintingHelper;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

import static com.turtlediary.www.R.id.webview_hidden;

public class ContentWebviewActivity extends HomeBaseActivity {
    private WebView webView, webviewForPdf;
    private TextView textViewHeader;
    private TextView btnPrint, tvTimeLeft;
    private String urlToLoad;
    private boolean isPrintable;
    private ImageView imageViewBackFromWeb;
    private RelativeLayout rlProgressContainer, rlOverlayLayout;
    private String pdfFileLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.content_webview);
        urlToLoad = getIntent().getStringExtra(AppConstants.URL_TO_LOAD);
        isPrintable = getIntent().getBooleanExtra(AppConstants.IS_PRINTABLE, false);
        initWidgets();
        setDataForMainWebview();
    }

    private void initWidgets() {
        rlProgressContainer = (RelativeLayout) findViewById(R.id.progressBarSignInContainer);
        rlOverlayLayout = (RelativeLayout) findViewById(R.id.pdf_msg_overlay);
        tvTimeLeft = (TextView) findViewById(R.id.tv_sec);
        btnPrint = (TextView) findViewById(R.id.btn_print);
        if (isPrintable) {
            btnPrint.setVisibility(View.VISIBLE);
        } else {
            btnPrint.setVisibility(View.GONE);
        }
        btnPrint.setEnabled(false);
        btnPrint.setAlpha(0.5f);


        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                print();
            }
        });
        imageViewBackFromWeb = (ImageView) findViewById(R.id.iv_back_game_web);
        imageViewBackFromWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(ContentWebviewActivity.this, true); // true bcz , it is  a home/back button is not clicked
                finish();
            }
        });
        super.rlTopBar.setVisibility(View.GONE);
        super.rlBottomBar.setVisibility(View.GONE);
        webView = (WebView) findViewById(R.id.webview);
        webviewForPdf = (WebView) findViewById(webview_hidden);
    }

    private void setDataForMainWebview() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setUserAgentString(getPackageName());
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(AppConstants.USER_NAME, AppConstants.PASSWORD);
                Log.e("NOTE", "URL ON onReceivedHttpAuthRequest" + view.getUrl());
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("NOTE", "URL ON ERROR" + view.getUrl());
                rlProgressContainer.setVisibility(View.GONE);
            }
            @SuppressLint("NewApi")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Logg.p("", "request.getRequestHeaders()::" + request.getRequestHeaders());
                return null;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("NOTE", "URL" + url.toString());
                printEnable(false);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Logg.p("PageLoad finished", "");
                if (isPrintable) {
                    webView.evaluateJavascript("javascript:currentPdfUrl();", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) { //currentPdfUrl()
                            Logg.p("currentPdfUrl value", value + "");
                            pdfFileLink = trimDoubleQuotes(value);
                            printEnable(true);
                            if (isPrintable) {
                                if (!isEmptyPdfLink(pdfFileLink)) {
                                    if (isValidPdfLink(pdfFileLink)) {
                                        Toast.makeText(getApplicationContext(), "Pdf link is = " + pdfFileLink, Toast.LENGTH_SHORT).show();
                                        setDataForPdfWebview(value);
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not getting Any pdf in this Page", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                } else {
                    printEnable(true);

                }
            }

        });
        webView.loadUrl(urlToLoad, Util.getExtraHeader(ContentWebviewActivity.this));
    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    private void print() {
        if (isEmptyPdfLink(pdfFileLink)) {
            rlOverlayLayout.setVisibility(View.VISIBLE);
            createOverlayTimer(webView);
        } else {
            if (isValidPdfLink(pdfFileLink)) {
                rlOverlayLayout.setVisibility(View.VISIBLE);
                createOverlayTimer(webviewForPdf);
            } else {
                rlOverlayLayout.setVisibility(View.VISIBLE);
                createOverlayTimer(webView);
            }

        }
    }

    private void setDataForPdfWebview(String pdfFileLink) {
        String newUrl = trimDoubleQuotes(pdfFileLink);

        String urlpdf = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + newUrl + "";
        webviewForPdf.getSettings().setJavaScriptEnabled(true);
        webviewForPdf.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webviewForPdf.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed(AppConstants.USER_NAME, AppConstants.PASSWORD);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Logg.p("", "error::" + error.toString());
            }

            @SuppressLint("NewApi")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Logg.p("", "request.getRequestHeaders()::" + request.getRequestHeaders());
                return null;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                printEnable(true);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                printEnable(true);
                super.onPageStarted(view, url, favicon);
            }
        });
        this.webviewForPdf.getSettings().setUserAgentString(getPackageName());
        webviewForPdf.loadUrl(urlpdf, Util.getExtraHeader(ContentWebviewActivity.this));


    }

    public static String trimDoubleQuotes(String text) {
        int textLength = text.length();

        if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
            return text.substring(1, textLength - 1);
        }

        return text;
    }

    private void createOverlayTimer(final WebView webViewExpected) {

        printEnable(false);
        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                tvTimeLeft.setText(" " + sec + " secs");
            }

            public void onFinish() {
                rlOverlayLayout.setVisibility(View.GONE);
                try {
                    PrintingHelper.getInstance().createWebPrintJob(webViewExpected, ContentWebviewActivity.this, new PrintFinishListener() {
                        @Override
                        public void onFinish() {
                            finish();
                        }
                    });
                } catch (Exception ex) {
                    Logg.p("EXCEPTION: PRINTING", ex + "");
                }
            }

        }.start();
    }


    private void printEnable(boolean isEnable) {
        if (isEnable) {
            btnPrint.setEnabled(true);
            webView.setClickable(true);
            rlProgressContainer.setClickable(false);
            btnPrint.setAlpha(1.0f);
            rlProgressContainer.setVisibility(View.GONE);

        } else {
            btnPrint.setEnabled(false);
            webView.setEnabled(false);
            webView.setClickable(false);
            rlProgressContainer.setClickable(true);

            btnPrint.setAlpha(0.5f);
            rlProgressContainer.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        printEnable(true);
    }

    private boolean isValidPdfLink(String urlPdf) {
        if (urlPdf.equalsIgnoreCase("http://dev.beta.turtlediary.com/ws/currentws.pdf"))
            return false;
        else
            return true;

    }

    private boolean isEmptyPdfLink(String urlPdf) {
        if (pdfFileLink == null || pdfFileLink.isEmpty() || pdfFileLink.equals("null"))
            return true;
        else
            return false;

    }
}