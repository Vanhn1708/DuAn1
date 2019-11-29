package com.example.duan1.AdapterListOfSinger;

import android.content.Context;
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
    public void onBindViewHolder(@NonNull HolderListOfSinger holder, int position) {
        Single s = strings.get(position);
        holder.tvNamSongg.setText(s.getGenre());


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
