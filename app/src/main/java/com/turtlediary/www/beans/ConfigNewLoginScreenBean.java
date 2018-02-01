package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 30/10/17.
 */

public class ConfigNewLoginScreenBean implements Parcelable {

    @SerializedName("confirmPage")
    @Expose
    private ConfirmBean confirmPage;

    public ConfirmBean getConfirmPage() {
        return confirmPage;
    }

    public void setConfirmPage(ConfirmBean confirmPage) {
        this.confirmPage = confirmPage;
    }

    @SerializedName("comparePage")
    @Expose
    private ComparePageBean comparePage;

    public ComparePageBean getComparePage() {
        return comparePage;
    }

    public void setComparePage(ComparePageBean comparePage) {
        this.comparePage = comparePage;
    }

    @SerializedName("memberShipPage")
    @Expose
    private MemberShipBean memberShipPage;

    public MemberShipBean getMemberShipPage() {
        return memberShipPage;
    }

    public void setMemberShipPage(MemberShipBean memberShipPage) {
        this.memberShipPage = memberShipPage;
    }

    @SerializedName("informationHeading")
    @Expose
    private String informationHeading;

    @SerializedName("accountHeading")
    @Expose
    private String accountHeading;

    @SerializedName("SettingsHeading")
    @Expose
    private String SettingsHeading;

    public String getInformationHeading() {
        return informationHeading;
    }

    public void setInformationHeading(String informationHeading) {
        this.informationHeading = informationHeading;
    }

    public String getAccountHeading() {
        return accountHeading;
    }

    public void setAccountHeading(String accountHeading) {
        this.accountHeading = accountHeading;
    }

    public String getSettingsHeading() {
        return SettingsHeading;
    }

    public void setSettingsHeading(String settingsHeading) {
        SettingsHeading = settingsHeading;
    }

    @SerializedName("logoutHeading")

    @Expose
    private String logoutHeading;


    @SerializedName("logoutOkBtn")
    @Expose
    private String logoutOkBtn;


    @SerializedName("logoutCancelBtn")

    @Expose
    private String logoutCancelBtn;


    @SerializedName("logoutTextMsg")
    @Expose
    private String logoutTextMsg;


    @SerializedName("logOutplaceHolder")
    @Expose
    private String logOutplaceHolder;


    @SerializedName("accountPage")
    @Expose
    private AccountPage accountPage;

    @SerializedName("settingPage")
    @Expose
    private ConfigSettingBean settingPage;

    @SerializedName("informationPage")
    @Expose
    private ConfigInformationBean informationPage;


    @SerializedName("signinPage")
    @Expose
    private SignInPage signinPage;


    @SerializedName("signupPage")
    @Expose
    private SignUpPageBean signupPage;


    @SerializedName("LoginMainPage")
    @Expose
    private LoginMainPage LoginMainPage;

    public String getLogoutCancelBtn() {
        return logoutCancelBtn;
    }

    public void setLogoutCancelBtn(String logoutCancelBtn) {
        this.logoutCancelBtn = logoutCancelBtn;
    }

    public String getLogoutHeading() {
        return logoutHeading;
    }

    public void setLogoutHeading(String logoutHeading) {
        this.logoutHeading = logoutHeading;
    }

    public String getLogoutOkBtn() {
        return logoutOkBtn;
    }

    public void setLogoutOkBtn(String logoutOkBtn) {
        this.logoutOkBtn = logoutOkBtn;
    }

    public String getLogoutTextMsg() {
        return logoutTextMsg;
    }

    public void setLogoutTextMsg(String logoutTextMsg) {
        this.logoutTextMsg = logoutTextMsg;
    }

    public String getLogOutplaceHolder() {
        return logOutplaceHolder;
    }

    public void setLogOutplaceHolder(String logOutplaceHolder) {
        this.logOutplaceHolder = logOutplaceHolder;
    }

    public AccountPage getAccountPage() {
        return accountPage;
    }

    public void setAccountPage(AccountPage accountPage) {
        this.accountPage = accountPage;
    }

    public ConfigSettingBean getSettingPage() {
        return settingPage;
    }

    public void setSettingPage(ConfigSettingBean settingPage) {
        this.settingPage = settingPage;
    }


    public ConfigInformationBean getInformationPage() {
        return informationPage;
    }

    public void setInformationPage(ConfigInformationBean informationPage) {
        this.informationPage = informationPage;
    }

    public LoginMainPage getLoginMainPage() {
        return LoginMainPage;
    }

    public void setLoginMainPage(LoginMainPage loginMainPage) {
        LoginMainPage = loginMainPage;
    }

    public SignInPage getSigninPage() {
        return signinPage;
    }

    public void setSigninPage(SignInPage signinPage) {
        this.signinPage = signinPage;
    }

    public SignUpPageBean getSignupPage() {
        return signupPage;
    }

    public void setSignupPage(SignUpPageBean signupPage) {
        this.signupPage = signupPage;
    }


    public ConfigNewLoginScreenBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.confirmPage, flags);
        dest.writeParcelable(this.comparePage, flags);
        dest.writeParcelable(this.memberShipPage, flags);
        dest.writeString(this.informationHeading);
        dest.writeString(this.accountHeading);
        dest.writeString(this.SettingsHeading);
        dest.writeString(this.logoutHeading);
        dest.writeString(this.logoutOkBtn);
        dest.writeString(this.logoutCancelBtn);
        dest.writeString(this.logoutTextMsg);
        dest.writeString(this.logOutplaceHolder);
        dest.writeParcelable(this.accountPage, flags);
        dest.writeParcelable(this.settingPage, flags);
        dest.writeParcelable(this.informationPage, flags);
        dest.writeParcelable(this.signinPage, flags);
        dest.writeParcelable(this.signupPage, flags);
        dest.writeParcelable(this.LoginMainPage, flags);
    }

    protected ConfigNewLoginScreenBean(Parcel in) {
        this.confirmPage = in.readParcelable(ConfirmBean.class.getClassLoader());
        this.comparePage = in.readParcelable(ComparePageBean.class.getClassLoader());
        this.memberShipPage = in.readParcelable(MemberShipBean.class.getClassLoader());
        this.informationHeading = in.readString();
        this.accountHeading = in.readString();
        this.SettingsHeading = in.readString();
        this.logoutHeading = in.readString();
        this.logoutOkBtn = in.readString();
        this.logoutCancelBtn = in.readString();
        this.logoutTextMsg = in.readString();
        this.logOutplaceHolder = in.readString();
        this.accountPage = in.readParcelable(AccountPage.class.getClassLoader());
        this.settingPage = in.readParcelable(ConfigSettingBean.class.getClassLoader());
        this.informationPage = in.readParcelable(ConfigInformationBean.class.getClassLoader());
        this.signinPage = in.readParcelable(SignInPage.class.getClassLoader());
        this.signupPage = in.readParcelable(SignUpPageBean.class.getClassLoader());
        this.LoginMainPage = in.readParcelable(com.turtlediary.www.beans.LoginMainPage.class.getClassLoader());
    }

    public static final Creator<ConfigNewLoginScreenBean> CREATOR = new Creator<ConfigNewLoginScreenBean>() {
        @Override
        public ConfigNewLoginScreenBean createFromParcel(Parcel source) {
            return new ConfigNewLoginScreenBean(source);
        }

        @Override
        public ConfigNewLoginScreenBean[] newArray(int size) {
            return new ConfigNewLoginScreenBean[size];
        }
    };
}
