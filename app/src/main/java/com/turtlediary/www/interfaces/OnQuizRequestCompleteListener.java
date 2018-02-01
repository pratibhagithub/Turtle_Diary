package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.QuizBean;

/**
 * Created by arvind on 1/11/17.
 */

public interface OnQuizRequestCompleteListener {

    void requestSuccess(QuizBean quizBean);
    void requestFailed(String error);
}
