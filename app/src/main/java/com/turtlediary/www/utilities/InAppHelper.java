package com.turtlediary.www.utilities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;
import com.turtlediary.www.interfaces.OnServiceBindingCompletion;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pratibha on 6/12/17.
 */

public class InAppHelper {
    private   IInAppBillingService mService;
    private  boolean blnBind;
    private Activity activity;
    private  OnServiceBindingCompletion onServiceBindingCompletion;
    ServiceConnection  mServiceConn = new ServiceConnection() {
        String tag = "in_app_billing_ex";

        @Override
        public void onServiceDisconnected(ComponentName name) {
            onServiceBindingCompletion.onInAppServiceBindFailed("NOt Connected");

            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mService = IInAppBillingService.Stub.asInterface(service);
            onServiceBindingCompletion.onInAppServiceBindSucess(mService);
            Log.e(tag, "isBillingSupported() - mService!" + mService);

        }


    };;

    public InAppHelper(Activity activity,OnServiceBindingCompletion onServiceBindingCompletion )
    {
        this.activity=activity;
        this.onServiceBindingCompletion=onServiceBindingCompletion;
    }

    public  void bindingService() {
        String tag = "in_app_billing_ex";
        int result;

        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        blnBind = getApplicationContext().bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
        if (!blnBind) {
            onServiceBindingCompletion.onInAppServiceBindFailed("NOt Connected");
            Log.e(tag, "isBillingSupported() - FALSSE : return " + blnBind);
            return;
        }

        if (mService == null) {
            onServiceBindingCompletion.onInAppServiceBindFailed("NOt Connected");
            Log.e(tag, "isBillingSupported() - Null : return " + blnBind);
            return;
        }
        try {
            result = mService.isBillingSupported(3, activity.getPackageName(), "inapp");
            Log.e(tag, "isBillingSupported() - success : return " + String.valueOf(result));
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(tag, "isBillingSupported() - fail!");
            onServiceBindingCompletion.onInAppServiceBindFailed("NOt Connected,isBillingSupported() - fail!");

            return;
        }

    }

}
