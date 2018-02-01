package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 7/11/17.
 */

public class LessonBean implements Parcelable {
    @SerializedName("lesson")
    @Expose
    String lesson;
    @SerializedName("imageName")
    @Expose
    String imageName;
    @SerializedName("slug")
    @Expose
    String slug;
    @SerializedName("lessonName")
    @Expose
    String lessonName;
    @SerializedName("newTag")
    @Expose
    String newTag;

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
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

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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

    @SerializedName("isBlock")
    @Expose
    String isBlock;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lesson);
        dest.writeString(this.imageName);
        dest.writeString(this.slug);
        dest.writeString(this.lessonName);
        dest.writeString(this.newTag);
        dest.writeString(this.isBlock);
    }

    public LessonBean() {
    }

    protected LessonBean(Parcel in) {
        this.lesson = in.readString();
        this.imageName = in.readString();
        this.slug = in.readString();
        this.lessonName = in.readString();
        this.newTag = in.readString();
        this.isBlock = in.readString();
    }

    public static final Parcelable.Creator<LessonBean> CREATOR = new Parcelable.Creator<LessonBean>() {
        @Override
        public LessonBean createFromParcel(Parcel source) {
            return new LessonBean(source);
        }

        @Override
        public LessonBean[] newArray(int size) {
            return new LessonBean[size];
        }
    };
}
