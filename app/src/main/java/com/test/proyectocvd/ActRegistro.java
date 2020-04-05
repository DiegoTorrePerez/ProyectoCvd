package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ActRegistro extends AppCompatActivity {

    Spinner spNacionalidad,spDocumento;
    String data1[]  = {"Perú", "Arabia Saudita", "Argentina", "Australia", "Bélgica", "Bolivia", "Brasil", "Canadá", "Chile", "China", "Colombia", "Costa Rica", "Cuba", "Checoslovaquia", "Dinamarca", "Ecuador", "Egipto", "El Salvador", "Etiopía", "Estados Unidos", "Filipinas" , "Francia", "Grecia", "Guatemala", "Haití", "Honduras", "India", "Irán", "Iraq", "Líbano", "Liberia", "Luxemburgo", "México", "Nueva Zelandia", "Nicaragua", "Noruega", "Países Bajos", "Panamá", "Paraguay", "Polonia", "Reino Unido", "Irlanda del Norte", "República Árabe Siria", "República Dominicana"};
    String data2[]  = {"DNI", "Pasaporte","Carné de extranjería", "PTP", "Cédula de identidad"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro);
        asignarReferencias();
    }

    public void evaluacion(View view){
        Intent siguiente  = new Intent(this, ActEvaluacion.class);
        startActivity(siguiente);
    }
    private void asignarReferencias() {
        spNacionalidad = findViewById(R.id.spNacionalidad);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1);
        spNacionalidad.setAdapter(adaptador1);

        spDocumento = findViewById(R.id.spDocumento);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data2);
        spDocumento.setAdapter(adaptador2);
    }

}
