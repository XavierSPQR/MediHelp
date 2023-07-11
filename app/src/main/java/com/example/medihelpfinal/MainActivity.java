package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Button btn;
    private SensorManager sensorManager;
    private boolean isAccelerometerAvailable;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isVibrationEnabled = sharedPreferences.getBoolean("vibrationEnabled", true);
        // Find the button by its ID
        btn = findViewById(R.id.button);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        isAccelerometerAvailable = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null;

        // Set click listener for the button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call method to vibrate the device
                if (!isVibrationEnabled) {
                    // Disable vibration

                    if (vibrator != null && vibrator.hasVibrator()) {
                        vibrator.cancel();
                    }
                    vibrateDevice();

                }
                // Create intent to start the LoginActivity
                Intent intent = new Intent(MainActivity.this, loginactivity.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });

    }

    // Method to vibrate the device
    private void vibrateDevice() {
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isAccelerometerAvailable) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isAccelerometerAvailable) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Calculate the absolute acceleration vector
        float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

        // Check if the device is in landscape mode
        if (acceleration > 15) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}
