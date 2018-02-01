package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 30/10/17.
 */

public class ConfigBean implements Parcelable {

    @SerializedName("internetAlert")
    @Expose
    private InternetErrorBean internetAlert;

    public InternetErrorBean getInternetAlert() {
        return internetAlert;
    }

    public void setInternetAlert(InternetErrorBean internetAlert) {
        this.internetAlert = internetAlert;
    }

    @SerializedName("questionView")
    @Expose

    private QuestionViewBean questionView;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @SerializedName("baseUrl")
    @Expose
    private String baseUrl;

    public QuestionViewBean getQuestionView() {
        return questionView;
    }

    public void setQuestionView(QuestionViewBean questionView) {
        this.questionView = questionView;
    }

    @SerializedName("updateAlertMsg")
    @Expose
    private UpdateAlertMessageBean updateAlertMsg;

    public UpdateAlertMessageBean getUpdateAlertMsg() {
        return updateAlertMsg;
    }

    public void setUpdateAlertMsg(UpdateAlertMessageBean updateAlertMsg) {
        this.updateAlertMsg = updateAlertMsg;
    }

    @SerializedName("TDupgradeMsg")
    @Expose
    private String TDupgradeMsg;

    public String getTDupgradeMsg() {
        return TDupgradeMsg;
    }

    public void setTDupgradeMsg(String TDupgradeMsg) {
        this.TDupgradeMsg = TDupgradeMsg;
    }

    @SerializedName("appUpdatedbtnText")
    @Expose
    private String appUpdatedbtnText;
    @SerializedName("upgradeMsg")
    @Expose
    private String upgradeMsg;

    public String getUpgradeMsg() {
        return upgradeMsg;
    }

    public void setUpgradeMsg(String upgradeMsg) {
        this.upgradeMsg = upgradeMsg;
    }

    public String getTDloginBtnMsg() {
        return TDloginBtnMsg;
    }

    public void setTDloginBtnMsg(String TDloginBtnMsg) {
        this.TDloginBtnMsg = TDloginBtnMsg;
    }

    public String getAppUpdatedbtnText() {
        return appUpdatedbtnText;
    }

    public void setAppUpdatedbtnText(String appUpdatedbtnText) {
        this.appUpdatedbtnText = appUpdatedbtnText;
    }

    public static Creator<ConfigBean> getCREATOR() {
        return CREATOR;
    }

    @SerializedName("TDloginBtnMsg")
    @Expose
    private String TDloginBtnMsg;

    @SerializedName("newUser")
    @Expose
    private NewUserBean newUser;


    public NewUserBean getNewUser() {
        return newUser;
    }

    public void setNewUser(NewUserBean newUser) {
        this.newUser = newUser;
    }

    @SerializedName("NewLoginScreen")
    @Expose
    private ConfigNewLoginScreenBean NewLoginScreen;

    public ConfigNewLoginScreenBean getNewLoginScreen() {
        return NewLoginScreen;
    }

    public void setNewLoginScreen(ConfigNewLoginScreenBean newLoginScreen) {
        NewLoginScreen = newLoginScreen;
    }

    public String getTDregisterMsgNav() {
        return TDregisterMsgNav;
    }

    public void setTDregisterMsgNav(String TDregisterMsgNav) {
        this.TDregisterMsgNav = TDregisterMsgNav;
    }

    public String getTDupgradeMsgNav() {
        return TDupgradeMsgNav;
    }

    public void setTDupgradeMsgNav(String TDupgradeMsgNav) {
        this.TDupgradeMsgNav = TDupgradeMsgNav;
    }

    @SerializedName("TDregisterMsgNav")
    @Expose
    private String TDregisterMsgNav;

    @SerializedName("TDupgradeMsgNav")
    @Expose
    private String TDupgradeMsgNav;

    @SerializedName("login")
    @Expose
    private ConfigLoginBean login;

    public ConfigLoginBean getLogin() {
        return login;
    }

    public void setLogin(ConfigLoginBean login) {
        this.login = login;
    }

    public ConfigBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.internetAlert, flags);
        dest.writeParcelable(this.questionView, flags);
        dest.writeString(this.baseUrl);
        dest.writeParcelable(this.updateAlertMsg, flags);
        dest.writeString(this.TDupgradeMsg);
        dest.writeString(this.appUpdatedbtnText);
        dest.writeString(this.upgradeMsg);
        dest.writeString(this.TDloginBtnMsg);
        dest.writeParcelable(this.newUser, flags);
        dest.writeParcelable(this.NewLoginScreen, flags);
        dest.writeString(this.TDregisterMsgNav);
        dest.writeString(this.TDupgradeMsgNav);
        dest.writeParcelable(this.login, flags);
    }

    protected ConfigBean(Parcel in) {
        this.internetAlert = in.readParcelable(InternetErrorBean.class.getClassLoader());
        this.questionView = in.readParcelable(QuestionViewBean.class.getClassLoader());
        this.baseUrl = in.readString();
        this.updateAlertMsg = in.readParcelable(UpdateAlertMessageBean.class.getClassLoader());
        this.TDupgradeMsg = in.readString();
        this.appUpdatedbtnText = in.readString();
        this.upgradeMsg = in.readString();
        this.TDloginBtnMsg = in.readString();
        this.newUser = in.readParcelable(NewUserBean.class.getClassLoader());
        this.NewLoginScreen = in.readParcelable(ConfigNewLoginScreenBean.class.getClassLoader());
        this.TDregisterMsgNav = in.readString();
        this.TDupgradeMsgNav = in.readString();
        this.login = in.readParcelable(ConfigLoginBean.class.getClassLoader());
    }

    public static final Creator<ConfigBean> CREATOR = new Creator<ConfigBean>() {
        @Override
        public ConfigBean createFromParcel(Parcel source) {
            return new ConfigBean(source);
        }

        @Override
        public ConfigBean[] newArray(int size) {
            return new ConfigBean[size];
        }
    };
}
