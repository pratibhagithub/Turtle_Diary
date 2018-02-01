package com.turtlediary.www.applicationn;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.turtlediary.www.R;
import com.turtlediary.www.activities.ContentWebviewActivity;
import com.turtlediary.www.beans.BaseBean;
import com.turtlediary.www.utilities.Logg;
import com.turtlediary.www.utilities.Preferences;
import com.turtlediary.www.utilities.Util;

import io.fabric.sdk.android.Fabric;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    static MediaPlayer player = null;
    private static GoogleAnalytics sAnalytics;
    //public static Tracker sTracker;
    private FirebaseAnalytics sFirebaseAnalytics;
    public static MyApplication applicationContext = null;
    public static MyApplication getInstance(){

            return  applicationContext;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    private BaseBean baseBean;

    @Override
    public void onCreate() {
        super.onCreate();
        sFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sAnalytics = GoogleAnalytics.getInstance(this);
        Fabric.with(this, new Crashlytics());
        registerActivityLifecycleCallbacks(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        applicationContext = this;

    }

    private Tracker mTracker;


    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.app_tracker);
            mTracker.enableAutoActivityTracking(true);
            mTracker.enableAdvertisingIdCollection(true);
            mTracker.enableExceptionReporting(true);
        }
        return mTracker;
    }

    synchronized public FirebaseAnalytics getFirebaseAnalyticsObject() {
        if (sFirebaseAnalytics == null) {
            FirebaseAnalytics fireBaseAnalytics = FirebaseAnalytics.getInstance(this);
            return fireBaseAnalytics;
        }
        else
        {
            return sFirebaseAnalytics;

        }
    }
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }

    @Override
    public void onActivityStarted(Activity activity) {

    }


    @Override
    public void onActivityResumed(Activity activity) {
        if (activity.getClass().equals(ContentWebviewActivity.class)) {
            pausePlayer(activity);
        } else {
            if (Preferences.getBackgroundSoundEnability(activity)) {
                startPlayer(activity);
            } else {
                //Toast.makeText(activity, "Sound off", Toast.LENGTH_SHORT).show();
                if (player != null) {
                    stopPlayer(activity);
                }
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (player != null) {
            boolean isOnBackground = Util.isApplicationSentToBackground(activity);
            Logg.p("BACKGROUND SERVICE","Onp pause");
            Logg.p("BACKGROUND SERVICE",isOnBackground+"");
            if (isOnBackground) {
                player.pause();
            } else {

            }
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    public void stopPlayer(Activity activity) {

        if (player != null) {

            player.pause();
            player.stop();
            player = null;
        } else {

        }
    }


    public void startPlayer(Activity activity) {
        if (player != null) {
            if (player.isPlaying()) {
            } else {
                player.start();
            }

        } else {

            player = MediaPlayer.create(activity, R.raw.app_music_bg);
            player.setLooping(true); // Set looping
            player.setVolume(100, 100);
            player.start();
        }
    }

    public void pausePlayer(Activity activity) {

        if (player != null) {
            if (player.isPlaying()) {
                player.pause();}
            }
        }



}



