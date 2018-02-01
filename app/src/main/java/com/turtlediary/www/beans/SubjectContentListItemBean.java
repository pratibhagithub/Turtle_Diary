package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arvind on 1/11/17.
 */

public class SubjectContentListItemBean implements Parcelable {

    @SerializedName("topicName")
    @Expose
    private String topicName;
    @SerializedName("topicSlug")
    @Expose
    private String topicSlug;
    @SerializedName("topicImage")
    @Expose
    private String topicImage;
    @SerializedName("topicSound")
    @Expose
    private String topicSound;
    @SerializedName("topicLabel")
    @Expose
    private String topicLabel;
    @SerializedName("isBlock")
    @Expose
    private Integer isBlock;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicSlug() {
        return topicSlug;
    }

    public void setTopicSlug(String topicSlug) {
        this.topicSlug = topicSlug;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

    public String getTopicSound() {
        return topicSound;
    }

    public void setTopicSound(String topicSound) {
        this.topicSound = topicSound;
    }

    public String getTopicLabel() {
        return topicLabel;
    }

    public void setTopicLabel(String topicLabel) {
        this.topicLabel = topicLabel;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.topicName);
        dest.writeString(this.topicSlug);
        dest.writeString(this.topicImage);
        dest.writeString(this.topicSound);
        dest.writeString(this.topicLabel);
        dest.writeValue(this.isBlock);
    }

    public SubjectContentListItemBean() {
    }

    protected SubjectContentListItemBean(Parcel in) {
        this.topicName = in.readString();
        this.topicSlug = in.readString();
        this.topicImage = in.readString();
        this.topicSound = in.readString();
        this.topicLabel = in.readString();
        this.isBlock = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<SubjectContentListItemBean> CREATOR = new Parcelable.Creator<SubjectContentListItemBean>() {
        @Override
        public SubjectContentListItemBean createFromParcel(Parcel source) {
            return new SubjectContentListItemBean(source);
        }

        @Override
        public SubjectContentListItemBean[] newArray(int size) {
            return new SubjectContentListItemBean[size];
        }
    };
}
