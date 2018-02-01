package com.turtlediary.www.backgroundServices;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.interfaces.OnPurchaseCompletion;

import java.util.ArrayList;

import static com.turtlediary.www.fragments.MembershipFragment.BILLING_RESPONSE_RESULT_OK;
import static com.turtlediary.www.inAppUtilities.IabHelper.RESPONSE_BUY_INTENT;
import static com.turtlediary.www.inAppUtilities.IabHelper.RESPONSE_CODE;

/**
 * Created by pratibha on 4/12/17.
 */

public class AsynctaskToPurchaseSubscriptiom extends AsyncTask<String, Void, String> {
    private OnPurchaseCompletion onPurchaseCompletion;
    private ArrayList<String> responseList;
    Activity activity;
    private String productId;
    private IInAppBillingService mService;

    public AsynctaskToPurchaseSubscriptiom(Activity activity,String productId, OnPurchaseCompletion onPurchaseCompletion, IInAppBillingService mService) {
        this.onPurchaseCompletion = onPurchaseCompletion;
        this.activity = activity;
        this.productId = productId;
        this.mService=mService;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            Bundle bundle = mService.getBuyIntent(3, activity.getPackageName(),
                    productId, "subs", "No Extra Information");
            PendingIntent pendingIntent = bundle.getParcelable(RESPONSE_BUY_INTENT);
            if (bundle.getInt(RESPONSE_CODE) == BILLING_RESPONSE_RESULT_OK) {
                // Start purchase flow (this brings up the Google Play UI).
                // Result will be delivered through onActivityResult().


                activity.startIntentSenderForResult(pendingIntent.getIntentSender(),
                        1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
                        Integer.valueOf(0));
            }
        }catch (Exception ex)
        {
            Log.e("EXCEPTION IN ASYNCTASK",ex.toString());
        }
        return "Success";

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        onPurchaseCompletion.onPurchaseSuccess();
    }
}
