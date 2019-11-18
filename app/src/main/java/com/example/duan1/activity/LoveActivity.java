package com.example.duan1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.R;
import com.example.duan1.love.ListAlbumLove;
import com.example.duan1.love.ListMusicLove;

public class LoveActivity extends AppCompatActivity {
    ImageView imgsinger;
    ImageView imglbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.love_activity);
        imgsinger = findViewById(R.id.imgsinger);
        imglbum = findViewById(R.id.imglbum);
        imgsinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoveActivity.this, ListMusicLove.class);
                startActivity(intent);
            }
        });

        imglbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoveActivity.this, ListAlbumLove.class);
                startActivity(intent);
            }
        });
    }
}
