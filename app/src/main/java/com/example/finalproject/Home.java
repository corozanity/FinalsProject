package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(Home.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
