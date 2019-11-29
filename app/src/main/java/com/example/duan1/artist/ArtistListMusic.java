package com.example.duan1.artist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.duan1.AdapterAlbum.AlbumAdapter;
import com.example.duan1.AdapterListOfSinger.ListOfSingerAdapter;
import com.example.duan1.R;
import com.example.duan1.activity.ArtistsActivity;
import com.example.duan1.adapter.ArtistAdapter;
import com.example.duan1.model.Artists;
import com.example.duan1.model.Music;
import com.example.duan1.model.PlayMedia;
import com.example.duan1.model.Single;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArtistListMusic extends AppCompatActivity {
    private RecyclerView rccAllMusicOfSinger;
    private List<File> mySongs;
    private List<Music> musicList = new ArrayList<>();
    private List<Single> strings = new ArrayList<>();
    private ListOfSingerAdapter listOfSingerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list_music);

        rccAllMusicOfSinger = findViewById(R.id.rccAllMusicOfSinger);
        rccAllMusicOfSinger.setLayoutManager(new LinearLayoutManager(this));
        rccAllMusicOfSinger.setHasFixedSize(true);
        Bundle bundle = getIntent().getExtras();
        String nameSinger = bundle.getString("nameSinger");
        setTitle("Bai hat cua " + " " + nameSinger);

     mySongs = findSongs(Environment.getExternalStorageDirectory() );
        for (int i = 0; i < mySongs.size(); i++) {
            Music music = new Music();
            music.setName(mySongs.get(i).getName().replace("", ""));
            music.setPath(mySongs.get(i).getPath());
            musicList.add(music);
        }

        for (int i = 0; i < musicList.size(); i++) {

            try {
                MediaMetadataRetriever md = new MediaMetadataRetriever();
                Log.e("errpe", musicList.get(i).getPath());
                if (musicList.get(i).getPath().equals("/storage/emulated/0/Android/data/com.google.android.zdt.data/com.zing.mp3")) {
                    continue;
                } else {
                    md.setDataSource(musicList.get(i).getPath());
                    String genre = md.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                    Single single = new Single();
                    if (genre.equals(nameSinger)){
                       String h=musicList.get(i).getName();
                        single.setGenre(h);
                        strings.add(single);
                    }else
                        continue;


                }

            } catch (IllegalArgumentException e) {
             Log.e("abi",e.getMessage());
            }

        }

        Log.e("abu" + nameSinger,strings.size()+"");
        listOfSingerAdapter = new ListOfSingerAdapter(this, strings);
        rccAllMusicOfSinger.setAdapter(listOfSingerAdapter);


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


}
