package com.example.ea_android_wongchunho.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ea_android_wongchunho.Adapters.ProfileAdapter;
import com.example.ea_android_wongchunho.R;
import com.example.ea_android_wongchunho.Models.item;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    List<item> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        //Recycler View & Set Adapter
        recyclerView = view.findViewById(R.id.profileRecyclerView);
        profileAdapter = new ProfileAdapter(getContext(), itemList);
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setHasFixedSize(true);

        //Using intent to direct user to the home fragment
        ImageButton searchBtn = view.findViewById(R.id.profileBack);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NavigationActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add items to the profile fragment
        itemList = new ArrayList<item>();
        itemList.add(new item(R.drawable.ic_baseline_favorite_border_24, "Your Favourites"));
        itemList.add(new item(R.drawable.ic_baseline_payment_24, "Payment"));
        itemList.add(new item(R.drawable.ic_baseline_attach_money_24, "Promotions"));
        itemList.add(new item(R.drawable.ic_baseline_people_24, "Tell Your Friends"));
        itemList.add(new item(R.drawable.ic_baseline_share_24, "Share"));
        itemList.add(new item(R.drawable.ic_baseline_settings_24, "Settings"));
    }

}