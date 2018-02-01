package com.turtlediary.www.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.turtlediary.www.R;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.httpConnection.httpRequest.ContentTypeRequest;
import com.turtlediary.www.httpConnection.requestParam.ConfigRequestParam;
import com.turtlediary.www.interfaces.OnHttpRequestCompleteListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.SoundEffect;
import com.turtlediary.www.utilities.Util;

public class HomeBaseActivity extends AppCompatActivity {
    private TextView tvUpgrade, tvFreeTrial, tvLogin, tvloginInfoBottom;
    protected TextView tvTopBarHeader;
    protected ImageView ivSettings, ivBackAtTop, ivHomeAtTop;
    private RelativeLayout rlContainer;
    private LinearLayout llSignInContainer, llTrialContainer, llBottomMsgContainer, llSettingContainer;
    private BaseBean baseBean;
    protected RelativeLayout rlTopBar;
    protected RelativeLayout rlBottomBar;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_base);
        myApplication = MyApplication.getInstance();
        initViews();
        baseBeanCheckForDataSet();
        setListners();
    }

    private void initViews() {
        tvloginInfoBottom = (TextView) findViewById(R.id.login_info_bottom);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvUpgrade = (TextView) findViewById(R.id.tv_upgrade);
        tvFreeTrial = (TextView) findViewById(R.id.tv_freetrial);
        tvTopBarHeader = (TextView) findViewById(R.id.tv_top_bar_header);
        ivSettings = (ImageView) findViewById(R.id.iv_setting);
        ivBackAtTop = (ImageView) findViewById(R.id.iv_back);
        ivHomeAtTop = (ImageView) findViewById(R.id.iv_home);
        rlContainer = (RelativeLayout) findViewById(R.id.layout_container);
        llSignInContainer = (LinearLayout) findViewById(R.id.ll_login_container);
        llTrialContainer = (LinearLayout) findViewById(R.id.ll_trial_container);
        llBottomMsgContainer = (LinearLayout) findViewById(R.id.ll_bottom_msg_container);
        llSettingContainer = (LinearLayout) findViewById(R.id.ll_settingcontainer);
        rlTopBar = (RelativeLayout) findViewById(R.id.top_back_home_container);
        rlBottomBar = (RelativeLayout) findViewById(R.id.bottom_bar);
    }


    private void setListners() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSignInActivity(AppConstants.sLogin);
            }
        });
        tvUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Util.checkGooglePlayServicesAvaialbility(HomeBaseActivity.this);
                callSignUpActivity();

            }
        });
        tvFreeTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSignUpActivity();
            }
        });
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSignInActivity(AppConstants.sSetting);
            }
        });
        ivBackAtTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(HomeBaseActivity.this, true); // true bcz , it is  a home/back button is not clicked
                finish();
            }
        });
        ivHomeAtTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(HomeBaseActivity.this, HomeActivity.class);
                intents.putExtra(AppConstants.KEY_BASE_BEAN, myApplication.getBaseBean());
                intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                );
                startActivity(intents);
                finish();
                if (Preferences.getEffectSoundEnability(getApplicationContext()))
                    SoundEffect.getInstance().playSound(HomeBaseActivity.this, true); // true bcz , it is  a home/back button is not clicked
            }
        });
        Util.setEffect(tvLogin);
        Util.setEffect(tvUpgrade);
        Util.setEffect(tvFreeTrial);
        Util.setEffect(ivSettings);
        Util.setEffect(ivBackAtTop);
        Util.setEffect(ivHomeAtTop);
    }

    public void setView(int childView) {
        LayoutInflater inflator = LayoutInflater.from(getApplicationContext());
        inflator.inflate(childView, rlContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        baseBeanCheckForDataSet();
    }


    private void callSignInActivity(String callingScreen) {
        Intent intent = new Intent(HomeBaseActivity.this, SigninActivity.class);
        intent.putExtra(AppConstants.CONFIG_BEAN, myApplication.getBaseBean().getConfig());
        intent.putExtra(AppConstants.sCALLEDFROM, callingScreen);
        startActivity(intent);
    }

    private void callSignUpActivity() {
        if (Util.isNetworkAvailable(HomeBaseActivity.this)) {
            Intent intent = new Intent(HomeBaseActivity.this, SignUpActivity.class);
            intent.putExtra(AppConstants.CONFIG_BEAN, myApplication.getBaseBean().getConfig());
            startActivity(intent);
        }
    }

    private void setBottomText() {
        baseBean = myApplication.getBaseBean();
        try {
            if ((baseBean != null)) {
                tvUpgrade.setText(baseBean.getConfig().getTDupgradeMsg());
                tvLogin.setText(baseBean.getConfig().getTDloginBtnMsg());
                tvloginInfoBottom.setText(baseBean.getConfig().getTDupgradeMsgNav());
                tvFreeTrial.setText(baseBean.getConfig().getLogin().getSignUpBtnTxt());
                if (TextUtils.isEmpty((Preferences.getUserId(getApplicationContext())))) {//Not LoggedIn
                    llBottomMsgContainer.setVisibility(View.VISIBLE);
                    llSignInContainer.setVisibility(View.VISIBLE);
                    llTrialContainer.setVisibility(View.GONE);
                    tvloginInfoBottom.setText(baseBean.getConfig().getTDregisterMsgNav());
                    tvFreeTrial.setText(baseBean.getConfig().getLogin().getSignUpBtnTxt());
                } else {//LoggedIn
                    llSignInContainer.setVisibility(View.GONE);
                    llTrialContainer.setVisibility(View.VISIBLE);
                    tvloginInfoBottom.setText(baseBean.getConfig().getTDupgradeMsgNav());
                    if (Preferences.getUserStatus(getApplicationContext())) {
                        llBottomMsgContainer.setVisibility(View.GONE);
                        tvUpgrade.setVisibility(View.GONE);
                        llSettingContainer.setBackgroundColor(Util.gettingColor(this, (R.color.full_transparent)));
                    } else {
                        llBottomMsgContainer.setVisibility(View.VISIBLE);
                        tvUpgrade.setVisibility(View.VISIBLE);
                        llSettingContainer.setBackgroundColor(Util.gettingColor(this, (R.color.blue_bottom_bar_bg)));
                    }
                }
            } else {
                Log.e("context ", "base is null");
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    private void baseBeanCheckForDataSet() {
        if (baseBean == null) {
            baseBean = myApplication.getBaseBean();
            if (baseBean == null) { // This check we have used , if app crashes and then came to this activity, and static data vanishes, there we need o hit this api
                // else we are already hittting this api in splash
                getContentTypeForUser();
            }
        } else {
            setBottomText();
        }
    }

    public void disableBottomBarView(boolean isDisable) {
        tvUpgrade.setEnabled(!isDisable);
        tvFreeTrial.setEnabled(!isDisable);
        tvloginInfoBottom.setEnabled(!isDisable);
        tvLogin.setEnabled(!isDisable);
        ivHomeAtTop.setEnabled(!isDisable);
        ivBackAtTop.setEnabled(!isDisable);
        ivSettings.setEnabled(!isDisable);
    }

    private void getContentTypeForUser() {
        ContentTypeRequest.contentTypenUser(HomeBaseActivity.this, ConfigRequestParam.getParams(this), new OnHttpRequestCompleteListner() {
            @Override
            public void requestSuccess(BaseBean contentTypeBean) {
                MyApplication myApplication = MyApplication.getInstance();
                myApplication.setBaseBean(contentTypeBean);
                Preferences.saveUserStatus(getApplicationContext(), contentTypeBean.getUserStatus());
                setBottomText();
            }

            @Override
            public void requestFailed(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
