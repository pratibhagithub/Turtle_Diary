package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 13/12/17.
 */

public class InternetErrorBean implements Parcelable {

    @SerializedName("internetErrorHeading")
    @Expose
    String internetErrorHeading;

    @SerializedName("internetError")
    @Expose
    String internetError;

    @SerializedName("loginErrorHeading")
    @Expose
    String loginErrorHeading;

    @SerializedName("loginError")
    @Expose
    String loginError;

    public String getInternetErrorHeading() {
        return internetErrorHeading;
    }

    public void setInternetErrorHeading(String internetErrorHeading) {
        this.internetErrorHeading = internetErrorHeading;
    }

    public String getInternetError() {
        return internetError;
    }

    public void setInternetError(String internetError) {
        this.internetError = internetError;
    }

    public String getLoginErrorHeading() {
        return loginErrorHeading;
    }

    public void setLoginErrorHeading(String loginErrorHeading) {
        this.loginErrorHeading = loginErrorHeading;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public String getSessionErrorHeading() {
        return SessionErrorHeading;
    }

    public void setSessionErrorHeading(String sessionErrorHeading) {
        SessionErrorHeading = sessionErrorHeading;
    }

    public String getSessionError() {
        return sessionError;
    }

    public void setSessionError(String sessionError) {
        this.sessionError = sessionError;
    }

    public String getPaidHeading() {
        return paidHeading;
    }

    public void setPaidHeading(String paidHeading) {
        this.paidHeading = paidHeading;
    }

    public String getOfflineErrorHeading() {
        return offlineErrorHeading;
    }

    public void setOfflineErrorHeading(String offlineErrorHeading) {
        this.offlineErrorHeading = offlineErrorHeading;
    }

    public String getPaidError() {
        return paidError;
    }

    public void setPaidError(String paidError) {
        this.paidError = paidError;
    }

    @SerializedName("SessionErrorHeading")
    @Expose
    String SessionErrorHeading;

    @SerializedName("sessionError")
    @Expose
    String sessionError;



    @SerializedName("paidHeading")
    @Expose
    String paidHeading;

    @SerializedName("offlineErrorHeading")
    @Expose
    String offlineErrorHeading;

    @SerializedName("paidError")
    @Expose
    String paidError;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.internetErrorHeading);
        dest.writeString(this.internetError);
        dest.writeString(this.loginErrorHeading);
        dest.writeString(this.loginError);
        dest.writeString(this.SessionErrorHeading);
        dest.writeString(this.sessionError);
        dest.writeString(this.paidHeading);
        dest.writeString(this.offlineErrorHeading);
        dest.writeString(this.paidError);
    }

    public InternetErrorBean() {
    }

    protected InternetErrorBean(Parcel in) {
        this.internetErrorHeading = in.readString();
        this.internetError = in.readString();
        this.loginErrorHeading = in.readString();
        this.loginError = in.readString();
        this.SessionErrorHeading = in.readString();
        this.sessionError = in.readString();
        this.paidHeading = in.readString();
        this.offlineErrorHeading = in.readString();
        this.paidError = in.readString();
    }

    public static final Parcelable.Creator<InternetErrorBean> CREATOR = new Parcelable.Creator<InternetErrorBean>() {
        @Override
        public InternetErrorBean createFromParcel(Parcel source) {
            return new InternetErrorBean(source);
        }

        @Override
        public InternetErrorBean[] newArray(int size) {
            return new InternetErrorBean[size];
        }
    };
}
