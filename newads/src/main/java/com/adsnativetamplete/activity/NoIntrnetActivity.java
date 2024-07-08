package com.adsnativetamplete.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.ads.nativetemplates.R;

public class NoIntrnetActivity extends AppCompatActivity {

    private static NoIntrnetActivity activity;

    public static void killMe() {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_intrnet);
        activity = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        YoYo.with(Techniques.Bounce)
                .duration(7000)
                .playOn(findViewById(R.id.cloudanimation));


        LinearLayout linearLayout = findViewById(R.id.nointrnet);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
    }
}