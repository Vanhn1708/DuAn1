package com.example.duan1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    TextView tvName, title;
    ImageView img1;
    ImageView image;

    public ArtistHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        title = itemView.findViewById(R.id.title);

        image = itemView.findViewById(R.id.image);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArtistListMusic.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameSinger",artist.artist);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.title.setText(artist.artist);

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
