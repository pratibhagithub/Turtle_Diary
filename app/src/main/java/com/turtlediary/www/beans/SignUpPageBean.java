package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 8/11/17.
 */

public class SignUpPageBean implements Parcelable {
    @SerializedName("EmailHolder")
    @Expose
    String EmailHolder;

    @SerializedName("ChooseUsernameHolder")
    @Expose
    String ChooseUsernameHolder;

    @SerializedName("PasswordHolder")
    @Expose
    String PasswordHolder;

    @SerializedName("ConfirmPasswordHolder")
    @Expose
    String ConfirmPasswordHolder;


    @SerializedName("EmailText")
    @Expose
    String EmailText;

    @SerializedName("ChooseUsernameText")
    @Expose
    String ChooseUsernameText;

    @SerializedName("PasswordText")
    @Expose
    String PasswordText;

    public String getConfirmPasswordText() {
        return ConfirmPasswordText;
    }

    public void setConfirmPasswordText(String confirmPasswordText) {
        ConfirmPasswordText = confirmPasswordText;
    }

    public String getEmailHolder() {
        return EmailHolder;
    }

    public void setEmailHolder(String emailHolder) {
        EmailHolder = emailHolder;
    }

    public String getChooseUsernameHolder() {
        return ChooseUsernameHolder;
    }

    public void setChooseUsernameHolder(String chooseUsernameHolder) {
        ChooseUsernameHolder = chooseUsernameHolder;
    }

    public String getPasswordHolder() {
        return PasswordHolder;
    }

    public void setPasswordHolder(String passwordHolder) {
        PasswordHolder = passwordHolder;
    }

    public String getConfirmPasswordHolder() {
        return ConfirmPasswordHolder;
    }

    public void setConfirmPasswordHolder(String confirmPasswordHolder) {
        ConfirmPasswordHolder = confirmPasswordHolder;
    }

    public String getEmailText() {
        return EmailText;
    }

    public void setEmailText(String emailText) {
        EmailText = emailText;
    }

    public String getChooseUsernameText() {
        return ChooseUsernameText;
    }

    public void setChooseUsernameText(String chooseUsernameText) {
        ChooseUsernameText = chooseUsernameText;
    }

    public String getPasswordText() {
        return PasswordText;
    }

    public void setPasswordText(String passwordText) {
        PasswordText = passwordText;
    }

    public String getEmailErrorMsg() {
        return EmailErrorMsg;
    }

    public void setEmailErrorMsg(String emailErrorMsg) {
        EmailErrorMsg = emailErrorMsg;
    }

    public String getChooseUsernameErrorMsg1() {
        return ChooseUsernameErrorMsg1;
    }

    public void setChooseUsernameErrorMsg1(String chooseUsernameErrorMsg1) {
        ChooseUsernameErrorMsg1 = chooseUsernameErrorMsg1;
    }

    public String getChooseUsernameErrorMsg2() {
        return ChooseUsernameErrorMsg2;
    }

    public void setChooseUsernameErrorMsg2(String chooseUsernameErrorMsg2) {
        ChooseUsernameErrorMsg2 = chooseUsernameErrorMsg2;
    }

    public String getChooseUsernameErrorMsg3() {
        return ChooseUsernameErrorMsg3;
    }

    public void setChooseUsernameErrorMsg3(String chooseUsernameErrorMsg3) {
        ChooseUsernameErrorMsg3 = chooseUsernameErrorMsg3;
    }

    public String getPasswordErrorMsg1() {
        return PasswordErrorMsg1;
    }

    public void setPasswordErrorMsg1(String passwordErrorMsg1) {
        PasswordErrorMsg1 = passwordErrorMsg1;
    }

    public String getPasswordErrorMsg2() {
        return PasswordErrorMsg2;
    }

    public void setPasswordErrorMsg2(String passwordErrorMsg2) {
        PasswordErrorMsg2 = passwordErrorMsg2;
    }

    public String getPasswordErrorMsg3() {
        return PasswordErrorMsg3;
    }

    public void setPasswordErrorMsg3(String passwordErrorMsg3) {
        PasswordErrorMsg3 = passwordErrorMsg3;
    }

    public String getConfirmPasswordErrorMsg() {
        return ConfirmPasswordErrorMsg;
    }

    public void setConfirmPasswordErrorMsg(String confirmPasswordErrorMsg) {
        ConfirmPasswordErrorMsg = confirmPasswordErrorMsg;
    }

    public String getUserAlreayFound() {
        return userAlreayFound;
    }

    public void setUserAlreayFound(String userAlreayFound) {
        this.userAlreayFound = userAlreayFound;
    }

    public String getEmailAlreayFound() {
        return emailAlreayFound;
    }

    public void setEmailAlreayFound(String emailAlreayFound) {
        this.emailAlreayFound = emailAlreayFound;
    }

