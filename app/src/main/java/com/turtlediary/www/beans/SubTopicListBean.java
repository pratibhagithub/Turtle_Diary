package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 2/11/17.
 */

public class SubTopicListBean implements Parcelable {
    boolean isTopicSelected;

    public boolean isTopicSelected() {
        return isTopicSelected;
    }

    public void setTopicSelected(boolean topicSelected) {
        isTopicSelected = topicSelected;
    }

    @SerializedName("topicName")
    @Expose
    String topicName;
    @SerializedName("topicSlug")
    @Expose
    String topicSlug;
    @SerializedName("topicSound")
    @Expose
    String topicSound;
    @SerializedName("topicLabel")
    @Expose
    String topicLabel;
    @SerializedName("isBlock")
    @Expose
    String isBlock;

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
        dest.writeString(this.topicName);
        dest.writeString(this.topicSlug);
        dest.writeString(this.topicSound);
        dest.writeString(this.topicLabel);
        dest.writeString(this.isBlock);
    }

    public SubTopicListBean() {
    }

    protected SubTopicListBean(Parcel in) {
        this.topicName = in.readString();
        this.topicSlug = in.readString();
        this.topicSound = in.readString();
        this.topicLabel = in.readString();
        this.isBlock = in.readString();
    }

    public static final Parcelable.Creator<SubTopicListBean> CREATOR = new Parcelable.Creator<SubTopicListBean>() {
        @Override
        public SubTopicListBean createFromParcel(Parcel source) {
            return new SubTopicListBean(source);
        }

        @Override
        public SubTopicListBean[] newArray(int size) {
            return new SubTopicListBean[size];
        }
    };
}
