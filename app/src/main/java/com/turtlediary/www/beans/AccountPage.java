package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 31/10/17.
 */

public class AccountPage implements Parcelable {
    @SerializedName("DescTxt")
    @Expose
    String DescTxt;

    public String getDescTxt() {
        return DescTxt;
    }

    public void setDescTxt(String descTxt) {
        DescTxt = descTxt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DescTxt);
    }

    public AccountPage() {
    }

    protected AccountPage(Parcel in) {
        this.DescTxt = in.readString();
    }

    public static final Parcelable.Creator<AccountPage> CREATOR = new Parcelable.Creator<AccountPage>() {
        @Override
        public AccountPage createFromParcel(Parcel source) {
            return new AccountPage(source);
        }

        @Override
        public AccountPage[] newArray(int size) {
            return new AccountPage[size];
        }
    };
}
