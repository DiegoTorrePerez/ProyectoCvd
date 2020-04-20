package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActRegistro extends AppCompatActivity {

    Spinner spNacionalidad, spDocumento, spEdad;
    String data1[] = {"Perú", "Arabia Saudita", "Argentina", "Australia", "Bélgica", "Bolivia", "Brasil", "Canadá", "Chile", "China", "Colombia", "Costa Rica", "Cuba", "Checoslovaquia", "Dinamarca", "Ecuador", "Egipto", "El Salvador", "Etiopía", "Estados Unidos", "Filipinas", "Francia", "Grecia", "Guatemala", "Haití", "Honduras", "India", "Irán", "Iraq", "Líbano", "Liberia", "Luxemburgo", "México", "Nueva Zelandia", "Nicaragua", "Noruega", "Países Bajos", "Panamá", "Paraguay", "Polonia", "Reino Unido", "Irlanda del Norte", "República Árabe Siria", "República Dominicana"};
    String data2[] = {"DNI", "Pasaporte", "Carné de extranjería", "PTP", "Cédula de identidad"};
    String data3[] = {"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "93", "94", "95", "96", "97", "98","99","100", "101", "102", "103", "104","105","106", "107", "107", "108", "109","110","111"};
    EditText txtCelular, txtDocumento, txtContraseña;
    TextView txtOla;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro);
        asignarReferencias();
    }

    public void SiguienteLogin(View view) {
        Intent siguiente = new Intent(this, Login.class);
        startActivity(siguiente);
    }

    public void evaluacion(View view) {
        Intent siguiente = new Intent(this, ActEvaluacion.class);
        startActivity(siguiente);
    }

    private void asignarReferencias() {

        spNacionalidad = findViewById(R.id.spNacionalidad);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data1);
        spNacionalidad.setAdapter(adaptador1);

        spDocumento = findViewById(R.id.spDocumento);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2);
        spDocumento.setAdapter(adaptador2);

        spEdad = findViewById(R.id.spEdad);
        ArrayAdapter<String> adaptador3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data3);
        spEdad.setAdapter(adaptador3);

        txtCelular = findViewById(R.id.txtCelular);
        txtDocumento = findViewById(R.id.txtDocumento);
        txtContraseña = findViewById(R.id.txtContraseña);
        txtOla= findViewById(R.id.txtOla);
        btnRegistrar = findViewById(R.id.btnRegistrar);

    }
    public void registrar(View view){
        String url = "http://dchang.atwebpages.com/index.php/registro";
        md5 encryption = new md5(txtContraseña.getText().toString());
        String a = encryption.getEncryption();
        txtOla.setText(a);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast toast = Toast.makeText(ActRegistro.this,"Se insertó correctamente",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("=====>",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("nacionalidad",spNacionalidad.getSelectedItem().toString());
                params.put("edad",spEdad.getSelectedItem().toString());
                params.put("tipoDocumento",spDocumento.getSelectedItem().toString());
                params.put("nroCelular",txtCelular.getText().toString());
                params.put("contraseña",txtOla.getText().toString());
                params.put("nroDocumento",txtDocumento.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

