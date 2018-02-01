package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 27/10/17.
 */

public class ContentTypeCategoryBean implements Parcelable {
    @SerializedName("contentName")
    @Expose
    private String contentName;
    @SerializedName("contentType")
    @Expose
    private String contentType;


    @SerializedName("contentSound")
    @Expose
    private String contentSound;


    @SerializedName("contentLabel")
    @Expose
    private String contentLabel;


    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;


    @SerializedName("contentImage")
    @Expose
    private String contentImage;

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentSound() {
        return contentSound;
    }

    public void setContentSound(String contentSound) {
        this.contentSound = contentSound;
    }

    public String getContentLabel() {
        return contentLabel;
    }

    public void setContentLabel(String contentLabel) {
        this.contentLabel = contentLabel;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contentName);
        dest.writeString(this.contentType);
        dest.writeString(this.contentSound);
        dest.writeString(this.contentLabel);
        dest.writeString(this.contentUrl);
        dest.writeString(this.contentImage);
    }

    public ContentTypeCategoryBean() {
    }

    protected ContentTypeCategoryBean(Parcel in) {
        this.contentName = in.readString();
        this.contentType = in.readString();
        this.contentSound = in.readString();
        this.contentLabel = in.readString();
        this.contentUrl = in.readString();
        this.contentImage = in.readString();
    }

    public static final Creator<ContentTypeCategoryBean> CREATOR = new Creator<ContentTypeCategoryBean>() {
        @Override
        public ContentTypeCategoryBean createFromParcel(Parcel source) {
            return new ContentTypeCategoryBean(source);
        }

        @Override
        public ContentTypeCategoryBean[] newArray(int size) {
            return new ContentTypeCategoryBean[size];
        }
    };
}
