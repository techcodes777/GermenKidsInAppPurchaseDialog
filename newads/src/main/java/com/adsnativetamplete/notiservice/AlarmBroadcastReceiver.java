package com.adsnativetamplete.notiservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import com.adsnativetamplete.activity.NativeBaseActivity;
import com.google.android.ads.nativetemplates.R;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    void showNotification(Context context) {
        String CHANNEL_ID = "987654321";
        NotificationCompat.Builder mBuilder;
        Intent notificationIntent = new Intent(context, NativeBaseActivity.class);
        Bundle bundle = new Bundle();
        notificationIntent.putExtras(bundle);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "Videos", NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(mChannel);
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_logo)
                    .setLights(Color.RED, 300, 300)
                    .setChannelId(CHANNEL_ID)
                    .setContentTitle("App Videos");
        } else {
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_logo)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentTitle("App Videos");
        }

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setContentText("App Starting Schedule");
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(1, mBuilder.build());
    }
}