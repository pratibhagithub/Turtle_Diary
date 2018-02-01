package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.RegistrationResultBean;

/**
 * Created by pratibha on 13/11/17.
 */

public interface RegistrationCompleteInterface {
    void onRegistrationSucess(RegistrationResultBean response);
    void onRegistrationFailed(String msg);
}
