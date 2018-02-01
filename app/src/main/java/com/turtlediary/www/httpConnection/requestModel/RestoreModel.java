package com.turtlediary.www.httpConnection.requestModel;

/**
 * Created by pratibha on 5/12/17.
 */

public class RestoreModel {

    private String apiVersion, appVersion, forceupdateApp, updateApp, Envir, userDevice, userId, upgrade, action, regBy, username, password, receipt;
    /*  dictionary["userId"]=TDBridgingObjCSwiftData.TDuserId
    //others vars
    dictionary["upgrade"]="yes"
    dictionary["action"]="registration"
    dictionary["regBy"]="td_ios"
    //username
    dictionary["username"] = TDBridgingObjCSwiftData.TDuserName
    //password
    dictionary["password"] = TDBridgingObjCSwiftData.TDpassword
    //email
    //receipt
    dictionary["receipt"] = TDpurchase.sharedInstance.receiptData
*/

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
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

    public String getEnvir() {
        return Envir;
    }

    public void setEnvir(String envir) {
        Envir = envir;
    }

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRegBy() {
        return regBy;
    }

    public void setRegBy(String regBy) {
        this.regBy = regBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
