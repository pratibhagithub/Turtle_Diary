package com.turtlediary.www.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 15/11/17.
 */

public class RegistrationResultBean {
    @SerializedName("result")
    @Expose
    String result;


    @SerializedName("username")
    @Expose
    String username;


    @SerializedName("email")
    @Expose
    String email;


    @SerializedName("user_message")
    @Expose
    String user_message;

    @SerializedName("email_message")
    @Expose
    String email_message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }

    public String getEmail_message() {
        return email_message;
    }

    public void setEmail_message(String email_message) {
        this.email_message = email_message;
    }
    //    {"result":false,"username":true,"email":false,"user_message":"Username already exist"}

}