    public String getWarningMsg() {
        return warningMsg;
    }

    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg;
    }

    public String getSubmitBtn1() {
        return submitBtn1;
    }

    public void setSubmitBtn1(String submitBtn1) {
        this.submitBtn1 = submitBtn1;
    }

    public String getSubmitBtn2() {
        return submitBtn2;
    }

    public void setSubmitBtn2(String submitBtn2) {
        this.submitBtn2 = submitBtn2;
    }

    public String getEmailTextHeading() {
        return emailTextHeading;
    }

    public void setEmailTextHeading(String emailTextHeading) {
        this.emailTextHeading = emailTextHeading;
    }

    public String getUserNameHeading() {
        return userNameHeading;
    }

    public void setUserNameHeading(String userNameHeading) {
        this.userNameHeading = userNameHeading;
    }

    public String getPasswordHeading() {
        return passwordHeading;
    }

    public void setPasswordHeading(String passwordHeading) {
        this.passwordHeading = passwordHeading;
    }

    public String getRetypHeading() {
        return retypHeading;
    }

    public void setRetypHeading(String retypHeading) {
        this.retypHeading = retypHeading;
    }

    @SerializedName("ConfirmPasswordText")
    @Expose

    String ConfirmPasswordText;


    @SerializedName("EmailErrorMsg")
    @Expose
    String EmailErrorMsg;

    @SerializedName("ChooseUsernameErrorMsg1")
    @Expose
    String ChooseUsernameErrorMsg1;

    @SerializedName("ChooseUsernameErrorMsg2")
    @Expose
    String ChooseUsernameErrorMsg2;

    @SerializedName("ChooseUsernameErrorMsg3")
    @Expose
    String ChooseUsernameErrorMsg3;


    @SerializedName("PasswordErrorMsg1")
    @Expose
    String PasswordErrorMsg1;

    @SerializedName("PasswordErrorMsg2")
    @Expose
    String PasswordErrorMsg2;

    @SerializedName("PasswordErrorMsg3")
    @Expose
    String PasswordErrorMsg3;

    @SerializedName("ConfirmPasswordErrorMsg")
    @Expose
    String ConfirmPasswordErrorMsg;


    @SerializedName("userAlreayFound")
    @Expose
    String userAlreayFound;

    @SerializedName("emailAlreayFound")
    @Expose
    String emailAlreayFound;

    @SerializedName("warningMsg")
    @Expose
    String warningMsg;

    @SerializedName("submitBtn1")
    @Expose
    String submitBtn1;
    @SerializedName("submitBtn2")
    @Expose
    String submitBtn2;

    @SerializedName("emailTextHeading")
    @Expose
    String emailTextHeading;

    @SerializedName("userNameHeading")
    @Expose
    String userNameHeading;

    @SerializedName("passwordHeading")
    @Expose
    String passwordHeading;
    @SerializedName("retypHeading")
    @Expose
    String retypHeading;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.EmailHolder);
        dest.writeString(this.ChooseUsernameHolder);
        dest.writeString(this.PasswordHolder);
        dest.writeString(this.ConfirmPasswordHolder);
        dest.writeString(this.EmailText);
        dest.writeString(this.ChooseUsernameText);
        dest.writeString(this.PasswordText);
        dest.writeString(this.ConfirmPasswordText);
        dest.writeString(this.EmailErrorMsg);
        dest.writeString(this.ChooseUsernameErrorMsg1);
        dest.writeString(this.ChooseUsernameErrorMsg2);
        dest.writeString(this.ChooseUsernameErrorMsg3);
        dest.writeString(this.PasswordErrorMsg1);
        dest.writeString(this.PasswordErrorMsg2);
        dest.writeString(this.PasswordErrorMsg3);
        dest.writeString(this.ConfirmPasswordErrorMsg);
        dest.writeString(this.userAlreayFound);
        dest.writeString(this.emailAlreayFound);
        dest.writeString(this.warningMsg);
        dest.writeString(this.submitBtn1);
        dest.writeString(this.submitBtn2);
        dest.writeString(this.emailTextHeading);
        dest.writeString(this.userNameHeading);
        dest.writeString(this.passwordHeading);
        dest.writeString(this.retypHeading);
    }

    public SignUpPageBean() {
    }

    protected SignUpPageBean(Parcel in) {
        this.EmailHolder = in.readString();
        this.ChooseUsernameHolder = in.readString();
        this.PasswordHolder = in.readString();
        this.ConfirmPasswordHolder = in.readString();
        this.EmailText = in.readString();
        this.ChooseUsernameText = in.readString();
        this.PasswordText = in.readString();
        this.ConfirmPasswordText = in.readString();
        this.EmailErrorMsg = in.readString();
        this.ChooseUsernameErrorMsg1 = in.readString();
        this.ChooseUsernameErrorMsg2 = in.readString();
        this.ChooseUsernameErrorMsg3 = in.readString();
        this.PasswordErrorMsg1 = in.readString();
        this.PasswordErrorMsg2 = in.readString();
        this.PasswordErrorMsg3 = in.readString();
        this.ConfirmPasswordErrorMsg = in.readString();
        this.userAlreayFound = in.readString();
        this.emailAlreayFound = in.readString();
        this.warningMsg = in.readString();
        this.submitBtn1 = in.readString();
        this.submitBtn2 = in.readString();
        this.emailTextHeading = in.readString();
        this.userNameHeading = in.readString();
        this.passwordHeading = in.readString();
        this.retypHeading = in.readString();
    }

    public static final Parcelable.Creator<SignUpPageBean> CREATOR = new Parcelable.Creator<SignUpPageBean>() {
        @Override
        public SignUpPageBean createFromParcel(Parcel source) {
            return new SignUpPageBean(source);
        }

        @Override
        public SignUpPageBean[] newArray(int size) {
            return new SignUpPageBean[size];
        }
    };
}
