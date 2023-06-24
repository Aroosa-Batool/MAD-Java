package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class location extends AppCompatActivity {


        Button getLocation;
        double latitude;
        double longitude;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);

            getLocation = findViewById(R.id.button);

            int permissionCode = 1;
            int fine = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            int coarse = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);

            if (fine != PackageManager.PERMISSION_GRANTED && coarse != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION };
                ActivityCompat.requestPermissions(this, permissions, permissionCode);
            }
            LocationManager lm = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//        lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER or GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // Get the latitude and longitude from the location object
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    // Use the latitude and longitude values as needed
                    Log.d("**********MyLocation", "*******Latitude: " + latitude + ", Longitude: " + longitude);
                }
            });

            FirebaseDatabase database =  FirebaseDatabase.getInstance();
            DatabaseReference myref =  database.getReference("Hackathon");

            getLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mylocation l1 = new mylocation(latitude, longitude);
                    myref.child("Location").setValue(l1);

                }
            });




        }
    }