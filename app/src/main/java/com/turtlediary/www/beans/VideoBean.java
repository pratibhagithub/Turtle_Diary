package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 7/11/17.
 */

public class VideoBean implements Parcelable {
    @SerializedName("video")
    @Expose
    String video;
    @SerializedName("imageName")
    @Expose
    String imageName;
    @SerializedName("slug")
    @Expose
    String slug;
    @SerializedName("videoName")
    @Expose
    String videoName;
    @SerializedName("newTag")
    @Expose
    String newTag;

    @SerializedName("isBlock")
    @Expose
    String isBlock;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public String getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(String isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.video);
        dest.writeString(this.imageName);
        dest.writeString(this.slug);
        dest.writeString(this.videoName);
        dest.writeString(this.newTag);
        dest.writeString(this.isBlock);
    }

    public VideoBean() {
    }

    protected VideoBean(Parcel in) {
        this.video = in.readString();
        this.imageName = in.readString();
        this.slug = in.readString();
        this.videoName = in.readString();
        this.newTag = in.readString();
        this.isBlock = in.readString();
    }

    public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel source) {
            return new VideoBean(source);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };
}
