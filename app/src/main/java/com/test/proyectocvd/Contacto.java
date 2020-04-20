package com.test.proyectocvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.test.proyectocvd.entidades.Persona;
import com.test.proyectocvd.modelo.DAOPersona;

import java.util.ArrayList;

public class Contacto extends AppCompatActivity {

    EditText txtDni, txtNombres;
    Button btnGuardar, btnModificar, btnEliminar;
    ListView lstPersonas;

    ArrayList<Persona> listaPer = new ArrayList<>();
    DAOPersona daoPersona = new DAOPersona(this);

    int id;
    String dni,nombre;
    Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        daoPersona.openDB();
        asignarReferencias();
        cargarPersonas();

    }
    private void cargarPersonas() {
        listaPer = daoPersona.getPersonas();
        ArrayList<String> lista = new ArrayList<>();
        for(Persona per:listaPer){
            lista.add(per.getDni()+" - "+per.getNombres());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        lstPersonas.setAdapter(adapter);
    }

    private void asignarReferencias() {
        txtDni = findViewById(R.id.txtDni);
        txtNombres = findViewById(R.id.txtNombres);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnEliminar);
        lstPersonas = findViewById(R.id.lstPersonas);
        capturarEventos();
    }

    private void capturarEventos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
                daoPersona.registrarPersona(p);
                cargarPersonas();
                limpiar();
            }
        });
        lstPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p = listaPer.get(position);
                setId(p.getId());
                txtDni.setText(p.getDni());
                txtNombres.setText(p.getNombres());
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
                p.setId(id);
                daoPersona.modificarPersona(p);
                cargarPersonas();
                limpiar();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoPersona.eliminarPersona(id);
                cargarPersonas();
                limpiar();
            }
        });
    }

    private void capturarDatos(){
        dni = txtDni.getText().toString();
        nombre = txtNombres.getText().toString();
        p = new Persona(dni,nombre);
    }

    private void limpiar(){
        txtDni.setText("");
        txtNombres.setText("");
        txtDni.requestFocus();
    }
    private void setId(int ind){
        id = ind;
    }

}

