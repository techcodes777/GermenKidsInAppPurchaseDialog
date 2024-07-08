package com.dingo.germanforkids;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.adsnativetamplete.MyApp;
import com.dingo.germanforkids.purchase.Pref;
import com.dingo.germanforkids.utils.Utils;


public class AppControl extends MyApp {
    Context context;
    public static TextToSpeech textToSpeech;
    @Override
    public void onCreate() {
        super.onCreate();
        Pref.getInstance().init(getApplicationContext());
        context=this;
        new Utils(context);
    }

}
