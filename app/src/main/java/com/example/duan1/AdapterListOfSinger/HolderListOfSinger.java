package com.example.duan1.AdapterListOfSinger;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;

public class HolderListOfSinger extends RecyclerView.ViewHolder {
    public TextView tvNamSongg;


    public HolderListOfSinger(@NonNull View itemView) {
        super(itemView);
        tvNamSongg = itemView.findViewById(R.id.tvNamSongg);
    }
}
