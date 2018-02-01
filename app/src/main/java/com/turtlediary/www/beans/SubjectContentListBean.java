package com.turtlediary.www.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentListBean {

    @SerializedName("loginStatus")
    @Expose
    private Boolean loginStatus;
    @SerializedName("userStatus")
    @Expose
    private Boolean userStatus;
    @SerializedName("apiVersion")
    @Expose
    private String apiVersion;
    @SerializedName("topicList")
    @Expose
    private List<SubjectContentListItemBean> topicList = null;
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

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public List<SubjectContentListItemBean> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<SubjectContentListItemBean> topicList) {
        this.topicList = topicList;
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
