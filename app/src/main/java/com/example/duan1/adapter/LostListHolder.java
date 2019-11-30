package com.example.duan1.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;


public class LostListHolder extends RecyclerView.ViewHolder {

    public ImageView albumArt1;
    public TextView title1;
    public TextView detail1;





    public LostListHolder(@NonNull final View itemView) {
        super(itemView);

        albumArt1 = itemView.findViewById(R.id.albumArt1);
        title1 = itemView.findViewById(R.id.title1);
        detail1 = itemView.findViewById(R.id.detail1);




    }

}
