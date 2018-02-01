package com.turtlediary.www.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.turtlediary.www.R;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.beans.LoginMainPage;
import com.turtlediary.www.fragments.AccountFragment;
import com.turtlediary.www.fragments.InformationFragment;
import com.turtlediary.www.fragments.SettingFragment;
import com.turtlediary.www.fragments.SignInFragment;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

public class SigninActivity extends AppCompatActivity {

    private FrameLayout mainFragment;
    private ImageView ivFinishButton;
    private TextView tvInfoBar, tvSettingBar, tvAccountBar, tvSignInBar;
    private static final String TAG = SigninActivity.class.getSimpleName();
    private ConfigBean configBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_base);

        initWidgets();
        setListners();
        configBean = getIntent().getParcelableExtra(AppConstants.CONFIG_BEAN);
        setBarText();
        setFragment(getIntent());


    }

    private void setFragment(Intent intent) {
        if (intent != null) {
            try {
                String fragmentName = intent.getStringExtra(AppConstants.sCALLEDFROM);
                if (fragmentName.equalsIgnoreCase(AppConstants.sLogin)) {
                    setSingnInFragment();
                } else if (fragmentName.equalsIgnoreCase(AppConstants.sDialog)) {
                    setSingnInFragment();
                }else if (fragmentName.equalsIgnoreCase(AppConstants.sSetting)) {
                    setSettingBarFragment();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private void initWidgets() {
        mainFragment = (FrameLayout) findViewById(R.id.signin_fragment);
        tvSignInBar = (TextView) findViewById(R.id.tv_signin_bar);
        tvSettingBar = (TextView) findViewById(R.id.tv_setting_bar);
        tvInfoBar = (TextView) findViewById(R.id.tv_info_bar);
        tvAccountBar = (TextView) findViewById(R.id.tv_account_bar);
        ivFinishButton = (ImageView) findViewById(R.id.iv_finish_signin);

        Util.setEffect(tvSignInBar);
        Util.setEffect(tvSettingBar);
        Util.setEffect(tvInfoBar);
        Util.setEffect(tvAccountBar);
        Util.setEffect(ivFinishButton);
    }

    private void setListners() {
        if (TextUtils.isEmpty(Preferences.getUserId(SigninActivity.this))) {
            tvAccountBar.setEnabled(false);
            tvSignInBar.setEnabled(true);
        } else {
            tvAccountBar.setEnabled(true);
            tvSignInBar.setEnabled(false);
        }
        tvInfoBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoFragment();
            }
        });
        tvSettingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSettingBarFragment();
            }
        });
        tvAccountBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAccountfragment();
            }
        });
        tvSignInBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSingnInFragment();
            }
        });
        ivFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setInfoFragment() {
        setTabColors(AppConstants.sInformation);
        InformationFragment informationFragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.INFORMATION_BEAN, configBean.getNewLoginScreen().getInformationPage());
        informationFragment.setArguments(bundle);
        Util.fragmentTransec(informationFragment, SigninActivity.this, R.id.signin_fragment);
    }


    private void setSingnInFragment() {
        setTabColors(AppConstants.sLogin);
        SignInFragment signInFragment = new SignInFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.SIGN_BEAN, configBean.getNewLoginScreen().getSigninPage());
        signInFragment.setArguments(bundle);
        Util.fragmentTransec(signInFragment, SigninActivity.this, R.id.signin_fragment);
    }


    private void setAccountfragment() {
        setTabColors(AppConstants.sAccount);
        AccountFragment accountFragment = new AccountFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.ACCOUNT_BEAN, configBean.getNewLoginScreen().getAccountPage());
        accountFragment.setArguments(bundle);
        Util.fragmentTransec(accountFragment, SigninActivity.this, R.id.signin_fragment);
    }

    private void setSettingBarFragment() {
        setTabColors(AppConstants.sSetting);
        SettingFragment settingFragment = new SettingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.KEY_BASE_BEAN, configBean.getNewLoginScreen());
        settingFragment.setArguments(bundle);
        Util.fragmentTransec(settingFragment, SigninActivity.this, R.id.signin_fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        disableTabs();

    }

    private void disableTabs() {
        if (TextUtils.isEmpty((Preferences.getUserId(getApplicationContext())))) {
            tvSignInBar.setEnabled(true);
            tvSignInBar.setAlpha((float) 1.00);
            tvAccountBar.setEnabled(false);
            tvAccountBar.setAlpha((float) 0.50);

        } else {
            tvSignInBar.setEnabled(false);
            tvSignInBar.setAlpha((float) 0.50);
            tvAccountBar.setEnabled(true);
            tvAccountBar.setAlpha((float) 1.00);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setTabColors(String fragmentName) {
        if (fragmentName.equalsIgnoreCase(AppConstants.sInformation)) {
            tvInfoBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_orange));
            tvInfoBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_orange));
            tvSettingBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSignInBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvAccountBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
        }
        if (fragmentName.equalsIgnoreCase(AppConstants.sSetting)) {
            tvInfoBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSettingBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_orange));
            tvSignInBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvAccountBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
        }
        if (fragmentName.equalsIgnoreCase(AppConstants.sAccount)) {
            tvInfoBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSettingBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSignInBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvAccountBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_orange));
        }
        if (fragmentName.equalsIgnoreCase(AppConstants.sLogin)) {
            tvInfoBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSettingBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
            tvSignInBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_orange));
            tvAccountBar.setBackground(gettingDrawable(R.drawable.sign_in_strip_grey));
        }
        disableTabs();

    }

    private Drawable gettingDrawable(int drawableId) {
        return ContextCompat.getDrawable(getApplicationContext(), drawableId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void setBarText() {
        LoginMainPage loginMainPage = configBean.getNewLoginScreen().getLoginMainPage();
        tvSignInBar.setText(loginMainPage.getSignInHeading());
        tvAccountBar.setText(loginMainPage.getAccountHeading());
        tvSettingBar.setText(loginMainPage.getSettingsHeading());
        tvInfoBar.setText(loginMainPage.getInformationHeading());
    }

    public void enableTabButtonsDuringApi(boolean isEnable) {
        tvSignInBar.setEnabled(isEnable);
        tvSettingBar.setEnabled(isEnable);
        tvInfoBar.setEnabled(isEnable);
        tvAccountBar.setEnabled(isEnable);
        ivFinishButton.setEnabled(isEnable);
        disableTabs();
    }


}
