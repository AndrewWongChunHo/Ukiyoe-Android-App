package com.example.ea_android_wongchunho.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ea_android_wongchunho.R;
import com.example.ea_android_wongchunho.Models.item;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.profileViewHolder> {
    Context context;
    List<item> itemList;

    //Constructor
    public ProfileAdapter(Context context, List<item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    //Link profile_view to RecyclerView
    @NonNull
    @Override
    public profileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new profileViewHolder(LayoutInflater.from(context).inflate(R.layout.profile_view, parent, false));
    }

    //return the position from View Holder
    @Override
    public void onBindViewHolder(@NonNull profileViewHolder holder, int position) {
        holder.imageView.setImageResource(itemList.get(position).getImage());
        holder.textView.setText(itemList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class profileViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public profileViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_icon);
            textView = itemView.findViewById(R.id.profile_info);
        }
    }
}
