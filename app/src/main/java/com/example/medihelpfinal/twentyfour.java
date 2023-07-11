package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class twentyfour extends AppCompatActivity {
    private Button button, ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twentyfour);

        button = findViewById(R.id.button5);
        ambulance = findViewById(R.id.button8);

        // Set click listener for button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice(); // Vibrate the device
                Intent intent = new Intent(twentyfour.this, pharmacylist.class);
                startActivity(intent); // Start pharmacylist activity
            }
        });

        // Set click listener for ambulance button
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice(); // Vibrate the device
                Intent intent = new Intent(twentyfour.this, ambulanceview.class);
                startActivity(intent); // Start ambulanceview activity
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
