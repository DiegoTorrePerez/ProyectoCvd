package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void SiguienteConsejos(View view) {
        Intent siguiente  = new Intent(this, Consejos.class);
        startActivity(siguiente);
    }
    public void SiguienteEstadística(View view) {
        Intent siguiente  = new Intent(this, Estadistica.class);
        startActivity(siguiente);
    }
    public void SiguienteLlamadaEmergencia(View view) {
        Intent siguiente  = new Intent(this, LlamadaEmergencia.class);
        startActivity(siguiente);
    }
    public void SiguienteMapa(View view) {
        Intent siguiente  = new Intent(this, Mapa.class);
        startActivity(siguiente);
    }
    public void SiguienteMiEstado(View view) {
        Intent siguiente  = new Intent(this, Contacto.class);
        startActivity(siguiente);
    }
    public void SiguienteMisSintomas(View view) {
        Intent siguiente  = new Intent(this, ActEvaluacion.class);
        startActivity(siguiente);
    }

}
