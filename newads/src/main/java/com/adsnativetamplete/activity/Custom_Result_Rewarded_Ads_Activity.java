package com.adsnativetamplete.activity;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.adsnativetamplete.AdsKeys;
import com.adsnativetamplete.Globals;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.ads.nativetemplates.R;

public class Custom_Result_Rewarded_Ads_Activity extends AppCompatActivity {
    public static int random;
    public String action_str;
    private LinearLayout LLTop;
    private LinearLayout adPersonalCloseBtn;
    private LinearLayout adPersonalLlPlayStore;
    private ImageView ad_media_view;
    private RatingBar ad_stars;
    private RelativeLayout int_bg;
    private LinearLayout llPersonalAd;
    private LinearLayout llPersonalAdCenter;
    private TextView native_ad_call_to_action;
    private TextView querkaText;
    private TextView txt_body;
    private TextView txt_download;
    private TextView txt_rate;
    private TextView txt_title;
    private LinearLayout userCount;
    private int customcount = 0;
    private ImageView ImgClose;
    private TextView tvTimer;


    public void onBackPressed() {
    }


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int i = random;
        if (i == 2) {
            setContentView(R.layout.cust_rew2);
        } else if (i == 1) {
            setContentView(R.layout.cust_rew1);
        } else {
            setContentView(R.layout.cust_rew);
        }

        ImgClose = findViewById(R.id.ImgClose);
        ImgClose.setEnabled(false);
        ImgClose.setColorFilter(getResources().getColor(R.color.disable_code));
        tvTimer = (TextView) findViewById(R.id.tvTimer);

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        int count = prefs.getInt("count", 0); //0 is the default value.
        customcount = count;

        this.llPersonalAd = (LinearLayout) findViewById(R.id.llPersonalAd);
        this.llPersonalAdCenter = (LinearLayout) findViewById(R.id.llPersonalAdCenter);

