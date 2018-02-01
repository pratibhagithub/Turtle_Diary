package com.turtlediary.www.httpConnection.requestModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pratibha on 5/12/17.
 */

public class RegistrationForPaymentReceiptModel implements Parcelable {
    String regBy,username,password,email,receipt, userId,productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String action, apiVersion, forceupdateApp, Envir  ;
    private String appVersion, updateApp, userDevice;
    public String getRegBy() {
        return regBy;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.regBy);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
        dest.writeString(this.receipt);
        dest.writeString(this.userId);
        dest.writeString(this.productId);
        dest.writeString(this.action);
        dest.writeString(this.apiVersion);
        dest.writeString(this.forceupdateApp);
        dest.writeString(this.Envir);
        dest.writeString(this.appVersion);
        dest.writeString(this.updateApp);
        dest.writeString(this.userDevice);
    }

    public RegistrationForPaymentReceiptModel() {
    }

    protected RegistrationForPaymentReceiptModel(Parcel in) {
        this.regBy = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.email = in.readString();
        this.receipt = in.readString();
        this.userId = in.readString();
        this.productId = in.readString();
        this.action = in.readString();
        this.apiVersion = in.readString();
        this.forceupdateApp = in.readString();
        this.Envir = in.readString();
        this.appVersion = in.readString();
        this.updateApp = in.readString();
        this.userDevice = in.readString();
    }

    public static final Parcelable.Creator<RegistrationForPaymentReceiptModel> CREATOR = new Parcelable.Creator<RegistrationForPaymentReceiptModel>() {
        @Override
        public RegistrationForPaymentReceiptModel createFromParcel(Parcel source) {
            return new RegistrationForPaymentReceiptModel(source);
        }

        @Override
        public RegistrationForPaymentReceiptModel[] newArray(int size) {
            return new RegistrationForPaymentReceiptModel[size];
        }
    };
}
