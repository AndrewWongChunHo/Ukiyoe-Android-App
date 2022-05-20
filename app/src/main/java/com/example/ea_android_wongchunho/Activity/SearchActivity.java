package com.example.ea_android_wongchunho.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ea_android_wongchunho.Adapters.SearchAdapter;
import com.example.ea_android_wongchunho.Fragments.NavigationActivity;
import com.example.ea_android_wongchunho.R;
import com.example.ea_android_wongchunho.Models.painting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    TextView textView;
    DatabaseReference ref;
    ArrayList<painting> list;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Reference the database created in Firebase
        ref = FirebaseDatabase.getInstance().getReference().child("paintings");

        //Recycler View & Search View ID
        recyclerView = findViewById(R.id.search_recyclerView);
        searchView = findViewById(R.id.searchView);

        //Move to the previous page when clicking cancel button
        textView = findViewById(R.id.cancel_button);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });
    }

    //Data Authentication from Firebase
    @Override
    protected void onStart() {
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
                        SearchAdapter searchAdapter = new SearchAdapter(list);
                        recyclerView.setAdapter(searchAdapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        //OnQueryListener is used to initiate the search operation and
        // listen to the callbacks for changes to the query text
        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    //allow users to search in Lowercase
    private void search(String str){
        ArrayList<painting> myList = new ArrayList<>();
        for(painting object : list){
            if(object.getName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        //Set Adapter
        SearchAdapter searchAdapter = new SearchAdapter(myList);
        recyclerView.setAdapter(searchAdapter);

    }
}