package com.turtlediary.www.interfaces;

import java.util.ArrayList;

/**
 * Created by pratibha on 14/11/17.
 */

public interface OnSignUpFragmentChangeListner {
    void changeFragment(int value, String accountType, String subscritionType, ArrayList<String> signupInfo, ArrayList<String> ProductInfo );
}
