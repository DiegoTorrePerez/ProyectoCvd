package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Estadistica extends AppCompatActivity {


    TextView txtTamizados, txtConfirmados, txtHospitalizados, txtUci, TxtFallecidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        asignarReferencia();
    }

    private void asignarReferencia() {
        txtTamizados = findViewById(R.id.txtTamizados);
        txtConfirmados = findViewById(R.id.txtConfirmados);
        txtHospitalizados = findViewById(R.id.txtHospitalizados);
        txtUci = findViewById(R.id.txtHospitalizados);
        txtHospitalizados = findViewById(R.id.txtHospitalizados);
    }


}
