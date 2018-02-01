package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.R;
import com.turtlediary.www.backgroundServices.AsynctaskToPurchaseSubscriptiom;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.beans.ConfirmBean;
import com.turtlediary.www.interfaces.OnPurchaseCompletion;
import com.turtlediary.www.interfaces.OnServiceBindingCompletion;
import com.turtlediary.www.interfaces.OnSignUpFragmentChangeListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.CurrencyHelper;
import com.turtlediary.www.utilities.InAppHelper;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.ArrayList;
import java.util.HashMap;


public class PaymentFragment extends Fragment {
    private OnSignUpFragmentChangeListner onSignUpFragmentChangeListner;
    private IInAppBillingService mService;
    private RelativeLayout rlProgressContainer;


    private TextView tvSubscriptionHeaser, tvNote, tvTermsPrivacy, tvConfirmPayment, tvCancelMessage, tvAccountType, tvPlan, tvBill, tvChargeAfterFreeTrial;
    private ImageView ivBack;
    private ArrayList<String> purchaseProductInfo;
    ArrayList<String> infoList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ConfigBean configBean = getArguments().getParcelable(AppConstants.KEY_BASE_BEAN);
        String subscriptionType = getArguments().getString(AppConstants.SUBSCRIPTION_TYPE_Duration);
        String accountType = getArguments().getString(AppConstants.ACCOUNT_TYPE);
        infoList = getArguments().getStringArrayList(AppConstants.SIGNUP_DETAIL_LIST);
        purchaseProductInfo = getArguments().getStringArrayList(AppConstants.PURCHASE_PRODUCT_DETAIL);
        Logg.p("Index 0", infoList.get(0)); //email
        Logg.p("Index1", infoList.get(1));//pwd
        Logg.p("Index2", infoList.get(2));//uname
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        initWidgets(view);
        setListners();
        setData(configBean.getNewLoginScreen().getConfirmPage(), subscriptionType, accountType);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onSignUpFragmentChangeListner = (OnSignUpFragmentChangeListner) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement On Campare listner");
        }

    }

    // this we are doing , beacause "onAttach(Context context)", this function is not calling for API <23 ,
    // and hence we are not able to get signUpFragmentChangeListner and app was getting crash
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onSignUpFragmentChangeListner = (OnSignUpFragmentChangeListner) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement On Campare listner");
        }

    }

    private void initWidgets(View view) {
        rlProgressContainer = (RelativeLayout) view.findViewById(R.id.progressBarSignInContainer);
        tvSubscriptionHeaser = (TextView) view.findViewById(R.id.tv_subscription_header);
        tvNote = (TextView) view.findViewById(R.id.tv_note);
        tvTermsPrivacy = (TextView) view.findViewById(R.id.tv_terms_condition);
        tvConfirmPayment = (TextView) view.findViewById(R.id.tv_complete_payment);
        tvTermsPrivacy = (TextView) view.findViewById(R.id.tv_terms_condition);
        tvChargeAfterFreeTrial = (TextView) view.findViewById(R.id.tv_charge_after_fee_trial);
        tvBill = (TextView) view.findViewById(R.id.tv_bill_value);
        tvPlan = (TextView) view.findViewById(R.id.tv_plan_type);
        tvAccountType = (TextView) view.findViewById(R.id.tv_account_type);
        ivBack = (ImageView) view.findViewById(R.id.iv_back);
    }


    private void setListners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });
        tvConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // bindingService();
                inAppConnectionSetup();


            }
        });
    }


    private void setData(ConfirmBean confirmBean, String subscriptionTypeDuration, String accountType) {
        tvSubscriptionHeaser.setText(confirmBean.getPurchaseLabel());
        termsAndServices(confirmBean);
        tvConfirmPayment.setText(confirmBean.getContinueBtn());
        tvNote.setText(confirmBean.getConfirmNote1() + " " + subscriptionTypeDuration.toLowerCase() + " " + confirmBean.getConfirmNote2());

        if (accountType.equalsIgnoreCase(AppConstants.ENTERPRISE))
            tvAccountType.setText(confirmBean.getTDAccounttype() + " : " + confirmBean.getUserTypeTeacher());
        else if (accountType.equalsIgnoreCase(AppConstants.FAMILY))
            tvAccountType.setText(confirmBean.getTDAccounttype() + " " + confirmBean.getUserTypeStudent());

        if (subscriptionTypeDuration.equalsIgnoreCase(AppConstants.ANNUALLY))
            tvPlan.setText(confirmBean.getTDPlan() + " " + confirmBean.getPlanYearly());
        else if (subscriptionTypeDuration.equalsIgnoreCase(AppConstants.MONTHLY))
            tvPlan.setText(confirmBean.getTDPlan() + " " + confirmBean.getPlanMonthly());
   /*
   This if else is just to retain if user payment is for upgradation or he is a new user
   if userId exist and still he pays, that means he is upgrading , else he is a new user , on this basis we are showint its current billing
   * */
        if (!TextUtils.isEmpty(Preferences.getUserId(getActivity().getApplicationContext())))
            tvBill.setText(confirmBean.getTotalPrice() + " : " + purchaseProductInfo.get(1));
        else {
            CurrencyHelper currencyHelper=CurrencyHelper.getInstance();
            String curencySymbol= currencyHelper.getCurrencySymbol(Preferences.getCountryCode(getActivity()));
            tvBill.setText(confirmBean.getTotalPrice() + " : " +curencySymbol+ " 0.00");
        }

        String newLine = confirmBean.getFreeTrialMsgDetails().replace(AppConstants.PRICE, purchaseProductInfo.get(1));
        tvChargeAfterFreeTrial.setText(newLine);


    }

    private void termsAndServices(final ConfirmBean confirmBean) {
        SpannableString ss = new SpannableString(confirmBean.getWholeTremAndConditions());
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Util.callWebview(getActivity(), confirmBean.getConditionsUrl());
            }
        };

        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Util.callWebview(getActivity(), (confirmBean.getPolicyUrl()));
            }
        };

        int startingPositionTC = confirmBean.getWholeTremAndConditions().indexOf(confirmBean.getTermsandConditions());
        int endingPositionTC = startingPositionTC + confirmBean.getTermsandConditions().length();
        ss.setSpan(span1, startingPositionTC, endingPositionTC, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        int startingPositionPP = confirmBean.getWholeTremAndConditions().indexOf(confirmBean.getPrivacyPolicy());
        int endingPositionPP = startingPositionPP + confirmBean.getPrivacyPolicy().length();
        ss.setSpan(span2, startingPositionPP, endingPositionPP, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTermsPrivacy.setText(ss);
        tvTermsPrivacy.setMovementMethod(LinkMovementMethod.getInstance());
        tvTermsPrivacy.setHighlightColor(ContextCompat.getColor(getActivity(), R.color.blue_bottom_bar_bg));
    }
  /*  */

    private void callPurchaseAsync(String productId) {
        AsynctaskToPurchaseSubscriptiom asynctaskToPurchaseSubscriptiom = new AsynctaskToPurchaseSubscriptiom(getActivity(), productId, new OnPurchaseCompletion() {
            @Override
            public void onPurchaseSuccess() {
//after purchase success the, response is going Activity's on
            }

            @Override
            public void onPurchaseFailed() {

            }
        }, mService);
        asynctaskToPurchaseSubscriptiom.execute();
    }


    public HashMap getFilledData() {

        HashMap<String, String> userFilledDataHashmap = new HashMap<>();
        userFilledDataHashmap.put(AppConstants.EMAIL, infoList.get(0).toString());
        userFilledDataHashmap.put(AppConstants.PASSWORD, infoList.get(1).toString());
        userFilledDataHashmap.put(AppConstants.UNAME, infoList.get(2).toString());
        return userFilledDataHashmap;
    }

    private void inAppConnectionSetup() {
        InAppHelper inAppHelper = new InAppHelper(getActivity(), new OnServiceBindingCompletion() {
            @Override
            public void onInAppServiceBindSucess(IInAppBillingService service) {
                mService = service;
                callPurchaseAsync(purchaseProductInfo.get(0));
            }

            @Override
            public void onInAppServiceBindFailed(String failedMessage) {
            }
        });
        inAppHelper.bindingService();
    }

    public void showProgressBarPayment(boolean isToShow) {
        if (isToShow) {
            rlProgressContainer.setVisibility(View.VISIBLE);
            tvTermsPrivacy.setEnabled(false);
            tvConfirmPayment.setEnabled(false);
        } else {
            rlProgressContainer.setVisibility(View.GONE);
            tvTermsPrivacy.setEnabled(true);
            tvConfirmPayment.setEnabled(true);
        }

    }
}
