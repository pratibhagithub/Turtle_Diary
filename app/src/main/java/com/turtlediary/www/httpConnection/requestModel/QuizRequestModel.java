package com.turtlediary.www.httpConnection.requestModel;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizRequestModel {


    private String slug;
    private String appVersion;
    private String contentType;
    private String forceupdateApp;
    private String Envir;
    private String updateApp;
    private String userDevice;
    private String apiVersion;

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

    public String getEnvir() {
        return Envir;
    }

    public void setEnvir(String envir) {
        Envir = envir;
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
}
