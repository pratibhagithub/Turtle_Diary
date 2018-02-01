package com.turtlediary.www.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pratibha on 2/11/17.
 */

public class SubTopicBean implements Parcelable {
    @SerializedName("video")
    @Expose
    List<VideoBean> video;


    @SerializedName("lesson")
    @Expose
    List<LessonBean> lesson;


    @SerializedName("practic")
    @Expose
    List<PracticBean> practic;


    @SerializedName("subtopic")
    @Expose
    List<SubTopicListBean> subtopic;


    public List<VideoBean> getVideo() {return video;}

    public void setVideo(List<VideoBean> video) {this.video = video;}

    public List<LessonBean> getLesson() {return lesson;}

    public void setLesson(List<LessonBean> lesson) {
        this.lesson = lesson;
    }

    public List<PracticBean> getPractic() {
        return practic;
    }

    public void setPractic(List<PracticBean> practic) {
        this.practic = practic;
    }

    public List<SubTopicListBean> getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(List<SubTopicListBean> subtopic) {
        this.subtopic = subtopic;
    }

    public SubTopicBean() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.video);
        dest.writeTypedList(this.lesson);
        dest.writeTypedList(this.practic);
        dest.writeTypedList(this.subtopic);
    }

    protected SubTopicBean(Parcel in) {
        this.video = in.createTypedArrayList(VideoBean.CREATOR);
        this.lesson = in.createTypedArrayList(LessonBean.CREATOR);
        this.practic = in.createTypedArrayList(PracticBean.CREATOR);
        this.subtopic = in.createTypedArrayList(SubTopicListBean.CREATOR);
    }

    public static final Creator<SubTopicBean> CREATOR = new Creator<SubTopicBean>() {
        @Override
        public SubTopicBean createFromParcel(Parcel source) {
            return new SubTopicBean(source);
        }

        @Override
        public SubTopicBean[] newArray(int size) {
            return new SubTopicBean[size];
        }
    };
}
