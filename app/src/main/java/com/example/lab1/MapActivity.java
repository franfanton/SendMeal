package com.example.lab1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Bundle extras = getIntent().getExtras();
        mapFragment.getMapAsync(this);

        Toast.makeText(getApplicationContext(),"ENTRA 1", Toast.LENGTH_LONG);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap=googleMap;
        Toast.makeText(getApplicationContext(),"ENTRA 2", Toast.LENGTH_LONG);
    }
}