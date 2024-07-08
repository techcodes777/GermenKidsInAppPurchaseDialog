package com.dingo.germanforkids.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.activity.NativeBaseActivity;
import com.adsnativetamplete.activity.NoIntrnetActivity;
import com.adsnativetamplete.ads.InterstitialAds;
import com.adsnativetamplete.enums.AD_VIEW_TYPE1;
import com.adsnativetamplete.internetchecker.InternetAvailabilityChecker;
import com.adsnativetamplete.internetchecker.InternetConnectivityListener;
import com.adsnativetamplete.retrofit.AdsIdLoader;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.QueryPurchasesParams;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivitySplashBinding;
import com.dingo.germanforkids.purchase.Constant;
import com.dingo.germanforkids.purchase.Pref;
import com.dingo.germanforkids.utils.MyMediaPlayer;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class SplashActivity extends AppCompatActivity implements InternetConnectivityListener {
    MyMediaPlayer mediaPlayer;
    ActivitySplashBinding binding;
    private AppOpenAd appOpenAd = null;
    BillingClient billingClient;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        InternetAvailabilityChecker.init(this);
        InternetAvailabilityChecker internetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        internetAvailabilityChecker.addInternetConnectivityListener(this);
        mediaPlayer = new MyMediaPlayer(getApplicationContext());
        mediaPlayer.playSound(R.raw.splash_screen);

        YoYo.with(Techniques.Flash).duration(1000).repeat(YoYo.INFINITE).playOn(binding.ivLogo);

    }

    @Override
    public void onInternetConnectivityChanged() {
        if (InternetAvailabilityChecker.mIsInternetConnected) {

            billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener((billingResult, list) -> {
            }).build();
            final BillingClient finalBillingClient = billingClient;
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingServiceDisconnected() {

                    if (Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
                        NativeBaseActivity.isSubriptionDone = true;
                        inNext();
                    } else {
                        showAdsMain();
                    }
                }

                @Override
                public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        finalBillingClient.queryPurchasesAsync(
                                QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), (billingResult1, list) -> {
                                    if (list.size() > 0) {

                                        Pref.getInstance().setString(Constant.PURCHASE_DONE, "done");
                                        if (Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
                                            NativeBaseActivity.isSubriptionDone = true;
                                            inNext();
                                        }
                                    } else {
                                        Pref.getInstance().setString(Constant.PURCHASE_DONE, "");
                                        NativeBaseActivity.isSubriptionDone = false;
                                        showAdsMain();
                                    }
                                });
                    } else {
                        if (Pref.getInstance().getString(Constant.PURCHASE_DONE).equals("done")) {
                            NativeBaseActivity.isSubriptionDone = true;
                            inNext();
                        } else {
                            showAdsMain();
                        }
                    }
                }
            });

            NoIntrnetActivity.killMe();

        } else {
            Intent intent = new Intent(SplashActivity.this, NoIntrnetActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.StopMp();
    }

    private void showAdsMain() {
        Handler mainHandler = new Handler(Looper.getMainLooper(), message -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InterstitialAds.loadInterstitial(SplashActivity.this, AD_VIEW_TYPE1.ADMOB1);
                    switch (AdsKeys.firstScreenSetting) {
                        case "interstitial":
                            InterstitialAds.showAds(SplashActivity.this, null, false, true);
                            inNext();
                            break;
                        case "app open":
                            showOpenAds();
                            break;
                        case "none":
                        case "":
                            inNext();
                            break;
                    }
                }
            }, 2000);
            return true;
        });
        AdsIdLoader.appUpdateCall(SplashActivity.this, "63b664ff86cf1ea1fd4519d0", mainHandler);
    }

    private void inNext() {
        startActivity(new Intent(SplashActivity.this, StartScreenActivity.class));
        finish();
    }

    private void showOpenAds() {
        InterstitialAds.loadInterstitial(this, AD_VIEW_TYPE1.ADMOB1);
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                appOpenAd = ad;
                FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        inNext();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        appOpenAd = null;
                        inNext();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {

                    }
                };

                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(SplashActivity.this);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                inNext();
            }
        };
        AdRequest request = new AdRequest.Builder().build();
        AppOpenAd.load(SplashActivity.this, AdsKeys.appOpenAdsIdOne, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }
}