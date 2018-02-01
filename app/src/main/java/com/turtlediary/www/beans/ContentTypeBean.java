package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarun on 27/10/17.
 */

public class ContentTypeBean implements Parcelable {
    @SerializedName("game")
    @Expose
    private ContentTypeCategoryBean game;

    @SerializedName("video")
    @Expose
    private ContentTypeCategoryBean video;

    @SerializedName("quiz")
    @Expose
    private ContentTypeCategoryBean quiz;

    @SerializedName("lesson")
    @Expose private ContentTypeCategoryBean lesson;


    @SerializedName("printable")
    @Expose
    private ContentTypeCategoryBean printable;



    @SerializedName("assessment-tests")
    @Expose
    private ContentTypeCategoryBean assessment_test;



    public ContentTypeCategoryBean getGame() {
        return game;
    }

    public void setGame(ContentTypeCategoryBean game) {
        this.game = game;
    }

    public ContentTypeCategoryBean getVideo() {
        return video;
    }

    public void setVideo(ContentTypeCategoryBean video) {
        this.video = video;
    }

    public ContentTypeCategoryBean getQuiz() {
        return quiz;
    }

    public void setQuiz(ContentTypeCategoryBean quiz) {
        this.quiz = quiz;
    }

    public ContentTypeCategoryBean getLesson() {
        return lesson;
    }

    public void setLesson(ContentTypeCategoryBean lesson) {
        this.lesson = lesson;
    }

    public ContentTypeCategoryBean getPrintable() {
        return printable;
    }

    public void setPrintable(ContentTypeCategoryBean printable) {
        this.printable = printable;
    }

    public ContentTypeCategoryBean getAssessment_test() {
        return assessment_test;
    }

    public void setAssessment_test(ContentTypeCategoryBean assessment_test) {
        this.assessment_test = assessment_test;
    }

    public ContentTypeBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.game, flags);
        dest.writeParcelable(this.video, flags);
        dest.writeParcelable(this.quiz, flags);
        dest.writeParcelable(this.lesson, flags);
        dest.writeParcelable(this.printable, flags);
        dest.writeParcelable(this.assessment_test, flags);
    }

    protected ContentTypeBean(Parcel in) {
        this.game = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
        this.video = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
        this.quiz = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
        this.lesson = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
        this.printable = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
        this.assessment_test = in.readParcelable(ContentTypeCategoryBean.class.getClassLoader());
    }

    public static final Creator<ContentTypeBean> CREATOR = new Creator<ContentTypeBean>() {
        @Override
        public ContentTypeBean createFromParcel(Parcel source) {
            return new ContentTypeBean(source);
        }

        @Override
        public ContentTypeBean[] newArray(int size) {
            return new ContentTypeBean[size];
        }
    };
}
