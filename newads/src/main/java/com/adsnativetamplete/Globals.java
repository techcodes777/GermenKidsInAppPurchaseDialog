package com.adsnativetamplete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.module.AppGlideModule;
import com.google.android.ads.nativetemplates.R;

public class Globals extends AppGlideModule {
    public static Uri urishare;

    private static int chatCount = 0;
    private static int nativeCount = 0;
    private static int AdsonAdFailedToLoad = 0;
    private static  int nativeCountfaileadsCount=0;
    public static int getAdsonAdFailedToLoad() {
        return ++AdsonAdFailedToLoad;
    }
    public static int getNativenativeCountfaileadsCountCount() {
        return ++nativeCountfaileadsCount;
    }
    public static int getChatCount() {
        return ++chatCount;
    }


    public static int getNativeCount() {
        return ++nativeCount;
    }
    public static Boolean CheckNet(Context context) {
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return Boolean.valueOf(activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    public static void setPrefBoolean(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(context.getString(R.string.app_name), 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static Boolean getPrefBoolean(Context context, String str) {
        if (context != null) {
            return Boolean.valueOf(context.getSharedPreferences(context.getString(R.string.app_name), 0).getBoolean(str, false));
        }
        return false;
    }

    public static void showAdClickAlert(Context context, AdClickAlert adClickAlert) {
        adClickAlert.onOkClick();
    }

    @SuppressLint("WrongConstant")
    public static void openChromeCustomTabUrl(Context context, String str) {
        try {
            if (isAppInstalled(context, "com.android.chrome")) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(Color.parseColor("#66bb6a"));
                builder.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left);
                builder.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent build = builder.build();
                build.intent.setPackage("com.android.chrome");
                build.intent.setFlags(268435456);
                build.launchUrl(context, Uri.parse(str));
                return;
            }
            CustomTabsIntent.Builder builder2 = new CustomTabsIntent.Builder();
            builder2.setToolbarColor(Color.parseColor("#66bb6a"));
            builder2.setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left);
            builder2.setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right);
            CustomTabsIntent build2 = builder2.build();
            build2.intent.setFlags(268435456);
            build2.launchUrl(context, Uri.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String getPlaystoreUrl(String str) {
        return "https://play.google.com/store/apps/details?id=" + str;
    }

    public interface AdClickAlert {
        void onCanclelick();

        void onOkClick();
    }
}
