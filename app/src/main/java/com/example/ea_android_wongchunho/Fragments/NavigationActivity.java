package com.example.ea_android_wongchunho.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ea_android_wongchunho.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    QuizFragment quizFragment = new QuizFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Using fragments to create the bottom navigation bar
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //get the id created in the bottom_navigation xml
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                homeFragment).commit();
                        return true;
                    case R.id.quiz:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                quizFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                profileFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}