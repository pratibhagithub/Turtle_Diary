package com.turtlediary.www.interfaces;

import com.android.vending.billing.IInAppBillingService;

/**
 * Created by pratibha on 6/12/17.
 */

public interface OnServiceBindingCompletion {
    void onInAppServiceBindSucess(IInAppBillingService mService);
    void onInAppServiceBindFailed(String failedMessage);
}
