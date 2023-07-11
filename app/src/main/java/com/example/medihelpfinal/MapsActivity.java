package com.example.medihelpfinal;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.medihelpfinal.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private int latit,longit;
    private String branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent recieved=getIntent();
        latit=Integer.parseInt(recieved.getStringExtra("latd"));
        longit=Integer.parseInt(recieved.getStringExtra("lotd"));
        branch=recieved.getStringExtra("br");
        mMap = googleMap;

        // Add a marker move the camera
      //  LatLng branch = new LatLng(latit, longit);
     //   mMap.addMarker(new MarkerOptions().position(branch).title(String.valueOf(branch)));
     //   mMap.moveCamera(CameraUpdateFactory.newLatLng(branch));
        LatLng initialPosition = new LatLng(latit, longit); // San Francisco coordinates
        float zoomLevel = 10.0f;
        mMap.addMarker(new MarkerOptions().position(initialPosition).title("Branch 1"));
        // Move camera to initial position with animation
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialPosition, zoomLevel));
    }

}