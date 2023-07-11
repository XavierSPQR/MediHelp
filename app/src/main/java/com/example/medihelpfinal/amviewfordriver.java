package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class amviewfordriver extends AppCompatActivity {
    private RecyclerView amRecyclerView;
    private AmbulanceAdapterForD ambulanceAdapter;
    private AmbulanceModel amodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amviewfordriver);

        // Initialize RecyclerView
        amRecyclerView = findViewById(R.id.amviewford);
        amRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Configure FirebaseRecyclerOptions to query the database for AmbulanceModel objects
        FirebaseRecyclerOptions<AmbulanceModel> options =
                new FirebaseRecyclerOptions.Builder<AmbulanceModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Ambulancedriver"), AmbulanceModel.class)
                        .build();

        // Create an instance of AmbulanceAdapterForD with the options
        ambulanceAdapter = new AmbulanceAdapterForD(options);
        amRecyclerView.setAdapter(ambulanceAdapter);

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
