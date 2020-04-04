package com.test.proyectocvd;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng hospital1 = new LatLng(-12.057025, -77.015945);
        LatLng hospital2 = new LatLng(-12.041192, -76.992096);
        LatLng hospital3 = new LatLng(-12.078284, -77.039709);
        mMap.addMarker(new MarkerOptions().position(hospital1).title("Hospital 2 de Mayo"));
        mMap.addMarker(new MarkerOptions().position(hospital2).title("Hospital Hipolito Unanue"));
        mMap.addMarker(new MarkerOptions().position(hospital3).title("Hospital EsSalud Edgardo Rebagliati Martins"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital1));
    }
}
