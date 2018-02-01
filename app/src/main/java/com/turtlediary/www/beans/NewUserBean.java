package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 7/11/17.
 */

public class NewUserBean implements Parcelable {
    @SerializedName("AccountInformation")
    @Expose
    String AccountInformation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AccountInformation);
    }

    public NewUserBean() {
    }

    protected NewUserBean(Parcel in) {
        this.AccountInformation = in.readString();
    }

    public static final Parcelable.Creator<NewUserBean> CREATOR = new Parcelable.Creator<NewUserBean>() {
        @Override
        public NewUserBean createFromParcel(Parcel source) {
            return new NewUserBean(source);
        }

        @Override
        public NewUserBean[] newArray(int size) {
            return new NewUserBean[size];
        }
    };
}
