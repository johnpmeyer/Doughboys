package com.beginner.doughboys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable runnable3secs = new Runnable() {
            @Override
            public void run() {
                nextActivity();
                finish();
            }
        };

        Handler myHandler = new Handler();
        myHandler.postDelayed(runnable3secs, 3000);

    }

    public void nextActivity() {
        Intent intent = new Intent(this, Signin.class);
        startActivity(intent);

    }
}
