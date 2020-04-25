package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ActEvaluacion extends AppCompatActivity {
    RadioButton rbTos1, rbTos2,rbTos3,rbTos4,rbRespiracion1,rbRespiracion2,rbRespiracion3,rbRespiracion4,rbGarganta1,rbGarganta2,rbGarganta3,rbGarganta4,rbContacto1,rbContacto2,rbDesplazamiento1,rbDesplazamiento2,rbTrabajo1,rbTrabajo2;
    EditText etTemperatura;
    Button btnRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_evaluacion);
        rbTos1 = findViewById(R.id.rbTos1);
        rbTos2 = findViewById(R.id.rbTos2);
        rbTos3 = findViewById(R.id.rbTos3);
        rbTos4 = findViewById(R.id.rbTos4);
        rbRespiracion1 = findViewById(R.id.rbRespiracion1);
        rbRespiracion2 = findViewById(R.id.rbRespiracion2);
        rbRespiracion3 = findViewById(R.id.rbRespiracion3);
        rbRespiracion4 = findViewById(R.id.rbRespiracion4);
        rbGarganta1 = findViewById(R.id.rbGarganta1);
        rbGarganta2 = findViewById(R.id.rbGarganta2);
        rbGarganta3 = findViewById(R.id.rbGarganta3);
        rbGarganta4 = findViewById(R.id.rbGarganta4);
        rbContacto1 = findViewById(R.id.rbContacto1);
        rbContacto2 = findViewById(R.id.rbContacto2);
        rbDesplazamiento1 = findViewById(R.id.rbDesplazamiento1);
        rbDesplazamiento2 = findViewById(R.id.rbDesplazamiento2);
        rbTrabajo1 = findViewById(R.id.rbTrabajo1);
        rbTrabajo2 = findViewById(R.id.rbTrabajo2);
        etTemperatura = findViewById(R.id.etTemperatura);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);
    }


    public void registrarse(View view){
        String url = "http://dchang.atwebpages.com/index.php/triaje";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast toast = Toast.makeText(ActEvaluacion.this,"Se insertó correctamente",Toast.LENGTH_LONG);
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
            protected Map<String,String> getParams() {
                Log.d("=====>","fase1");
                Map<String, String> params = new HashMap<>();
                params.put("IdPaciente ","2");
                params.put("temperatura",etTemperatura.getText().toString());
                if (rbTos1.isChecked() == true) {
                    params.put("tos", rbTos1.getText().toString());
                } else if (rbTos2.isChecked() == true) {
                    params.put("tos", rbTos2.getText().toString());
                } else if (rbTos3.isChecked() == true) {
                    params.put("tos", rbTos3.getText().toString());
                } else if (rbTos4.isChecked() == true) {
                    params.put("tos", rbTos4.getText().toString());
                }
                if (rbRespiracion1.isChecked() == true) {
                    params.put("respira", rbRespiracion1.getText().toString());
                } else if (rbRespiracion2.isChecked() == true) {
                    params.put("respira", rbRespiracion2.getText().toString());
                } else if (rbRespiracion3.isChecked() == true) {
                    params.put("respira", rbRespiracion3.getText().toString());
                } else if (rbRespiracion4.isChecked() == true) {
                    params.put("respira", rbRespiracion4.getText().toString());
                }
                if (rbGarganta1.isChecked() == true) {
                    params.put("dolor_garganta", rbGarganta1.getText().toString());
                } else if (rbGarganta2.isChecked() == true) {
                    params.put("dolor_garganta", rbGarganta2.getText().toString());
                } else if (rbGarganta3.isChecked() == true) {
                    params.put("dolor_garganta", rbGarganta3.getText().toString());
                } else if (rbGarganta4.isChecked() == true) {
                    params.put("dolor_garganta", rbGarganta4.getText().toString());
                }
                if (rbContacto1.isChecked() == true) {
                    params.put("contacto_enf", rbContacto1.getText().toString());
                } else if (rbContacto2.isChecked() == true) {
                    params.put("contacto_enf", rbContacto2.getText().toString());
                }
                if (rbDesplazamiento1.isChecked() == true) {
                    params.put("desplazamiento", rbDesplazamiento1.getText().toString());
                } else if (rbDesplazamiento2.isChecked() == true) {
                    params.put("desplazamiento", rbDesplazamiento2.getText().toString());
                }
                if (rbTrabajo1.isChecked() == true) {
                    params.put("trabaja_salud", rbTrabajo1.getText().toString());
                } else if (rbTrabajo2.isChecked() == true) {
                    params.put("trabaja_salud", rbTrabajo2.getText().toString());
                }
                Log.d("=====>","fase2");
                params.put("enfermedades","10");
                params.put("puntaje ","40");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Intent siguiente  = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }

}
