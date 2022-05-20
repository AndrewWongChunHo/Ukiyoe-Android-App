package com.example.ea_android_wongchunho.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ea_android_wongchunho.Activity.StartGame;
import com.example.ea_android_wongchunho.R;


public class QuizFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        //Direct users to the start game class
        Button button = view.findViewById(R.id.play_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), StartGame.class);
                startActivity(i);
            }
        });
        return view;
    }

}