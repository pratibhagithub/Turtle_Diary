package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 15/11/17.
 */

public class ConfirmBean implements Parcelable {

    /*  "": "Account type",
              "": "Plan: ",
              "": "Number of Children",
              "": "You will be billed",
              "": "Error",
              "": "Complete Payment",
              "": "Back",
              "": "Your Subscriptions Details",
              "": "Note:- This in-app purchased renewed automatically.",
              "": "Note:- This is a ",
              "": " membership and will be renewed automatically 24 hours before expiry of current subscription.\n\n Cancel anytime by turning off automatic renewal.\n\n",
              "": "Need help? Send us an e-mail at support@turtlediary.com.",
              "": "ENTERPRISE",
              "": "FAMILY",
              "": "5",
              "": "35",
              "": "$",
              "": "Monthly",
              "": "Yearly",
              "": "By subscribing, I agree TurtleDiary Terms & Conditions and Privacy Policy.",
              "": "Terms & Conditions",
              "": "Privacy Policy",
              "": "https://www.turtlediary.com/terms-and-conditions.html?no_header=1&no_footer=2",
              "": "https://www.turtlediary.com/privacy-policy.html?no_header=1&no_footer=2",*/
    @SerializedName("TermsandConditions")
    @Expose
    String TermsandConditions;

    @SerializedName("PrivacyPolicy")
    @Expose
    String PrivacyPolicy;


    @SerializedName("ConditionsUrl")
    @Expose
    String ConditionsUrl;


    @SerializedName("PolicyUrl")
    @Expose
    String PolicyUrl;


    @SerializedName("confirmNote2")
    @Expose
    String confirmNote2;

    @SerializedName("confirmNote3")
    @Expose
    String confirmNote3;


    @SerializedName("userTypeTeacher")
    @Expose
    String userTypeTeacher;


    @SerializedName("userTypeStudent")
    @Expose
    String userTypeStudent;

    @SerializedName("PremiumMaxUser")
    @Expose
    String PremiumMaxUser;

    @SerializedName("planYearly")
    @Expose
    String planYearly;

    @SerializedName("EnterMaxUser")
    @Expose
    String EnterMaxUser;

    @SerializedName("currencySign")
    @Expose
    String currencySign;

    @SerializedName("planMonthly")
    @Expose
    String planMonthly;


    @SerializedName("WholeTremAndConditions")
    @Expose
    String WholeTremAndConditions;


    @SerializedName("TDAccounttype")
    @Expose
    String TDAccounttype;

    @SerializedName("TDPlan")
    @Expose
    String TDPlan;


    @SerializedName("Childrens")
    @Expose


    String Childrens;
    @SerializedName("TotalPrice")
    @Expose
    String TotalPrice;

    @SerializedName("ErrorHeading")
    @Expose
    String ErrorHeading;

    @SerializedName("confirmNote1")
    @Expose
    String confirmNote1;

    @SerializedName("BackBtnLabel")
    @Expose
    String BackBtnLabel;

    @SerializedName("continueBtn")
    @Expose
    String continueBtn;
    @SerializedName("freeTrialMsgDetails")
    @Expose
    String freeTrialMsgDetails;

    public String getFreeTrialMsgDetails() {
        return freeTrialMsgDetails;
    }

    public void setFreeTrialMsgDetails(String freeTrialMsgDetails) {
        this.freeTrialMsgDetails = freeTrialMsgDetails;
    }

    public String getTermsandConditions() {
        return TermsandConditions;
    }

    public void setTermsandConditions(String termsandConditions) {
        TermsandConditions = termsandConditions;
    }

