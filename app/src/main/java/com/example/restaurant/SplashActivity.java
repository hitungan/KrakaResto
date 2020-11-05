package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(ChooseRole.class)
                .withSplashTimeOut(2500)
                .withBackgroundColor(Color.parseColor("#ffffff"))

                .withLogo(R.mipmap.logo_new);

        View easysplashScreen = config.create();
        setContentView(easysplashScreen);

    }
}