        try {
            this.ad_media_view = (ImageView) findViewById(R.id.native_ad_media);
            this.txt_title = (TextView) findViewById(R.id.native_ad_title);
            this.txt_body = (TextView) findViewById(R.id.native_ad_social_context);
            this.txt_rate = (TextView) findViewById(R.id.txt_rate);
            this.txt_download = (TextView) findViewById(R.id.txt_download);
            this.native_ad_call_to_action = (TextView) findViewById(R.id.native_ad_call_to_action);
            this.adPersonalCloseBtn = (LinearLayout) findViewById(R.id.adPersonalCloseBtn);
            this.userCount = (LinearLayout) findViewById(R.id.userCount);
            this.adPersonalLlPlayStore = (LinearLayout) findViewById(R.id.adPersonalLlPlayStore);
            this.querkaText = (TextView) findViewById(R.id.querkaText);
            this.ad_stars = (RatingBar) findViewById(R.id.ad_stars);
            this.int_bg = (RelativeLayout) findViewById(R.id.int_bg);
            ((RequestBuilder) Glide.with((FragmentActivity) this).load(AdsKeys.image_small_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into((ImageView) findViewById(R.id.native_ad_icon));
            ((RequestBuilder) Glide.with((FragmentActivity) this).load(AdsKeys.image_big_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.ad_media_view);
            this.txt_title.setText(AdsKeys.app_name_ads);
            this.txt_body.setText(AdsKeys.description_one_ads);
            this.querkaText.setText(AdsKeys.description_two_ads);
            this.userCount.setVisibility(8);
            this.adPersonalCloseBtn.setVisibility(8);
            this.adPersonalLlPlayStore.setVisibility(8);
            this.native_ad_call_to_action.setText(AdsKeys.button_txt);

            int i2 = random;
            if (i2 == 2) {
                random = 0;
                SlideToAbove30(findViewById(R.id.llcus3));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity.FadeIn(customIntAds_Activity.findViewById(R.id.llPersonalAd));
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity2 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity2.FadeIn(customIntAds_Activity2.findViewById(R.id.main));
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity3 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity3.FadeIn(customIntAds_Activity3.findViewById(R.id.aa));
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity4 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity4.FadeIn(customIntAds_Activity4.findViewById(R.id.querkaText));
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity6 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity6.FadeIn(customIntAds_Activity6.findViewById(R.id.adPersonalLlCloseInstallBtns));
                    }
                }, 1000);
            } else if (i2 == 1) {
                random = i2 + 1;
                SlideToAbove20(findViewById(R.id.native_ad_icon));
                SlideToAbove30(findViewById(R.id.cvTopAd));
                findViewById(R.id.querkaText).setVisibility(0);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity.FadeIn(customIntAds_Activity.findViewById(R.id.aa));
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity2 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity2.FadeIn(customIntAds_Activity2.findViewById(R.id.adPersonalLlCloseInstallBtnsCenter));
                    }
                }, 2200);
            } else {
                random = i2 + 1;
                SlideToTop(findViewById(R.id.native_ad_icon), 1000);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity.SlideToAbove(customIntAds_Activity.findViewById(R.id.native_ad_title), 600);
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity2 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity2.SlideToAbove(customIntAds_Activity2.findViewById(R.id.banner), 1000);
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity3 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity3.SlideToAbove(customIntAds_Activity3.findViewById(R.id.native_ad_social_context), 1200);
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity4 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity4.SlideToAbove(customIntAds_Activity4.findViewById(R.id.querkaText), 900);
                        Custom_Result_Rewarded_Ads_Activity customIntAds_Activity7 = Custom_Result_Rewarded_Ads_Activity.this;
                        customIntAds_Activity7.SlideToAbove(customIntAds_Activity7.findViewById(R.id.adPersonalLlCloseInstallBtns), 1600);
                    }
                }, 800);
            }
            findViewById(R.id.native_ad_call_to_action).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Globals.openChromeCustomTabUrl(Custom_Result_Rewarded_Ads_Activity.this, AdsKeys.play_store_url);
                }
            });
            ImgClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    closeAd();
                }
            });
            this.adPersonalCloseBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    closeAd();
                }
            });
            new CountDownTimer(15000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    int sec = (int) (millisUntilFinished / 1000);
                    if (sec < 10) {
                        ImgClose.setEnabled(true);
                        ImgClose.setColorFilter(getResources().getColor(R.color.black));
                        ImgClose.setBackgroundResource(R.drawable.rounded_white);
                    }
                    if (sec == 0) {
                        tvTimer.setText("Rewarded Done");
                    } else {
                        tvTimer.setText(sec + " Seconds Remaining");
                    }
                }

                @Override
                public void onFinish() {
                    tvTimer.setText("Rewarded Done");
                }
            }.start();
        } catch (Exception unused) {
            closeAd();
        }
    }

    public void closeAd() {
        if (tvTimer.getText().toString().equals("Rewarded Done")) {
            setResult(RESULT_OK, null);
        } else {
            setResult(RESULT_CANCELED, null);
        }
        finish();
    }

    public void SlideToAbove(final View view, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 5.0f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            @SuppressLint("WrongConstant")
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    public void SlideToAbove30(final View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.5f, 1, 0.0f);
        translateAnimation.setDuration((long) 1);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            @SuppressLint("WrongConstant")
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    public void SlideToAbove20(final View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        loadAnimation.setFillAfter(true);
        view.startAnimation(loadAnimation);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.5f, 1, 0.0f);
        translateAnimation.setDuration((long) 1);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            @SuppressLint("WrongConstant")
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                Animation loadAnimation = AnimationUtils.loadAnimation(Custom_Result_Rewarded_Ads_Activity.this, R.anim.zoom_out);
                loadAnimation.setFillAfter(true);
                view.startAnimation(loadAnimation);
            }
        });
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    public void SlideToTop(final View view, int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -2.0f, 1, 0.0f);
        translateAnimation.setDuration((long) i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            @SuppressLint("WrongConstant")
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        translateAnimation.setFillEnabled(true);
        view.startAnimation(translateAnimation);
    }

    public void FadeIn(final View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        view.startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            @SuppressLint("WrongConstant")
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(0);
            }
        });
    }
}