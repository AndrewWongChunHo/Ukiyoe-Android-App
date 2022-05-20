package com.example.ea_android_wongchunho.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ea_android_wongchunho.Activity.MainActivity;
import com.example.ea_android_wongchunho.R;

public class OpeningAnimation extends AppCompatActivity {

    TextView slogan;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_animation);

        lottie = findViewById(R.id.lottie);

        //Set the movement and duration of the animation
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // direct users to the home page after the animation
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }, 5000);
    }
}