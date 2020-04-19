package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    Spinner spNacionalidad,spDocumento, spEdad;
    String data1[]  = {"Perú", "Arabia Saudita", "Argentina", "Australia", "Bélgica", "Bolivia", "Brasil", "Canadá", "Chile", "China", "Colombia", "Costa Rica", "Cuba", "Checoslovaquia", "Dinamarca", "Ecuador", "Egipto", "El Salvador", "Etiopía", "Estados Unidos", "Filipinas" , "Francia", "Grecia", "Guatemala", "Haití", "Honduras", "India", "Irán", "Iraq", "Líbano", "Liberia", "Luxemburgo", "México", "Nueva Zelandia", "Nicaragua", "Noruega", "Países Bajos", "Panamá", "Paraguay", "Polonia", "Reino Unido", "Irlanda del Norte", "República Árabe Siria", "República Dominicana"};
    String data2[]  = {"DNI", "Pasaporte","Carné de extranjería", "PTP", "Cédula de identidad"};
    String data3[]  = {"18", "19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75"};
    EditText txtCelular, txtDocumento, txtContraseña;
    Button btnRegistrar, btnAtras;
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

        spEdad = findViewById(R.id.spEdad);
        ArrayAdapter<String> adaptador3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data3);
        spEdad.setAdapter(adaptador3);

        txtCelular = findViewById(R.id.txtCelular);
        txtDocumento = findViewById(R.id.txtDocumento);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String TipoDocumento, NroDocumento, Contrasena, Nacionalidad, NoCelular, Edad;
            NoCelular = txtCelular.getText().toString();
            Contrasena = txtContraseña.getText().toString();
            NroDocumento = txtDocumento.getText().toString();
            Nacionalidad = spNacionalidad.getSelectedItem().toString();
            Edad = spEdad.getSelectedItem().toString();
            TipoDocumento = spDocumento.getSelectedItem().toString();

//            if(!correo.isEmpty() && !pass.isEmpty()){
//                validarUsuario("http://eatdate.atwebpages.com/index.php/login");
//            }else{
//                Toast.makeText(MainActivity.this,"Los campos no pueden ser nulos",Toast.LENGTH_LONG).show();
//            }
        }
    });
    }
/*
    private void guardarPreferencias(String nombres,String apellidos){
        SharedPreferences preferences = getSharedPreferences("preferenciasUsuario",MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("u_nombres",nombres);
        editor.putString("u_apellidos",apellidos);
        editor.commit();
    }

    private void validarUsuario(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0){
                        Toast.makeText(ActRegistro.this,"Correo o contraseña incorrecta",Toast.LENGTH_LONG).show();
                        txtContraseña.setText("");
                    } else {
                        List<String> items = new ArrayList<>();
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            items.add(object.getString("nombres"));
                            items.add(object.getString("apellidos"));
                            items.add(object.getString("apellidos"));
                            items.add(object.getString("apellidos"));
                        }
                        guardarPreferencias(items.get(0),items.get(1));
                        Intent intent = new Intent(getApplicationContext(),ActEvaluacion.class);
                        startActivity(intent);
                    }
                }catch (JSONException e){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActRegistro.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", txtCorreo.getText().toString());
                parametros.put("password", txtPassword.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/

}
