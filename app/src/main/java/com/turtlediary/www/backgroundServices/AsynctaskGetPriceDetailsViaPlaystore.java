package com.turtlediary.www.backgroundServices;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.beans.PlayStoreProductBean;
import com.turtlediary.www.interfaces.OnPriceGettingCompletion;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.turtlediary.www.fragments.MembershipFragment.BILLING_RESPONSE_RESULT_OK;

/**
 * Created by pratibha on 1/12/17.
 */

public class AsynctaskGetPriceDetailsViaPlaystore extends AsyncTask<String, Void, String> {
    private OnPriceGettingCompletion onPriceGettingCompletion;
    private ArrayList<String> responseList;
    Activity activity;
    private ArrayList<String> productIds;
    private IInAppBillingService mService;

    public AsynctaskGetPriceDetailsViaPlaystore(Activity activity,ArrayList<String> productIds, OnPriceGettingCompletion onPriceGettingCompletion, IInAppBillingService mService) {
        this.onPriceGettingCompletion = onPriceGettingCompletion;
        this.activity = activity;
        this.productIds = productIds;
        this.mService=mService;
    }


    @Override
    protected String doInBackground(String... params) {
        try {
            ArrayList<String> skuList = new ArrayList<String>();
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
         ArrayList<PlayStoreProductBean> mPlayStoreProductBeenList = new ArrayList<>();
        try {
            for (String thisResponse : responseList) {
                PlayStoreProductBean playStoreProductBean = new PlayStoreProductBean();
                JSONObject object = new JSONObject(thisResponse);
                String skuId = object.getString("productId");
                String price = object.getString("price");
                String currencyCode = object.getString("price_currency_code");
                String priceInMacros = object.getString("price_amount_micros");
                playStoreProductBean.setCurrencyCode(currencyCode);
                playStoreProductBean.setProductAmountInMacros(priceInMacros);
                playStoreProductBean.setProductId(skuId);
                playStoreProductBean.setProductPrice(price);
                mPlayStoreProductBeenList.add(playStoreProductBean);

            }
           // setPrice();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        onPriceGettingCompletion.getPriceDetails(mPlayStoreProductBeenList);
    }
}
