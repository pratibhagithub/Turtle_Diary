package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 30/10/17.
 */

public class ConfigLoginBean implements Parcelable {
    @SerializedName("signUpBtnTxt")
    @Expose


    private String signUpBtnTxt;


    @SerializedName("invalidText")
    @Expose
    private String invalidText;

    public String getInvalidText() {
        return invalidText;
    }

    public void setInvalidText(String invalidText) {
        this.invalidText = invalidText;
    }

    public String getSignUpBtnTxt() {
        return signUpBtnTxt;
    }

    public void setSignUpBtnTxt(String signUpBtnTxt) {
        this.signUpBtnTxt = signUpBtnTxt;
    }

    public ConfigLoginBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.signUpBtnTxt);
        dest.writeString(this.invalidText);
    }

    protected ConfigLoginBean(Parcel in) {
        this.signUpBtnTxt = in.readString();
        this.invalidText = in.readString();
    }

    public static final Creator<ConfigLoginBean> CREATOR = new Creator<ConfigLoginBean>() {
        @Override
        public ConfigLoginBean createFromParcel(Parcel source) {
            return new ConfigLoginBean(source);
        }

        @Override
        public ConfigLoginBean[] newArray(int size) {
            return new ConfigLoginBean[size];
        }
    };
}
