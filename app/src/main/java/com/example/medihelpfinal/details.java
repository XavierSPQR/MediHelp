package com.example.medihelpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class details extends AppCompatActivity {

    private Button btn2;
    private Context context;
    private DBHandler dbHandler;
    private List<ToDo> prolist;
    private String pname, page, pemail, ptelephone, stype, smessage, desc, dcnamech;
    private TextView tv, ev;
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Initialize UI elements
        ed = findViewById(R.id.editTextTextMultiLine);
        context = this;

        // Get data from Intent
        pname = getIntent().getStringExtra("name");
        page = getIntent().getStringExtra("age");
        pemail = getIntent().getStringExtra("email");
        ptelephone = getIntent().getStringExtra("phone");
        stype = getIntent().getStringExtra("type");
        dcnamech = getIntent().getStringExtra("dcname");
        desc = ed.getText().toString();

        String lastTwoDigits = ptelephone.substring(ptelephone.length() - 3);
        System.out.println(lastTwoDigits); // Output: 45

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String currentDate = dateFormat.format(new Date());
        String refno = page + currentDate + lastTwoDigits;

        // Find UI elements by their IDs
        btn2 = findViewById(R.id.button18);
        tv = findViewById(R.id.patientname);
        ev = findViewById(R.id.textView16);
        tv.setText(pname + "(Age:" + page + ")");
        ev.setText(pemail);
        smessage = refno + "====Reference\n\n" +
                pname + "====name====\n" +
                page + "====age====\n" +
                dcnamech + "===doctorname\n" +
                ptelephone + "====telephone\n" +
                stype + "type\n" +
                desc;

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrateDevice();
                try {
                    String recieverEmail = pemail;
                    String senderEmail = "prabhaththarangaspqr@gmail.com";
                    String password = "ozkhyrspwshsveoc";
                    String stringHost = "smtp.gmail.com";
                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    // Create a new session with authenticator
                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, password);
                        }
                    });

                    // Create a new message
                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recieverEmail));
                    mimeMessage.setSubject(stype);
                    mimeMessage.setText(smessage);

                    // Send the message in a separate thread
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            } catch (MessagingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    thread.start();
                    Toast.makeText(getApplicationContext(), "Successfully submitted.\n " +
                            "We will get back to you shortly", Toast.LENGTH_SHORT).show();
                } catch (AddressException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Address", Toast.LENGTH_SHORT).show();
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                }

                // Start the checkupnumber activity
                Intent intent = new Intent(details.this, checkupnumber.class);
                intent.putExtra("referno", refno);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Start the dashboard activity
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
}