    public String getPrivacyPolicy() {
        return PrivacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        PrivacyPolicy = privacyPolicy;
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

    public String getConfirmNote2() {
        return confirmNote2;
    }

    public void setConfirmNote2(String confirmNote2) {
        this.confirmNote2 = confirmNote2;
    }

    public String getConfirmNote3() {
        return confirmNote3;
    }

    public void setConfirmNote3(String confirmNote3) {
        this.confirmNote3 = confirmNote3;
    }

    public String getUserTypeTeacher() {
        return userTypeTeacher;
    }

    public void setUserTypeTeacher(String userTypeTeacher) {
        this.userTypeTeacher = userTypeTeacher;
    }

    public String getUserTypeStudent() {
        return userTypeStudent;
    }

    public void setUserTypeStudent(String userTypeStudent) {
        this.userTypeStudent = userTypeStudent;
    }

    public String getPremiumMaxUser() {
        return PremiumMaxUser;
    }

    public void setPremiumMaxUser(String premiumMaxUser) {
        PremiumMaxUser = premiumMaxUser;
    }

    public String getPlanYearly() {
        return planYearly;
    }

    public void setPlanYearly(String planYearly) {
        this.planYearly = planYearly;
    }

    public String getEnterMaxUser() {
        return EnterMaxUser;
    }

    public void setEnterMaxUser(String enterMaxUser) {
        EnterMaxUser = enterMaxUser;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    public String getPlanMonthly() {
        return planMonthly;
    }

    public void setPlanMonthly(String planMonthly) {
        this.planMonthly = planMonthly;
    }

    public String getWholeTremAndConditions() {
        return WholeTremAndConditions;
    }

    public void setWholeTremAndConditions(String wholeTremAndConditions) {
        WholeTremAndConditions = wholeTremAndConditions;
    }

    public String getTDAccounttype() {
        return TDAccounttype;
    }

    public void setTDAccounttype(String TDAccounttype) {
        this.TDAccounttype = TDAccounttype;
    }

    public String getTDPlan() {
        return TDPlan;
    }

    public void setTDPlan(String TDPlan) {
        this.TDPlan = TDPlan;
    }

    public String getChildrens() {
        return Childrens;
    }

    public void setChildrens(String childrens) {
        Childrens = childrens;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getErrorHeading() {
        return ErrorHeading;
    }

    public void setErrorHeading(String errorHeading) {
        ErrorHeading = errorHeading;
    }

    public String getConfirmNote1() {
        return confirmNote1;
    }

    public void setConfirmNote1(String confirmNote1) {
        this.confirmNote1 = confirmNote1;
    }

    public String getBackBtnLabel() {
        return BackBtnLabel;
    }

    public void setBackBtnLabel(String backBtnLabel) {
        BackBtnLabel = backBtnLabel;
    }

    public String getContinueBtn() {
        return continueBtn;
    }

    public void setContinueBtn(String continueBtn) {
        this.continueBtn = continueBtn;
    }

    public String getPurchaseLabel() {
        return purchaseLabel;
    }

    public void setPurchaseLabel(String purchaseLabel) {
        this.purchaseLabel = purchaseLabel;
    }

    public String getConfirmNote() {
        return confirmNote;
    }

    public void setConfirmNote(String confirmNote) {
        this.confirmNote = confirmNote;
    }

    @SerializedName("purchaseLabel")
    @Expose
    String purchaseLabel;
    @SerializedName("confirmNote")
    @Expose
    String confirmNote;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TermsandConditions);
        dest.writeString(this.PrivacyPolicy);
        dest.writeString(this.ConditionsUrl);
        dest.writeString(this.PolicyUrl);
        dest.writeString(this.confirmNote2);
        dest.writeString(this.confirmNote3);
        dest.writeString(this.userTypeTeacher);
        dest.writeString(this.userTypeStudent);
        dest.writeString(this.PremiumMaxUser);
        dest.writeString(this.planYearly);
        dest.writeString(this.EnterMaxUser);
        dest.writeString(this.currencySign);
        dest.writeString(this.planMonthly);
        dest.writeString(this.WholeTremAndConditions);
        dest.writeString(this.TDAccounttype);
        dest.writeString(this.TDPlan);
        dest.writeString(this.Childrens);
        dest.writeString(this.TotalPrice);
        dest.writeString(this.ErrorHeading);
        dest.writeString(this.confirmNote1);
        dest.writeString(this.BackBtnLabel);
        dest.writeString(this.continueBtn);
        dest.writeString(this.purchaseLabel);
        dest.writeString(this.confirmNote);
    }

    public ConfirmBean() {
    }

    protected ConfirmBean(Parcel in) {
        this.TermsandConditions = in.readString();
        this.PrivacyPolicy = in.readString();
        this.ConditionsUrl = in.readString();
        this.PolicyUrl = in.readString();
        this.confirmNote2 = in.readString();
        this.confirmNote3 = in.readString();
        this.userTypeTeacher = in.readString();
        this.userTypeStudent = in.readString();
        this.PremiumMaxUser = in.readString();
        this.planYearly = in.readString();
        this.EnterMaxUser = in.readString();
        this.currencySign = in.readString();
        this.planMonthly = in.readString();
        this.WholeTremAndConditions = in.readString();
        this.TDAccounttype = in.readString();
        this.TDPlan = in.readString();
        this.Childrens = in.readString();
        this.TotalPrice = in.readString();
        this.ErrorHeading = in.readString();
        this.confirmNote1 = in.readString();
        this.BackBtnLabel = in.readString();
        this.continueBtn = in.readString();
        this.purchaseLabel = in.readString();
        this.confirmNote = in.readString();
    }

    public static final Parcelable.Creator<ConfirmBean> CREATOR = new Parcelable.Creator<ConfirmBean>() {
        @Override
        public ConfirmBean createFromParcel(Parcel source) {
            return new ConfirmBean(source);
        }

        @Override
        public ConfirmBean[] newArray(int size) {
            return new ConfirmBean[size];
        }
    };
}
