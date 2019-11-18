package com.example.duan1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duan1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView tracks = (ImageView) findViewById(R.id.tracks);
        ImageView artists = (ImageView) findViewById(R.id.artists);
        ImageView playlist = (ImageView) findViewById(R.id.playlist);
        ImageView albums = (ImageView) findViewById(R.id.albums);

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
