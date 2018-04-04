package com.briankenjiaguilar.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView longtitudeTextView, latitudeTextView, accuracyTextView, altitudeTextView, addressTextView;
    private LocationManager locationManager;
    private LocationListener locationListener;

    //Updates the location and displays it on the main activity
    public void updateLocationInfo(Location location) {
        latitudeTextView.setText("Latitude: " + location.getLatitude());
        longtitudeTextView.setText("Longtitude: " + location.getLongitude());
        accuracyTextView.setText("Accuracy: " + location.getAccuracy());
        altitudeTextView.setText("Altitude: " + location.getAltitude());

        String address = "";
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> listOfAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (listOfAddresses != null && listOfAddresses.size() > 0) {

                if (listOfAddresses.get(0).getSubThoroughfare() != null) {
                    address += listOfAddresses.get(0).getSubThoroughfare() + "\n";
                }

                if (listOfAddresses.get(0).getThoroughfare() != null) {
                    address += listOfAddresses.get(0).getThoroughfare() + "\n";
                }

                if (listOfAddresses.get(0).getLocality() != null) {
                    address += listOfAddresses.get(0).getLocality() + "\n";
                }

                if (listOfAddresses.get(0).getPostalCode() != null) {
                    address += listOfAddresses.get(0).getPostalCode() + "\n";
                }

                if (listOfAddresses.get(0).getCountryName() != null) {
                    address += listOfAddresses.get(0).getCountryName();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        addressTextView.setText("Address:\n" + address);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //Finding the text views by their id
        longtitudeTextView = (TextView) findViewById(R.id.longtitudeTextView);
        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        accuracyTextView = (TextView) findViewById(R.id.accuracyTextView);
        altitudeTextView = (TextView) findViewById(R.id.altitudeTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}
            @Override
            public void onProviderEnabled(String s) {}
            @Override
            public void onProviderDisabled(String s) {}
        };

        //Checking to see if the user granted permission to use their location for the application
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(location !=null){
                updateLocationInfo(location);
            }
        }

    }

}
