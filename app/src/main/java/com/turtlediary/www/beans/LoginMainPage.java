package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 31/10/17.
 */

public class LoginMainPage implements Parcelable {
    @SerializedName("informationHeading")
    @Expose
    String informationHeading;

    @SerializedName("accountHeading")
    @Expose
    String accountHeading;

    @SerializedName("SettingsHeading")
    @Expose
    String SettingsHeading;

    @SerializedName("SignInHeading")
    @Expose
    String SignInHeading;

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

    public String getSignInHeading() {
        return SignInHeading;
    }

    public void setSignInHeading(String signInHeading) {
        SignInHeading = signInHeading;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.informationHeading);
        dest.writeString(this.accountHeading);
        dest.writeString(this.SettingsHeading);
        dest.writeString(this.SignInHeading);
    }

    public LoginMainPage() {
    }

    protected LoginMainPage(Parcel in) {
        this.informationHeading = in.readString();
        this.accountHeading = in.readString();
        this.SettingsHeading = in.readString();
        this.SignInHeading = in.readString();
    }

    public static final Parcelable.Creator<LoginMainPage> CREATOR = new Parcelable.Creator<LoginMainPage>() {
        @Override
        public LoginMainPage createFromParcel(Parcel source) {
            return new LoginMainPage(source);
        }

        @Override
        public LoginMainPage[] newArray(int size) {
            return new LoginMainPage[size];
        }
    };
}
