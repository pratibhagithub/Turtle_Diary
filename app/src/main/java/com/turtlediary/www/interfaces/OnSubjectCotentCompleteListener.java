package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.SubjectContentListBean;

/**
 * Created by arvind on 1/11/17.
 */

public interface OnSubjectCotentCompleteListener {


    void requestSuccess(SubjectContentListBean subjectContentListBean);
    void requestFailed(String error);

}
