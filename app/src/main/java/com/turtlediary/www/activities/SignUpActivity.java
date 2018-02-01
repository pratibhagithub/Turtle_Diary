package com.turtlediary.www.activities;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.turtlediary.www.R;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.beans.RegistrationResponseBean;
import com.turtlediary.www.fragments.AccountSignupFragment;
import com.turtlediary.www.fragments.CompareFragment;
import com.turtlediary.www.fragments.MembershipFragment;
import com.turtlediary.www.fragments.PaymentFragment;
import com.turtlediary.www.httpConnection.httpRequest.PaymentReciepRequest;
import com.turtlediary.www.httpConnection.requestParam.RegistrationRequestParam;
import com.turtlediary.www.interfaces.OnPaymentConfirmation;
import com.turtlediary.www.interfaces.OnSignUpFragmentChangeListner;
import com.turtlediary.www.interfaces.TabChangeListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.turtlediary.www.R.id.signup_fragment;

public class SignUpActivity extends AppCompatActivity implements OnSignUpFragmentChangeListner,TabChangeListner {
    public TextView tvPayment, tvAccBar, tvMemberBAr;
    private ConfigBean configBean;
    private ImageView ivCancel;
    private FrameLayout frameLayout;
    public ServiceConnection mServiceConn;
    public boolean isShowDialog = true;
    public PaymentFragment paymentFragment;

    //We use this so that when we ame back from Compare fragment then we donot require to call the add dialog,
    // bcz at Compare fragment we do, popBackStack for MemberFragment and thus call onCreatView() Again


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        MyApplication myApplication = (MyApplication) getApplicationContext();

