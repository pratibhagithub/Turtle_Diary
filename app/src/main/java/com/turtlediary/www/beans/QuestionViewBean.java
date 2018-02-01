package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 8/11/17.
 */

public class QuestionViewBean implements Parcelable {
    @SerializedName("heading")
    @Expose
    String heading;

    @SerializedName("sumText_1")
    @Expose
    String sumText_1;

    @SerializedName("sumText_2")
    @Expose
    String sumText_2;


    @SerializedName("answeLabel")
    @Expose
    String answeLabel;

    @SerializedName("cancelBtnLabel")
    @Expose
    String cancelBtnLabel;

    @SerializedName("enterBtnLabel")
    @Expose
    String enterBtnLabel;

    @SerializedName("wrongLabel")
    @Expose
    String wrongLabel;


    public QuestionViewBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSumText_1() {
        return sumText_1;
    }

    public void setSumText_1(String sumText_1) {
        this.sumText_1 = sumText_1;
    }

    public String getSumText_2() {
        return sumText_2;
    }

    public void setSumText_2(String sumText_2) {
        this.sumText_2 = sumText_2;
    }

    public String getAnsweLabel() {
        return answeLabel;
    }

    public void setAnsweLabel(String answeLabel) {
        this.answeLabel = answeLabel;
    }

    public String getCancelBtnLabel() {
        return cancelBtnLabel;
    }

    public void setCancelBtnLabel(String cancelBtnLabel) {
        this.cancelBtnLabel = cancelBtnLabel;
    }

    public String getEnterBtnLabel() {
        return enterBtnLabel;
    }

    public void setEnterBtnLabel(String enterBtnLabel) {
        this.enterBtnLabel = enterBtnLabel;
    }

    public String getWrongLabel() {
        return wrongLabel;
    }

    public void setWrongLabel(String wrongLabel) {
        this.wrongLabel = wrongLabel;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.heading);
        dest.writeString(this.sumText_1);
        dest.writeString(this.sumText_2);
        dest.writeString(this.answeLabel);
        dest.writeString(this.cancelBtnLabel);
        dest.writeString(this.enterBtnLabel);
        dest.writeString(this.wrongLabel);
    }

    protected QuestionViewBean(Parcel in) {
        this.heading = in.readString();
        this.sumText_1 = in.readString();
        this.sumText_2 = in.readString();
        this.answeLabel = in.readString();
        this.cancelBtnLabel = in.readString();
        this.enterBtnLabel = in.readString();
        this.wrongLabel = in.readString();
    }

    public static final Creator<QuestionViewBean> CREATOR = new Creator<QuestionViewBean>() {
        @Override
        public QuestionViewBean createFromParcel(Parcel source) {
            return new QuestionViewBean(source);
        }

        @Override
        public QuestionViewBean[] newArray(int size) {
            return new QuestionViewBean[size];
        }
    };
}
