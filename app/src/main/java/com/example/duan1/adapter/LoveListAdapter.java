package com.example.duan1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.MusicSqlite;
import com.example.duan1.R;
import com.example.duan1.model.Music;


import java.util.List;

public class LoveListAdapter extends RecyclerView.Adapter<LostListHolder> {
    private MusicSqlite bookDatabaseHelper;
    private List<Music> dataLoaiSaches;
    private Context context;

    public LoveListAdapter(List<Music> dataLoaiSaches, Context context, MusicSqlite bookDatabaseHelper) {
        this.bookDatabaseHelper = bookDatabaseHelper;
        this.dataLoaiSaches = dataLoaiSaches;
        this.context = context;
    }


    @NonNull
    @Override
    public LostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new LostListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostListHolder holder, int position) {
        final Music music = dataLoaiSaches.get(position);
        holder.title1.setText(music.name);
        holder.detail1.setText(music.artist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,music.name+"",Toast.LENGTH_LONG).show();
                Toast.makeText(context,music.artist+"",Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return dataLoaiSaches.size();
    }

}
