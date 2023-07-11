package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ambulanceview extends AppCompatActivity {
    RecyclerView amrecyclerView;
    AmbulanceAdapter ambulanceAdapter;
    Boolean b;
    AmbulanceModel amodel;
    Button cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulanceview);

        // Initialize RecyclerView
        amrecyclerView = findViewById(R.id.amview);
        amrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configure FirebaseRecyclerOptions to query the database for AmbulanceModel objects
        FirebaseRecyclerOptions<AmbulanceModel> options =
                new FirebaseRecyclerOptions.Builder<AmbulanceModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Ambulancedriver"), AmbulanceModel.class)
                        .build();

        // Create an instance of AmbulanceAdapter with the options
        ambulanceAdapter = new AmbulanceAdapter(options);
        amrecyclerView.setAdapter(ambulanceAdapter);

        // Initialize AmbulanceModel
        amodel = new AmbulanceModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Start listening for changes in the database and update the adapter accordingly
        ambulanceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop listening for changes in the database when the activity is stopped
        ambulanceAdapter.stopListening();
    }
}
