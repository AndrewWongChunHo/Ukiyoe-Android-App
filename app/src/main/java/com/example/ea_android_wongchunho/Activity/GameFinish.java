package com.example.ea_android_wongchunho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ea_android_wongchunho.Fragments.NavigationActivity;
import com.example.ea_android_wongchunho.R;

public class GameFinish extends AppCompatActivity {

    TextView tvPoints, tvPersonalBest;
    // Using Android SharedPreferences class to display scores
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finish);

        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);

        //set the value of points
        tvPoints.setText("" + points);
        // Instantiate the SharedPreferences
        sharedPreferences = getSharedPreferences("pref", 0);
        int pointsSP = sharedPreferences.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // To check if points are greater than pointsSP
        if(points > pointsSP){
            pointsSP = points;
            // Insert the values into the editor with the key "pointsSP"
            editor.putInt("pointsSP", pointsSP);
            editor.commit();
        }
        // Set the tvPersonalBest with the value of pointsSP
        tvPersonalBest.setText("" + pointsSP);


    }

    public void restart(View view) {
        // Replay the game
        Intent intent = new Intent(GameFinish.this, StartGame.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        //End the game
        Intent intent = new Intent(GameFinish.this, NavigationActivity.class);
        startActivity(intent);
        finish();
    }
}