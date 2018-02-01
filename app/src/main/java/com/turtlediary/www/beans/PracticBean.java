package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 3/11/17.
 */

public class PracticBean implements Parcelable {
    @SerializedName("practice")
    @Expose
    String practice;

    @SerializedName("imageName")
    @Expose
    String imageName;

    @SerializedName("slug")
    @Expose
    String slug;

    @SerializedName("webView")
    @Expose
    String webView;

    public String getWebView() {
        return webView;
    }

    public void setWebView(String webView) {
        this.webView = webView;
    }

    @SerializedName("practiceName")
    @Expose
    String practiceName;

    @SerializedName("practiceVersion")
    @Expose
    String practiceVersion;

    @SerializedName("newTag")
    @Expose
    String newTag;

    @SerializedName("isBlock")
    @Expose
    boolean isBlock;

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
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

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getPracticeVersion() {
        return practiceVersion;
    }

    public void setPracticeVersion(String practiceVersion) {
        this.practiceVersion = practiceVersion;
    }

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }


    public PracticBean() {
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.practice);
        dest.writeString(this.imageName);
        dest.writeString(this.slug);
        dest.writeString(this.webView);
        dest.writeString(this.practiceName);
        dest.writeString(this.practiceVersion);
        dest.writeString(this.newTag);
        dest.writeByte(this.isBlock ? (byte) 1 : (byte) 0);
    }

    protected PracticBean(Parcel in) {
        this.practice = in.readString();
        this.imageName = in.readString();
        this.slug = in.readString();
        this.webView = in.readString();
        this.practiceName = in.readString();
        this.practiceVersion = in.readString();
        this.newTag = in.readString();
        this.isBlock = in.readByte() != 0;
    }

    public static final Creator<PracticBean> CREATOR = new Creator<PracticBean>() {
        @Override
        public PracticBean createFromParcel(Parcel source) {
            return new PracticBean(source);
        }

        @Override
        public PracticBean[] newArray(int size) {
            return new PracticBean[size];
        }
    };
}
