package com.example.e_commerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements LocationListener {
    private ImageButton gps;
    private ImageView goback;
    private Button btnSaveAdd;
    private EditText country, state, city, ComAddress;
    private static final int LOCATION_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int STORAGE_REQUEST_CODE = 200;
    private String[] locationPermission;
    private String[] cameraPermission;
    private String[] storageePermission;
    private LocationManager locationManager;
    private double latitude, longititude;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        goback = findViewById(R.id.arrowLocationBack);
        gps = findViewById(R.id.gps);
        country = findViewById(R.id.country);
        btnSaveAdd= findViewById(R.id.locSafe);
        progressBar= findViewById(R.id.progressBarLocate);
        state = findViewById(R.id.State);
        ComAddress = findViewById(R.id.locAddress);
        city = findViewById(R.id.City);
        locationPermission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        btnSaveAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        onBackPressed();
                    }
                },1000);
            }
        });
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLocationPermission()) {
                    detectLocation();

                } else {
                    requestLocationPermission();
                }
            }
        });
    }

    private void detectLocation() {
        Toast.makeText(this, "Please wait.....", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
    private boolean checkLocationPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==(PackageManager.PERMISSION_GRANTED);
        return  result;
    }
    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this,locationPermission,LOCATION_REQUEST_CODE);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude= location.getLatitude();
        longititude= location.getLongitude();
        findAddress();

    }

    private void findAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder= new Geocoder(this, Locale.getDefault());
        try {
            addresses= geocoder.getFromLocation(latitude,longititude,1);
            String address= addresses.get(0).getAddressLine(0);
            String citty= addresses.get(0).getLocality();
            String statte= addresses.get(0).getAdminArea();
            String countryy= addresses.get(0).getCountryName();
            country.setText(countryy);
            state.setText(statte);
            city.setText(citty);
            ComAddress.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
        Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show();
    }









    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch(requestCode){
           case LOCATION_REQUEST_CODE:{
               if (grantResults.length>0){
                   boolean locationAccepted= grantResults[0]==PackageManager.PERMISSION_GRANTED;
                   if (locationAccepted){
                      detectLocation();
                   }else {
                       Toast.makeText(this, "Location denied", Toast.LENGTH_SHORT).show();
                   }
               }
           }break;
       }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}