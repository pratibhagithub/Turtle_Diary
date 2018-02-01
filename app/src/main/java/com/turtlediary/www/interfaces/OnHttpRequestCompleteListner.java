package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.BaseBean;

/**
 * Created by pratibha on 26/10/17.
 */

public interface OnHttpRequestCompleteListner {
    void requestSuccess(BaseBean contentTypeBean);
    void requestFailed(String error);
}
