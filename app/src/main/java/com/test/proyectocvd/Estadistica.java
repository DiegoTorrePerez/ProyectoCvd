package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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


    TextView txtTamizados, txtConfirmados, txtHospitalizados, txtUci, txtFallecidos, txtFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        txtTamizados = findViewById(R.id.txtTamizados);
        txtConfirmados = findViewById(R.id.txtConfirmados);
        txtHospitalizados = findViewById(R.id.txtHospitalizados);
        txtUci = findViewById(R.id.txtUci);
        txtFallecidos = findViewById(R.id.txtFallecidos);
        txtFecha = findViewById(R.id.txtFecha);
        obtenerDatosVolley();

    }

    public void obtenerDatosVolley() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://dchang.atwebpages.com/index.php/estadistica", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.i("=======>",jsonArray.toString());

                    List<String> items = new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        items.add(object.getString("fecha"));
                        items.add(object.getString("tamizados"));
                        items.add(object.getString("confirmados"));
                        items.add(object.getString("hospitalizados"));
                        items.add(object.getString("uci"));
                        items.add(object.getString("fallecidos"));
                    }
                    txtFecha.setText(items.get(0));
                    txtHospitalizados.setText(items.get(3));
                    txtTamizados.setText(items.get(1));
                    txtConfirmados.setText(items.get(2));
                    txtUci.setText(items.get(4));
                    txtFallecidos.setText(items.get(5));

                } catch (JSONException e){
                    Log.i("=======>",e.getMessage());
                    Toast.makeText(Estadistica.this,"error2 ", Toast.LENGTH_LONG).show();
                    txtTamizados.setText("2");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Estadistica.this,"error3 ", Toast.LENGTH_LONG).show();
                txtTamizados.setText("3");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
