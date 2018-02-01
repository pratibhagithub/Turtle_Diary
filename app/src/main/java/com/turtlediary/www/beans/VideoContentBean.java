package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 8/11/17.
 */

public class VideoContentBean implements Parcelable {
    @SerializedName("videoTitle")
    @Expose
    String videoTitle;
    @SerializedName("videoUrl")
    @Expose
    String videoUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.videoTitle);

        dest.writeString(this.videoUrl);
    }

    public VideoContentBean() {
    }

    protected VideoContentBean(Parcel in) {
        this.videoTitle = in.readString();
        this.videoUrl = in.readString();
    }

    public static final Parcelable.Creator<VideoContentBean> CREATOR = new Parcelable.Creator<VideoContentBean>() {
        @Override
        public VideoContentBean createFromParcel(Parcel source) {
            return new VideoContentBean(source);
        }

        @Override
        public VideoContentBean[] newArray(int size) {
            return new VideoContentBean[size];
        }
    };
}
