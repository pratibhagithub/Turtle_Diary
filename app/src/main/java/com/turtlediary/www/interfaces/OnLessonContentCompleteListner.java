package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.LessonResponseBean;

/**
 * Created by pratibha on 7/11/17.
 */

public interface OnLessonContentCompleteListner {
    void onSucces(LessonResponseBean lessonResponseBean);
    void onFailed(String error);
}
