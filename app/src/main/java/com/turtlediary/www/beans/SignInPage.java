package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SignInPage implements Parcelable {
    public String getLoginasText() {
        return LoginasText;
    }

    public void setLoginasText(String loginasText) {
        LoginasText = loginasText;
    }

    public String getLoginasHeading() {
        return LoginasHeading;
    }

    public void setLoginasHeading(String loginasHeading) {
        LoginasHeading = loginasHeading;
    }

    public String getClasscodeHeading() {
        return ClasscodeHeading;
    }

    public void setClasscodeHeading(String classcodeHeading) {
        ClasscodeHeading = classcodeHeading;
    }

    public String getPasswordHeading() {
        return PasswordHeading;
    }

    public void setPasswordHeading(String passwordHeading) {
        PasswordHeading = passwordHeading;
    }

    public String getUsernameHeading() {
        return UsernameHeading;
    }

    public void setUsernameHeading(String usernameHeading) {
        UsernameHeading = usernameHeading;
    }

    public String getAuthorizeHeading() {
        return AuthorizeHeading;
    }

    public void setAuthorizeHeading(String authorizeHeading) {
        AuthorizeHeading = authorizeHeading;
    }

    public String getForgetuserNameHeading() {
        return ForgetuserNameHeading;
    }

    public void setForgetuserNameHeading(String forgetuserNameHeading) {
        ForgetuserNameHeading = forgetuserNameHeading;
    }

    public String getForgetpasswordHeading() {
        return ForgetpasswordHeading;
    }

    public void setForgetpasswordHeading(String forgetpasswordHeading) {
        ForgetpasswordHeading = forgetpasswordHeading;
    }

    public String getNotaMemberHeading() {
        return NotaMemberHeading;
    }

    public void setNotaMemberHeading(String notaMemberHeading) {
        NotaMemberHeading = notaMemberHeading;
    }

    public String getSignUpHeading() {
        return SignUpHeading;
    }

    public void setSignUpHeading(String signUpHeading) {
        SignUpHeading = signUpHeading;
    }

    public String getTryAgain() {
        return tryAgain;
    }

    public void setTryAgain(String tryAgain) {
        this.tryAgain = tryAgain;
    }

    public String getUsernameHolder() {
        return usernameHolder;
    }

    public void setUsernameHolder(String usernameHolder) {
        this.usernameHolder = usernameHolder;
    }

    public String getPasswordHolder() {
        return passwordHolder;
    }

    public void setPasswordHolder(String passwordHolder) {
        this.passwordHolder = passwordHolder;
    }

    public String getClassCodeHolder() {
        return classCodeHolder;
    }

    public void setClassCodeHolder(String classCodeHolder) {
        this.classCodeHolder = classCodeHolder;
    }

    public String getLoginasHolder() {
        return loginasHolder;
    }

    public void setLoginasHolder(String loginasHolder) {
        this.loginasHolder = loginasHolder;
    }

    public List<SignInPageLoginOption> getLoginOptions() {
        return loginOptions;
    }

    public void setLoginOptions(List<SignInPageLoginOption> loginOptions) {
        this.loginOptions = loginOptions;
    }

    public String getForgetPasswordUrl() {
        return forgetPasswordUrl;
    }

    public void setForgetPasswordUrl(String forgetPasswordUrl) {
        this.forgetPasswordUrl = forgetPasswordUrl;
    }

    public String getForgetUsernameUrl() {
        return forgetUsernameUrl;
    }

    public void setForgetUsernameUrl(String forgetUsernameUrl) {
        this.forgetUsernameUrl = forgetUsernameUrl;
    }

    public String getClientsiteErrorMsgHeading() {
        return clientsiteErrorMsgHeading;
    }

    public void setClientsiteErrorMsgHeading(String clientsiteErrorMsgHeading) {
        this.clientsiteErrorMsgHeading = clientsiteErrorMsgHeading;
    }

    public String getServerErrorMsgHeading() {
        return serverErrorMsgHeading;
    }

    public void setServerErrorMsgHeading(String serverErrorMsgHeading) {
        this.serverErrorMsgHeading = serverErrorMsgHeading;
    }

    public String getClassCodeErrorMsg() {
        return classCodeErrorMsg;
    }

    public void setClassCodeErrorMsg(String classCodeErrorMsg) {
        this.classCodeErrorMsg = classCodeErrorMsg;
    }

    public String getLoginAsErrorMsg() {
        return loginAsErrorMsg;
    }

    public void setLoginAsErrorMsg(String loginAsErrorMsg) {
        this.loginAsErrorMsg = loginAsErrorMsg;
    }

    public String getUsernameErrorMsg() {
        return usernameErrorMsg;
    }

    public void setUsernameErrorMsg(String usernameErrorMsg) {
        this.usernameErrorMsg = usernameErrorMsg;
    }

    public String getPasswordErrorMsg() {
        return passwordErrorMsg;
    }

    public void setPasswordErrorMsg(String passwordErrorMsg) {
        this.passwordErrorMsg = passwordErrorMsg;
    }

    public String getOkBtnText() {
        return okBtnText;
    }

    public void setOkBtnText(String okBtnText) {
        this.okBtnText = okBtnText;
    }

    public String getCancelBtnText() {
        return cancelBtnText;
    }

    public void setCancelBtnText(String cancelBtnText) {
        this.cancelBtnText = cancelBtnText;
    }


    public String getParentQuestionText() {
        return parentQuestionText;
    }

    public List<ParentSectionDataBean> getParentSection() {
        return parentSection;
    }

    public void setParentQuestionText(String parentQuestionText) {

        this.parentQuestionText = parentQuestionText;
    }

    public String getCombineKey() {
        return combineKey;
    }

    public void setCombineKey(String combineKey) {
        this.combineKey = combineKey;
    }

    public String getParentHeading() {
        return ParentHeading;
    }

    public void setParentHeading(String parentHeading) {
        ParentHeading = parentHeading;
    }

    @SerializedName("LoginasText")
    @Expose
    String LoginasText;


    @SerializedName("LoginasHeading")
    @Expose
    String LoginasHeading;

    @SerializedName("ClasscodeHeading")
    @Expose
    String ClasscodeHeading;

    @SerializedName("PasswordHeading")
    @Expose
    String PasswordHeading;

    @SerializedName("UsernameHeading")
    @Expose
    String UsernameHeading;


    @SerializedName("AuthorizeHeading")
    @Expose
    String AuthorizeHeading;


    @SerializedName("ForgetuserNameHeading")
    @Expose
    String ForgetuserNameHeading;


    @SerializedName("ForgetpasswordHeading")
    @Expose
    String ForgetpasswordHeading;

    @SerializedName("NotaMemberHeading")
    @Expose
    String NotaMemberHeading;

    @SerializedName("SignUpHeading")
    @Expose
    String SignUpHeading;




    @SerializedName("usernameHolder")
    @Expose
    String usernameHolder;

    @SerializedName("passwordHolder")
    @Expose
    String passwordHolder;

    @SerializedName("classCodeHolder")
    @Expose
    String classCodeHolder;

    @SerializedName("loginasHolder")
    @Expose
    String loginasHolder;

    @SerializedName("loginOptions")
    @Expose
    List<SignInPageLoginOption> loginOptions;


@SerializedName("forgetPasswordUrl")
    @Expose
String forgetPasswordUrl;


@SerializedName("forgetUsernameUrl")
    @Expose
String forgetUsernameUrl;

    @SerializedName("clientsiteErrorMsgHeading")
    @Expose
String clientsiteErrorMsgHeading;

    @SerializedName("serverErrorMsgHeading")
    @Expose
String serverErrorMsgHeading;

    @SerializedName("loginAsErrorMsg")
    @Expose
String classCodeErrorMsg;

    @SerializedName("classCodeErrorMsg")
    @Expose
String loginAsErrorMsg;


    @SerializedName("usernameErrorMsg")
    @Expose
String usernameErrorMsg;

    @SerializedName("passwordErrorMsg")
    @Expose
String passwordErrorMsg;

    @SerializedName("okBtnText")
    @Expose
String okBtnText;


@SerializedName("cancelBtnText")
    @Expose
String cancelBtnText;



    @SerializedName("parentQuestionText")
    @Expose
String parentQuestionText;
    @SerializedName("combineKey")
    @Expose
String combineKey;

    @SerializedName("ParentHeading")
    @Expose
String ParentHeading;


 @SerializedName("parentSection")
    @Expose
List<ParentSectionDataBean> parentSection;


    public void setParentSection(List<ParentSectionDataBean> parentSection) {
        this.parentSection = parentSection;
    }

    @SerializedName("tryAgain")
    @Expose

String tryAgain;


    public SignInPage() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LoginasText);
        dest.writeString(this.LoginasHeading);
        dest.writeString(this.ClasscodeHeading);
        dest.writeString(this.PasswordHeading);
        dest.writeString(this.UsernameHeading);
        dest.writeString(this.AuthorizeHeading);
        dest.writeString(this.ForgetuserNameHeading);
        dest.writeString(this.ForgetpasswordHeading);
        dest.writeString(this.NotaMemberHeading);
        dest.writeString(this.SignUpHeading);
        dest.writeString(this.tryAgain);
        dest.writeString(this.usernameHolder);
        dest.writeString(this.passwordHolder);
        dest.writeString(this.classCodeHolder);
        dest.writeString(this.loginasHolder);
        dest.writeTypedList(this.loginOptions);
        dest.writeString(this.forgetPasswordUrl);
        dest.writeString(this.forgetUsernameUrl);
        dest.writeString(this.clientsiteErrorMsgHeading);
        dest.writeString(this.serverErrorMsgHeading);
        dest.writeString(this.classCodeErrorMsg);
        dest.writeString(this.loginAsErrorMsg);
        dest.writeString(this.usernameErrorMsg);
        dest.writeString(this.passwordErrorMsg);
        dest.writeString(this.okBtnText);
        dest.writeString(this.cancelBtnText);
        dest.writeString(this.parentQuestionText);
        dest.writeString(this.combineKey);
        dest.writeString(this.ParentHeading);
        dest.writeTypedList(this.parentSection);
        dest.writeString(this.tryAgain);
    }

    protected SignInPage(Parcel in) {
        this.LoginasText = in.readString();
        this.LoginasHeading = in.readString();
        this.ClasscodeHeading = in.readString();
        this.PasswordHeading = in.readString();
        this.UsernameHeading = in.readString();
        this.AuthorizeHeading = in.readString();
        this.ForgetuserNameHeading = in.readString();
        this.ForgetpasswordHeading = in.readString();
        this.NotaMemberHeading = in.readString();
        this.SignUpHeading = in.readString();
        this.tryAgain = in.readString();
        this.usernameHolder = in.readString();
        this.passwordHolder = in.readString();
        this.classCodeHolder = in.readString();
        this.loginasHolder = in.readString();
        this.loginOptions = in.createTypedArrayList(SignInPageLoginOption.CREATOR);
        this.forgetPasswordUrl = in.readString();
        this.forgetUsernameUrl = in.readString();
        this.clientsiteErrorMsgHeading = in.readString();
        this.serverErrorMsgHeading = in.readString();
        this.classCodeErrorMsg = in.readString();
        this.loginAsErrorMsg = in.readString();
        this.usernameErrorMsg = in.readString();
        this.passwordErrorMsg = in.readString();
        this.okBtnText = in.readString();
        this.cancelBtnText = in.readString();
        this.parentQuestionText = in.readString();
        this.combineKey = in.readString();
        this.ParentHeading = in.readString();
        this.parentSection = in.createTypedArrayList(ParentSectionDataBean.CREATOR);
        this.tryAgain = in.readString();
    }

    public static final Creator<SignInPage> CREATOR = new Creator<SignInPage>() {
        @Override
        public SignInPage createFromParcel(Parcel source) {
            return new SignInPage(source);
        }

        @Override
        public SignInPage[] newArray(int size) {
            return new SignInPage[size];
        }
    };
}
