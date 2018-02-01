package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 31/10/17.
 */

public class SignInPageParentSection implements Parcelable {

    @SerializedName("numText")
    @Expose
    String numText;

    public String getNumText() {
        return numText;
    }

    public void setNumText(String numText) {
        this.numText = numText;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @SerializedName("index")
    @Expose
    String index;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.numText);
        dest.writeString(this.index);
    }

    public SignInPageParentSection() {
    }

    protected SignInPageParentSection(Parcel in) {
        this.numText = in.readString();
        this.index = in.readString();
    }

    public static final Parcelable.Creator<SignInPageParentSection> CREATOR = new Parcelable.Creator<SignInPageParentSection>() {
        @Override
        public SignInPageParentSection createFromParcel(Parcel source) {
            return new SignInPageParentSection(source);
        }

        @Override
        public SignInPageParentSection[] newArray(int size) {
            return new SignInPageParentSection[size];
        }
    };
}
