package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class LlamadaEmergencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada_emergencia);
    }
    public void Llamada1(View view){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:113"));
        if(ActivityCompat.checkSelfPermission(LlamadaEmergencia.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return;
        startActivity(i);
    }
    public void Llamada2(View view){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:106"));
        if(ActivityCompat.checkSelfPermission(LlamadaEmergencia.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return;
        startActivity(i);
    }
}
