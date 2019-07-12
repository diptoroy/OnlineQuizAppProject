package com.diptoroy.example.onlinequizappproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.diptoroy.example.onlinequizappproject.R;

public class SplashScreen extends AppCompatActivity {

    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent splash = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(splash);
                finish();
            }
        },TIME_OUT);
    }
}
