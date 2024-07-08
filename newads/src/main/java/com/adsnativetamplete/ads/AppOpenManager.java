package com.adsnativetamplete.ads;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import static com.adsnativetamplete.activity.NativeBaseActivity.isSubriptionDone;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.MyApp;
import com.adsnativetamplete.NativePreferenceUtil;
import com.adsnativetamplete.activity.CustomAppOpenAds_Activity;
import com.adsnativetamplete.activity.NativeBaseActivity;
import com.adsnativetamplete.enums.AD_VIEW_TYPE1;
import com.adsnativetamplete.retrofit.AdsIdLoader;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback;

import java.util.Date;

public class AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {
    private long loadTime = 0;
    private static final String LOG_TAG = "AppOpenManager";
    private static String AD_UNIT_ID = "abc";
    private AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private AppOpenAdLoadCallback loadCallback;
    private static boolean isShowingAd = false;
    private final MyApp myApplication;

    /** Constructor */
    public AppOpenManager(MyApp myApplication, SharedPreferences sharedpreferences) {
        this.myApplication = myApplication;
        if (sharedpreferences.getString("openid","").toString().trim().length()<0){

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("openid", "");
            editor.commit();
        }
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }


    /** LifecycleObserver methods */
    @OnLifecycleEvent(ON_START)
    public void onStart() {
        if (!isSubriptionDone) {
            showAdIfAvailable();
        }

        Log.d(LOG_TAG, "onStart");
    }
    public void fetchAd() {
        SharedPreferences sharedpreferences = currentActivity.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        // Have unused ad, no need to fetch another.
        if (isAdAvailable()) {
            return;
        }

        loadCallback =
                new AppOpenAdLoadCallback() {
                    /**
                     * Called when an app open ad has loaded.
                     *
                     * @param ad the loaded app open ad.
                     */
                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        AppOpenManager.this.appOpenAd = ad;
                        AppOpenManager.this.loadTime = (new Date()).getTime();
                    }

                    /**
                     * Called when an app open ad has failed to load.
                     *
                     * @param loadAdError the error.
                     */
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        // Handle the error.
                    }

                };
        AdRequest request = getAdRequest();
        AppOpenAd.load(
                myApplication, sharedpreferences.getString("openid",""),  request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    /** Creates and returns ad request. */
    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    /** Utility method to check if ad was loaded more than n hours ago. */
    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }
    /** Utility method that checks if ad exists and can be shown. */
    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    /** ActivityLifecycleCallback methods */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}

    @Override
    public void onActivityDestroyed(Activity activity) {
        currentActivity = null;
    }

    /** Shows the ad if one isn't already showing. */
    public void showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        if (!isShowingAd && isAdAvailable()) {
            Log.d(LOG_TAG, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallback =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            AppOpenManager.this.appOpenAd = null;
                            isShowingAd = false;
                            if (!isSubriptionDone) {
                                fetchAd();
                            }
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {}

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAd = true;
                        }
                    };

            appOpenAd.show(currentActivity);

        } else {
            Log.d(LOG_TAG, "Can not show ad.");
            if (!isSubriptionDone) {
                fetchAd();
            }


        }
    }
}