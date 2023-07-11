package com.example.medihelpfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class categorylist extends AppCompatActivity {
    private Button btn; // Button for selecting Infectious Diseases category
    private Button dl1; // Button for selecting Chronic Diseases category
    private Button dl2; // Button for selecting Mental health disorders category
    private Button dl3; // Button for selecting Physical injuries category
    private Button dl4; // Button for selecting Allergy reactions category
    private Button dec; // Button for indicating inability to decide category
    private Button say; // Button for navigating to resview activity
    private String selection; // Placeholder for selected category
    private String doctorType; // Placeholder for selected doctor type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorylist);

        // Initialize UI elements
        btn = findViewById(R.id.button9);
        dl1 = findViewById(R.id.button10);
        dl2 = findViewById(R.id.button11);
        dl3 = findViewById(R.id.button12);
        dl4 = findViewById(R.id.button13);
        dec = findViewById(R.id.button14);
        say = findViewById(R.id.nsay);

        // Set click listener for selecting Infectious Diseases category
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "Infectious Diseases");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for selecting Chronic Diseases category
        dl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "CHRONIC DISEASES");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for selecting Mental health disorders category
        dl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "Mental health disorders");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for selecting Physical injuries category
        dl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "Physical injuries");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for selecting Allergy reactions category
        dl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "Allergy reactions");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for indicating inability to decide category
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, selectprofile.class);
                intent.putExtra("stype", "I can't decide");
                startActivity(intent);
                vibrateDevice();
            }
        });

        // Set click listener for navigating to resview activity
        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categorylist.this, resview.class);
                startActivity(intent);
                vibrateDevice();
            }
        });
    }

    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
}
