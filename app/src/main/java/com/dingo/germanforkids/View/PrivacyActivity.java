package com.dingo.germanforkids.View;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dingo.germanforkids.R;
import com.dingo.germanforkids.databinding.ActivityPrivacyBinding;

public class PrivacyActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPrivacyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.simpleWebView.loadUrl("https://learnkidsgame.ourportfolios.co/privacy-policy/");
        binding.imgBacks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBacks:
                onBackPressed();
                break;
        }
    }
}