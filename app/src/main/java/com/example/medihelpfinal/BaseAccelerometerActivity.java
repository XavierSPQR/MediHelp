package com.example.medihelpfinal;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseAccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private int currentOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        // Calculate tilt angles
        float roll = (float) Math.atan2(y, z) * (180f / (float) Math.PI);
        float pitch = (float) Math.atan2(-x, Math.sqrt(y * y + z * z)) * (180f / (float) Math.PI);

        // Determine the new orientation based on the tilt angles
        int newOrientation;
        if (pitch < -45f) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
        } else if (pitch > 45f) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        } else if (roll < -45f) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        } else if (roll > 45f) {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else {
            newOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        }

        // Change the orientation if it's different from the current orientation
        if (currentOrientation != newOrientation) {
            currentOrientation = newOrientation;
            setRequestedOrientation(newOrientation);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for accelerometer
    }
}

