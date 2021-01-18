package com.example.lab1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lab1.Helpers.PermissionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final LatLng SFO = new LatLng(-31.635754, -60.68295565);
    private static final LatLng SOH = new LatLng(-31.6169202, -60.675051238656);
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap myMap;
    LatLng ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void finish() {
        Intent datos = new Intent();
        datos.putExtra("ubicacion", getUbicacion());
        setResult(RESULT_OK, datos);
        super.finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        //Solicitud de permiso para la ubicación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (myMap != null) {
                myMap.setMyLocationEnabled(true);
                //myMap.moveCamera();
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            myMap.setMyLocationEnabled(true);
        }


        // Move to a place with indoor (SFO airport).
//        int currentLeft = 0, currentTop = 0, currentRight = 0, currentBottom = 0;
//        myMap.setPadding(currentLeft, currentTop, currentRight, currentBottom);
//        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SFO, 18));
        // Add a marker to the Opera House.
        myMap.addMarker(new MarkerOptions().position(SOH).title("UTN PERRI"));
        // Add a camera idle listener.
        myMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                //Toast.makeText(getApplicationContext(),"CameraChangeListener: " + myMap.getCameraPosition(), Toast.LENGTH_LONG).show();
                //mMessageView.setText("CameraChangeListener: " + myMap.getCameraPosition());
            }
        });


        Bundle extras = getIntent().getExtras();
        ubicacion = extras.getParcelable("ubicacion");
        myMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                myMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
                ubicacion = latLng;
                finish();
            }
        });
    }

    public LatLng getUbicacion() {
        return ubicacion;
    }

}