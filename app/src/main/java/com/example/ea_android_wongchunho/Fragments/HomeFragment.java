package com.example.ea_android_wongchunho.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ea_android_wongchunho.Activity.MainActivity;
import com.example.ea_android_wongchunho.Activity.SearchActivity;
import com.example.ea_android_wongchunho.Adapters.PaintingAdapter;
import com.example.ea_android_wongchunho.Adapters.SliderAdapter;
import com.example.ea_android_wongchunho.R;
import com.example.ea_android_wongchunho.Models.painting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view;
    SliderView sliderView;
    private SliderAdapter sliderAdapter;
    DatabaseReference ref;
    ArrayList<painting> list;
    RecyclerView recyclerView;

    //Make an array of images for the slider
    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //define the slider view for the image slider
        sliderView = view.findViewById(R.id.imageSlider);
        sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);

        //set the animation move of the image slider
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        //Recycler View
        recyclerView = view.findViewById(R.id.paintingRecyclerView);

        //Reference the Firebase
        ref = FirebaseDatabase.getInstance().getReference().child("paintings");

        //Return to previous page
        ImageButton imageButton = view.findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        //Youtube Player
        //Getting a reference to the YouTubePlayerView and adding a YouTubePlayerListener to it.
        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        //Using intent to direct user to the search page
        ImageButton searchBtn = view.findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
            }
        });

        TextView hintText = view.findViewById(R.id.hintText);
        hintText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchActivity.class);
                startActivity(i);
            }
        });

        return view;
    }


    //Reference the firebase and data authentication
    @Override
    public void onStart() {
        super.onStart();
        if(ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()){
                            list.add(ds.getValue(painting.class));
                        }
                        PaintingAdapter paintingAdapter = new PaintingAdapter(list);
                        recyclerView.setAdapter(paintingAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}