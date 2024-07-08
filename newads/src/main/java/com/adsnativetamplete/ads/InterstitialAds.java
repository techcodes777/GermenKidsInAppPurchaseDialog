package com.adsnativetamplete.ads;

import static com.adsnativetamplete.AdsKeys.Custom;
import static com.adsnativetamplete.activity.NativeBaseActivity.isSubriptionDone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.activity.Custom_Interstitial_Ads_Activity;
import com.adsnativetamplete.enums.AD_VIEW_TYPE1;
import com.google.android.ads.nativetemplates.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class InterstitialAds {

    private static final String TAG = "InterstitialAds";
    public static int adsCount = 0;
    static InterstitialAd mInterstitialAdAdmob;
    static int advCount = 0;
    static int AdsonAdFailedToLoad = 0;
    private static Intent intent;
    private static boolean isFinished;
    private static Activity activity1;
    private static Dialog adLoadDialog;
    private static Boolean fbloadorshow = false;
    private static Intent fbintent;
    private static Activity fbactvity;
    private static Boolean fbisfinished;
    private final int adAmbCount = 0;

    public static void loadInterstitial(Activity activity, AD_VIEW_TYPE1 adViewType) {
        if (isSubriptionDone) {
            return;
        }
        if (adViewType == AD_VIEW_TYPE1.ADMOB1) {
            if (!AdsKeys.interstitialId1.isEmpty()) {

                AdRequest adRequest = new AdRequest.Builder().build();

                InterstitialAd.load(activity, AdsKeys.interstitialId1, adRequest,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                mInterstitialAdAdmob = interstitialAd;

                            }

                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                                if (adViewType == AD_VIEW_TYPE1.ADMOB1) {

                                    if (!AdsKeys.interstitialId2.isEmpty()) {
                                        loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB2);
                                    } else {
                                        mInterstitialAdAdmob = null;
                                        loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB3);
                                    }
                                }
                            }
                        });
            } else {
                if (adViewType == AD_VIEW_TYPE1.ADMOB1) {
                    if (!AdsKeys.interstitialId2.isEmpty()) {
                        loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB2);
                    } else {
                        mInterstitialAdAdmob = null;
                        loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB3);
                    }
                }
            }
        } else if (adViewType == AD_VIEW_TYPE1.ADMOB2) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AdsKeys.interstitialId2, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAdAdmob = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mInterstitialAdAdmob = null;
                            if (adViewType == AD_VIEW_TYPE1.ADMOB2) {
                                loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB3);
                            }
                        }
                    });
        } else if (adViewType == AD_VIEW_TYPE1.ADMOB3) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity, AdsKeys.interstitialId3, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAdAdmob = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mInterstitialAdAdmob = null;

                        }
                    });
        }

    }


    public static void showAds(Activity activity, Intent intent, boolean isFinished, boolean isCounter) {

        if (isSubriptionDone) {
            if (intent != null) {
                activity.startActivity(intent);
            }
            if (isFinished) {
                activity.finish();
            }
            return;
        } else {
            if (isCounter) {
                AdsKeys.clickCountsInters++;
                if (AdsKeys.clickCountsInters % Integer.parseInt(AdsKeys.interstitialCount) != 0) {
                    if (intent != null) {
                        activity.startActivity(intent);
                    }
                    if (isFinished) {
                        activity.finish();
                    }
                    return;
                }
            }
        }
        //showLoaderDialog(activity);
        if (mInterstitialAdAdmob != null) {
            mInterstitialAdAdmob.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);

                    if (!AdsKeys.image_small_ads.equals("") && !AdsKeys.image_big_ads.equals("") && !AdsKeys.app_name_ads.equals("") && !AdsKeys.description_one_ads.equals("") && !AdsKeys.play_store_url.equals("")) {

                        callCustomActivity(activity, intent, isFinished);
                    } else {
                        if (intent != null) {
                            activity.startActivity(intent);
                        }
                        if (isFinished) {
                            activity.finish();
                        }
                    }


                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB1);
                    if (intent != null) {
                        activity.startActivity(intent);
                    }
                    if (isFinished) {
                        activity.finish();
                    }
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }
            });
            mInterstitialAdAdmob.show(activity);

        } else {
            loadInterstitial(activity, AD_VIEW_TYPE1.ADMOB1);

            if (!AdsKeys.image_small_ads.equals("") && !AdsKeys.image_big_ads.equals("") && !AdsKeys.app_name_ads.equals("") && !AdsKeys.description_one_ads.equals("") && !AdsKeys.play_store_url.equals("")) {
                callCustomActivity(activity, intent, isFinished);
            } else {
                if (intent != null) {
                    activity.startActivity(intent);
                }
                if (isFinished) {
                    activity.finish();
                }
            }


        }


    }


    public static void showLoaderDialog(Activity context) {
        try {
            if (!(context).isFinishing()) {
                if (adLoadDialog != null)
                    if (adLoadDialog.isShowing())
                        adLoadDialog.dismiss();

                adLoadDialog = new Dialog(context, R.style.MyDialog);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_loader, null);

                WindowManager.LayoutParams params = adLoadDialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.MATCH_PARENT;

                adLoadDialog.setContentView(view);
                adLoadDialog.show();
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissLoaderDialog() {
        try {
            if (adLoadDialog != null && adLoadDialog.isShowing())
                adLoadDialog.dismiss();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }


    public static void callCustomActivity(Activity activity, Intent intent, boolean isFinished) {
        if (!AdsKeys.image_small_ads.equals("") && !AdsKeys.image_big_ads.equals("") && !AdsKeys.app_name_ads.equals("") && !AdsKeys.description_one_ads.equals("") && !AdsKeys.description_two_ads.equals("") && !AdsKeys.button_txt.equals("") && !AdsKeys.play_store_url.equals("")) {
            if (Custom.equals("true")) {
                Intent intent1 = new Intent(activity, Custom_Interstitial_Ads_Activity.class);
                if (intent != null) {
                    intent1.putExtra("passedIntent", intent);
                }
                activity.startActivity(intent1);
            } else {
                if (intent != null) {
                    activity.startActivity(intent);
                }
                if (isFinished) {
                    activity.finish();
                }
            }

        } else {
            if (intent != null) {
                activity.startActivity(intent);
            }
        }
        if (isFinished) {
            activity.finish();
        }
    }
}