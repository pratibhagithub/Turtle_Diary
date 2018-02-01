package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.ConfigBean;
import com.turtlediary.www.beans.RegistrationResultBean;
import com.turtlediary.www.beans.SignUpPageBean;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.RegistrationRequest;
import com.turtlediary.www.httpConnection.requestModel.RegistrationRequestModel;
import com.turtlediary.www.interfaces.OnSignUpFragmentChangeListner;
import com.turtlediary.www.interfaces.RegistrationCompleteInterface;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AccountSignupFragment extends Fragment {

    private EditText eduserName, edUsereEmail, eduserPassword, edConfirmPwd;
    private TextView tvUserName, tvUsereEmail, tvUserPassword, tvConfirmPwd, tvRegistration;
    private ConfigBean configBean;
    private RelativeLayout rlProgressContainer;
    private OnSignUpFragmentChangeListner onSignUpFragmentChangeListner;
    private String subscriptionType;
    private String accountType;
    private ArrayList<String> purchaseProductInfo;
    private  SignUpPageBean signUpPageBean;
    private View tempView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configBean = getArguments().getParcelable(AppConstants.KEY_BASE_BEAN);
        signUpPageBean = configBean.getNewLoginScreen().getSignupPage();

        subscriptionType = getArguments().getString(AppConstants.SUBSCRIPTION_TYPE_Duration);
        purchaseProductInfo = getArguments().getStringArrayList(AppConstants.PURCHASE_PRODUCT_DETAIL);

        accountType = getArguments().getString(AppConstants.ACCOUNT_TYPE);
        View view = inflater.inflate(R.layout.fragment_account_signup, container, false);
        initWidgets(view);

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


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initWidgets(View view) {
        edUsereEmail = (EditText) view.findViewById(R.id.ed_email);
        eduserName = (EditText) view.findViewById(R.id.ed_choose_user_name);
        eduserPassword = (EditText) view.findViewById(R.id.ed_pwd);
        edConfirmPwd = (EditText) view.findViewById(R.id.confirm_ed_password);

        tvUserName = (TextView) view.findViewById(R.id.tv_choose_username);
        tvUsereEmail = (TextView) view.findViewById(R.id.tv_email);
        tvUserPassword = (TextView) view.findViewById(R.id.tv_pwd);
        tvConfirmPwd = (TextView) view.findViewById(R.id.confirm_pwd_label);
        tvRegistration = (TextView) view.findViewById(R.id.tv_sign_up);
        tempView = (View) view.findViewById(R.id.view);


        edUsereEmail.setHint(signUpPageBean.getEmailHolder());
        eduserName.setHint(signUpPageBean.getChooseUsernameHolder());
        eduserPassword.setHint(signUpPageBean.getPasswordHolder());
        edConfirmPwd.setHint(signUpPageBean.getConfirmPasswordHolder());


        tvUserName.setText(signUpPageBean.getChooseUsernameText());
        tvUsereEmail.setText(signUpPageBean.getEmailText());
        tvUserPassword.setText(signUpPageBean.getPasswordText());
        tvConfirmPwd.setText(signUpPageBean.getConfirmPasswordText());
        tvRegistration.setText(configBean.getNewLoginScreen().getSignupPage().getSubmitBtn1());
        tvRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              validateWidgets();
               /* ArrayList<String> infoList=new ArrayList<String>();
                infoList.add(edUsereEmail.getText().toString());
                infoList.add(eduserPassword.getText().toString());
                infoList.add(eduserName.getText().toString());
                onSignUpFragmentChangeListner.changeFragment(AppConstants.PAYMENT,accountType,subscriptionType,infoList,purchaseProductInfo);*/
            }
        });

        rlProgressContainer = (RelativeLayout) view.findViewById(R.id.progressBarSignInContainer);     KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            tempView.setVisibility(View.VISIBLE);
                        } else {
                            tempView.setVisibility(View.GONE);
                        }

                    }
                });

    }

    private void registration(String uname, String pwd, final String email) {
        showProgressBar(true);
        if (isAdded()) {
            Gson gson = new Gson();
            RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel();
            registrationRequestModel.setAction(ApiKeys.sREGISTRATION_ACTION_BEFORE_PAYMENT);
            registrationRequestModel.setEnvir(ApiKeys.ENVIR);
            registrationRequestModel.setAppVersion(Util.getApplicationVersion(getActivity()));
            registrationRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
            registrationRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
            if (getResources().getBoolean(R.bool.isTablet)) {
                registrationRequestModel.setUserDevice(ApiKeys.sUserDevice);
            } else {
                registrationRequestModel.setUserDevice(getString(R.string.phone));
            }
            registrationRequestModel.setApiVersion(ApiKeys.API_VERSION);
            registrationRequestModel.setEmail(email);
            ;
            registrationRequestModel.setUsername(uname);
            registrationRequestModel.setPassword(pwd);

            String data = gson.toJson(registrationRequestModel);
            Map<String, String> apiParamMap = new HashMap<>();
            apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sREGISTRATION);//"appmodule.App"
            apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
            apiParamMap.put(ApiKeys.KEY_DATA, data);//checkUserStat
            RegistrationRequest.userRegistration(getActivity(),new RegistrationCompleteInterface() {
                @Override
                public void onRegistrationSucess(RegistrationResultBean responseBean) {

                    showProgressBar(false);
                    ArrayList<String> infoList=new ArrayList<String>();
                    infoList.add(edUsereEmail.getText().toString());
                    infoList.add(eduserPassword.getText().toString());
                    infoList.add(eduserName.getText().toString());
                    onSignUpFragmentChangeListner.changeFragment(AppConstants.PAYMENT,accountType,subscriptionType,infoList,purchaseProductInfo);

                }

                @Override
                public void onRegistrationFailed(String response) {
                    Util.showToast(getActivity(),response);
                    Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
                    showProgressBar(false);


                }
            }, apiParamMap);

        }

    }


    private void validateWidgets() {
        String userName = eduserName.getText().toString();
        String userPassword = eduserPassword.getText().toString();
        String userConfirmPassword = edConfirmPwd.getText().toString();
        String userEmail = edUsereEmail.getText().toString();


        View requestFocus = null;
        boolean cancel = false;
        eduserName.setError(null);
        eduserPassword.setError(null);
        edConfirmPwd.setError(null);
        edUsereEmail.setError(null);
        if (TextUtils.isEmpty(userName)) {
            eduserName.setError(signUpPageBean.getChooseUsernameErrorMsg1());
            requestFocus = eduserName;
            cancel = true;
        } else if (userName.length() < 6 ) {
            eduserName.setError(signUpPageBean.getChooseUsernameErrorMsg1());
            requestFocus = eduserName;
            cancel = true;
        }else if (userName.length() > 22) {
            eduserName.setError(signUpPageBean.getChooseUsernameErrorMsg2());
            requestFocus = eduserName;
            cancel = true;
        }


        if (TextUtils.isEmpty(userEmail)) {
            edUsereEmail.setError(signUpPageBean.getEmailErrorMsg());
            requestFocus = edUsereEmail;
            cancel = true;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            edUsereEmail.setError(signUpPageBean.getEmailErrorMsg());
            requestFocus = edUsereEmail;
            cancel = true;
        }


        if (TextUtils.isEmpty(userPassword)) {
            eduserPassword.setError(signUpPageBean.getPasswordErrorMsg1());
            requestFocus = eduserPassword;
            cancel = true;
        } else if (userPassword.length() < 6) {
            eduserPassword.setError(signUpPageBean.getPasswordErrorMsg1());
            requestFocus = eduserName;
            cancel = true;
        }else if (userPassword.length() >20) {
            eduserPassword.setError(signUpPageBean.getPasswordErrorMsg2());
            requestFocus = eduserName;
            cancel = true;
        }
    /*    if (TextUtils.isEmpty(userConfirmPassword)) {
            edConfirmPwd.setError("");
            requestFocus = edConfirmPwd;
            cancel = true;
        } else*/ if (!userPassword.equals(userConfirmPassword)) {
            edConfirmPwd.setError(signUpPageBean.getConfirmPasswordErrorMsg());
            requestFocus = edConfirmPwd;
            cancel = true;
        }

        if (cancel) {
            cancel = false;
            requestFocus.requestFocus();
        } else {
            registration(userName, userPassword, userEmail);
        }

    }

    private void showProgressBar(boolean isShow) {

        if (isShow) {
            eduserPassword.setEnabled(false);
            edConfirmPwd.setEnabled(false);
            edUsereEmail.setEnabled(false);
            eduserName.setEnabled(false);
            tvRegistration.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            eduserPassword.setEnabled(true);
            edConfirmPwd.setEnabled(true);
            edUsereEmail.setEnabled(true);
            eduserName.setEnabled(true);
            tvRegistration.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }


    }


}
