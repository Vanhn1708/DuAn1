package com.example.duan1.AdapterListOfSinger;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.AdapterAlbum.HolderAlbum;
import com.example.duan1.R;
import com.example.duan1.model.Single;

import java.util.List;

public class ListOfSingerAdapter  extends RecyclerView.Adapter<HolderListOfSinger> {

    private Context context;
    private List<Single> strings;
    private MediaPlayer mediaPlayer;

    public ListOfSingerAdapter(Context context, List<Single> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public HolderListOfSinger onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_namesong, parent, false);
        return new HolderListOfSinger(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderListOfSinger holder, final int position) {
        final Single s = strings.get(position);
        holder.tvNamSongg.setText(s.getGenre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediaPlayer = new MediaPlayer();
                Uri uri= Uri.parse("file:///"+s.getPath());
               mediaPlayer  = MediaPlayer.create(context,uri);
                mediaPlayer.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
