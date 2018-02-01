package com.turtlediary.www.httpConnection.requestModel;

/**
 * Created by pratibha on 27/10/17.
 */

public class LoginRequestModel {


    private String action, apiVersion, Envir, classcode, usertype, uId, updateApp, username, forceupdateApp;
    private String appVersion, password,language,slug,userDevice;

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getEnvir() {
        return Envir;
    }

    public void setEnvir(String envir) {
        Envir = envir;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUpdateApp() {
        return updateApp;
    }

    public void setUpdateApp(String updateApp) {
        this.updateApp = updateApp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForceupdateApp() {
        return forceupdateApp;
    }

    public void setForceupdateApp(String forceupdateApp) {
        this.forceupdateApp = forceupdateApp;
    }
}
