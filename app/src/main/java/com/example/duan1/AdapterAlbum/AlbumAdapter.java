package com.example.duan1.AdapterAlbum;

import android.content.Context;
import android.content.Intent;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.activity.CategoryMusic;
import com.example.duan1.model.Single;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<HolderAlbum> {
    private Context context;
    private List<Single> strings;
    public AlbumAdapter(Context context, List<Single> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public HolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single, parent, false);
        return new HolderAlbum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderAlbum holder, int position) {
        Single s = strings.get(position);
        holder.tvSingle.setText(s.getGenre());
        holder.tvSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CategoryMusic.class);
                Bundle bundle = new Bundle();
                bundle.putString("theLoai",holder.tvSingle.getText().toString());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return strings.size();
    }
}
