package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;


public class Mapa extends AppCompatActivity {


    TextView txtMensaje;
    private long MIN_TIME = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        asignarReferencias();
    }
    private void asignarReferencias() {
        txtMensaje = findViewById(R.id.txtMensaje);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
        }else{
            iniciarLocalizacion();
        }
    }

    private void iniciarLocalizacion() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion local = new Localizacion();
        local.setMainActivity(this,txtMensaje);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnabled){
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);

        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,0,local);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,0,local);
        MIN_TIME=0;
        txtMensaje.setText("Localizacion Agregada");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] granResults){
        if (requestCode ==1000){
            if (granResults[0] ==PackageManager.PERMISSION_GRANTED){
                iniciarLocalizacion();
                return;
            }
        }
    }

}
