package com.turtlediary.www.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.vending.billing.IInAppBillingService;
import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.SigninActivity;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.ConfigNewLoginScreenBean;
import com.turtlediary.www.beans.ConfigSettingBean;
import com.turtlediary.www.beans.ProductBean;
import com.turtlediary.www.beans.ProductPriceBean;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.LogoutRequest;
import com.turtlediary.www.httpConnection.httpRequest.ProductIDRequest;
import com.turtlediary.www.httpConnection.httpRequest.RestoreRequest;
import com.turtlediary.www.httpConnection.requestModel.LogoutRequestModel;
import com.turtlediary.www.httpConnection.requestModel.RestoreModel;
import com.turtlediary.www.httpConnection.requestParam.ProductIDfromServerParam;
import com.turtlediary.www.inAppUtilities.IabHelper;
import com.turtlediary.www.inAppUtilities.IabResult;
import com.turtlediary.www.inAppUtilities.Inventory;
import com.turtlediary.www.inAppUtilities.Purchase;
import com.turtlediary.www.interfaces.OnDialogEventListners;
import com.turtlediary.www.interfaces.OnLogOutComplete;
import com.turtlediary.www.interfaces.OnRestoreCompletion;
import com.turtlediary.www.interfaces.OnServiceBindingCompletion;
import com.turtlediary.www.interfaces.ProductIdCompleteInterface;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.InAppHelper;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pratibha on 24/10/17.
 */

public class SettingFragment extends Fragment {
    private TextView tvRestore, tvLoginLogout, tvBgSoundLabel, tvEffectSoundLabel, tvSoundLabel;
    private ConfigNewLoginScreenBean configNewLoginScreenBean;
    private RelativeLayout rlProgressContainer;
    private ToggleButton tgBgSound, tgSoundEffect;
    private IabHelper mIabHelper;

