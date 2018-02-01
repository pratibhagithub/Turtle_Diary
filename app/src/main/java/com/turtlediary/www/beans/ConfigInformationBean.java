package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 30/10/17.
 */

public class ConfigInformationBean implements Parcelable {
    @SerializedName("InfoHeadingLabel")
    @Expose
    private String InfoHeadingLabel;


    @SerializedName("LeftHeading")
    @Expose
    private String LeftHeading;


    @SerializedName("leftDesc")
    @Expose
    private String leftDesc;


    @SerializedName("rightLabel")
    @Expose
    private  String rightLabel;


    @SerializedName("rightDesc")
    @Expose
    private  String rightDesc;


    @SerializedName("appVersion")
    @Expose
    private  String appVersion;

    public String getInfoHeadingLabel() {
        return InfoHeadingLabel;
    }

    public void setInfoHeadingLabel(String infoHeadingLabel) {
        InfoHeadingLabel = infoHeadingLabel;
    }

    public String getLeftHeading() {
        return LeftHeading;
    }

    public void setLeftHeading(String leftHeading) {
        LeftHeading = leftHeading;
    }

    public String getLeftDesc() {
        return leftDesc;
    }

    public void setLeftDesc(String leftDesc) {
        this.leftDesc = leftDesc;
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
    }

    public String getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public ConfigInformationBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.InfoHeadingLabel);
        dest.writeString(this.LeftHeading);
        dest.writeString(this.leftDesc);
        dest.writeString(this.rightLabel);
        dest.writeString(this.rightDesc);
        dest.writeString(this.appVersion);
    }

    protected ConfigInformationBean(Parcel in) {
        this.InfoHeadingLabel = in.readString();
        this.LeftHeading = in.readString();
        this.leftDesc = in.readString();
        this.rightLabel = in.readString();
        this.rightDesc = in.readString();
        this.appVersion = in.readString();
    }

    public static final Creator<ConfigInformationBean> CREATOR = new Creator<ConfigInformationBean>() {
        @Override
        public ConfigInformationBean createFromParcel(Parcel source) {
            return new ConfigInformationBean(source);
        }

        @Override
        public ConfigInformationBean[] newArray(int size) {
            return new ConfigInformationBean[size];
        }
    };
}
