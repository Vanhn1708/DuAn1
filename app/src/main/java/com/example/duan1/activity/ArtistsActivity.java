package com.example.duan1.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.model.Music;
import com.example.duan1.model.PlayMedia;
import com.example.duan1.R;
import com.example.duan1.adapter.ArtistAdapter;
import com.example.duan1.model.Artists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArtistsActivity extends AppCompatActivity {
    private TextView tvName;
    private SeekBar seekBar;
    private TextView tvStart;
    private TextView tvEnd;
    private ImageView imgPlay;
    MediaPlayer mediaPlayer;
    Boolean isPlaying = true;
    private RecyclerView rvMusic;
    boolean isCreated;
    ImageView imgPre, imgNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_activity);

        rvMusic = findViewById(R.id.rvMusic);
        loadMusic();

    }

    ArtistAdapter adapter;
    List<Artists> list = new ArrayList<>();

    private void loadMusic() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null);
                String[] projection = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST};
                if (cursor.moveToFirst()) {
                    do {
                        list.add(new Artists(
                                cursor.getString(cursor.getColumnIndex(projection[0]))
                                , cursor.getString(cursor.getColumnIndex(projection[1]))));
                    } while (cursor.moveToNext());
                    cursor.close();
                    adapter = new ArtistAdapter(ArtistsActivity.this, list, imgPre, imgNext);
                    adapter.setOnChangeMusic1(new PlayMedia() {


                        @Override
                        public void onChangeMusic1(Artists artists) {

                        }

                        @Override
                        public void onChangeMusic(Music music) {

                        }
                    });
                    Log.e("count", list.size() + "");
                    rvMusic.setAdapter(adapter);
                    rvMusic.setLayoutManager(new GridLayoutManager(this, 2));

                }
            }
        }
    }



    private double startTime, finalTime;


    public String miliesToString(double time) {
        String minute = TimeUnit.MILLISECONDS.toMinutes((long) time) < 10 ? "0" + TimeUnit.MILLISECONDS.toMinutes((long) time) : TimeUnit.MILLISECONDS.toMinutes((long) time) + "";
        int s = (int) (TimeUnit.MILLISECONDS.toSeconds((long) time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) time)));
        String second = s < 10 ? "0" + s : s + "";
        return String.format("%s : %s",
                minute,
                second);
    }

    public void onStop(View view) {
        if (isCreated) {
            isPlaying = true;
//            onPlay(null);
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isCreated) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
