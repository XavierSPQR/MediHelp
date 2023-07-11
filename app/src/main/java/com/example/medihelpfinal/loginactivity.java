package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Context;
public class loginactivity extends AppCompatActivity {
    private Button btn;
    private Button btn2;
    private Button staff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        btn=findViewById(R.id.button4);
        staff=findViewById(R.id.btnStaff);
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();
                startActivity(new Intent(loginactivity.this, validatestaff.class));
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                Intent intent=new Intent(loginactivity.this, dashboard.class);
                startActivity(intent);
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