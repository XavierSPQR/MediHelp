package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class resview extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resview);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up FirebaseRecyclerOptions to query the "doctors" child in Firebase Realtime Database
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MainModel model) {
                // Handle the item click
                openAnotherActivity(model);
                vibrateDevice();
            }
        });
    }

    private void openAnotherActivity(MainModel model) {
        // Extract the required data from the model and pass it to the next activity
        String docname = model.getDname();
        Intent intent = new Intent(resview.this, selectprofile.class);
        intent.putExtra("dcname", docname);
        startActivity(intent); // Start the next activity
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MainModel model) {
                        // Handle the item click
                        openAnotherActivity(model);
                        vibrateDevice();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MainModel model) {
                        vibrateDevice();
                        // Handle the item click
                        openAnotherActivity(model);
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {
        // Set up FirebaseRecyclerOptions to query the "doctors" child based on the search text
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors").orderByChild("dname").startAt(str).endAt(str + "~"), MainModel.class)
                        .build();
        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }

    private void vibrateDevice() {
        // Vibrates the device for 200 milliseconds
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200);
        }
    }
}
