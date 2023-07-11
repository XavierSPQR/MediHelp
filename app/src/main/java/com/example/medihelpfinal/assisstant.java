package com.example.medihelpfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class assisstant extends AppCompatActivity {

    private TextView textView;
    private TextView showRes;
    private EditText editText;
    private String input;
    private Context context;
    private Button button;
    private Vibrator vibrator;
    private Handler handler;
    private int count = 0;
    private Map<String, String> responses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assisstant);

        // Initialize views
        textView = findViewById(R.id.textView4);
        editText = findViewById(R.id.editTextTextMultiLine2);
        button = findViewById(R.id.button20);

        handler = new Handler();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        responses = new HashMap<>();
        responses.put("headache", "Physical Injuries");
        responses.put("injured", "Physical Injuries");
        responses.put("pain", "Physical Injuries");
        responses.put("stress", "Mental Health Disorders");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    // Handle sending the user message
    private void sendMessage() {
        String message = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(message)) {
            String response = getResponse(message);
            editText.setText("");
            if (!response.equals("unrecognized")) {
                // Show dialog with response
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(response);
                builder.setMessage("Look for " + response + " in Select Category");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(assisstant.this, categorylist.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        editText.setText("");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                // Show dialog for unrecognized input
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Unrecognized Input");
                builder.setMessage("Please retry with a different keyword or contact support");
                builder.setPositiveButton("Contact Support", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        performAction();
                    }
                });
                builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        editText.setText("");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    // Get the appropriate response based on the user message
    private String getResponse(String message) {
        for (String key : responses.keySet()) {
            if (message.toLowerCase().contains(key)) {
                return responses.get(key);
            }
        }
        return "unrecognized";
    }

    // Update the text view with random instructions periodically
    private void updateText() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String[] strings = {"Describe how you feel", "Use keywords to help me understand you better",
                        "Ex: Headache in the mornings only", "We will select what is best for you based on your description"};
                Random random = new Random();
                int randomIndex = random.nextInt(strings.length);
                String randomString = strings[randomIndex];
                textView.setText(randomString);
                updateText();
            }
        }, 3000);
    }

    // Perform the desired action when contacting support
    private void performAction() {
        String phoneNumber = "1234567890"; // Replace with the desired phone number
        openDialer(phoneNumber);
    }

    // Open the dialer with the given phone number
    private void openDialer(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Handle back press
    }
}
