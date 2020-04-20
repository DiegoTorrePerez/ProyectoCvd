package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadistica extends AppCompatActivity {


    TextView txtTamizados, txtConfirmados, txtHospitalizados, txtUci, txtFallecidos;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        txtTamizados = findViewById(R.id.txtTamizados);
        queue = Volley.newRequestQueue(this);
        obtenerDatosVolley();

    }
    public void obtenerDatosVolley() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://dchang.atwebpages.com/index.php/estadistica", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mJsonArray = response.getJSONArray(null);
                    for (int i=0;i<mJsonArray.length();i++){
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String fecha = mJsonObject.getString("fecha");
                        Toast.makeText(Estadistica.this,"fecha: "+ fecha, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(Estadistica.this,"error2 ", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Estadistica.this,"error3 ", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }
}
