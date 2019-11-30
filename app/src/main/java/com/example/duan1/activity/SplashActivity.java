package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.duan1.MusicSqlite;
import com.example.duan1.R;
import com.example.duan1.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private MusicSqlite musicSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        musicSqlite = new MusicSqlite(this);
        musicSqlite.createDataBase();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1500);
    }

}
