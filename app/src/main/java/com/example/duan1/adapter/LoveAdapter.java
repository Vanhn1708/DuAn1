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


import com.example.duan1.model.PlayMedia;
import com.example.duan1.R;
import com.example.duan1.model.Artists;
import com.example.duan1.model.Song;

import java.util.List;

class LoveHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvArtist;
    ImageView img1;
    ImageView img2;

    public LoveHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);

        img2 = itemView.findViewById(R.id.img2);
    }
}


public class LoveAdapter extends RecyclerView.Adapter<ArtistHolder> {
    Context context;
    List<Song> list;
    PlayMedia playMedia;
    ImageView imgPre, imgNext;

    public LoveAdapter(Context context, List<Song> list, ImageView imgPre, ImageView imgNext) {
        this.context = context;
        this.list = list;
        this.imgNext = imgNext;
        this.imgPre = imgPre;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistHolder(LayoutInflater.from(context).inflate(R.layout.item_love, parent, false));
    }

    int id = 0;

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, final int position) {
        final Song artist = list.get(position);
        holder.tvName.setText(artist.music);

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
