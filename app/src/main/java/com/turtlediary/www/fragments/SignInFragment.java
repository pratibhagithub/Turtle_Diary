package com.turtlediary.www.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.SignUpActivity;
import com.turtlediary.www.activities.SigninActivity;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.beans.LoginBean;
import com.turtlediary.www.beans.SignInPage;
import com.turtlediary.www.beans.SignInPageLoginOption;
import com.turtlediary.www.httpConnection.ApiKeys;
import com.turtlediary.www.httpConnection.httpRequest.LoginRequest;
import com.turtlediary.www.httpConnection.requestModel.LoginRequestModel;
import com.turtlediary.www.interfaces.OnLoginInterface;
import com.turtlediary.www.utilities.AppConstants;
import com.turtlediary.www.utilities.Util;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.turtlediary.www.utilities.Util.setEffect;

/**
 * Created by pratibha on 24/10/17.
 */

public class SignInFragment extends Fragment {


    private Activity activity;
    private Spinner spinnerUserType;
    private EditText edClassCode, edUserName, edpassword;
    private TextView tvAuthoriseDevice, tvForgetUserName, tvForgetPwd, tvSignUp;
    private TextView tvLoginAsLabel, tvClassCodeLabel, tvUserNameLabel, tvPwdLabel, tvNotMemberLabel;
    private String userType = ApiKeys.sTeacher;
    private LinearLayout llClasscode;
    private RelativeLayout rlProgressContainer;
    private View tempView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin,
                container, false);
        initWidgets(view);
        SignInPage signInPage = getArguments().getParcelable(AppConstants.SIGN_BEAN);
        if (signInPage != null)
            setText(signInPage);
        initListners(signInPage);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
    }

    private void initWidgets(View view) {
        rlProgressContainer = (RelativeLayout) view.findViewById(R.id.progressBarSignInContainer);
        spinnerUserType = (Spinner) view.findViewById(R.id.spinner_login_user_type);
        edClassCode = (EditText) view.findViewById(R.id.ed_class_code);
        edUserName = (EditText) view.findViewById(R.id.ed_username);
        edpassword = (EditText) view.findViewById(R.id.ed_password);
        llClasscode = (LinearLayout) view.findViewById(R.id.classcode_ll);
        tvAuthoriseDevice = (TextView) view.findViewById(R.id.tv_authorise_device);
        tvForgetUserName = (TextView) view.findViewById(R.id.tv_forget_user);
        tvForgetPwd = (TextView) view.findViewById(R.id.tv_forget_pwd);
        tvLoginAsLabel = (TextView) view.findViewById(R.id.tv_login_as_label);
        tvClassCodeLabel = (TextView) view.findViewById(R.id.tv_class_code_label);
        tvUserNameLabel = (TextView) view.findViewById(R.id.tv_username_label);
        tvPwdLabel = (TextView) view.findViewById(R.id.tv_pwd_label);
        tvNotMemberLabel = (TextView) view.findViewById(R.id.tv_not_a_mem_label);
        tvSignUp = (TextView) view.findViewById(R.id.tv_sign_up);
        tempView = (View) view.findViewById(R.id.view);
        setEffect(tvForgetUserName);
        setEffect(tvForgetPwd);
        setEffect(tvSignUp);
        setEffect(tvAuthoriseDevice);


    }

    private void initListners(final SignInPage signInPage) {

        tvAuthoriseDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateWidgets(signInPage);

            }
        });
        tvForgetUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(signInPage.getForgetUsernameUrl());
            }
        });
        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(signInPage.getForgetPasswordUrl());
            }
        });

        spinnerUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                userType = selectedItem;
                if (userType.equalsIgnoreCase(ApiKeys.sSTudent)) {
                    llClasscode.setVisibility(View.VISIBLE);
                } else {
                    llClasscode.setVisibility(View.GONE);
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        KeyboardVisibilityEvent.setEventListener(
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

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isNetworkAvailable(getActivity())) {
                    MyApplication myApplication = MyApplication.getInstance();
                    Intent intent = new Intent(getActivity(), SignUpActivity.class);
                    intent.putExtra(AppConstants.CONFIG_BEAN, myApplication.getBaseBean().getConfig());
                    startActivity(intent);

            }

            }
        });
    }

    private void validateWidgets(SignInPage signInPage) {
        String userName = edUserName.getText().toString(), userPassword = edpassword.getText().toString(), userClassCode = edClassCode.getText().toString();
        View requestFocus = null;
        boolean cancel = false;
        edClassCode.setError(null);
        edpassword.setError(null);
        edUserName.setError(null);
        if (TextUtils.isEmpty(userName)) {
            edUserName.setError(signInPage.getUsernameErrorMsg());
            requestFocus = edUserName;
            cancel = true;
        }
        if (userType.equalsIgnoreCase(ApiKeys.sSTudent)) {
            if (TextUtils.isEmpty(userClassCode)) {
                edClassCode.setError(signInPage.getClassCodeErrorMsg());
                requestFocus = edClassCode;
                cancel = true;
            }
        }
        if (TextUtils.isEmpty(userPassword)) {
            edpassword.setError(signInPage.getPasswordErrorMsg());
            requestFocus = edpassword;
            cancel = true;
        }

        if (cancel) {
            cancel = false;
            requestFocus.requestFocus();
        } else {
            login(userName, userPassword, userType, userClassCode);
        }

    }


    private void login(String uname, String pwd, final String userType, String classCode) {
        showProgressBar(true);
        if (isAdded()) {
            Gson gson = new Gson();
            LoginRequestModel loginRequestModel = new LoginRequestModel();
            loginRequestModel.setSlug(ApiKeys.sPreKVideoApp);
            loginRequestModel.setuId(Util.getUniqueDeviceID(getActivity()));
            loginRequestModel.setAction(ApiKeys.sLogin);
            loginRequestModel.setAppVersion(Util.getApplicationVersion(getActivity()));
            loginRequestModel.setForceupdateApp(ApiKeys.FORCE_UPDATE_APP + "");
            loginRequestModel.setLanguage(ApiKeys.sEnglish);
            loginRequestModel.setUpdateApp(ApiKeys.sUpdateAppvalue);
            if (getResources().getBoolean(R.bool.isTablet)) {
                loginRequestModel.setUserDevice(ApiKeys.sUserDevice);
            } else {
                loginRequestModel.setUserDevice(getString(R.string.phone));
            }
            loginRequestModel.setApiVersion(ApiKeys.API_VERSION);
            loginRequestModel.setEnvir(ApiKeys.ENVIR);
            loginRequestModel.setClasscode(classCode);
            loginRequestModel.setUsertype(userType);
            loginRequestModel.setUsername(uname);
            loginRequestModel.setPassword(pwd);

            String data = gson.toJson(loginRequestModel);
            Map<String, String> apiParamMap = new HashMap<>();
            apiParamMap.put(ApiKeys.KEY_S, ApiKeys.sAPI_Login);
            apiParamMap.put(ApiKeys.KEY_V, Util.getRandomNumber() + "");
            apiParamMap.put(ApiKeys.KEY_DATA, data);
            LoginRequest.userLogin(getActivity(), new OnLoginInterface() {
                @Override
                public void onLoginSuccess(LoginBean loginBean) {
                    showProgressBar(false);
                    String s = loginBean.getStatus();
                    if (s.equalsIgnoreCase("success")) {
                        Util.savePreferenceValue(getActivity(), edUserName.getText().toString(), edpassword.getText().toString(), loginBean.getToken(), userType, loginBean.getUserId(), loginBean.getUserStatus(), loginBean.getEmail());
                        getActivity().finish();
                    } else {
                        Util.savePreferenceValue(getActivity(), "", "", "", "", "", false, "");
                    }
                }

                @Override
                public void onLoginFailed(String msg)
                {
                    Util.showToast(getActivity(), msg);
                    showProgressBar(false);
                    Util.savePreferenceValue(getActivity(), "", "", "", "", "", false, "");
                }
            }, apiParamMap);

        }

    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            ((SigninActivity) getActivity()).enableTabButtonsDuringApi(false);
            tvAuthoriseDevice.setEnabled(false);
            tvSignUp.setEnabled(false);
            tvForgetPwd.setEnabled(false);
            tvForgetUserName.setEnabled(false);
            edClassCode.setEnabled(false);
            edUserName.setEnabled(false);
            edpassword.setEnabled(false);
            spinnerUserType.setEnabled(false);
            rlProgressContainer.setVisibility(View.VISIBLE);
        } else {
            ((SigninActivity) getActivity()).enableTabButtonsDuringApi(true);
            tvAuthoriseDevice.setEnabled(true);
            tvSignUp.setEnabled(true);
            tvForgetPwd.setEnabled(true);
            tvForgetUserName.setEnabled(true);
            edClassCode.setEnabled(true);
            edUserName.setEnabled(true);
            edpassword.setEnabled(true);
            spinnerUserType.setEnabled(true);
            rlProgressContainer.setVisibility(View.GONE);
        }

    }

    private void openBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void setText(SignInPage signInPage) {
        edClassCode.setHint(signInPage.getClassCodeHolder());
        edpassword.setHint(signInPage.getPasswordHolder());
        edUserName.setHint(signInPage.getUsernameHolder());
        tvAuthoriseDevice.setText(signInPage.getAuthorizeHeading());
        tvForgetPwd.setText(signInPage.getForgetpasswordHeading());
        tvForgetUserName.setText(signInPage.getForgetuserNameHeading());
        tvNotMemberLabel.setText(signInPage.getNotaMemberHeading());
        tvLoginAsLabel.setText(signInPage.getLoginasHeading());
        tvPwdLabel.setText(signInPage.getPasswordHeading());
        tvUserNameLabel.setText(signInPage.getUsernameHeading());
        tvClassCodeLabel.setText(signInPage.getClasscodeHeading());
        tvSignUp.setText(signInPage.getSignUpHeading());
        setLoginAsDropDownList(signInPage);
    }

    private void setLoginAsDropDownList(SignInPage signInPage) {
        ArrayAdapter<String> itemsAdapter;
        List<SignInPageLoginOption> itemsClassList = signInPage.getLoginOptions();
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < itemsClassList.size(); i++) {
            items.add(itemsClassList.get(i).getName());
        }
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinnerUserType.setAdapter(itemsAdapter);
    }


}
