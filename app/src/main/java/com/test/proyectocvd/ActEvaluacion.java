package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActEvaluacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_evaluacion);
    }

    public void foto(View view){
        Intent siguiente  = new Intent(this, Foto.class);
        startActivity(siguiente);
    }
    public void siguiente(View view){
        Intent siguiente  = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }
}
