package com.example.duan1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView tracks = findViewById(R.id.tracks);
        ImageView artists = findViewById(R.id.artists);
        ImageView playlist = findViewById(R.id.playlist);
        ImageView albums = findViewById(R.id.albums);


        tracks.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Tracks category is clicked on.
            @Override
            public void onClick(View view) {
                Intent tracksIntent = new Intent(MainActivity.this, TracksActivity.class);
                startActivity(tracksIntent);
            }
        });
        albums.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Albums category is clicked on.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
                } else {
                    if (Build.VERSION.SDK_INT < 19) {
                        startActivity(intent);
                    } else {
                        startActivity(intent);
                    }
                }

                startActivity(intent);
            }
        });

        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playlist category is clicked on.
            @Override
            public void onClick(View view) {
                Intent playlistIntent = new Intent(MainActivity.this, LoveActivity.class);
                startActivity(playlistIntent);
            }
        });

    }


}
