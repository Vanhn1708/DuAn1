package com.example.duan1.love;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.duan1.MusicSqlite;
import com.example.duan1.R;



import com.example.duan1.adapter.LoveListAdapter;
import com.example.duan1.adapter.MusicAdapter;
import com.example.duan1.model.Artists;
import com.example.duan1.model.Music;
import com.example.duan1.model.PlayMedia;
import com.example.duan1.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListMusicLove extends AppCompatActivity {
    private RecyclerView rccListLove;
    private MusicSqlite musicSqlite ;
    private List<Music> musicList;
    private LoveListAdapter listMusicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music_love);
        rccListLove = findViewById(R.id.rccListLove);
        rccListLove.setLayoutManager(new LinearLayoutManager(this));
        rccListLove.setHasFixedSize(true);

        musicSqlite = new MusicSqlite(this);

        musicList = musicSqlite.musicList();
        Log.e("size",musicList.size()+"");
        for (int i = 0; i<musicList.size();i++){
            Music music= new Music();
            Log.e("name",      music.name = musicList.get(i).getName());
            Log.e("path",      music.name = musicList.get(i).getPath());
            Log.e("artit",      music.name = musicList.get(i).getArtist());

        }

        if (musicList != null){
            listMusicAdapter = new LoveListAdapter(musicList,this,musicSqlite);
            rccListLove.setAdapter(listMusicAdapter);

        }

    }

}
