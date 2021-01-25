package com.example.lab1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lab1.Helpers.PermissionUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.Random;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap myMap;
    LatLng ubicacion, miUbicacion;

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

                // Mover el mapa a la posición actual.
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(provider);
                if(location!=null){
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    miUbicacion = new LatLng(latitude, longitude);
                    myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 18));
                }

                // Agrego marcador a una posicion random
                Random r = new Random();

                // Una direccion aleatoria de 0 a 359 grados
                int direccionRandomEnGrados = r.nextInt(360);

                // Una distancia aleatoria de 100 a 1000 metros
                int distanciaMinima = 100;
                int distanciaMaxima = 1000;
                int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

                LatLng nuevaPosicion = SphericalUtil.computeOffset(
                        miUbicacion,
                        distanciaRandomEnMetros,
                        direccionRandomEnGrados
                );

                //Marcador del local de comidas
                myMap.addMarker(new MarkerOptions().position(nuevaPosicion).title("SEND MEAL - CASA DE COMIDAS"));

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.color(0x66FF0000);

                // Agregar ambos puntos
                polylineOptions.add(miUbicacion);
                polylineOptions.add(nuevaPosicion);

                myMap.addPolyline(polylineOptions);

            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);

        }

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