package com.example.duan1.AdapterAlbum;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;

public class HolderAlbum extends RecyclerView.ViewHolder {
    public TextView tvSingle;



    public HolderAlbum(@NonNull View itemView) {
        super(itemView);
        tvSingle = itemView.findViewById(R.id.tvNamSong);
    }
}