    public List<ProductBean> productBeanList;
    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            checkUserSubscription(inventory);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,
                container, false);
        // Here ConfigNewLoginScreenBean is requuired, as for logout
        configNewLoginScreenBean = getArguments().getParcelable(AppConstants.KEY_BASE_BEAN);
        initWidgets(view);
        if (configNewLoginScreenBean != null)
            setText(configNewLoginScreenBean.getSettingPage());
        setListners();
        return view;
    }

    private void initWidgets(View view) {
        tgBgSound = (ToggleButton) view.findViewById(R.id.tg_background_sound);
        tgSoundEffect = (ToggleButton) view.findViewById(R.id.tg_effect_sound);
        rlProgressContainer = (RelativeLayout) view.findViewById(R.id.progress_container_setting);
        tvLoginLogout = (TextView) view.findViewById(R.id.logout);
        tvRestore = (TextView) view.findViewById(R.id.restore);
        tvBgSoundLabel = (TextView) view.findViewById(R.id.tv_bg_sound_label);
        tvEffectSoundLabel = (TextView) view.findViewById(R.id.tv_effect_sound_label);
        tvSoundLabel = (TextView) view.findViewById(R.id.tv_sound_label);
        tgBgSound.setChecked(Preferences.getBackgroundSoundEnability(getActivity()));
        tgSoundEffect.setChecked(Preferences.getEffectSoundEnability(getActivity()));
    }

    private void setListners() {
        tvLoginLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.okayCancelDialog(getActivity(), configNewLoginScreenBean.getLogoutTextMsg(), configNewLoginScreenBean.getLogoutHeading(), configNewLoginScreenBean.getLogoutOkBtn(), configNewLoginScreenBean.getLogoutCancelBtn(), new OnDialogEventListners() {
                    @Override
                    public void onOkay(DialogInterface dialog) {
                        callLogoutApi();
                        dialog.dismiss();
                    }
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });
        tvRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductIDFromServer();
                // Toast.makeText(getActivity(), "Depend On In-APP-Purchase , will implement  later", Toast.LENGTH_SHORT).show();
            }
        });
        tgBgSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Preferences.saveBackgroundSoundEnability(getActivity(), isChecked);
                MyApplication myApplication = (MyApplication) getActivity().getApplicationContext();
                if (isChecked)
                {
                    myApplication.startPlayer((SigninActivity) getActivity());
                    Preferences.saveBackgroundSoundEnability(getActivity(), true);
                    myApplication.startPlayer((SigninActivity) getActivity());

                }else
                {
                     myApplication.stopPlayer((SigninActivity) getActivity());
                     Preferences.saveBackgroundSoundEnability(getActivity(), false);
                }


            }
        });
        tgSoundEffect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Preferences.saveEffectSoundEnability(getActivity(), isChecked);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        diableButtons();
    }

    private void diableButtons() {
        if (TextUtils.isEmpty((Preferences.getUserId(getActivity())))) {
            tvLoginLogout.setAlpha((float) 0.50);
            tvLoginLogout.setEnabled(false);
            tvRestore.setAlpha((float) 0.50);
            tvRestore.setEnabled(false);

        } else {
            tvLoginLogout.setAlpha((float) 1.00);
            tvLoginLogout.setEnabled(true);
            tvRestore.setAlpha((float) 1.00);
            tvRestore.setEnabled(true);
        }
    }

    private void setText(ConfigSettingBean configSettingBean) {
        tvBgSoundLabel.setText(configSettingBean.getBackgroundsoundHeading());
        tvEffectSoundLabel.setText(configSettingBean.getEffectsoundHeading());
        tvSoundLabel.setText(configSettingBean.getSoundHeading());
        tvLoginLogout.setText(configSettingBean.getLogoutBtn());
        tvRestore.setText(configSettingBean.getRestore());
        Util.setEffect(tvRestore);
        Util.setEffect(tvLoginLogout);
    }

    private void callLogoutApi() {
        showProgressBar(true);
        if (isAdded()) {
            Gson gson = new Gson();
            LogoutRequestModel logoutRequestModel = new LogoutRequestModel();
            logoutRequestModel.setAction(ApiKeys.LOGOUT);
            logoutRequestModel.setuId(Util.getUniqueDeviceID(getActivity()));
            logoutRequestModel.setAppVersion(Util.getApplicationVersion(getActivity()));
            logoutRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
            logoutRequestModel.setLanguage(ApiKeys.sEnglish);
            if (getResources().getBoolean(R.bool.isTablet)) {
                logoutRequestModel.setUserDevice(ApiKeys.sUserDevice);
            } else {
                logoutRequestModel.setUserDevice(getString(R.string.phone));
            }
            logoutRequestModel.setApiVersion(ApiKeys.API_VERSION);
            logoutRequestModel.setEnvir(ApiKeys.ENVIR);
            logoutRequestModel.setUsertype(Preferences.getUserType(getActivity()));
            logoutRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
            logoutRequestModel.setPassword(Preferences.getUserPassword(getActivity()));
            logoutRequestModel.setUsername(Preferences.getUserNAme(getActivity()));

            String data = gson.toJson(logoutRequestModel);
            Map<String, String> apiParamMap = new HashMap<>();
            apiParamMap.put(ApiKeys.KEY_S, ApiKeys.API_LOGOUT);
            apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
            apiParamMap.put(ApiKeys.KEY_DATA, data);

            LogoutRequest.callLogOutApi(getActivity(), new OnLogOutComplete() {
                @Override
                public void logoutSuccess() {
                    showProgressBar(false);
                    Logg.p("TAG", "LogOUt  Success");
                    Preferences.saveUserId(getActivity(), "");
                    Preferences.saveUserName(getActivity(), "");
                    Preferences.saveUserPassword(getActivity(), "");
                    Preferences.saveUserName(getActivity(), "");
                    Preferences.saveUserToken(getActivity(), "");

                    // Preferences.saveUserStatus(getActivity(), false);
                    getActivity().finish();

                }

                @Override
                public void logoutFailed(String msg) {

                    showProgressBar(false);
                    Util.showToast(getActivity(),msg);
                    Logg.p("TAG", "LogOUt  failed");

                }
            }, apiParamMap);

        }

    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            ((SigninActivity) getActivity()).enableTabButtonsDuringApi(false);
            tgBgSound.setEnabled(false);
            tgSoundEffect.setEnabled(false);
            tvLoginLogout.setEnabled(false);
            tvRestore.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            ((SigninActivity) getActivity()).enableTabButtonsDuringApi(true);
            tgBgSound.setEnabled(true);
            tgSoundEffect.setEnabled(true);
            tvLoginLogout.setEnabled(true);
            tvRestore.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }

    }

    private void queryItems() {
        mIabHelper = new IabHelper(getActivity(), AppConstants.BASE64KEY_INAPP);
        mIabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (result.isSuccess()) {
                    Logg.p("result", result.toString());
                    mIabHelper.queryInventoryAsync(mGotInventoryListener);
                }else
                {
                    showProgressBar(false);
                }
            }
        });
    }






    private void getProductIDFromServer() {
        showProgressBar(true);
        if (isAdded()) {
            ProductIDRequest.getProductId(getActivity(), new ProductIdCompleteInterface() {
                @Override
                public void productIDSucess(ProductPriceBean productPriceBean) {
                    productBeanList = productPriceBean.getData();
                    inAppConnectionSetup();
                }
                @Override
                public void productIDFailed(String msg) {
                    showProgressBar(false);
                    Util.showToast(getActivity(),msg);
                }
            }, ProductIDfromServerParam.getParam(getActivity()));

        }
    }

    private void checkUserSubscription(Inventory inventory) {
        String purchaseItemId = "";
        Purchase purchase;
        boolean isSubscriber = false;
        for (int i = 0; i < productBeanList.size(); i++) {
            if (inventory.hasPurchase(productBeanList.get(i).getProduct_id())) {
                purchaseItemId = productBeanList.get(i).getProduct_id();
                isSubscriber = true;
                break;
            }
        }
        Logg.p("Token is", isSubscriber + "");

        if (isSubscriber) {
            purchase = inventory.getPurchase(purchaseItemId);
            String token = purchase.getToken();
            setRestore(token);
            Logg.p("Token is", purchase.getToken());

        }else
        {
            showProgressBar(false);

        }
    }

    private void setRestore(String token) {

        if (isAdded()) {
            Gson gson = new Gson();
            RestoreModel restoreModel = new RestoreModel();
            restoreModel.setEnvir(ApiKeys.ENVIR);
            restoreModel.setAppVersion(Util.getApplicationVersion(getActivity()));
            restoreModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
            restoreModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
            if (getResources().getBoolean(R.bool.isTablet)) {
                restoreModel.setUserDevice(ApiKeys.sUserDevice);
            } else {
                restoreModel.setUserDevice(getString(R.string.phone));
            }
            restoreModel.setApiVersion(ApiKeys.API_VERSION);
            restoreModel.setAction(ApiKeys.REGISTRATION);
            restoreModel.setRegBy(ApiKeys.TD_ANDROID);
            restoreModel.setUpgrade(ApiKeys.YES);
            restoreModel.setUsername(Preferences.getUserNAme(getActivity()));
            restoreModel.setUpgrade(Preferences.getUserPassword(getActivity()));
            restoreModel.setReceipt(token);

            String data = gson.toJson(restoreModel);
            Map<String, String> apiParamMap = new HashMap<>();
            apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sRESTORE);
            apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
            apiParamMap.put(ApiKeys.KEY_DATA, data);
            RestoreRequest.getRestore(getActivity(), apiParamMap, new OnRestoreCompletion() {
                @Override
                public void restorSuccess() {
                    showProgressBar(false);
                }

                @Override
                public void restorFailed(String msg) {
                    Util.showToast(getActivity(),msg);
                    showProgressBar(false);
                }
            });

        }
    }

    private void inAppConnectionSetup()
    {
        InAppHelper inAppHelper=new InAppHelper(getActivity(), new OnServiceBindingCompletion() {
            @Override
            public void onInAppServiceBindSucess(IInAppBillingService mService) {
                queryItems();
            }
            @Override
            public void onInAppServiceBindFailed(String failedMessage) {
                showProgressBar(false);

            }
        });
        inAppHelper.bindingService();
    }
}