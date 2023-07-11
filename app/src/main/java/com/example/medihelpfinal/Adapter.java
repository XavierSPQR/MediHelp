package com.example.medihelpfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<ToDo> {

    private Context context;
    private int resources;
    List<ToDo> todos;

    // Constructor
    Adapter(Context context, int resources, List<ToDo> todos) {
        super(context, resources, todos);
        this.context = context;
        this.resources = resources;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate the layout for each row of the ListView
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resources, parent, false);

        // Get references to the TextViews in the row layout
        TextView unname = row.findViewById(R.id.textView22);
        TextView unnaddress = row.findViewById(R.id.textView21);
        TextView ueemail = row.findViewById(R.id.textView20);
        TextView uaage = row.findViewById(R.id.textView23);

        // Get the ToDo object for the current position
        ToDo toDo = todos.get(position);

        // Set the data from the ToDo object to the TextViews
        unname.setText(toDo.getName());
        unnaddress.setText(toDo.getAddress());
        ueemail.setText(toDo.getEmail());
        uaage.setText(toDo.getAge());

        // Return the populated row view
        return row;
    }
}
