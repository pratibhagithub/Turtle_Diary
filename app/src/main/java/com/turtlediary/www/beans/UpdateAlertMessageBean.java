package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 2/11/17.
 */

public class UpdateAlertMessageBean implements Parcelable {

    @SerializedName("UPdateMsg")
    @Expose
    private String UPdateMsg;


    @SerializedName("OkBtn")
    @Expose
    private String OkBtn;

    public String getOkBtn() {
        return OkBtn;
    }

    public void setOkBtn(String okBtn) {
        OkBtn = okBtn;
    }

    public String getCancelBtn() {
        return CancelBtn;
    }

    public void setCancelBtn(String cancelBtn) {
        CancelBtn = cancelBtn;
    }

    public String getUPdateMsg() {
        return UPdateMsg;
    }

    public void setUPdateMsg(String UPdateMsg) {
        this.UPdateMsg = UPdateMsg;
    }

    public String getUpdradeBtnText() {
        return updradeBtnText;
    }

    public void setUpdradeBtnText(String updradeBtnText) {
        this.updradeBtnText = updradeBtnText;
    }

    public String getForceUpgradeMsg() {
        return forceUpgradeMsg;
    }

    public void setForceUpgradeMsg(String forceUpgradeMsg) {
        this.forceUpgradeMsg = forceUpgradeMsg;
    }

    public String getUPdateheading() {
        return UPdateheading;
    }

    public void setUPdateheading(String UPdateheading) {
        this.UPdateheading = UPdateheading;
    }

    @SerializedName("CancelBtn")
    @Expose
    private String CancelBtn;

    @SerializedName("updradeBtnText")
    @Expose
    private String updradeBtnText;

   @SerializedName("forceUpgradeMsg")
    @Expose
    private String forceUpgradeMsg;



    @SerializedName("UPdateheading")
    @Expose
    private String UPdateheading;


    @SerializedName("bestViewText")
    @Expose
    String bestViewText;

    public String getBestViewText() {
        return bestViewText;
    }

    public void setBestViewText(String bestViewText) {
        this.bestViewText = bestViewText;
    }

    public UpdateAlertMessageBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UPdateMsg);
        dest.writeString(this.OkBtn);
        dest.writeString(this.CancelBtn);
        dest.writeString(this.updradeBtnText);
        dest.writeString(this.forceUpgradeMsg);
        dest.writeString(this.UPdateheading);
        dest.writeString(this.bestViewText);
    }

    protected UpdateAlertMessageBean(Parcel in) {
        this.UPdateMsg = in.readString();
        this.OkBtn = in.readString();
        this.CancelBtn = in.readString();
        this.updradeBtnText = in.readString();
        this.forceUpgradeMsg = in.readString();
        this.UPdateheading = in.readString();
        this.bestViewText = in.readString();
    }

    public static final Creator<UpdateAlertMessageBean> CREATOR = new Creator<UpdateAlertMessageBean>() {
        @Override
        public UpdateAlertMessageBean createFromParcel(Parcel source) {
            return new UpdateAlertMessageBean(source);
        }

        @Override
        public UpdateAlertMessageBean[] newArray(int size) {
            return new UpdateAlertMessageBean[size];
        }
    };
}
