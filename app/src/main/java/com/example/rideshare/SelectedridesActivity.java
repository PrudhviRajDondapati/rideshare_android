package com.example.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class SelectedridesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<RideModel> main_list;
    SelectedridesAdapter adapter;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rideselected_list);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelectedridesActivity.this, NavActivity.class);
                startActivity(myIntent);
            }
        });
        FirebaseRecyclerOptions<SelectedridesModel> options =
                new FirebaseRecyclerOptions.Builder<SelectedridesModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ridesopted"),SelectedridesModel.class)
                        .build();

        adapter = new SelectedridesAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
