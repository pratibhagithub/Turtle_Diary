package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.SubTopicBean;

/**
 * Created by pratibha on 2/11/17.
 */

public interface OnSubTopicCompleteListener {
    void requestSuccess(SubTopicBean topicbean);

    void requestFailed(String error);
}
