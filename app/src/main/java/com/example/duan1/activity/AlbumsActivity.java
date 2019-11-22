package com.example.duan1.activity;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.AdapterAlbum.AlbumAdapter;
import com.example.duan1.PresenterView;
import com.example.duan1.Presenterr;
import com.example.duan1.R;
import com.example.duan1.adapter.ArtistAdapter;
import com.example.duan1.model.Music;
import com.example.duan1.model.Single;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity /* implements PresenterView */{
    private List<File> mySongs;
    private List<Music> musicList = new ArrayList<>();
    private List<Single> strings = new ArrayList<>();
    private ArtistAdapter artistAdapter;
    private RecyclerView rccView;
    private AlbumAdapter albumAdapter;
    private Presenterr presenterr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        rccView = findViewById(R.id.rccVieww);
        rccView.setLayoutManager(new LinearLayoutManager(this));
        rccView.setHasFixedSize(true);
//        presenterr = new Presenterr(this,AlbumsActivity.this);
//        presenterr.GetSong();
        mySongs = findSongs(Environment.getExternalStorageDirectory());
        for (int i = 0; i < mySongs.size(); i++) {
            Music music = new Music();
            music.setName(mySongs.get(i).getName().replace(".mp3", ""));
            music.setPath(mySongs.get(i).getPath());
            musicList.add(music);
        }


        for (int i = 0; i < musicList.size(); i++) {


            try {
                MediaMetadataRetriever md = new MediaMetadataRetriever();
                Log.e("errpe",musicList.get(i).getPath() );
                if (musicList.get(i).getPath().equals("/storage/emulated/0/Android/data/com.google.android.zdt.data/com.zing.mp3")){
                    continue;
                }else {
                    md.setDataSource(musicList.get(i).getPath());
                    String genre = md.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
                    Single single = new Single();
                    single.setGenre(genre);
                    if(strings.size()==0){
                        strings.add(single);
                    }else {
                        try {

                            if (strings.get(0).getGenre() != null&&strings.get(0).getGenre().equals(genre)){
                                continue;
                            }else {
                                strings.add(single);
                            }
                        }catch (NullPointerException e){
                            Log.d("error",e.getMessage());
                            e.getMessage();
                        }

                    }
                }

            }catch (IllegalArgumentException e){
                e.getMessage();
            }

        }

        albumAdapter =new AlbumAdapter(this,strings);
        rccView.setAdapter(albumAdapter);


    }

//    @Override
//    public void data(ArrayList<File> files) {
//        this.mySongs = files;
//
//
//    }


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
}