        configBean = myApplication.getBaseBean().getConfig();
        initWidgets();
        setListners();
        setMemberShipFragment();


    }

    private void initWidgets() {
        tvMemberBAr = (TextView) findViewById(R.id.tv_membership_bar);
        tvAccBar = (TextView) findViewById(R.id.tv_acc_bar);
        frameLayout = (FrameLayout) findViewById(R.id.signup_fragment);
        tvPayment = (TextView) findViewById(R.id.tv_payment_bar);
        ivCancel = (ImageView) findViewById(R.id.iv_finish_signup);
        tvMemberBAr.setText(configBean.getNewLoginScreen().getInformationHeading());
        tvAccBar.setText(configBean.getNewLoginScreen().getAccountHeading());
        tvPayment.setText(configBean.getNewLoginScreen().getSettingsHeading());
    }

    private void setListners() {
        tvMemberBAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void setMemberShipFragment() {
        setTabColors(AppConstants.MEMBERSHIP);
        MembershipFragment membershipFragment = new MembershipFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.KEY_BASE_BEAN, configBean);
        membershipFragment.setArguments(bundle);
        Util.fragmentTransec(membershipFragment, SignUpActivity.this, signup_fragment);
    }

    private void setPaymentFragment(String accountType, String subscritionType, ArrayList<String> signupInfo, ArrayList<String> purchaseProductInfo) {
        setTabColors(AppConstants.PAYMENT);
         paymentFragment = new PaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.KEY_BASE_BEAN, configBean);
        bundle.putString(AppConstants.SUBSCRIPTION_TYPE_Duration, subscritionType);
        bundle.putStringArrayList(AppConstants.PURCHASE_PRODUCT_DETAIL, purchaseProductInfo);

        bundle.putString(AppConstants.ACCOUNT_TYPE, accountType);
        bundle.putStringArrayList(AppConstants.SIGNUP_DETAIL_LIST, signupInfo);
        paymentFragment.setArguments(bundle);
        Util.fragmentTransecWithBAckstack(paymentFragment, SignUpActivity.this, signup_fragment);
    }


    private void setAccountFragment(String accountType, String subscritionType, ArrayList<String> purchaseProductInfo) {
        setTabColors(AppConstants.ACCOUNT);
        AccountSignupFragment accountSignupFragment = new AccountSignupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.KEY_BASE_BEAN, configBean);
        bundle.putString(AppConstants.SUBSCRIPTION_TYPE_Duration, subscritionType);
        bundle.putStringArrayList(AppConstants.PURCHASE_PRODUCT_DETAIL, purchaseProductInfo);
        bundle.putString(AppConstants.ACCOUNT_TYPE, accountType);
        accountSignupFragment.setArguments(bundle);
        Util.fragmentTransec(accountSignupFragment, SignUpActivity.this, signup_fragment);
    }


    @Override
    public void changeFragment(int value, String accountType, String subscritionType, ArrayList<String> signupInfo, ArrayList<String> purchaseProductInfo) {
        isShowDialog = false;
        if (value == AppConstants.COMPARE) {
            setTabColors(AppConstants.COMPARE);
            CompareFragment compareFragment = new CompareFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.KEY_BASE_BEAN, configBean);
            compareFragment.setArguments(bundle);
            Util.fragmentTransecWithBAckstack(compareFragment, SignUpActivity.this, signup_fragment);
        } else if (value == AppConstants.ACCOUNT) {
            setAccountFragment(accountType, subscritionType, purchaseProductInfo);
        } else if (value == AppConstants.PAYMENT) {
            setPaymentFragment(accountType, subscritionType, signupInfo, purchaseProductInfo);
        }
    }


    public void setTabColors(int fragment) {
        if (fragment == (AppConstants.ACCOUNT)) {
            tvAccBar.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_orange));
            tvMemberBAr.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
            tvPayment.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
        }
        if (fragment == (AppConstants.PAYMENT)) {
            tvPayment.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_orange));
            tvMemberBAr.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
            tvAccBar.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
        }
        if (fragment == (AppConstants.MEMBERSHIP) || fragment == AppConstants.COMPARE) {
            tvMemberBAr.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_orange));
            tvAccBar.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
            tvPayment.setBackground(Util.gettingDrawable(SignUpActivity.this, R.drawable.sign_in_strip_grey));
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        {
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");
            Log.e("In app purchase", responseCode + "");
            Log.e("In app purchase",purchaseData+"");
            Log.e("In app purchase",dataSignature+"");
            if (resultCode == RESULT_OK) {
                try {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");
                    String purchaseToken = jo.getString("purchaseToken");
                    Logg.p("You have bought the ", sku + ". Excellent choice,adventurer!");
                    if(paymentFragment!=null) {
                        HashMap<String, String> hasmapUserAddedData = paymentFragment.getFilledData();
                        getReceiptForPayment(hasmapUserAddedData,purchaseToken, sku);
                    }
                } catch (JSONException e) {
                    Logg.p("Fail to parse purchase.", e.toString());
                    e.printStackTrace();
                }
            }
        }
    }


    public  void getReceiptForPayment( HashMap<String,String> hasmapUserAddedData,String purchaseToken,String prodId) {
        {
            if(paymentFragment!=null) {
                paymentFragment.showProgressBarPayment(true);
            }
            PaymentReciepRequest.getPaymentReceiptFromServer(SignUpActivity.this, RegistrationRequestParam.getParam(hasmapUserAddedData,purchaseToken,prodId,SignUpActivity.this), new OnPaymentConfirmation() {
                @Override
                public void paymentSuccess(RegistrationResponseBean registrationResponseBean) {
                    paymentFragment.showProgressBarPayment(false);
                    String status=registrationResponseBean.getStatus();
                    if (status.equalsIgnoreCase("success")) {
                        Util.savePreferenceValue(SignUpActivity.this,registrationResponseBean.getUsername().toString(),registrationResponseBean.getPassword(), registrationResponseBean.getToken(),"",registrationResponseBean.getUserId(),registrationResponseBean.getUserStatus(),registrationResponseBean.getEmail());
                        Toast.makeText(SignUpActivity.this, registrationResponseBean.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                    }
                }
                @Override
                public void paymentFailed(String msg)
                 {
                     paymentFragment.showProgressBarPayment(false);
                     Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();
                 }
            });
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Logg.p("On Resume -- Membership", "");

    }
    @Override
    public void changeListner(int value) {
        setTabColors(value);

    }
}

