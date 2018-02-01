package com.turtlediary.www.interfaces;

import com.turtlediary.www.beans.RegistrationResponseBean;

/**
 * Created by pratibha on 5/12/17.
 */

public interface OnPaymentConfirmation {
    void paymentSuccess( RegistrationResponseBean registrationResponseBean);
    void paymentFailed(String msg);
}
