package com.test.proyectocvd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMaps extends SupportMapFragment implements OnMapReadyCallback {

    double lat,lon;

    public FragmentMaps(){

    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View rootView =  super.onCreateView(layoutInflater, viewGroup, bundle);
        if(getArguments() !=null){
            this.lat = getArguments().getDouble("lat");
            this.lon = getArguments().getDouble("lon");

        }
        getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(lat,lon);
        float zoom = 17;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        googleMap.addMarker(new MarkerOptions().position(latLng).title("MI UBICACIÓN").icon(BitmapDescriptorFactory.fromResource(R.drawable.pegman)));
        UiSettings settings = googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);

        LatLng Bernales = new LatLng(-11.914676,-77.038537);
        googleMap.addMarker(new MarkerOptions().position(Bernales).title("Hospital Sergio E. Bernales")
                .snippet("Hospital del estado especializado en coronavirus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)).infoWindowAnchor(0.5f, 0.5f));
        LatLng DosdeMayo = new LatLng(-12.056836,-77.015923);
        googleMap.addMarker(new MarkerOptions().position(DosdeMayo).title("Hospital Nacional Dos De Mayos")
                .snippet("Hospital del estado especializado en coronavirus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)).infoWindowAnchor(0.5f, 0.5f));
        LatLng Arzobispo = new LatLng(-12.050008,-77.042751);
        googleMap.addMarker(new MarkerOptions().position(Arzobispo).title("Hospital Nacional Arzobispo Loayza")
                .snippet("Hospital del estado especializado en coronavirus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)).infoWindowAnchor(0.5f, 0.5f));
        LatLng Carrion = new LatLng(-12.062552,-77.123695);
        googleMap.addMarker(new MarkerOptions().position(Carrion).title("Hospital Nacional Daniel Alcides Carrión")
                .snippet("Hospital del estado especializado en coronavirus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)).infoWindowAnchor(0.5f, 0.5f));
        LatLng sanJuan = new LatLng(-11.966571,-77.003493);
        googleMap.addMarker(new MarkerOptions().position(sanJuan).title("Hospital San Juan de Lurigancho")
                .snippet("Hospital del estado especializado en coronavirus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)).infoWindowAnchor(0.5f, 0.5f));

    }
}
