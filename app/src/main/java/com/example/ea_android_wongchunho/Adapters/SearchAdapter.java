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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    ArrayList<painting> list;

    //Constructor
    public SearchAdapter(ArrayList<painting> list) {
        this.list = list;
    }


    //Link item_view to the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    //return position
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.artist.setText(list.get(position).getArtist());
        //get image position from the firebase
        Glide.with(holder.imageView.getContext()).load(list.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //Define the variables used in the item_view xml
        TextView name, artist;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.search_name);
            artist = itemView.findViewById(R.id.search_artist);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
