package com.example.duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.artist.ArtistListMusic;

import com.example.duan1.model.PlayMedia;
import com.example.duan1.R;
import com.example.duan1.model.Artists;

import java.util.List;

class ArtistHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvArtist;
    ImageView img1;
    ImageView img2;

    public ArtistHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvArtist = itemView.findViewById(R.id.tvArtist);
        img1 = itemView.findViewById(R.id.img1);
        img2 = itemView.findViewById(R.id.img2);
    }
}


public class ArtistAdapter extends RecyclerView.Adapter<ArtistHolder> {
    Context context;
    List<Artists> list;
    PlayMedia playMedia;
    ImageView imgPre, imgNext;

    public ArtistAdapter(Context context, List<Artists> list, ImageView imgPre, ImageView imgNext) {
        this.context = context;
        this.list = list;
        this.imgNext = imgNext;
        this.imgPre = imgPre;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistHolder(LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false));
    }

    int id = 0;

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, final int position) {
        final Artists artist = list.get(position);
        //       holder.tvName.setText(artist.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArtistListMusic.class);
                context.startActivity(intent);
            }
        });
        holder.tvArtist.setText(artist.artist);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playMedia.onChangeMusic1(artist);
//                id = position;
//            }
//        });

//        imgPre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (id != 0) {
//                    playMedia.onChangeMusic1(list.get(id - 1));
//                    id--;
//                }
//            }
//        });
//        imgNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (id < list.size() - 1) {
//                    playMedia.onChangeMusic1(list.get(id + 1));
//                    id++;
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnChangeMusic1(PlayMedia playMedia) {
        this.playMedia = playMedia;
    }

    public void switchMusic(int position) {
    }

}
