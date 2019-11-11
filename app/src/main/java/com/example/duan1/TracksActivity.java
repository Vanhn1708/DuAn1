package com.example.duan1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class TracksActivity extends AppCompatActivity {
    static MediaPlayer mediaPlayer;
    private ListView lvList;
    String[] items;

    private SeekBar skB;
    private ImageButton btnPlay;
    private ImageButton bntPause;
    private ImageButton btnStop;
    private Uri uri;
    private ArrayList<File> mySong;
    Handler handler = new Handler();
    Runnable runnable;
    private TextView begin;
    private TextView end;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_list);

        lvList = findViewById(R.id.lvList);

        begin = findViewById(R.id.begin);
        end = findViewById(R.id.end);
        skB = findViewById(R.id.skB);

        btnPlay = findViewById(R.id.btnPlay);
        bntPause = findViewById(R.id.bntPause);
        btnStop = findViewById(R.id.btnStop);


        handler = new Handler();
//        update = new Thread(){
//            @Override
//            public void run() {
//                int total = mediaPlayer.getDuration();
//                int current = 0;
//                skB.setMax(total);
//                while (current <total){
//                    try {
//                        sleep(500);
//                        current = mediaPlayer.getCurrentPosition();
//                        skB.setProgress(current);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//
//
////                super.run();
//            }
//        };
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }


//âmmmaamwacdddđ
        mySong = findSongs(Environment.getExternalStorageDirectory());
        items = new String[mySong.size()];
        for (int i = 0; i < mySong.size(); i++) {

            items[i] = mySong.get(i).getName().toString().replace(".mp3", "");


        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(), R.layout.song_layout, R.id.tvSong, items);
        lvList.setAdapter(adp);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Uri uri = Uri.parse(mySong.get(i).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                total = mediaPlayer.getDuration();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(final MediaPlayer mediaPlayer) {
                        skB.setMax(mediaPlayer.getDuration());
                        mediaPlayer.start();
                        changeSeekBar();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (mediaPlayer != null) {
                                    try {
                                        Message msg = new Message();
                                        msg.what = mediaPlayer.getCurrentPosition();

                                        hand.sendMessage(msg);
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();


                    }
                });

                skB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if (b) {
                            mediaPlayer.seekTo(i);
                        }

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });


                bntPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.pause();
                    }
                });
                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                    }
                });

                btnStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.stop();
                        handler.removeCallbacks(runnable);
                        resetseek();
                    }
                });

            }
        });
        Log.e("mysong", mySong.size() + "");


    }


    private void changeSeekBar() {
        skB.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private Handler hand = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int curr = msg.what;
            skB.setProgress(curr);
            String Time = cear(curr);
            begin.setText(Time);

            String Tiemdend = cear(total - curr);
            end.setText("-" + Tiemdend);

        }
    };

    public String cear(int time) {
        String tineLable = "";
        int ein = time / 1000 / 60;
        int ec = time / 1000 % 60;
        tineLable = ein + ":";
        if (ec < 10) tineLable += "0";
        tineLable += ec;
        return tineLable;
    }

    public void resetseek() {
        if (skB.getProgress() > 0) {
            skB.setProgress(0);
        }
    }

    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();


        for (File singleFile : files) {
            if (singleFile.isDirectory() && !singleFile.isHidden()) {
                al.addAll(findSongs(singleFile));

            } else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")) {
                    al.add(singleFile);
                }
            }
        }
        return al;


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }
}
