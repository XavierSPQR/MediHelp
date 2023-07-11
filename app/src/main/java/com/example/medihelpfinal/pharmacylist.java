package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class pharmacylist extends AppCompatActivity {
    private Button map, map2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacylist);

        // Find buttons in the layout
        map = findViewById(R.id.button2);
        map2 = findViewById(R.id.button3);

        // Set click listener for map button 1
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice(); // Vibrate the device
                // Create intent to open MapsActivity
                Intent intent = new Intent(pharmacylist.this, MapsActivity.class);
                String l = "7";
                String lo = "80";
                String b = "Branch 1";
                // Pass branch details and coordinates to MapsActivity
                intent.putExtra("br", b);
                intent.putExtra("latd", l);
                intent.putExtra("lotd", lo);
                startActivity(intent); // Start MapsActivity
            }
        });

        // Set click listener for map button 2
        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice(); // Vibrate the device
                // Create intent to open MapsActivity
                Intent intent = new Intent(pharmacylist.this, MapsActivity.class);
                String l = "7";
                String lo = "80";
                String b = "Branch 2";
                // Pass branch details and coordinates to MapsActivity
                intent.putExtra("br", b);
                intent.putExtra("latd", l);
                intent.putExtra("lotd", lo);
                startActivity(intent); // Start MapsActivity
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
