package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 14/11/17.
 */

public class ParentSectionDataBean implements Parcelable {
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

    public ParentSectionDataBean() {
    }

    protected ParentSectionDataBean(Parcel in) {
        this.numText = in.readString();
        this.index = in.readString();
    }

    public static final Parcelable.Creator<ParentSectionDataBean> CREATOR = new Parcelable.Creator<ParentSectionDataBean>() {
        @Override
        public ParentSectionDataBean createFromParcel(Parcel source) {
            return new ParentSectionDataBean(source);
        }

        @Override
        public ParentSectionDataBean[] newArray(int size) {
            return new ParentSectionDataBean[size];
        }
    };
}
