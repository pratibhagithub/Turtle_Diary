package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 7/11/17.
 */

public class LessonResponseBean implements Parcelable {
    @SerializedName("content")
    @Expose
    private LessonContentBean content;

    public String getCssLink2() {
        return cssLink2;
    }

    public void setCssLink2(String cssLink2) {
        this.cssLink2 = cssLink2;
    }

    public String getCssLink1() {
        return cssLink1;
    }

    public void setCssLink1(String cssLink1) {
        this.cssLink1 = cssLink1;
    }

    @SerializedName("cssLink2")
    @Expose
    private String imageHost;

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    @SerializedName("imageHost")

    @Expose
    private String cssLink2;
    @SerializedName("cssLink1")
    @Expose
    private String cssLink1;

    public LessonContentBean getContent() {
        return content;
    }

    public void setContent(LessonContentBean content) {
        this.content = content;
    }

    public LessonResponseBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.content, flags);
        dest.writeString(this.imageHost);
        dest.writeString(this.cssLink2);
        dest.writeString(this.cssLink1);
    }

    protected LessonResponseBean(Parcel in) {
        this.content = in.readParcelable(LessonContentBean.class.getClassLoader());
        this.imageHost = in.readString();
        this.cssLink2 = in.readString();
        this.cssLink1 = in.readString();
    }

    public static final Creator<LessonResponseBean> CREATOR = new Creator<LessonResponseBean>() {
        @Override
        public LessonResponseBean createFromParcel(Parcel source) {
            return new LessonResponseBean(source);
        }

        @Override
        public LessonResponseBean[] newArray(int size) {
            return new LessonResponseBean[size];
        }
    };
}
