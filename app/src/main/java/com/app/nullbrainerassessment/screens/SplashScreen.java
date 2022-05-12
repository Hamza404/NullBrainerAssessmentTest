package com.app.nullbrainerassessment.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.app.nullbrainerassessment.R;
import com.app.nullbrainerassessment.databinding.ActivityMainBinding;
import com.app.nullbrainerassessment.databinding.SplashScreenActivityBinding;

public class SplashScreen extends AppCompatActivity {

    SplashScreenActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashScreenActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int SPLASH_DELAY = 1000;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, LoginScreen.class));
            finish();
        }, SPLASH_DELAY);
    }
}