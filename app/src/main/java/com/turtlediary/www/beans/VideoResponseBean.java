package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pratibha on 8/11/17.
 */

public class VideoResponseBean implements Parcelable {
    @SerializedName("content")
    @Expose
    private List<VideoContentBean> content;

    public List<VideoContentBean> getContentList() {
        return content;
    }

    public void setContentList(List<VideoContentBean> content) {
        this.content = content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.content);
    }

    public VideoResponseBean() {
    }

    protected VideoResponseBean(Parcel in) {
        this.content = in.createTypedArrayList(VideoContentBean.CREATOR);
    }

    public static final Parcelable.Creator<VideoResponseBean> CREATOR = new Parcelable.Creator<VideoResponseBean>() {
        @Override
        public VideoResponseBean createFromParcel(Parcel source) {
            return new VideoResponseBean(source);
        }

        @Override
        public VideoResponseBean[] newArray(int size) {
            return new VideoResponseBean[size];
        }
    };
}
