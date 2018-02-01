package com.turtlediary.www.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizSubjectPropertyBean {

    @SerializedName("subjectName")
    @Expose
    private String subjectName;
    @SerializedName("subjectSlug")
    @Expose
    private String subjectSlug;
    @SerializedName("subjectSound")
    @Expose
    private String subjectSound;
    @SerializedName("subjectLabel")
    @Expose
    private String subjectLabel;
    @SerializedName("subjectUrl")
    @Expose
    private String subjectUrl;
    @SerializedName("subjectImage")
    @Expose
    private String subjectImage;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectSlug() {
        return subjectSlug;
    }

    public void setSubjectSlug(String subjectSlug) {
        this.subjectSlug = subjectSlug;
    }

    public String getSubjectSound() {
        return subjectSound;
    }

    public void setSubjectSound(String subjectSound) {
        this.subjectSound = subjectSound;
    }

    public String getSubjectLabel() {
        return subjectLabel;
    }

    public void setSubjectLabel(String subjectLabel) {
        this.subjectLabel = subjectLabel;
    }

    public String getSubjectUrl() {
        return subjectUrl;
    }

    public void setSubjectUrl(String subjectUrl) {
        this.subjectUrl = subjectUrl;
    }

    public String getSubjectImage() {
        return subjectImage;
    }

    public void setSubjectImage(String subjectImage) {
        this.subjectImage = subjectImage;
    }
}
