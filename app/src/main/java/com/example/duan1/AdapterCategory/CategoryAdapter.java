package com.example.duan1.AdapterCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.model.Single;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAlbum> {
    private Context context;
    private List<Single> strings;
    public CategoryAdapter(Context context, List<Single> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public CategoryAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryAlbum(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAlbum holder, int position) {
        Single s = strings.get(position);
        holder.tvSingle.setText("Single: "+s.getSingle());
        holder.tvNamSong.setText("Name Song: "+s.getNameSong());
//        holder.tvSingle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, CategoryMusic.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("theLoai",holder.tvSingle.getText().toString());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}
