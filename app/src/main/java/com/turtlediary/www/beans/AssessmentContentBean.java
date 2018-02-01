package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 27/12/17.
 */

public class AssessmentContentBean implements Parcelable {
    @SerializedName("contentName")
    @Expose
    private String contentName;


    @SerializedName("contentSlug")
    @Expose
    private String contentSlug;


    @SerializedName("contentSound")
    @Expose
    private String contentSound;


    @SerializedName("contentLabel")
    @Expose
    private String contentLabel;


    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;


    @SerializedName("nextPageType")
    @Expose
    private String nextPageType;


    @SerializedName("contentImage")
    @Expose
    private String contentImage;


    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentSound() {
        return contentSound;
    }

    public void setContentSound(String contentSound) {
        this.contentSound = contentSound;
    }

    public String getContentSlug() {
        return contentSlug;
    }

    public void setContentSlug(String contentSlug) {
        this.contentSlug = contentSlug;
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

    public String getNextPageType() {
        return nextPageType;
    }

    public void setNextPageType(String nextPageType) {
        this.nextPageType = nextPageType;
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
        dest.writeString(this.contentSlug);
        dest.writeString(this.contentSound);
        dest.writeString(this.contentLabel);
        dest.writeString(this.contentUrl);
        dest.writeString(this.nextPageType);
        dest.writeString(this.contentImage);
    }

    public AssessmentContentBean() {
    }

    protected AssessmentContentBean(Parcel in) {
        this.contentName = in.readString();
        this.contentSlug = in.readString();
        this.contentSound = in.readString();
        this.contentLabel = in.readString();
        this.contentUrl = in.readString();
        this.nextPageType = in.readString();
        this.contentImage = in.readString();
    }

    public static final Creator<AssessmentContentBean> CREATOR = new Creator<AssessmentContentBean>() {
        @Override
        public AssessmentContentBean createFromParcel(Parcel source) {
            return new AssessmentContentBean(source);
        }

        @Override
        public AssessmentContentBean[] newArray(int size) {
            return new AssessmentContentBean[size];
        }
    };
}
