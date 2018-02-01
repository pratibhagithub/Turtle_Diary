package com.turtlediary.www.interfaces;

/**
 * Created by pratibha on 31/10/17.
 */

public interface OnLogOutComplete {
    void logoutSuccess();
    void logoutFailed(String failMsg);

}
