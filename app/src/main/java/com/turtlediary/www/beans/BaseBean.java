package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 27/10/17.
 */

public class BaseBean implements Parcelable {

    public boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    @SerializedName("updateApp")
    @Expose
    private boolean updateApp;

    @SerializedName("forceupdateApp")
    @Expose
    private boolean forceupdateApp;


    public boolean isUpdateApp() {
        return updateApp;
    }

    public void setUpdateApp(boolean updateApp) {
        this.updateApp = updateApp;
    }

    public boolean isForceupdateApp() {
        return forceupdateApp;
    }

    public void setForceupdateApp(boolean forceupdateApp) {
        this.forceupdateApp = forceupdateApp;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public boolean isTrialPeriod() {
        return isTrialPeriod;
    }

    public void setTrialPeriod(boolean trialPeriod) {
        isTrialPeriod = trialPeriod;
    }

    @SerializedName("userStatus")
    @Expose

    private boolean userStatus;



    @SerializedName("isTrialPeriod")
    @Expose
    private boolean isTrialPeriod;


    @SerializedName("config")
    @Expose
    private ConfigBean config;

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }


    @SerializedName("ContentType")
    @Expose
    private ContentTypeBean contentType;

    public ContentTypeBean getContentType() {
        return contentType;
    }

    public void setContentType(ContentTypeBean contentType) {
        this.contentType = contentType;
    }

    public BaseBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.updateApp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.forceupdateApp ? (byte) 1 : (byte) 0);
        dest.writeByte(this.userStatus ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTrialPeriod ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.config, flags);
        dest.writeParcelable(this.contentType, flags);
    }

    protected BaseBean(Parcel in) {
        this.updateApp = in.readByte() != 0;
        this.forceupdateApp = in.readByte() != 0;
        this.userStatus = in.readByte() != 0;
        this.isTrialPeriod = in.readByte() != 0;
        this.config = in.readParcelable(ConfigBean.class.getClassLoader());
        this.contentType = in.readParcelable(ContentTypeBean.class.getClassLoader());
    }

    public static final Creator<BaseBean> CREATOR = new Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };
}
