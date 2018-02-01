package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 31/10/17.
 */

public class SignInPageLoginOption implements Parcelable {

    @SerializedName("name")
    @Expose
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOpenIndex() {
        return openIndex;
    }

    public void setOpenIndex(String openIndex) {
        this.openIndex = openIndex;
    }

    @SerializedName("value")

    @Expose
    String value;

    @SerializedName("openIndex")
    @Expose
    String openIndex;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
        dest.writeString(this.openIndex);
    }

    public SignInPageLoginOption() {
    }

    protected SignInPageLoginOption(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
        this.openIndex = in.readString();
    }

    public static final Parcelable.Creator<SignInPageLoginOption> CREATOR = new Parcelable.Creator<SignInPageLoginOption>() {
        @Override
        public SignInPageLoginOption createFromParcel(Parcel source) {
            return new SignInPageLoginOption(source);
        }

        @Override
        public SignInPageLoginOption[] newArray(int size) {
            return new SignInPageLoginOption[size];
        }
    };
}
