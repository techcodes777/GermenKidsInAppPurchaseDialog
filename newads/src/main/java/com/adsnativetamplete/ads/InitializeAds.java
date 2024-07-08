package com.adsnativetamplete.ads;

import com.adsnativetamplete.MyApp;
import com.adsnativetamplete.activity.NativeBaseActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONObject;

public class InitializeAds {
    public static void initializeAds(MyApp activity) {
        JSONObject consent = new JSONObject();

        if (NativeBaseActivity.isSubriptionDone) {
               return;
        }
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

    }
}