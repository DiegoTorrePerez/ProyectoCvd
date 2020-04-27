package com.test.proyectocvd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Color.BLACK;

public class Login extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    CheckBox checkBox;
    TextView txtola;
    EditText txtDocumento, txtContraseña;
    Button btnIngresar;
    GoogleApiClient googleApiClient;
    String SiteKey = "6Lf2Ku8UAAAAAM0em8iDij9BVHgpeSpavP7VhRox";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Log.d("=====>",""+ FirebaseInstanceId.getInstance().getToken());
        setContentView(R.layout.activity_login);
        asignarReferencia();
        checkBox = findViewById(R.id.check_box);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Login.this)
                .build();
        googleApiClient.connect();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && status.isSuccess()){
                                        Toast.makeText(getApplicationContext(),
                                                "Successfully verified",Toast.LENGTH_LONG).show();
                                        checkBox.setTextColor(Color.GREEN);
                                    }
                                }
                            });
                }else {
                    checkBox.setTextColor(Color.BLACK);
                }
            }
        });
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
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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

    public void SiguRegistro(View view) {
        Intent siguiente  = new Intent(this, ActRegistro.class);
        startActivity(siguiente);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
