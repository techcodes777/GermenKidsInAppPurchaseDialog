package com.adsnativetamplete.ads;


import static com.adsnativetamplete.activity.NativeBaseActivity.isSubriptionDone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.adsnativetamplete.AdsKeys;
import com.google.android.ads.nativetemplates.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

@SuppressWarnings("UnusedDeclaration")
public class BannerAdsView extends LinearLayout {

    public BannerAdsView(Context context) {
        this(context, null);
    }

    public BannerAdsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public BannerAdsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        View layout = View.inflate(context, R.layout.custom_banner_layout, this);

        if (isSubriptionDone) {
            layout.setVisibility(GONE);
            return;
        } else {
            try {
                FrameLayout greedyxbannerad = findViewById(R.id.banner_ad_container);
                RelativeLayout bannerlout = findViewById(R.id.bannerlout);
                greedyxbannerad.setVisibility(View.VISIBLE);
                bannerlout.setVisibility(View.VISIBLE);
                loadadmobBannerAd((Activity) context, greedyxbannerad);
            } catch (Exception e) {
                Log.e("----error", e.getMessage() + "");
                Toast.makeText(context, e.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void loadadmobBannerAd(Activity context, FrameLayout rlBannerAd) {
        if (isSubriptionDone) {
          return;
        }
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AdsKeys.BannerAds1);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                loadadmobBannerAd2(context, rlBannerAd);
            }

            @Override
            public void onAdLoaded() {
            }
        });
        AdRequest build = new AdRequest.Builder().build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        rlBannerAd.addView(adView, params);
        adView.loadAd(build);

    }

    public void loadadmobBannerAd2(Activity context, FrameLayout rlBannerAd) {

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AdsKeys.BannerAds2);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                loadadmobBannerAd3(context, rlBannerAd);
            }

            @Override
            public void onAdLoaded() {
            }
        });
        AdRequest build = new AdRequest.Builder().build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        rlBannerAd.addView(adView, params);
        adView.loadAd(build);
    }

    public void loadadmobBannerAd3(Activity context, FrameLayout rlBannerAd) {

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AdsKeys.BannerAds3);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded() {
            }
        });
        AdRequest build = new AdRequest.Builder().build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        rlBannerAd.addView(adView, params);
        adView.loadAd(build);
    }
}

