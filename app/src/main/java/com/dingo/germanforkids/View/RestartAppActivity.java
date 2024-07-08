package com.dingo.germanforkids.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dingo.germanforkids.databinding.ActivityRestartAppBinding;

public class RestartAppActivity extends AppCompatActivity {
    ActivityRestartAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestartAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRestartApp.setOnClickListener(view -> {
            finishAffinity();
            Intent intent = new Intent(RestartAppActivity.this, SplashActivity.class);
            startActivity(intent);
        });
    }
}