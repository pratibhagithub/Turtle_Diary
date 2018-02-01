package com.turtlediary.www.httpConnection.requestModel;

/**
 * Created by pratibha on 13/11/17.
 */

public class RegistrationRequestModel {


     /*        action   :   checkUserStat
                apiVersion   :   2.3                 
                forceupdateApp   : 15                 
                Envir   :   dev                 
                appVersion   :   16.9                  
                password   :   123456                  
                email   :   rohit95@gmail.com                
                updateApp   : 15
                userDevice   :   ipad                  
                username   :   rohit95
                s =     appmodule.App */


    private String action, apiVersion, forceupdateApp, Envir  ;
    private String appVersion, password, email, updateApp, userDevice,username;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
