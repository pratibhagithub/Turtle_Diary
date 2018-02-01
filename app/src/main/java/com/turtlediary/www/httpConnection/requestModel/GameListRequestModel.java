package com.turtlediary.www.httpConnection.requestModel;

/**
 * Created by arvind on 31/10/17.
 */

public class GameListRequestModel {



    private String slug;
    private String appVersion;
    private String Envir;
    private String contentType;
    private String forceupdateApp;

    private String updateApp;
    private String userDevice;
    private String apiVersion;
    private String groupSlug;
    private boolean isGroup;

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

    public String getForceUpdateApp() {
        return forceupdateApp;
    }

    public void setForceUpdateApp(String forceUpdateApp) {
        this.forceupdateApp = forceUpdateApp;
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
