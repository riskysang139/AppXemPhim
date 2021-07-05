package com.example.appxemphim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bumptech.glide.Glide;
import com.example.appxemphim.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        Glide.with(getBaseContext()).load(R.drawable.logomovieapp).circleCrop().into(binding.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },3000);
    }
}