package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pratibha on 7/11/17.
 */

public class LessonContentBean implements Parcelable {
    @SerializedName("lessonPlan")
    @Expose
    String lessonPlan;
    @SerializedName("lessonTitle")
    @Expose
    String lessonTitle;

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonPlan() {
        return lessonPlan;
    }

    public void setLessonPlan(String lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    public LessonContentBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lessonPlan);
        dest.writeString(this.lessonTitle);
    }

    protected LessonContentBean(Parcel in) {
        this.lessonPlan = in.readString();
        this.lessonTitle = in.readString();
    }

    public static final Creator<LessonContentBean> CREATOR = new Creator<LessonContentBean>() {
        @Override
        public LessonContentBean createFromParcel(Parcel source) {
            return new LessonContentBean(source);
        }

        @Override
        public LessonContentBean[] newArray(int size) {
            return new LessonContentBean[size];
        }
    };
}
