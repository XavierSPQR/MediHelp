package com.example.medihelpfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class selectprofile extends AppCompatActivity {
    private ListView listView;
    Context context;
    private DBHandler dbHandler;
    private List<ToDo> prolist;
    private String type, docnamefromselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectprofile);
        context=this;

        // Initialize the database handler
        dbHandler=new DBHandler(context);

        // Retrieve the values from the intent
        type=getIntent().getStringExtra("stype");
        docnamefromselect=getIntent().getStringExtra("dcname");

        listView=findViewById(R.id.list);
        prolist=new ArrayList<>();
        prolist=dbHandler.getAllProfiles();

        // Create an adapter for the ListView
        Adapter adapter=new Adapter(context,R.layout.activity_singleprofile,prolist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vibrateDevice();
                ToDo todo=prolist.get(position);

                // Create an alert dialog to display profile details
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(todo.getName());
                builder.setPositiveButton("Confirm Channeling for this profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vibrateDevice();

                        // Start the details activity with profile information
                        Intent intent=new Intent(context, details.class);
                        intent.putExtra("name",String.valueOf(todo.getName()));
                        intent.putExtra("phone",String.valueOf(todo.getAddress()));
                        intent.putExtra("age",String.valueOf(todo.getAge()));
                        intent.putExtra("email",String.valueOf(todo.getEmail()));
                        intent.putExtra("type",type);
                        intent.putExtra("dcname",docnamefromselect);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vibrateDevice();

                        // Start the selectprofile activity again
                        Intent intent=new Intent(context, selectprofile.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }

    // Vibrates the device for a short duration
    private void vibrateDevice() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(200); // Vibrate for 200 milliseconds
        }
    }
}
