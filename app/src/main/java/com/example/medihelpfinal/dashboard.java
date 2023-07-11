package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {
    private Button btn;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button abt;
    private Button pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize buttons
        btn = findViewById(R.id.button22);
        btn2 = findViewById(R.id.button21);
        btn4 = findViewById(R.id.button25);
        btn3 = findViewById(R.id.button23);
        abt = findViewById(R.id.button24);
        pro = findViewById(R.id.button26);

        // Set click listeners for the buttons
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();
                // Launch the profile activity
                Intent intent = new Intent(dashboard.this, profile.class);
                startActivity(intent);
            }
        });

        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();
                // Launch the About Us activity
                Intent intent = new Intent(dashboard.this, AboutUs.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                // Launch the assistant activity
                Intent intent = new Intent(dashboard.this, assisstant.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                // Launch the category list activity
                Intent intent = new Intent(dashboard.this, categorylist.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                // Launch the settings activity
                Intent intent = new Intent(dashboard.this, settings.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                // Launch the twentyfour activity
                Intent intent = new Intent(dashboard.this, twentyfour.class);
                startActivity(intent);
            }
        });
    }

    // Method to vibrate the device
    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
}
