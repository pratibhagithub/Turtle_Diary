package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 14/11/17.
 */

public class ProductBean implements Parcelable {
    @SerializedName("id")
    @Expose
    String id;


    @SerializedName("product_id")
    @Expose
    String product_id;


    @SerializedName("appid")
    @Expose
    String appid;


    @SerializedName("amount")
    @Expose
    String amount;
    @SerializedName("dollarAmount")
    @Expose
    String dollarAmount;
    @SerializedName("currency_type")
    @Expose
    String currency_type;
    @SerializedName("subscription")
    @Expose
    String subscription;
    @SerializedName("default_select")
    @Expose
    String default_select;
    @SerializedName("AppName")
    @Expose
    String AppName;
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("created_at")
    @Expose
    String created_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(String dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getDefault_select() {
        return default_select;
    }

    public void setDefault_select(String default_select) {
        this.default_select = default_select;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.product_id);
        dest.writeString(this.appid);
        dest.writeString(this.amount);
        dest.writeString(this.dollarAmount);
        dest.writeString(this.currency_type);
        dest.writeString(this.subscription);
        dest.writeString(this.default_select);
        dest.writeString(this.AppName);
        dest.writeString(this.status);
        dest.writeString(this.created_at);
    }

    public ProductBean() {
    }

    protected ProductBean(Parcel in) {
        this.id = in.readString();
        this.product_id = in.readString();
        this.appid = in.readString();
        this.amount = in.readString();
        this.dollarAmount = in.readString();
        this.currency_type = in.readString();
        this.subscription = in.readString();
        this.default_select = in.readString();
        this.AppName = in.readString();
        this.status = in.readString();
        this.created_at = in.readString();
    }

    public static final Parcelable.Creator<ProductBean> CREATOR = new Parcelable.Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel source) {
            return new ProductBean(source);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };
}
