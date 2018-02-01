package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.AssesmentBean;

/**
 * Created by pratibha on 27/12/17.
 */

public interface AssessmentComplete {

    void requestSuccess(AssesmentBean assesmentBean);
    void requestFailed(String error);
}
