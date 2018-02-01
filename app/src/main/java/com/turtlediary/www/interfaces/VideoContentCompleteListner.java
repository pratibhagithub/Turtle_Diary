package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.VideoResponseBean;

/**
 * Created by pratibha on 8/11/17.
 */

public interface VideoContentCompleteListner {
    void onSucces(VideoResponseBean videoResponseBean);
    void onFailed(String msg);

}
