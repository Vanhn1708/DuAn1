package com.example.duan1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
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

import com.example.duan1.MusicSqlite;
import com.example.duan1.love.ListMusicLove;
import com.example.duan1.model.PlayMedia;
import com.example.duan1.R;
import com.example.duan1.model.Music;

import java.util.List;

class MusicHolder extends RecyclerView.ViewHolder {
    TextView title, detail;
    ImageView popupMenuBtn;
    ImageView albumArt;

    public MusicHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        detail = itemView.findViewById(R.id.detail);
        popupMenuBtn = itemView.findViewById(R.id.popupMenuBtn);
        albumArt = itemView.findViewById(R.id.albumArt);
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
        final Music music1 = list.get(position);
        holder.title.setText(music1.music);
        holder.detail.setText(music1.artist);
        holder.popupMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.tools) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Xóa");
                            builder.setMessage("Bạn có muốn xóa không?");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    deleteNote(position);
                                    Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        } else if (id == R.id.tool1) {
                            MusicSqlite musicSqlite = new MusicSqlite(context);
                            musicSqlite.insertAccount(music1);
                            Intent intent = new Intent(context, ListMusicLove.class);

                            context.startActivity(intent);
                            Toast.makeText(context, "them", Toast.LENGTH_SHORT).show();
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
                playMedia.onChangeMusic(music1);
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

    MediaPlayer mediaPlayer;

    private void deleteNote(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
}
