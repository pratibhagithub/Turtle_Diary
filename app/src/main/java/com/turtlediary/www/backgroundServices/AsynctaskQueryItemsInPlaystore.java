package com.turtlediary.www.backgroundServices;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.interfaces.OnPriceGettingCompletion;
import com.turtlediary.www.interfaces.OnQueryCompletion;

import java.util.ArrayList;

/**
 * Created by pratibha on 6/12/17.
 */

public class AsynctaskQueryItemsInPlaystore extends AsyncTask<String, Void, String> {
    private OnPriceGettingCompletion onPriceGettingCompletion;
    private ArrayList<String> responseList;
    Activity activity;
    private ArrayList<String> productIds;
    private IInAppBillingService mService;
    private OnQueryCompletion onQueryCompletion;

    public AsynctaskQueryItemsInPlaystore(Activity activity, ArrayList<String> productIds, OnQueryCompletion onQueryCompletion, IInAppBillingService mService) {
        this.onQueryCompletion = onQueryCompletion;
        this.activity = activity;
        this.productIds = productIds;
        this.mService=mService;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
           /* ArrayList<String> skuList = new ArrayList<String>();
            for(int i=0;i<productIds.size();i++) {
                skuList.add(productIds.get(i));
            }
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList("ITEM_ID_LIST", skuList);
            Bundle skuDetails = mService.getSkuDetails(3,
                    activity.getPackageName(), "subs", querySkus);

            int response = skuDetails.getInt("RESPONSE_CODE");
            Log.e("response IN ASYNCTASK",response+"");
            Log.e("list size IN ASYNCTASK",skuDetails.toString());

            if (response == BILLING_RESPONSE_RESULT_OK) {
                responseList = skuDetails.getStringArrayList("DETAILS_LIST");
            }*/
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
        onQueryCompletion.onQueryItemSuccess();
    }
}
