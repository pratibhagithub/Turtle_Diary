package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 14/11/17.
 */

public class ComparePageBean implements Parcelable {
    @SerializedName("btnA")
    @Expose
    String btnA;

    @SerializedName("btnB")
    @Expose
    String btnB;


    @SerializedName("btnC")
    @Expose
    String btnC;

    public String getBtnA() {
        return btnA;
    }

    public void setBtnA(String btnA) {
        this.btnA = btnA;
    }

    public String getBtnB() {
        return btnB;
    }

    public void setBtnB(String btnB) {
        this.btnB = btnB;
    }

    public String getBtnC() {
        return btnC;
    }

    public void setBtnC(String btnC) {
        this.btnC = btnC;
    }

    public String getCompareLabel() {
        return compareLabel;
    }

    public void setCompareLabel(String compareLabel) {
        this.compareLabel = compareLabel;
    }

    public String getLoginoptionscreen() {
        return loginoptionscreen;
    }

    public void setLoginoptionscreen(String loginoptionscreen) {
        this.loginoptionscreen = loginoptionscreen;
    }

    public String getConditions() {
        return Conditions;
    }

    public void setConditions(String conditions) {
        Conditions = conditions;
    }

    public String getPolicy() {
        return Policy;
    }

    public void setPolicy(String policy) {
        Policy = policy;
    }

    public String getConditionsUrl() {
        return ConditionsUrl;
    }

    public void setConditionsUrl(String conditionsUrl) {
        ConditionsUrl = conditionsUrl;
    }

    public String getPolicyUrl() {
        return PolicyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        PolicyUrl = policyUrl;
    }

    public static Creator<ComparePageBean> getCREATOR() {
        return CREATOR;
    }

    @SerializedName("compareLabel")
    @Expose
    String compareLabel;


    @SerializedName("loginoptionscreen")
    @Expose
    String loginoptionscreen;


    @SerializedName("Conditions")
    @Expose
    String Conditions;

    @SerializedName("Policy")
    @Expose
    String Policy;


    @SerializedName("ConditionsUrl")
    @Expose
    String ConditionsUrl;


    @SerializedName("PolicyUrl")
    @Expose
    String PolicyUrl;


    public ComparePageBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.btnA);
        dest.writeString(this.btnB);
        dest.writeString(this.btnC);
        dest.writeString(this.compareLabel);
        dest.writeString(this.loginoptionscreen);
        dest.writeString(this.Conditions);
        dest.writeString(this.Policy);
        dest.writeString(this.ConditionsUrl);
        dest.writeString(this.PolicyUrl);
    }

    protected ComparePageBean(Parcel in) {
        this.btnA = in.readString();
        this.btnB = in.readString();
        this.btnC = in.readString();
        this.compareLabel = in.readString();
        this.loginoptionscreen = in.readString();
        this.Conditions = in.readString();
        this.Policy = in.readString();
        this.ConditionsUrl = in.readString();
        this.PolicyUrl = in.readString();
    }

    public static final Creator<ComparePageBean> CREATOR = new Creator<ComparePageBean>() {
        @Override
        public ComparePageBean createFromParcel(Parcel source) {
            return new ComparePageBean(source);
        }

        @Override
        public ComparePageBean[] newArray(int size) {
            return new ComparePageBean[size];
        }
    };
}
