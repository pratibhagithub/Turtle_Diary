package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.PrintableCategoryBean;

/**
 * Created by pratibha on 12/12/17.
 */

public interface OnPrintableRequestCompleteListner {
    void requestSuccess(PrintableCategoryBean printableCategoryBean);
    void requestFailed(String error);

}
