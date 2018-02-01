package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 12/12/17.
 */

public class PrintableCategoryBean implements Parcelable {
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
    private PrintableContentList contentList;


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

    public PrintableContentList getContentList() {
        return contentList;
    }

    public void setContentList(PrintableContentList contentList) {
        this.contentList = contentList;
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
        dest.writeParcelable(this.contentList, flags);
    }

    public PrintableCategoryBean() {
    }

    protected PrintableCategoryBean(Parcel in) {
        this.loginStatus = in.readString();
        this.userStatus = in.readString();
        this.pageHeading = in.readString();
        this.commonMsg = in.readString();
        this.contentType = in.readString();
        this.contentList = in.readParcelable(PrintableContentList.class.getClassLoader());
    }

    public static final Parcelable.Creator<PrintableCategoryBean> CREATOR = new Parcelable.Creator<PrintableCategoryBean>() {
        @Override
        public PrintableCategoryBean createFromParcel(Parcel source) {
            return new PrintableCategoryBean(source);
        }

        @Override
        public PrintableCategoryBean[] newArray(int size) {
            return new PrintableCategoryBean[size];
        }
    };
}
