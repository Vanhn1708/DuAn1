package com.example.duan1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    ///
    @NonNull
    @Override
    public LostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new LostListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostListHolder holder, final int position) {
        final Music music1 = dataLoaiSaches.get(position);
        holder.title1.setText(music1.music);
        holder.detail1.setText(music1.artist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteNote(position);
                        Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
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
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataLoaiSaches.size();
    }

    private void deleteNote(int position) {
        dataLoaiSaches.remove(position);
        notifyDataSetChanged();
    }
}
