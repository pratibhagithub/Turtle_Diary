package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.LoginBean;

/**
 * Created by pratibha on 27/10/17.
 */

public interface OnLoginInterface {
    public void onLoginSuccess(LoginBean loginBean);
    public void onLoginFailed(String failedMsg);

}
