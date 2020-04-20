package com.test.proyectocvd;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Localizacion implements LocationListener {

    TextView txtMensaje;
    Mapa mainActivity;

    public Mapa getMainActivity(){
        return mainActivity;
    }

    public void setMainActivity(Mapa mainActivity, TextView txtMensaje){
        this.mainActivity = mainActivity;
        this.txtMensaje = txtMensaje;
    }
    @Override
    public void onLocationChanged(Location location) {
        String texto = "Mi ubicacion es: \nLatitud = "+location.getLongitude()+"\nLongitus = "+location.getLongitude();
        txtMensaje.setText(texto);
        mapa(location.getLatitude(),location.getLongitude());
    }

    private void mapa(double latitude, double longitude) {
        com.test.proyectocvd.FragmentMaps fragment = new com.test.proyectocvd.FragmentMaps();
        Bundle bundle = new Bundle();
        bundle.putDouble("lat",new Double(latitude));
        bundle.putDouble("lon",new Double(longitude));
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getMainActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,fragment,null);
        fragmentTransaction.commit();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status){
            case LocationProvider.AVAILABLE:
                Log.d("debug","LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug","LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug","LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

        txtMensaje.setText("GPS ACTIVADO");
    }

    @Override
    public void onProviderDisabled(String provider) {
        txtMensaje.setText("GPS DESACTIVADO");

    }
}
