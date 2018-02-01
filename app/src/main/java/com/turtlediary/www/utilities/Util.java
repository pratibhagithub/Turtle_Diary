package com.turtlediary.www.utilities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.WebViewActivity;
import com.turtlediary.www.applicationn.MyApplication;
import com.turtlediary.www.interfaces.OnDialogEventListners;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by pratibha on 24/10/17.
 */

public class Util {


    public static void fragmentTransec(Fragment fragment, Activity activity, int framelayoutId) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(framelayoutId, fragment);
        transaction.commit();
    }

    public static void fragmentTransecWithBAckstack(Fragment fragment, Activity activity, int framelayoutId) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(framelayoutId, fragment);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    /**
     * @param context the context
     * @return <code>true</code> if another application will be above this one.
     */
    public static boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageNameTopActivity;
        List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
        if(Build.VERSION.SDK_INT > 20){
             packageNameTopActivity = am.getRunningAppProcesses().get(0).processName;
        }
        else{
            packageNameTopActivity = am.getRunningTasks(1).get(0).topActivity.getPackageName();
        }
        if (!tasks.isEmpty()) {
            Logg.p("BACKGROUND SERVICE topActivity",packageNameTopActivity);

            if (packageNameTopActivity.equals(context.getPackageName())) {
                System.out.println("BACKGROUND SERVICE " + true + "");
                return true;
            } else {
                System.out.println(" BACKGROUND SERVICE" + false + "");
                return false;
            }
        } else {
            Logg.p("BACKGROUND SERVICE tasks is empty","");
            System.out.println(" BACKGROUND SERVICE " + false + "");
            return false;
        }

    }

    /***
     * *
     *
     * @param baseActivity is the activity from where we are calling
     * @param callingClass
     * @param isToFinish   is parente activity needed to finish
     */
    public static void callActivity(Activity baseActivity, Class callingClass, boolean isToFinish) {
        Intent mainIntent = new Intent(baseActivity, callingClass);
        baseActivity.startActivity(mainIntent);
        if (isToFinish)
            baseActivity.finish();
    }

    /**
     * Method to openup the dialog
     */
    public static void okayCancelDialog(Activity activity, String msg, String title, String okText, String cancelText, final OnDialogEventListners onDialogEventListners) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//, android.R.style.Theme_DeviceDefault_Light_Dialog
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        } else {
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme
            );
        }

        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogEventListners.onOkay(dialog);

                    }
                })
                .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogEventListners.onCancel(dialog);

                    }
                })
                .show();

    }

    /**
     * Method to openup the dialog
     */
    public static void quesViewDialog(Activity activity, String msg, String title, String okText, String cancelText, final OnDialogEventListners onDialogEventListners) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//, android.R.style.Theme_DeviceDefault_Light_Dialog
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        } else {
            builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme
            );
        }


        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogEventListners.onOkay(dialog);

                    }
                })
                .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogEventListners.onCancel(dialog);

                    }
                })
                .show();

    }


    /**
     * Method gets the version of the application using {@link PackageManager}
     *
     * @param context context
     * @return application version name
     */
    public static String getApplicationVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Metghod gives the unique ID if the device
     * https://medium.com/@ssaurel/how-to-retrieve-an-unique-id-to-identify-android-devices-6f99fd5369eb
     *
     * @param context
     * @return
     */
    public static String getUniqueDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999) + 1;
    }


    public static void setEffect(View view) {
        view.setOnTouchListener(null);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setAlpha(0.5f);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        v.setAlpha(1f);
                        break;
                    }
                }
                return false;
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            //  inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
            // inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }

    }

    public static Drawable gettingDrawable(Activity activity, int drawableId) {
        return ContextCompat.getDrawable(activity, drawableId);
    }

    public static int gettingColor(Activity activity, int drawableId) {
        return ContextCompat.getColor(activity, drawableId);
    }

    public static void callWebview(Activity activity, String url) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(AppConstants.URL_TO_LOAD, url);
        activity.startActivity(intent);
    }


    public static void setGoogleAnayticsData(Activity activity, String screenName, String eventCategory) {
        MyApplication application = (MyApplication) activity.getApplication();
        Tracker mTracker = application.getDefaultTracker();
        sendGoogleAnalyticalException(mTracker);

/*        Log.e("********","***********");
        Log.e("ANALYTICS DATA","activity=="+activity);
        Log.e("ANALYTICS DATA","screenName=="+screenName);
        Log.e("ANALYTICS DATA","eventCategory=="+eventCategory);*/
        mTracker.setScreenName(screenName);
        {
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory(eventCategory)
                    .setLabel(eventCategory)
                    .build());
        }
    }

    public static void sendGoogleAnalyticalException(final Tracker tracker) {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                defaultUncaughtExceptionHandler.uncaughtException(thread, throwable);
                tracker.send(new HitBuilders.ExceptionBuilder().setDescription(throwable.getMessage().toString()).setFatal(true).build());
            }
        });
    }


    public static void getKeyHash(Activity activity) {
        // Add code to print out the key hash
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    public static String serverErrorMessageSet(Activity activity, String msg) {
        MyApplication myApplication = (MyApplication) activity.getApplication();
        String strMsg = "";
        if (msg.equalsIgnoreCase(AppConstants.NETWORK_ERROR))
            strMsg = myApplication.getBaseBean().getConfig().getInternetAlert().getInternetError();
        if (msg.equalsIgnoreCase(AppConstants.NETWORK_NOT_AVAILABLE))
            strMsg = myApplication.getBaseBean().getConfig().getInternetAlert().getInternetError();
        return strMsg;
    }

    public static void setFirebaseAnalyticsLog(Activity activity, String screenName, String eventCategory) {
        MyApplication application = (MyApplication) activity.getApplication();
        FirebaseAnalytics firebaseAnalytics = application.getFirebaseAnalyticsObject();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, screenName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, eventCategory);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
        firebaseAnalytics.setMinimumSessionDuration(2000);
        firebaseAnalytics.setSessionTimeoutDuration(300000);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    public static void savePreferenceValue(Activity activity, String uName, String uPwd, String uToken, String uType, String uId, boolean uStatus, String userEmailId) {
        Preferences.saveUserEmail(activity, userEmailId);
        Preferences.saveUserName(activity, uName);
        Preferences.saveUserPassword(activity, uPwd);
        Preferences.saveUserToken(activity, uToken);
        Preferences.saveUserType(activity, uType);
        Preferences.saveUserId(activity, uId);
        Preferences.saveUserStatus(activity, uStatus);
    }

    public static Map<String, String> getExtraHeader(Activity activity) {
        String token = AppConstants.USER_NAME + ":" + AppConstants.PASSWORD;
        String basic = "Basic " + Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put("Authorization", basic);
        extraHeaders.put("Authorization-token", "bearer " + Preferences.getUserToken(activity));
        return extraHeaders;
    }

    public static boolean checkGooglePlayServicesAvaialbility(Activity activity) {
        final int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            Logg.p("PLAY SERVICES-1", GooglePlayServicesUtil.getErrorString(status));
            Logg.p("PLAY SERVICES-1", GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE + "");
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, activity, 1);
            dialog.show();
            return false;
        } else {
            Logg.p("PLAY SERVICES-2", GooglePlayServicesUtil.getErrorString(status));
            Logg.p("PLAY SERVICES-2", GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE + "");

            // google play services is updated.
            //your code goes here...
            return true;
        }
    }

    public static boolean isPremeiumMember(Activity activity) {
        if (TextUtils.isEmpty(Preferences.getUserId(activity.getApplicationContext())) || !(Preferences.getUserStatus(activity.getApplicationContext()))) {
            return false;
        } else {
            return true;
        }
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Activity activity) {
        MyApplication myApplication = (MyApplication) activity.getApplication();

        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (isConnected == false) {
            String strMsg = myApplication.getBaseBean().getConfig().getInternetAlert().getInternetError();
            showToast(activity, strMsg);
        } else {

        }
        return isConnected;
    }

    public static void openAppInPlayStore(Activity activity) {
   /*     String playStoreLink = "https://play.google.com/store/apps/details?id=com.turtlediary.www&hl=en";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse(playStoreLink));
        activity.startActivity(intent);*/

        final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

}
