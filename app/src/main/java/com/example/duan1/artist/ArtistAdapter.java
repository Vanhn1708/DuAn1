package com.example.duan1.artist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.ArtistListMusic;
import com.example.duan1.ArtistsActivity;
import com.example.duan1.MainActivity;
import com.example.duan1.Music;
import com.example.duan1.PlayMedia;
import com.example.duan1.R;

import java.util.List;

class MusicHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvArtist;
    ImageView img1;
    ImageView img2;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvArtist = itemView.findViewById(R.id.tvArtist);
//        img1 = itemView.findViewById(R.id.img1);
        img2 = itemView.findViewById(R.id.img2);
    }
}


public class ArtistAdapter extends RecyclerView.Adapter<MusicHolder> {
    Context context;
    List<Artists> list;
    PlayMedia playMedia;
//    ImageView imgPre, imgNext;

    public ArtistAdapter(Context context, List<Artists> list, ImageView imgPre, ImageView imgNext) {
        this.context = context;
        this.list = list;
//        this.imgNext = imgNext;
//        this.imgPre = imgPre;
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false));
    }

    int id = 0;

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, final int position) {
        final Artists artist = list.get(position);
//        holder.tvName.setText(artist.name);
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

    private void deleteNote(int position) {
//        sachDAO.deleteSachByID(list.get(position).getMaSach());
        list.remove(position);
        notifyDataSetChanged();
    }
}
