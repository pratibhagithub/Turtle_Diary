package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.SignUpActivity;
import com.turtlediary.www.backgroundServices.AsynctaskGetPriceDetailsViaPlaystore;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.beans.MemberShipBean;
import com.turtlediary.www.beans.ParentSectionDataBean;
import com.turtlediary.www.beans.PlayStoreProductBean;
import com.turtlediary.www.beans.ProductBean;
import com.turtlediary.www.beans.ProductPriceBean;
import com.turtlediary.www.beans.SignInPage;
import com.turtlediary.www.httpConnection.httpRequest.ProductIDRequest;
import com.turtlediary.www.httpConnection.requestParam.ProductIDfromServerParam;
import com.turtlediary.www.interfaces.OnPriceGettingCompletion;
import com.turtlediary.www.interfaces.OnServiceBindingCompletion;
import com.turtlediary.www.interfaces.OnSignUpFragmentChangeListner;
import com.turtlediary.www.interfaces.ProductIdCompleteInterface;
import com.turtlediary.www.interfaces.TabChangeListner;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.CurrencyHelper;
import com.turtlediary.www.utilities.InAppHelper;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.facebook.FacebookSdk.getApplicationContext;


public class MembershipFragment extends Fragment {
    private ConfigBean configBean;
    TabChangeListner tabChangeListner;
    private MemberShipBean memberShipBean;
    private TextView tvJoinTDHeader, tvTermsAndPolicy, tvCompare, tvData;
    private TextView tvFAmilyHeader, tvFamilySubHeader, tvFamilAnnualSwitch, tvFamilyMonthlySwitch, tvFamilyMonthlyPrice, tvFamilyAnnualPrice, tvFamilyStart;
    private TextView tvEnterpriseHeader, tvEnterpriseSubHeader, tbEnterpriseAnnualSwitch, tvEnterpriseMonthlySwitch, tvEnterpriseMonthlyPrice, tvEnterpriseAnnualPrice, tvEnterpriseStart;
    private RelativeLayout rlProgressContainer;
    private OnSignUpFragmentChangeListner mSignUpFragmentChangeListner;
    private String subscriptionTypeFamily;
    private String subscriptionTypeEnterPrise;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    private List<ProductBean> mProductBeanList;
    private ArrayList<PlayStoreProductBean> mPlayStoreProductBeenList = new ArrayList<>();
    private CurrencyHelper currencyHelper;
    private ArrayList<String> mPurchaseProductInfo = new ArrayList<>();

