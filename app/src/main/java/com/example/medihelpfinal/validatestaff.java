package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class validatestaff extends AppCompatActivity implements SensorEventListener {

    private EditText suname, spassword;
    private Button button;
    private String nm, pw;
    private SensorManager sensorManager;
    private boolean isAccelerometerAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validatestaff);

        // Initialize EditText fields and Button
        suname = findViewById(R.id.stfuser);
        spassword = findViewById(R.id.stfpassword);
        button = findViewById(R.id.driverupdate);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        isAccelerometerAvailable = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get instance of FirebaseAuth
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String email = suname.getText().toString();
                String password = spassword.getText().toString();

                // Sign in with email and password
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // User authentication successful
                                FirebaseUser user = mAuth.getCurrentUser();
                                // Redirect to amviewfordriver activity
                                Intent intent = new Intent(validatestaff.this, amviewfordriver.class);
                                intent.putExtra("drmail", email);
                                startActivity(intent);
                            } else {
                                // User authentication failed
                                Toast.makeText(validatestaff.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
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
