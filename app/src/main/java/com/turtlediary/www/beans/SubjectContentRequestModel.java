package com.turtlediary.www.beans;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentRequestModel {


/*
    {\n  \"slug\" : \"math\",\n
        \"appVersion\" : \"16.12\",\n
        \"isGroup\" : false,\n
        \"Envir\" : \"dev\",\n
        \"contentType\" : \"video\",\n
        \"forceupdateApp\" : 15,\n
        \"updateApp\" : 15,\n
        \"userDevice\" : \"iphone\",\n
        \"apiVersion\" : \"2.3\",\n
        \"groupSlug\" : \"\"\n}";*/


    private String slug;
    private String appVersion;
    private boolean isGroup;
    private String Envir;
    private String contentType;
    private String forceupdateApp;

    private String updateApp;
    private String userDevice;
    private String apiVersion;
    private String groupSlug;


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getEnvir() {
        return Envir;
    }

    public void setEnvir(String envir) {
        Envir = envir;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getForceupdateApp() {
        return forceupdateApp;
    }

    public void setForceupdateApp(String forceupdateApp) {
        this.forceupdateApp = forceupdateApp;
    }

    public String getUpdateApp() {
        return updateApp;
    }

    public void setUpdateApp(String updateApp) {
        this.updateApp = updateApp;
    }

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getGroupSlug() {
        return groupSlug;
    }

    public void setGroupSlug(String groupSlug) {
        this.groupSlug = groupSlug;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
