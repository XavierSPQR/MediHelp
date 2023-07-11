package com.example.medihelpfinal;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AmbulanceAdapter extends FirebaseRecyclerAdapter<AmbulanceModel, AmbulanceAdapter.amviewholder> {

    private TextView showavailable;
    private Button callambulance;
    private boolean a;
    private String phonenumber;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AmbulanceAdapter(@NonNull FirebaseRecyclerOptions<AmbulanceModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public amviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amitemview, parent, false);
        return new amviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull amviewholder holder, int position, @NonNull AmbulanceModel model) {
        // Set the ambulance driver's number
        holder.showname.setText(model.getdNumber());

        a = model.getIsAvailable();
        if (!a) {
            showavailable.setText("Not Available");
            callambulance.setVisibility(View.INVISIBLE);
        } else {
            showavailable.setText("Available, Call now!");

            // Handle click on call button
            callambulance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Dial the ambulance driver's phone number
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String phoneNumber = model.getPhone();
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    class amviewholder extends RecyclerView.ViewHolder {
        TextView showname;

        public amviewholder(@NonNull View itemView) {
            super(itemView);
            showname = itemView.findViewById(R.id.amnumber);
            showavailable = itemView.findViewById(R.id.amavailable);
            callambulance = itemView.findViewById(R.id.amcall);
        }
    }
}
