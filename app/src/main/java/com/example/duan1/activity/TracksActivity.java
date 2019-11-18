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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.adapter.MusicAdapter;
import com.example.duan1.model.PlayMedia;
import com.example.duan1.R;
import com.example.duan1.model.Artists;
import com.example.duan1.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TracksActivity extends AppCompatActivity {
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
        setContentView(R.layout.selection_list);
        imgPre = findViewById(R.id.imgPre);
        imgNext = findViewById(R.id.imgNext);
        rvMusic = findViewById(R.id.rvMusic);
        loadMusic();
        initView();

    }

    MusicAdapter adapter;
    List<Music> list = new ArrayList<>();

    private void loadMusic() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            }
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null);
                String[] projection = {MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST};
                if (cursor.moveToFirst()) {
                    do {
                        list.add(new Music(cursor.getString(cursor.getColumnIndex(projection[0]))
                                , cursor.getString(cursor.getColumnIndex(projection[1]))
                                , cursor.getString(cursor.getColumnIndex(projection[2]))));
                    } while (cursor.moveToNext());
                    cursor.close();
                    adapter = new MusicAdapter(TracksActivity.this, list, imgPre, imgNext);
                    adapter.setOnChangeMusic(new PlayMedia() {
                        @Override
                        public void onChangeMusic(Music music) {
                            try {
                                if (isCreated) {
                                    mediaPlayer.stop();
                                    mediaPlayer.release();
                                }
                                isPlaying = false;
                                tvName.setText(music.name);
                                mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(music.path);
                                mediaPlayer.prepare();
                                seekBar.setMax(mediaPlayer.getDuration());
                                onPlay(null);
                                isCreated = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onChangeMusic1(Artists artist) {

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

