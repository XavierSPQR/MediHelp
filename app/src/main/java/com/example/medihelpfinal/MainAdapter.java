package com.example.medihelpfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.OnItemClickListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myvieworder> {
    private OnItemClickListener listener;

    // Constructor and other methods

    // Define the click listener interface
    public interface OnItemClickListener {
        void onItemClick(MainModel model);
    }

    // Method to set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myvieworder holder, int position, @NonNull MainModel model) {
        holder.name.setText(model.dname);
        holder.dtype.setText(model.dtype);
        Glide.with(holder.img.getContext())
                .load(model.getDemail())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.ambulance)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(model); // Pass the selected model to the click listener
                }
            }
        });
    }

    @NonNull
    @Override
    public myvieworder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainitem, parent, false);
        return new myvieworder(view);
    }

    class myvieworder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, dtype;

        public myvieworder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.vname);
            dtype = (TextView) itemView.findViewById(R.id.vdtype);
        }
    }
}
