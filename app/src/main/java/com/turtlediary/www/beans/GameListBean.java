 package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arvind on 31/10/17.
 */

public class GameListBean implements Parcelable{

    @SerializedName("loginStatus")
    @Expose
    private Boolean loginStatus;
    @SerializedName("userStatus")
    @Expose
    private Boolean userStatus;
    @SerializedName("apiVersion")
    @Expose
    private String apiVersion;
    @SerializedName("game")
    @Expose
    private List<GameListModel> game;

    protected GameListBean(Parcel in) {
        apiVersion = in.readString();
    }

    public static final Creator<GameListBean> CREATOR = new Creator<GameListBean>() {
        @Override
        public GameListBean createFromParcel(Parcel in) {
            return new GameListBean(in);
        }

        @Override
        public GameListBean[] newArray(int size) {
            return new GameListBean[size];
        }
    };

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public List<GameListModel> getGame() {
        return game;
    }

    public void setGame(List<GameListModel> game) {
        this.game = game;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(apiVersion);
    }
}
