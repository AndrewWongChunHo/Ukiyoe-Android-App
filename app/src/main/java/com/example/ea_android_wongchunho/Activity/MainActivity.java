package com.example.ea_android_wongchunho.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ea_android_wongchunho.Fragments.NavigationActivity;
import com.example.ea_android_wongchunho.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Direct users to the home fragment
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toHomeButton:
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
                break;
        }
    }
}