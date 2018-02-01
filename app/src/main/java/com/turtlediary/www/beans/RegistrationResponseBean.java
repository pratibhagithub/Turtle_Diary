package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 5/12/17.
 */

public class RegistrationResponseBean implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("email")
    @Expose

    private String email;


    @SerializedName("userStatus")
    @Expose
    private Boolean userStatus;


    @SerializedName("userId")
    @Expose
    private String userId;


    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("token")
    @Expose
    private String token;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
        dest.writeValue(this.userStatus);
        dest.writeString(this.userId);
        dest.writeString(this.message);
        dest.writeString(this.token);
    }

    public RegistrationResponseBean() {
    }

    protected RegistrationResponseBean(Parcel in) {
        this.status = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.email = in.readString();
        this.userStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.userId = in.readString();
        this.message = in.readString();
        this.token = in.readString();
    }

    public static final Creator<RegistrationResponseBean> CREATOR = new Creator<RegistrationResponseBean>() {
        @Override
        public RegistrationResponseBean createFromParcel(Parcel source) {
            return new RegistrationResponseBean(source);
        }

        @Override
        public RegistrationResponseBean[] newArray(int size) {
            return new RegistrationResponseBean[size];
        }
    };
}
