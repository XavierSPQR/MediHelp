package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editprofile extends AppCompatActivity {
    private EditText uname;
    private EditText uaddress;
    private EditText uemail;
    private EditText uage;
    private DBHandler dbHandler;
    private Context context;
    private Button edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        // Initialize UI elements
        uname = findViewById(R.id.name);
        uaddress = findViewById(R.id.address);
        uemail = findViewById(R.id.ge);
        uage = findViewById(R.id.age);
        edit = findViewById(R.id.button7);
        delete = findViewById(R.id.button17);

        // Initialize context and database handler
        context = this;
        dbHandler = new DBHandler(context);

        // Retrieve the profile ID from the intent
        final String id = getIntent().getStringExtra("id");

        // Get the profile from the database based on the ID and populate the UI fields
        ToDo toDo = dbHandler.getSingleProfile(Integer.parseInt(id));
        uname.setText(toDo.getName());
        uaddress.setText(toDo.getAddress());
        uemail.setText(toDo.getEmail());
        uage.setText(toDo.getAge());

        // Update button click listener
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();

                // Get the updated values from the UI fields
                String nm = uname.getText().toString();
                String ad = uaddress.getText().toString();
                String em = uemail.getText().toString();
                String ag = uage.getText().toString();

                // Create a new ToDo object with the updated values and ID
                ToDo toDo1 = new ToDo(nm, ad, em, ag, Integer.parseInt(id));

                // Update the profile in the database
                int state = dbHandler.updateProfile(toDo1);

                // Start the dashboard activity
                startActivity(new Intent(context, dashboard.class));
            }
        });

        // Delete button click listener
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrateDevice();

                // Get the values from the UI fields
                String nm = uname.getText().toString();
                String ad = uaddress.getText().toString();
                String em = uemail.getText().toString();
                String ag = uage.getText().toString();

                // Create a new ToDo object with the values and ID
                ToDo toDo1 = new ToDo(nm, ad, em, ag, Integer.parseInt(id));

                // Delete the profile from the database
                dbHandler.deleteProfile(toDo1.getId());

                // Start the dashboard activity
                startActivity(new Intent(context, dashboard.class));
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
