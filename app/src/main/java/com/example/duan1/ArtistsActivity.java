package com.example.duan1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.artist.ArtistAdapter;
import com.example.duan1.artist.Artists;

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
        rvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ArtistsActivity.this,ArtistListMusic.class);
                startActivity(intent);
                Log.e("abc", "ABC");
            }
        });
        loadMusic();
//        initView();

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
                String[] projection = { MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST};
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
                            try {
                                if (isCreated) {
                                    mediaPlayer.stop();
                                    mediaPlayer.release();
                                }
                                isPlaying = false;
//                                tvName.setText(artists.name);
                                mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(artists.path);
                                mediaPlayer.prepare();
                                seekBar.setMax(mediaPlayer.getDuration());
                                onPlay(null);
                                isCreated = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onChangeMusic(Music music) {

                        }
                    });
                    Log.e("count", list.size() + "");
               rvMusic.setAdapter(adapter);
                 rvMusic.setLayoutManager(new LinearLayoutManager(this));

                }
            }
        }
    }

    private void initView() {
        tvName = findViewById(R.id.tvName);
        seekBar = findViewById(R.id.seekBar);
        tvStart = findViewById(R.id.tvStart);
        tvEnd = findViewById(R.id.tvEnd);
        imgPlay = findViewById(R.id.imgPlay);
        seekBar.setEnabled(false);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvStart.setText(miliesToString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                startTime = seekBar.getProgress();
            }
        });
    }

    private double startTime, finalTime;

    public void onPlay(View view) {
        if (isCreated) {
            if (isPlaying) {
                seekBar.setEnabled(false);
                mediaPlayer.pause();
                imgPlay.setImageResource(android.R.drawable.ic_media_play);
                isPlaying = false;
            } else {
                isPlaying = true;
                seekBar.setEnabled(true);
                mediaPlayer.start();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                tvStart.setText(miliesToString(startTime));
                tvEnd.setText(miliesToString(finalTime));
                seekBar.setProgress((int) startTime);
                myHanler.postDelayed(UpdateSongTime, 100);
                imgPlay.setImageResource(android.R.drawable.ic_media_pause);
            }
        }
    }

    private Handler myHanler = new Handler();
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekBar.setProgress((int) startTime);
            myHanler.postDelayed(this, 100);
        }
    };


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
            onPlay(null);
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
