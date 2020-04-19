package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView txtola;
    EditText txtDocumento, txtContraseña;
    Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarReferencia();
    }

    private void asignarReferencia() {
        txtDocumento = findViewById(R.id.txtDocumento);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtola = findViewById(R.id.txtola);
        btnIngresar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String documento;
                documento = txtDocumento.getText().toString();
                md5 encryption = new md5(txtContraseña.getText().toString());
                String a = encryption.getEncryption();
                txtola.setText(a);

                if(!documento.isEmpty() && !a.isEmpty()){
                    validarUsuario("http://dchang.atwebpages.com/index.php/loginCV19");
                }else{
                    Toast.makeText(Login.this,"Los campos no pueden ser nulos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void guardarPreferencias(String nombres,String apellidos){
        SharedPreferences preferences = getSharedPreferences("preferenciasUsuario",MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("u_nombres",nombres);
        editor.putString("u_apellidos",apellidos);
        editor.commit();
    }

    private void validarUsuario(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() == 0){
                        Toast.makeText(Login.this,"Correo o contraseña incorrecta",Toast.LENGTH_LONG).show();
                        txtDocumento.setText("");
                        txtContraseña.setText("");
                    } else {
                        List<String> items = new ArrayList<>();
                        for(int i = 0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            items.add(object.getString("IdPaciente"));
                            items.add(object.getString("TipoDocumento"));
                            items.add(object.getString("NroDocumento"));
                            items.add(object.getString("Resultado_IdResultado"));
                        }
                        guardarPreferencias(items.get(0),items.get(1));
                        Intent intent = new Intent(getApplicationContext(),ActEvaluacion.class);
                        startActivity(intent);
                    }
                }catch (JSONException e){
                    Toast.makeText(Login.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("tip_doc", "DNI");
                parametros.put("nro_doc", txtDocumento.getText().toString());
                parametros.put("clave", txtola.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
