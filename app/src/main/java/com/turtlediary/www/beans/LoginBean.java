package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 27/10/17.
 */

public class LoginBean implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("email")
    @Expose
    private String email;





    @SerializedName("userStatus")
    @Expose
    private Boolean userStatus;








    @SerializedName("token")
    @Expose
    private String token;






    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Creator<LoginBean> getCREATOR() {
        return CREATOR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.email);
        dest.writeValue(this.userStatus);
        dest.writeString(this.userId);
        dest.writeString(this.message);
        dest.writeString(this.token);
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.status = in.readString();
        this.email = in.readString();
        this.userStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.userId = in.readString();
        this.message = in.readString();
        this.token = in.readString();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
