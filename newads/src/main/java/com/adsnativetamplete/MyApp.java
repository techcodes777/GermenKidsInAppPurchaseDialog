package com.adsnativetamplete;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.adsnativetamplete.activity.NativeBaseActivity;
import com.adsnativetamplete.ads.AppOpenManager;
import com.adsnativetamplete.ads.InitializeAds;
import com.adsnativetamplete.notiservice.AlarmBroadcastReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyApp extends Application {

    public static AppOpenManager appOpenManager;
    private static MyApp MyApplications;

    public static MyApp getApplication() {
        return MyApplications;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        NativeBaseActivity.isSubriptionDone = false;
        MyApplications = this;
        Sharedads.getInstance().init(this);
        InitializeAds.initializeAds(this);
        new NativePreferenceUtil(this).setCountRate();
        startAlarmBroadcastReceiver(this);
    }

    public static void startAlarmBroadcastReceiver(Context context) {
        Intent _intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, _intent, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
