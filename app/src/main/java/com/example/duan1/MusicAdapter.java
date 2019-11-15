package com.example.duan1;

import android.content.Context;
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

import java.util.List;

class MusicHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvArtist;
    ImageView img1;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvArtist = itemView.findViewById(R.id.tvArtist);
        img1 = itemView.findViewById(R.id.img1);
    }
}


public class MusicAdapter extends RecyclerView.Adapter<MusicHolder> {
    Context context;
    List<Music> list;
    PlayMedia playMedia;
    ImageView imgPre, imgNext;

    public MusicAdapter(Context context, List<Music> list, ImageView imgPre, ImageView imgNext) {
        this.context = context;
        this.list = list;
        this.imgNext = imgNext;
        this.imgPre = imgPre;
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(LayoutInflater.from(context).inflate(R.layout.item_music, parent, false));
    }

    int id = 0;

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, final int position) {
        final Music music = list.get(position);
        holder.tvName.setText(music.name);
        holder.tvArtist.setText(music.artist);
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.settings) {
                            Toast.makeText(context, "dsa", Toast.LENGTH_SHORT).show();

                        } else if (id == R.id.tools) {
                            Toast.makeText(context, "dddsda", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMedia.onChangeMusic(music);
                id = position;
            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) {
                    playMedia.onChangeMusic(list.get(id - 1));
                    id--;
                }
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id < list.size() - 1) {
                    playMedia.onChangeMusic(list.get(id + 1));
                    id++;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnChangeMusic(PlayMedia playMedia) {
        this.playMedia = playMedia;
    }

    public void switchMusic(int position) {
    }
}
