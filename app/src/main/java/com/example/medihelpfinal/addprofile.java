package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addprofile extends AppCompatActivity {

    private EditText uname;
    private EditText uaddress;
    private EditText uemail;
    private EditText uage;
    private Button add, back;
    private DBHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprofile);

        // Initialize UI elements
        uname = findViewById(R.id.name);
        uaddress = findViewById(R.id.address);
        uemail = findViewById(R.id.ge);
        uage = findViewById(R.id.age);
        add = findViewById(R.id.button7);
        back = findViewById(R.id.button17);

        context = this;
        dbHandler = new DBHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();

                // Get user input values
                String inuname = uname.getText().toString();
                String inaddress = uaddress.getText().toString();
                String inemail = uemail.getText().toString();
                String inage = uage.getText().toString();

                // Validate email address
                String userEmail = inemail;
                boolean isValid = isValidEmail(userEmail);

                if (isValid) {
                    // Create new ToDo object and add it to the database
                    ToDo toDo = new ToDo(inuname, inaddress, inemail, inage);
                    dbHandler.addProfile(toDo);
                    startActivity(new Intent(context, profile.class));
                } else {
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();
                startActivity(new Intent(context, profile.class));
            }
        });
    }

    // Email validation method
    public boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Method to vibrate the device
    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }

}
