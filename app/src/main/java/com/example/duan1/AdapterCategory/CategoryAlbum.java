package com.example.duan1.AdapterCategory;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;

public class CategoryAlbum extends RecyclerView.ViewHolder {
    public TextView tvNamSong;
    public TextView tvSingle;





    public CategoryAlbum(@NonNull View itemView) {
        super(itemView);

        tvNamSong = (TextView) itemView.findViewById(R.id.tvNamSong);
        tvSingle = (TextView) itemView.findViewById(R.id.tvSingle);
    }
}
