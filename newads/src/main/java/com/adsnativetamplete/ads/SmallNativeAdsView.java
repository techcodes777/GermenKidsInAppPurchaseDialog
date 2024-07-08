package com.adsnativetamplete.ads;


import static com.adsnativetamplete.activity.NativeBaseActivity.isSubriptionDone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.Globals;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

@SuppressWarnings("UnusedDeclaration")
public class SmallNativeAdsView extends LinearLayout {

    public static NativeAd nativeAds;
    static NativeAd nativeAda;
    static FrameLayout aplovinnative_ad_container;


    public SmallNativeAdsView(Context context) {
        this(context, null);
    }

    public SmallNativeAdsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public SmallNativeAdsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        View layout = View.inflate(context, R.layout.custom_small_native_layout, this);


        if (isSubriptionDone) {
            layout.setVisibility(GONE);
        } else {
            try {
                aplovinnative_ad_container = findViewById(R.id.aplovinnative_ad_container);
                FrameLayout greedyxbannerad = findViewById(R.id.native_ad_container);
                RelativeLayout nativelout = findViewById(R.id.nativelout);

                ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);

                greedyxbannerad.setVisibility(View.VISIBLE);
                nativelout.setVisibility(View.VISIBLE);
                loadadmobNativeAd((Activity) context, greedyxbannerad, shimmer_view_container);
            } catch (Exception e) {
                Log.e("----error", e.getMessage() + "");
                Toast.makeText(context, e.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public static NativeAd getNativeAds() {
        return nativeAds;
    }

    public static void loadadmobNativeAd(final Activity context, final FrameLayout frameLayout, ShimmerFrameLayout view) {
        if (!isSubriptionDone) {
            refreshAd(context, frameLayout, view);
        }
    }

    public static void refreshAd(final Activity context, FrameLayout frameLayout, ShimmerFrameLayout view) {
        AdLoader.Builder builder = new AdLoader.Builder(context, AdsKeys.nativeId1);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {

            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                if (nativeAds != null) {
                    nativeAds.destroy();
                }
                nativeAds = nativeAd;
                NativeAdView adView = (NativeAdView) context.getLayoutInflater().inflate(R.layout.small_ads_native_ad_jem, null);
                populateUnifiedNativeAdView(context, nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
                view.setVisibility(View.GONE);
                view.stopShimmer();
            }
        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(false).build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {

            @Override
            @Deprecated
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                if (!AdsKeys.nativeId2.isEmpty()) {
                    refreshAd2(context, frameLayout, view);
                }

//                Toast.makeText(context, loadAdError.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }).build();
        AdRequest adRequest = new AdRequest.Builder().build();

        adLoader.loadAd(adRequest);
    }

    public static void refreshAd2(final Activity context, FrameLayout frameLayout, ShimmerFrameLayout view) {
        AdLoader.Builder builder = new AdLoader.Builder(context, AdsKeys.nativeId2);

        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {

            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {


                if (nativeAds != null) {
                    nativeAds.destroy();
                }
                nativeAds = nativeAd;
                NativeAdView adView = (NativeAdView) context.getLayoutInflater().inflate(R.layout.small_ads_native_ad_jem, null);
                populateUnifiedNativeAdView(context, nativeAd, adView);
                if (frameLayout != null) {

                    frameLayout.removeAllViews();
                }
                frameLayout.addView(adView);


                view.setVisibility(View.GONE);
                view.stopShimmer();
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(false).build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {

            @Override
            @Deprecated
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                if (!AdsKeys.nativeId3.isEmpty()) {
                    refreshAd3(context, frameLayout, view);
                }
            }
        }).build();
        AdRequest adRequest = new AdRequest.Builder().build();

        adLoader.loadAd(adRequest);
    }

    public static void refreshAd3(final Activity context, FrameLayout frameLayout, ShimmerFrameLayout view) {
        AdLoader.Builder builder = new AdLoader.Builder(context, AdsKeys.nativeId2);

        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {

            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {


                if (nativeAds != null) {
                    nativeAds.destroy();
                }
                nativeAds = nativeAd;
                NativeAdView adView = (NativeAdView) context.getLayoutInflater().inflate(R.layout.small_ads_native_ad_jem, null);
                populateUnifiedNativeAdView(context, nativeAd, adView);
                if (frameLayout != null) {

                    frameLayout.removeAllViews();
                }
                frameLayout.addView(adView);


                view.setVisibility(View.GONE);
                view.stopShimmer();
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(false).build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {

            @Override
            @Deprecated
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);


            }
        }).build();
        AdRequest adRequest = new AdRequest.Builder().build();

        adLoader.loadAd(adRequest);
    }


    public static void showMyCustomNative(Activity activity, ViewGroup viewGroup, String type, ShimmerFrameLayout animation_vieaaw) {
        final ViewGroup viewGroup2 = viewGroup;
        if (!AdsKeys.image_small_ads.equals("") && !AdsKeys.image_big_ads.equals("") && !AdsKeys.app_name_ads.equals("") && !AdsKeys.description_one_ads.equals("") && !AdsKeys.play_store_url.equals("")) {

            View inflate = LayoutInflater.from(activity).inflate(R.layout.cust_small_native, viewGroup2, false);


            ImageView iv_banner = inflate.findViewById(R.id.iv_banner);
            TextView textView = inflate.findViewById(R.id.tv_appname);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll_app_panel);
            RatingBar ratingBar = (RatingBar) inflate.findViewById(R.id.ad_stars);
            TextView textView2 = inflate.findViewById(R.id.tv_rating);
            TextView textView3 = inflate.findViewById(R.id.tv_download);
            TextView textView4 = inflate.findViewById(R.id.tv_desc);
            Button button = (Button) inflate.findViewById(R.id.btn_install);

            View view = inflate;
            ((RequestBuilder) Glide.with(activity).load(AdsKeys.image_big_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).listener(new RequestListener<Drawable>() {
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    return false;
                }

                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    Toast.makeText(activity, glideException.getMessage() + "", Toast.LENGTH_SHORT).show();
                    viewGroup2.removeAllViews();


                    return false;
                }
            }).into((ImageView) inflate.findViewById(R.id.iv_banner));
            ((RequestBuilder) Glide.with(activity).load(AdsKeys.image_small_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into((ImageView) inflate.findViewById(R.id.iv_logo));
            textView.setText(AdsKeys.app_name_ads);
            linearLayout.setVisibility(View.GONE);
            button.setText(AdsKeys.button_txt);


            textView4.setText(AdsKeys.description_one_ads);
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Globals.openChromeCustomTabUrl(activity, AdsKeys.play_store_url);
                }
            });
            iv_banner.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Globals.openChromeCustomTabUrl(activity, AdsKeys.play_store_url);
                }
            });
            viewGroup.removeAllViews();
            viewGroup2.addView(view);

//            if ((customModel.getCustom_ads_details().size() - 1) == customcount) {
//                customcount = 0;
//            } else {
//                customcount++;
//
//            }
            if (animation_vieaaw != null) {
                animation_vieaaw.setVisibility(View.GONE);
                animation_vieaaw.stopShimmer();
            }
            return;
        }
    }


    private static void populateUnifiedNativeAdView(Activity activity, NativeAd nativeAd, NativeAdView adView) {

        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

//        if (happyAppStructureBase.getSecond_native_layout_on_off()==1){
//
//            Animation animBlink = AnimationUtils.loadAnimation(activity,
//                    R.anim.blink);
//            adView.findViewById(R.id.ad_call_to_action).startAnimation(animBlink);
//        }
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (AdsKeys.blinksAdsButton.equals("true")) {
            YoYo.with(Techniques.Flash).duration(2000).repeat(1000).playOn(adView.findViewById(R.id.ad_call_to_action));
        }


        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

    }


}

