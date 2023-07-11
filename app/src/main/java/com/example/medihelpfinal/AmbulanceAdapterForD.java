package com.example.medihelpfinal;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AmbulanceAdapterForD extends FirebaseRecyclerAdapter<AmbulanceModel, AmbulanceAdapterForD.amviewholderford> {
    private TextView showavailable;
    private Button setAvailable;
    private boolean a;
    private String phonenumber;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AmbulanceAdapterForD(@NonNull FirebaseRecyclerOptions<AmbulanceModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public amviewholderford onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amitemviewfordriver, parent, false);
        return new amviewholderford(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull AmbulanceAdapterForD.amviewholderford holder, int position, @NonNull AmbulanceModel model) {
        // Set the ambulance driver's number
        holder.showname.setText(model.getdNumber());

        a = model.getIsAvailable();
        if (!a) {
            showavailable.setText("On Work");
            setAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update the availability status to true in the database
                    Map<String, Object> map = new HashMap<>();
                    map.put("isAvailable", true);
                    FirebaseDatabase.getInstance().getReference().child("Ambulancedriver")
                            .child(getRef(position).getKey()).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // Handle success
                                }
                            });
                }
            });
        } else {
            showavailable.setText("UnOccupied");
            setAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update the availability status to false in the database
                    Map<String, Object> map = new HashMap<>();
                    map.put("isAvailable", false);
                    FirebaseDatabase.getInstance().getReference().child("Ambulancedriver")
                            .child(getRef(position).getKey()).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    // Handle success
                                }
                            });
                }
            });
        }
    }

    class amviewholderford extends RecyclerView.ViewHolder {
        TextView showname;

        public amviewholderford(@NonNull View itemView) {
            super(itemView);
            showname = itemView.findViewById(R.id.amnumberford);
            showavailable = itemView.findViewById(R.id.amavailableford);
            setAvailable = itemView.findViewById(R.id.amcallford);
        }
    }
}
