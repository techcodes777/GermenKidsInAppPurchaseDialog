package com.dingo.germanforkids.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.adsnativetamplete.ads.InterstitialAds;
import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityWritingDialogBinding;

public class WritingDialogActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityWritingDialogBinding binding;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWritingDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
        binding.imgBacks.setOnClickListener(this);
        binding.linearLayout1.setOnClickListener(this);
        binding.linearLayout2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBacks:
                onBackPressed();
                break;
            case R.id.linearLayout1:
                InterstitialAds.showAds(this, new Intent(this, MainDraw.class).putExtra("name", name), false, true);
                break;
            case R.id.linearLayout2:
                InterstitialAds.showAds(this, new Intent(getApplicationContext(), MainDraw.class).putExtra("name", name), false, true);
                break;
        }
    }

    private void initComponents() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            name = intent.getExtras().getString("name");
        }
        binding.txtTittle.setText(name);
    }
}