package com.adsnativetamplete.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.adsnativetamplete.AdsKeys;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.ads.nativetemplates.R;

public class CustomAppOpenAds_Activity extends AppCompatActivity {
    public static Activity activity;
    RatingBar ad_stars;
    TextView txt_rate;
    private TextView btn_call_to_action;
    private ImageView iv_ad_icon;
    private ImageView iv_myapp_logo;
    private LinearLayout ll_continue_app;
    private ImageView media_view;
    private TextView txt_context;
    private TextView txt_download;
    private TextView txt_myapp_name;
    private TextView txt_title;
    private int customcount = 0;

    public void onBackPressed() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cust_appopen_qureka);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        int count = prefs.getInt("count", 0); //0 is the default value.
        customcount = count;

        this.ll_continue_app = (LinearLayout) findViewById(R.id.ll_continue_app);
        this.iv_myapp_logo = (ImageView) findViewById(R.id.iv_myapp_logo);
        this.txt_myapp_name = (TextView) findViewById(R.id.txt_myapp_name);
        this.media_view = (ImageView) findViewById(R.id.media_view);
        this.txt_title = (TextView) findViewById(R.id.txt_appname);
        this.iv_ad_icon = (ImageView) findViewById(R.id.iv_ad_icon);
        this.ad_stars = (RatingBar) findViewById(R.id.ad_stars);
        this.txt_rate = (TextView) findViewById(R.id.txt_rate);
        this.txt_download = (TextView) findViewById(R.id.txt_download);
        this.txt_context = (TextView) findViewById(R.id.txt_context);
        this.btn_call_to_action = (TextView) findViewById(R.id.btn_call_to_action);
//            SharedPreferences sharedPreferences = getSharedPreferences(activity.getPackageName(), 0);
//            this.mysharedpreferences = sharedPreferences;
        this.txt_myapp_name.setText(getString(R.string.app_name));
        txt_myapp_name.setVisibility(View.VISIBLE);
//                ((RequestBuilder) Glide.with((FragmentActivity) this).load(customModel.getCustom_ads_details().get(customcount).getSmall_logo()).diskCacheStrategy(DiskCacheStrategy.ALL)).into((ImageView) findViewById(R.id.iv_myapp_logo));

        ((RequestBuilder) Glide.with((FragmentActivity) this).load(AdsKeys.image_big_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.media_view);
        this.txt_title.setText(AdsKeys.app_name_ads);
        this.txt_context.setText(AdsKeys.description_two_ads);
        ((RequestBuilder) Glide.with((FragmentActivity) this).load(AdsKeys.image_small_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.iv_ad_icon);
        ((RequestBuilder) Glide.with((FragmentActivity) this).load(AdsKeys.image_small_ads).diskCacheStrategy(DiskCacheStrategy.ALL)).into(this.iv_myapp_logo);

        this.btn_call_to_action.setText(AdsKeys.button_txt);
        this.btn_call_to_action.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AdsKeys.play_store_url)));
            }
        });
        this.ll_continue_app.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                closeAd();
            }
        });
        return;
    }

    public void closeAd() {
        finish();
    }

}
