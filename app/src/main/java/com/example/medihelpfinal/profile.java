package com.example.medihelpfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity {
    private Button add;
    private ListView listView;
    Context context;
    private DBHandler dbHandler;
    private List<ToDo> prolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize variables and views
        context = this;
        dbHandler = new DBHandler(context);
        add = findViewById(R.id.button15);
        listView = findViewById(R.id.list);

        // Retrieve profile data from the database
        prolist = new ArrayList<>();
        prolist = dbHandler.getAllProfiles();

        // Set up the adapter for the ListView
        Adapter adapter = new Adapter(context, R.layout.activity_singleprofile, prolist);
        listView.setAdapter(adapter);

        // Add button click listener to navigate to the "addprofile" activity
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();
                startActivity(new Intent(context, addprofile.class));
            }
        });

        // ListView item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vibrateDevice();
                ToDo todo = prolist.get(position);

                // Display an alert dialog with profile details
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getName());
                builder.setMessage(todo.getEmail());

                // Handle the "Delete Profile" button click
                builder.setNegativeButton("Delete Profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the profile from the database and navigate to the "dashboard" activity
                        dbHandler.deleteProfile(todo.getId());
                        startActivity(new Intent(context, dashboard.class));
                    }
                });

                // Handle the "Edit Profile" button click
                builder.setNeutralButton("Edit Profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vibrateDevice();
                        // Navigate to the "editprofile" activity and pass the profile ID as an extra
                        Intent intent = new Intent(context, editprofile.class);
                        intent.putExtra("id", String.valueOf(todo.getId()));
                        startActivity(intent);
                    }
                });

                // Show the alert dialog
                builder.show();
            }
        });
    }

    // Vibrate the device
    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
}
