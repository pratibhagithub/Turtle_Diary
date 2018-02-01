package com.turtlediary.www.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizBean {

    @SerializedName("loginStatus")
    @Expose
    private Boolean loginStatus;
    @SerializedName("userStatus")
    @Expose
    private Boolean userStatus;
    @SerializedName("subjectList")
    @Expose
    private QuizSubjectListBean subjectList;
    @SerializedName("commonMsg")
    @Expose
    private String commonMsg;
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("pageHeading")
    @Expose
    private String pageHeading;

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public QuizSubjectListBean getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(QuizSubjectListBean subjectList) {
        this.subjectList = subjectList;
    }

    public String getCommonMsg() {
        return commonMsg;
    }

    public void setCommonMsg(String commonMsg) {
        this.commonMsg = commonMsg;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPageHeading() {
        return pageHeading;
    }

    public void setPageHeading(String pageHeading) {
        this.pageHeading = pageHeading;
    }
}
