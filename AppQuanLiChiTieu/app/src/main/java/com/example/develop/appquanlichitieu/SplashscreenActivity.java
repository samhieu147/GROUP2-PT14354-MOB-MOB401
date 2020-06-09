package com.example.develop.appquanlichitieu;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //Sau 3s tự chuyển sang màn hình C01.2
        Thread Time = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(SplashscreenActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        Time.start();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
