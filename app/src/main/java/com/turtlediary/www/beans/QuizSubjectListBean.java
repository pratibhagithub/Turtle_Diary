package com.turtlediary.www.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arvind on 1/11/17.
 */

public class QuizSubjectListBean {

    @SerializedName("math")
    @Expose
    private QuizSubjectPropertyBean math;
    @SerializedName("ela")
    @Expose
    private QuizSubjectPropertyBean ela;
    @SerializedName("science")
    @Expose
    private QuizSubjectPropertyBean science;

    public QuizSubjectPropertyBean getMath() {
        return math;
    }

    public void setMath(QuizSubjectPropertyBean math) {
        this.math = math;
    }

    public QuizSubjectPropertyBean getEla() {
        return ela;
    }

    public void setEla(QuizSubjectPropertyBean ela) {
        this.ela = ela;
    }

    public QuizSubjectPropertyBean getScience() {
        return science;
    }

    public void setScience(QuizSubjectPropertyBean science) {
        this.science = science;
    }
}