    /**
     * // This we require because when we confirm payment , we need to show  that
     * // whether we hav selected monthly subscrption or Annual subscriptin
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_membership, container, false);
        initWidgets(view);
        configBean = getArguments().getParcelable(AppConstants.KEY_BASE_BEAN);
        memberShipBean = configBean.getNewLoginScreen().getMemberShipPage();
        setText();
        setListners();
        getProductIDFromServer();

        Logg.p("Hello", "Hi");
        Log.e("Hello", "Hi");
        tabChangeListner = (TabChangeListner) getActivity();
        tabChangeListner.changeListner(AppConstants.MEMBERSHIP);

        currencyHelper = CurrencyHelper.getInstance();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mSignUpFragmentChangeListner = (OnSignUpFragmentChangeListner) getActivity();
        } catch (Exception ex) {
            Logg.p("Exception while initialising changeListner", ex.toString());

        }

    }

    // this we are doing , beacause "onAttach(Context context)", this function is not calling for API <23 ,
    // and hence we are not able to get mSignUpFragmentChangeListner and app was getting crash
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                mSignUpFragmentChangeListner = (OnSignUpFragmentChangeListner) getActivity();
                tabChangeListner.changeListner(AppConstants.MEMBERSHIP);
            } catch (Exception ex) {
                Logg.p("Exception while initialising changeListner", ex.toString());
            }
        } else {

        }

    }

    private void initWidgets(View view) {
        tvJoinTDHeader = (TextView) view.findViewById(R.id.tv_jointd_header);
        tvData = (TextView) view.findViewById(R.id.tv_data);
        tvFAmilyHeader = (TextView) view.findViewById(R.id.tv_family_header);
        tvFamilySubHeader = (TextView) view.findViewById(R.id.tv_family_subheader);
        tvFamilAnnualSwitch = (TextView) view.findViewById(R.id.tv_left_anually);
        tvFamilyMonthlySwitch = (TextView) view.findViewById(R.id.tv_left_monthly);
        tvFamilyMonthlyPrice = (TextView) view.findViewById(R.id.tv_family_monthy_subscription_price);
        tvFamilyAnnualPrice = (TextView) view.findViewById(R.id.tv_family_annual_subscription_price);
        tvFamilyStart = (TextView) view.findViewById(R.id.tv_family_start_trial);
        tvEnterpriseHeader = (TextView) view.findViewById(R.id.tv_enterprise_header);
        tvEnterpriseSubHeader = (TextView) view.findViewById(R.id.tv_enterprise_subheader);
        tbEnterpriseAnnualSwitch = (TextView) view.findViewById(R.id.tv_right_anually);
        tvEnterpriseMonthlySwitch = (TextView) view.findViewById(R.id.tv_right_monthly);
        tvEnterpriseMonthlyPrice = (TextView) view.findViewById(R.id.tv_enterprise_monthly_subscription_price);
        tvEnterpriseAnnualPrice = (TextView) view.findViewById(R.id.tv_enterprise_annual_subscription_price);
        tvEnterpriseStart = (TextView) view.findViewById(R.id.tv_enterprise_start_trial);
        tvCompare = (TextView) view.findViewById(R.id.tv_compare);
        tvTermsAndPolicy = (TextView) view.findViewById(R.id.tv_terms_sentence);
        rlProgressContainer = (RelativeLayout) view.findViewById(R.id.progressBarSignInContainer);
    }

    public static void quesViewDialog(final Activity activity, SignInPage signInPage) {
        final EditText edAnswer;
        TextView tvHeader, tvQues, tvCancel, tvOk;

        final ParentSectionDataBean number1 = getRandom(signInPage.getParentSection());
        final ParentSectionDataBean number2 = getRandom(signInPage.getParentSection());
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//, android.R.style.Theme_DeviceDefault_Light_Dialog
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        } else {
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme
            );
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.quesview_dialog, null);
        edAnswer = (EditText) dialogView.findViewById(R.id.ed_answer);
        final TextView tvTryAgain = (TextView) dialogView.findViewById(R.id.tv_try_again);
        tvTryAgain.setVisibility(View.GONE);
        tvHeader = (TextView) dialogView.findViewById(R.id.tv_ques_view_header);
        tvQues = (TextView) dialogView.findViewById(R.id.tv_ques);
        tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        tvOk = (TextView) dialogView.findViewById(R.id.tvOk);
        tvOk.setText(signInPage.getOkBtnText());
        tvCancel.setText(signInPage.getCancelBtnText());
        tvHeader.setText(signInPage.getParentHeading());
        tvQues.setText(signInPage.getParentQuestionText() + " " + number1.getNumText() + " " + signInPage.getCombineKey() + " " + number2.getNumText() + "");
        tvTryAgain.setText(signInPage.getTryAgain());
        builder.setCancelable(false);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ans = Integer.parseInt(number1.getIndex()) + Integer.parseInt(number2.getIndex());
                if (edAnswer.getText().toString().equals(String.valueOf(ans))) {
                    Util.hideSoftKeyboard(activity);
                    dialog.dismiss();
                } else {
                    Util.hideSoftKeyboard(activity);
                    tvTryAgain.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    private void setText() {
        tvJoinTDHeader.setText(memberShipBean.getJoinTD());
        tvFAmilyHeader.setText(memberShipBean.getPremiumHeading());
        tvFamilySubHeader.setText(memberShipBean.getPremiumSubHeading());
        tvFamilAnnualSwitch.setText(memberShipBean.getPremiumSegmentAnnually());
        tvFamilyMonthlySwitch.setText(memberShipBean.getPremiumSegmentMonthly());
        tvFamilyMonthlyPrice.setText(memberShipBean.getTDheading());
        tvFamilyAnnualPrice.setText(memberShipBean.getTDheading());
        tvFamilyStart.setText(memberShipBean.getBuyNowPaid());


        tvEnterpriseHeader.setText(memberShipBean.getEnterpriseHeading());
        tvEnterpriseSubHeader.setText(memberShipBean.getEnterpriseSubHeading());
        tbEnterpriseAnnualSwitch.setText(memberShipBean.getEnterpriseSegmentAnnually());
        tvEnterpriseMonthlySwitch.setText(memberShipBean.getEnterpriseSegmentMonthly());
        tvEnterpriseMonthlyPrice.setText(memberShipBean.getTDheading());
        tvEnterpriseAnnualPrice.setText(memberShipBean.getTDheading());
        tvEnterpriseStart.setText(memberShipBean.getBuyNowPaid());

        tvEnterpriseMonthlyPrice.setText(memberShipBean.getEnterpriseMonthly());
        tvEnterpriseAnnualPrice.setText(memberShipBean.getEnterpriseYearly());
        tvFamilyMonthlyPrice.setText(memberShipBean.getPremiumMonthly());
        tvFamilyAnnualPrice.setText(memberShipBean.getPremiumYearly());


        tvCompare.setText(memberShipBean.getCompareBtn());
        tvTermsAndPolicy.setText(memberShipBean.getWholeTremAndConditions());
        termsAndServices();

        /**
         * This we are doing because ,
         * on Page load we want Monthly subscription selecd in both cases(Family and Enterprise)**/
        toggleSubscriptionFamily(tvFamilyMonthlySwitch, tvFamilAnnualSwitch, tvFamilyAnnualPrice, false);
        toggleSubscriptionEnterPrise(tvEnterpriseMonthlySwitch, tbEnterpriseAnnualSwitch, tvEnterpriseAnnualPrice, false);
        subscriptionTypeFamily = AppConstants.MONTHLY;
        subscriptionTypeEnterPrise = AppConstants.MONTHLY;
    }

    private void setListners() {
        tvFamilAnnualSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionTypeFamily = AppConstants.ANNUALLY;
                toggleSubscriptionFamily(tvFamilAnnualSwitch, tvFamilyMonthlySwitch, tvFamilyAnnualPrice, true);

            }
        });
        tvFamilyMonthlySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionTypeFamily = AppConstants.MONTHLY;
                toggleSubscriptionFamily(tvFamilyMonthlySwitch, tvFamilAnnualSwitch, tvFamilyAnnualPrice, false);

            }
        });
        tvEnterpriseMonthlySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionTypeEnterPrise = AppConstants.MONTHLY;
                toggleSubscriptionEnterPrise(tvEnterpriseMonthlySwitch, tbEnterpriseAnnualSwitch, tvEnterpriseAnnualPrice, false);


            }
        });
        tbEnterpriseAnnualSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionTypeEnterPrise = AppConstants.ANNUALLY;
                toggleSubscriptionEnterPrise(tbEnterpriseAnnualSwitch, tvEnterpriseMonthlySwitch, tvEnterpriseAnnualPrice, true);
            }
        });
        tvCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSignUpFragmentChangeListner != null) {
                    mSignUpFragmentChangeListner.changeFragment(AppConstants.COMPARE, null, null, null, null);
                } else {
                    Logg.p("mSignUpFragmentChangeListner is null", "");

                }
            }
        });
        tvEnterpriseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPurchaseProductInfo = null;
                mPurchaseProductInfo = new ArrayList<String>(); // we are making it null just because it is creationg issue , as 2nd time we came in this page it is adding value in newr index

                /**
                 "AppConstants.ENTERPRISE" And  "AppConstants.ACCOUNT" , we require because when we confirm payment , we need to show  that
                 whether we hav selected family account or enterprise account*/
                if (mPlayStoreProductBeenList != null && mPlayStoreProductBeenList.size() > 0) {

                    if (subscriptionTypeEnterPrise.equalsIgnoreCase(AppConstants.MONTHLY)) {
                            mPurchaseProductInfo.add(mProductBeanList.get(0).getProduct_id());
                            mPurchaseProductInfo.add(currencyHelper.getPriceValue(AppConstants.ENTERPRISE_MONTH_INDEX, mPlayStoreProductBeenList));
                    } else {
                            mPurchaseProductInfo.add(mProductBeanList.get(1).getProduct_id());
                            mPurchaseProductInfo.add(currencyHelper.getPriceValue(AppConstants.ENTERPRISE_YEAR_INDEX, mPlayStoreProductBeenList));

                    }
                    callOtherFragment(AppConstants.ENTERPRISE, subscriptionTypeEnterPrise, null, mPurchaseProductInfo);
                }

            }
        });
        tvFamilyStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPurchaseProductInfo = null;
                mPurchaseProductInfo = new ArrayList<String>();
                if (mPlayStoreProductBeenList != null && mPlayStoreProductBeenList.size() > 0) {
                    if (subscriptionTypeFamily.equalsIgnoreCase(AppConstants.MONTHLY)) {
                        mPurchaseProductInfo.add(mProductBeanList.get(2).getProduct_id());
                        mPurchaseProductInfo.add(currencyHelper.getPriceValue(AppConstants.FAMILY_MONTH_INDEX, mPlayStoreProductBeenList));
                        Logg.p("Product price Family Monthly ", mPurchaseProductInfo.get(1) + "");

                    } else {
                        mPurchaseProductInfo.add(mProductBeanList.get(3).getProduct_id());
                        mPurchaseProductInfo.add(currencyHelper.getPriceValue(AppConstants.FAMILY_YEAR_INDEX, mPlayStoreProductBeenList));
                        Logg.p("Product price Family Yearly", mPurchaseProductInfo.get(1) + "");

                    }

                    callOtherFragment(AppConstants.FAMILY, subscriptionTypeFamily, null, mPurchaseProductInfo);
                }
            }
        });
    }

    private void toggleSubscriptionFamily(TextView selectedView, TextView unSelectedView, View annualSubscriptionPrice, boolean isShowAnnaul) {
        if (mPlayStoreProductBeenList.size() > 0) {
            if (isShowAnnaul) {
                String calculatedFamilyMonthyPrice = currencyHelper.getCalculatedMonthyRental(AppConstants.FAMILY_YEAR_INDEX, mPlayStoreProductBeenList,getActivity()) + " " + memberShipBean.getFreeTrialMsg();//currencyHelper.getCurrencySymbol(mPlayStoreProductBeenList.get(0).getCurrencyCode()) + currencyHelper.getCalculatedMonthyRental(currencyHelper.getMacrosValue(AppConstants.FAMILY_YEAR_INDEX,mPlayStoreProductBeenList)) + " " + memberShipBean.getFreeTrialMsg();
                tvFamilyMonthlyPrice.setText(calculatedFamilyMonthyPrice);
                tvFamilyAnnualPrice.setText(currencyHelper.getPriceValue(AppConstants.FAMILY_YEAR_INDEX, mPlayStoreProductBeenList) + " " + memberShipBean.getFreeTrialMsg());
                annualSubscriptionPrice.setVisibility(View.VISIBLE);

            } else {
                tvFamilyMonthlyPrice.setText(currencyHelper.getPriceValue(AppConstants.FAMILY_MONTH_INDEX, mPlayStoreProductBeenList) + " " + memberShipBean.getFreeTrialMsg());
                annualSubscriptionPrice.setVisibility(View.INVISIBLE);
            }
        } else {
            if (isShowAnnaul)
                annualSubscriptionPrice.setVisibility(View.VISIBLE);
            else
                annualSubscriptionPrice.setVisibility(View.INVISIBLE);
        }
        selectedView.setBackground(Util.gettingDrawable(getActivity(), R.drawable.start_blue));
        selectedView.setTextColor(Util.gettingColor(getActivity(), R.color.white));
        unSelectedView.setBackground(Util.gettingDrawable(getActivity(), R.drawable.blue_line_bg));
        unSelectedView.setTextColor(Util.gettingColor(getActivity(), R.color.blue_bottom_bar_bg));
    }

    private void toggleSubscriptionEnterPrise(TextView selectedView, TextView unSelectedView, View annualSubscriptionPrice, boolean isShowAnnaul) {
        if (mPlayStoreProductBeenList.size() > 0) {
            if (isShowAnnaul) {
                String calculatedEnterPriseMonthyPrice = currencyHelper.getCalculatedMonthyRental(AppConstants.ENTERPRISE_YEAR_INDEX, mPlayStoreProductBeenList,getActivity()) + " " + memberShipBean.getFreeTrialMsg();//currencyHelper.getCurrencySymbol(mPlayStoreProductBeenList.get(0).getCurrencyCode()) + currencyHelper.getCalculatedMonthyRental(currencyHelper.getMacrosValue(AppConstants.ENTERPRISE_YEAR_INDEX,mPlayStoreProductBeenList)) + " " + memberShipBean.getFreeTrialMsg();
                tvEnterpriseMonthlyPrice.setText(calculatedEnterPriseMonthyPrice);
                tvEnterpriseAnnualPrice.setText(currencyHelper.getPriceValue(AppConstants.ENTERPRISE_YEAR_INDEX, mPlayStoreProductBeenList) + " " + memberShipBean.getFreeTrialMsg());
                annualSubscriptionPrice.setVisibility(View.VISIBLE);
            } else {
                tvEnterpriseMonthlyPrice.setText(currencyHelper.getPriceValue(AppConstants.ENTERPRISE_MONTH_INDEX, mPlayStoreProductBeenList) + " " + memberShipBean.getFreeTrialMsg());
                annualSubscriptionPrice.setVisibility(View.INVISIBLE);
            }
        } else {
            if (isShowAnnaul)
                annualSubscriptionPrice.setVisibility(View.VISIBLE);
            else
                annualSubscriptionPrice.setVisibility(View.INVISIBLE);
        }


        selectedView.setBackground(Util.gettingDrawable(getActivity(), R.drawable.start_blue));
        selectedView.setTextColor(Util.gettingColor(getActivity(), R.color.white));
        unSelectedView.setBackground(Util.gettingDrawable(getActivity(), R.drawable.blue_line_bg));
        unSelectedView.setTextColor(Util.gettingColor(getActivity(), R.color.blue_bottom_bar_bg));
    }

    private void termsAndServices() {
        SpannableString ss = new SpannableString(memberShipBean.getWholeTremAndConditions());
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Util.callWebview(getActivity(), memberShipBean.getConditionsUrl());
            }
        };

        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Util.callWebview(getActivity(), (memberShipBean.getPolicyUrl()));
            }
        };

        int startingPositionTC = memberShipBean.getWholeTremAndConditions().indexOf(memberShipBean.getTermsandConditions());
        int endingPositionTC = startingPositionTC + memberShipBean.getTermsandConditions().length();
        ss.setSpan(span1, startingPositionTC, endingPositionTC, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        int startingPositionPP = memberShipBean.getWholeTremAndConditions().indexOf(memberShipBean.getPrivacyPolicy());
        int endingPositionPP = startingPositionPP + memberShipBean.getPrivacyPolicy().length();
        ss.setSpan(span2, startingPositionPP, endingPositionPP, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTermsAndPolicy.setText(ss);
        tvTermsAndPolicy.setMovementMethod(LinkMovementMethod.getInstance());
        tvTermsAndPolicy.setHighlightColor(ContextCompat.getColor(getActivity(), R.color.blue_bottom_bar_bg));
    }

    private void getProductIDFromServer() {
        showProgressBar(true);
        if (isAdded()) {

            ProductIDRequest.getProductId(getActivity(), new ProductIdCompleteInterface() {
                @Override
                public void productIDSucess(ProductPriceBean productPriceBean) {
                    showProgressBar(false);
                    mProductBeanList = productPriceBean.getData();
                    inAppConnectionSetup();
                    SignUpActivity activity = (SignUpActivity) getActivity();
                    if (activity.isShowDialog) {
                        quesViewDialog(getActivity(), configBean.getNewLoginScreen().getSigninPage());
                    }
                }

                @Override
                public void productIDFailed(String msg) {
                    showProgressBar(false);
                    Util.showToast(getActivity(), msg);
                }
            }, ProductIDfromServerParam.getParam(getActivity()));

        }
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            tvTermsAndPolicy.setEnabled(false);
            tvCompare.setEnabled(false);
            tvFamilAnnualSwitch.setEnabled(false);
            tvFamilyMonthlySwitch.setEnabled(false);
            tvFamilyStart.setEnabled(false);
            tbEnterpriseAnnualSwitch.setEnabled(false);
            tvEnterpriseMonthlySwitch.setEnabled(false);
            tvEnterpriseStart.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            tvTermsAndPolicy.setEnabled(true);
            tvCompare.setEnabled(true);
            tvFamilAnnualSwitch.setEnabled(true);
            tvFamilyMonthlySwitch.setEnabled(true);
            tvFamilyStart.setEnabled(true);
            tbEnterpriseAnnualSwitch.setEnabled(true);
            tvEnterpriseMonthlySwitch.setEnabled(true);
            tvEnterpriseStart.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }
    }


    public static ParentSectionDataBean getRandom(List<ParentSectionDataBean> list) {
        int rnd = new Random().nextInt(list.size());
        return list.get(rnd);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getPriceDetail(IInAppBillingService inappService) {
        ArrayList<String> productIds = new ArrayList<>();
        for (int i = 0; i < mProductBeanList.size(); i++) {
            productIds.add(mProductBeanList.get(i).getProduct_id());
        }
        AsynctaskGetPriceDetailsViaPlaystore asynctaskGetPriceDetailsViaPlaystore = new AsynctaskGetPriceDetailsViaPlaystore(getActivity(), productIds, new OnPriceGettingCompletion() {
            @Override
            public void getPriceDetails(ArrayList<PlayStoreProductBean> playStoreProductBeenListt) {
                mPlayStoreProductBeenList = playStoreProductBeenListt;
                setPrice();
            }
        }, inappService);
        asynctaskGetPriceDetailsViaPlaystore.execute();


    }

    private void setPrice() {
        for (int i = 0; i < mPlayStoreProductBeenList.size(); i++) {
            String skuId = mPlayStoreProductBeenList.get(i).getProductId();
            String price = mPlayStoreProductBeenList.get(i).getProductPrice();

            if (skuId.equals(mProductBeanList.get(0).getProduct_id())) {
                mPlayStoreProductBeenList.get(i).setIndex(AppConstants.ENTERPRISE_MONTH_INDEX);
                tvEnterpriseMonthlyPrice.setText(price + " " + memberShipBean.getFreeTrialMsg());
            } else if (skuId.equals(mProductBeanList.get(1).getProduct_id())) {
                tvEnterpriseAnnualPrice.setText(price + " " + memberShipBean.getEnterpriseLast());
                mPlayStoreProductBeenList.get(i).setIndex(AppConstants.ENTERPRISE_YEAR_INDEX);

            } else if (skuId.equals(mProductBeanList.get(2).getProduct_id())) {
                tvFamilyMonthlyPrice.setText(price + " " + memberShipBean.getFreeTrialMsg());
                mPlayStoreProductBeenList.get(i).setIndex(AppConstants.FAMILY_MONTH_INDEX);

            } else if (skuId.equals(mProductBeanList.get(3).getProduct_id())) {
                tvFamilyAnnualPrice.setText(price + " " + memberShipBean.getEnterpriseLast());
                mPlayStoreProductBeenList.get(i).setIndex(AppConstants.FAMILY_YEAR_INDEX);

            }
        }
        String data = memberShipBean.getConfirmNote3();
        try {
            String newData = data.replace(AppConstants.EM_PRICE, currencyHelper.getPriceValue(AppConstants.ENTERPRISE_MONTH_INDEX, mPlayStoreProductBeenList));
            newData = newData.replace(AppConstants.EA_PRICE, currencyHelper.getPriceValue(AppConstants.ENTERPRISE_YEAR_INDEX, mPlayStoreProductBeenList));
            newData = newData.replace(AppConstants.FM_PRICE, currencyHelper.getPriceValue(AppConstants.FAMILY_MONTH_INDEX, mPlayStoreProductBeenList));
            newData = newData.replace(AppConstants.FA_PRICE, currencyHelper.getPriceValue(AppConstants.FAMILY_YEAR_INDEX, mPlayStoreProductBeenList));
            tvData.setText(newData);

        } catch (Exception ex) {
            tvData.setText(data);

            ex.printStackTrace();
        }

    }

    private void inAppConnectionSetup() {
        InAppHelper inAppHelper = new InAppHelper(getActivity(), new OnServiceBindingCompletion() {
            @Override
            public void onInAppServiceBindSucess(IInAppBillingService mService) {
                getPriceDetail(mService);
            }

            @Override
            public void onInAppServiceBindFailed(String failedMessage) {
            }
        });
        inAppHelper.bindingService();
    }

    private void callOtherFragment(String accountType, String SubscriptionType, ArrayList<String> infolista, ArrayList<String> purchaseProductInfo) {
        if (TextUtils.isEmpty((Preferences.getUserId(getApplicationContext()))))//NotLoggedIn
        {
            if (mSignUpFragmentChangeListner != null) {
                //  mSignUpFragmentChangeListner.changeFragment(AppConstants.ACCOUNT, AppConstants.FAMILY, subscriptionTypeFamily, null, mPurchaseProductInfo);
                mSignUpFragmentChangeListner.changeFragment(AppConstants.ACCOUNT, accountType, SubscriptionType, infolista, purchaseProductInfo);
            } else {
                Logg.p("mSignUpFragmentChangeListner is null", "");
            }
        } else {
            if (!Preferences.getUserStatus(getApplicationContext())) {
                ArrayList<String> infoListFromPreferences = new ArrayList<String>();
                infoListFromPreferences.add(Preferences.getUserEmail(getApplicationContext()));
                infoListFromPreferences.add(Preferences.getUserPassword(getApplicationContext()));
                infoListFromPreferences.add(Preferences.getUserNAme(getApplicationContext()));
                if (mSignUpFragmentChangeListner != null) {
                    // mSignUpFragmentChangeListner.changeFragment(AppConstants.PAYMENT, AppConstants.FAMILY, subscriptionTypeFamily, infoListFromPreferences, mPurchaseProductInfo);
                    mSignUpFragmentChangeListner.changeFragment(AppConstants.PAYMENT, accountType, SubscriptionType, infoListFromPreferences, purchaseProductInfo);
                } else {
                    Logg.p("mSignUpFragmentChangeListner", "");
                }
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Logg.p("On Resume -- Membership", "");

    }


}
