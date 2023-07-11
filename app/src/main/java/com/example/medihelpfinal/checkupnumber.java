package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class checkupnumber extends AppCompatActivity {
    private Button btn; // Button for triggering an action
    private TextView v, cnum; // TextViews for displaying text
    private String pna; // String variable to store a name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkupnumber);

        // Initialize UI elements
        btn = findViewById(R.id.button30);
        v = findViewById(R.id.textView6);
        cnum = findViewById(R.id.textView7);

        // Retrieve data from intent and display it in TextViews
        String refno = getIntent().getStringExtra("referno");
        cnum.setText(refno + "\nthis is your reference number");
        pna = getIntent().getStringExtra("name");
        v.setText(pna);

        // Set click listener for the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice(); // Trigger device vibration
                Intent intent = new Intent(checkupnumber.this, dashboard.class);
                startActivity(intent); // Start the dashboard activity
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
