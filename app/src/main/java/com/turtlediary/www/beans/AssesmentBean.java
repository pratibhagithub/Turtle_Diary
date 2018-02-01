package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pratibha on 27/12/17.
 */

public class AssesmentBean  implements Parcelable {
    @SerializedName("loginStatus")
    @Expose
    private String loginStatus;

    @SerializedName("userStatus")
    @Expose
    private String userStatus;


    @SerializedName("pageHeading")
    @Expose
    private String pageHeading;

    @SerializedName("commonMsg")
    @Expose
    private String commonMsg;

    @SerializedName("contentType")

    @Expose
    private String contentType;

    @SerializedName("contentList")
    @Expose
    private List<AssessmentContentBean> contentList;

    public List<AssessmentContentBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<AssessmentContentBean> contentList) {
        this.contentList = contentList;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPageHeading() {
        return pageHeading;
    }

    public void setPageHeading(String pageHeading) {
        this.pageHeading = pageHeading;
    }

    public String getCommonMsg() {
        return commonMsg;
    }

    public void setCommonMsg(String commonMsg) {
        this.commonMsg = commonMsg;
    }


    public AssesmentBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.loginStatus);
        dest.writeString(this.userStatus);
        dest.writeString(this.pageHeading);
        dest.writeString(this.commonMsg);
        dest.writeString(this.contentType);
        dest.writeTypedList(this.contentList);
    }

    protected AssesmentBean(Parcel in) {
        this.loginStatus = in.readString();
        this.userStatus = in.readString();
        this.pageHeading = in.readString();
        this.commonMsg = in.readString();
        this.contentType = in.readString();
        this.contentList = in.createTypedArrayList(AssessmentContentBean.CREATOR);
    }

    public static final Creator<AssesmentBean> CREATOR = new Creator<AssesmentBean>() {
        @Override
        public AssesmentBean createFromParcel(Parcel source) {
            return new AssesmentBean(source);
        }

        @Override
        public AssesmentBean[] newArray(int size) {
            return new AssesmentBean[size];
        }
    };
}