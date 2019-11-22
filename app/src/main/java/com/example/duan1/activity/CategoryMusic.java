package com.example.duan1.activity;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.AdapterCategory.CategoryAdapter;
import com.example.duan1.R;
import com.example.duan1.adapter.ArtistAdapter;
import com.example.duan1.model.Music;
import com.example.duan1.model.Single;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMusic extends AppCompatActivity {
    private RecyclerView rccView;
    private List<File> mySongs;
    private List<Music> musicList = new ArrayList<>();
    private List<Single> strings = new ArrayList<>();
    private ArtistAdapter artistAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CategoryAdapter categoryAdapter;
    private String s;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_music);
        Bundle bundle = getIntent().getExtras();
        s = bundle.getString("theLoai");
        setTitle("Thể loại "+ s);
        rccView = (RecyclerView) findViewById(R.id.rccViewa);
        layoutManager = new LinearLayoutManager(this);
        rccView.setLayoutManager(layoutManager);
        rccView.setHasFixedSize(true);
        mySongs = findSongs(Environment.getExternalStorageDirectory());

        for (int i = 0; i < mySongs.size(); i++) {
            Music music = new Music();
            music.setName(mySongs.get(i).getName().replace(".mp3", ""));
            music.setPath(mySongs.get(i).getPath());
            musicList.add(music);
        }

        for (int i = 0; i < musicList.size(); i++) {

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            try {
                mmr.setDataSource(musicList.get(i).getPath());
                String genre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
                String single = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String nameSong = mySongs.get(i).getName().replace(".mp3", "");
                Single single1 = new Single();
                single1.setNameSong(nameSong);
                single1.setSingle(single);
                single1.setGenre(genre);
                if (s.equals(genre)){
                    strings.add(single1);
                }else {
                    continue;
                }
                categoryAdapter =new CategoryAdapter(this,strings);
                rccView.setAdapter(categoryAdapter);
            }catch (Exception e){
                e.getMessage();
            }


//            if(strings.size()==0){
//                strings.add(single);
//            }else {
//                if (strings.get(0).getGenre().equals(genre)){
//                    Toast.makeText(CategoryMusic.this, "dsadsa",Toast.LENGTH_SHORT).show();
//                }else {
//                    strings.add(single);
//                }
//            }


        }



    }

    public List<File> findSongs(File root) {
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
