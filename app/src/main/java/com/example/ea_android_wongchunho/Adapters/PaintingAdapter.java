package com.example.ea_android_wongchunho.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ea_android_wongchunho.R;
import com.example.ea_android_wongchunho.Models.painting;

import java.util.ArrayList;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingAdapter.MyViewHolder> {


    ArrayList<painting> list;

    public PaintingAdapter(ArrayList<painting> list) {
        this.list = list;
    }

    //Link painting_view to RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painting_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //To return the correct position from RecyclerView.ViewHolder
        holder.name.setText(list.get(position).getName());
        holder.artist.setText(list.get(position).getArtist());
        holder.year.setText(list.get(position).getYear());
        //get image position from the firebase
        Glide.with(holder.imageView.getContext()).load(list.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //create a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, artist, year;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.paintingImage);
            name = itemView.findViewById(R.id.paintingName);
            artist = itemView.findViewById(R.id.paintingArtist);
            year = itemView.findViewById(R.id.year);
        }
    }
}
