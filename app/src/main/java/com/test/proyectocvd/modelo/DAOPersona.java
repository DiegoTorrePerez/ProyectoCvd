package com.test.proyectocvd.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.test.proyectocvd.entidades.Persona;
import com.test.proyectocvd.util.Constantes;
import com.test.proyectocvd.util.DBPersona;

import java.util.ArrayList;

public class DAOPersona {
    DBPersona dbPersona;
    SQLiteDatabase database;
    public DAOPersona(Context context){
        dbPersona = new DBPersona(context);
    }
    public void openDB(){
        database = dbPersona.getWritableDatabase();
    }
    public void closeDB(){
        dbPersona.close();
        database.close();
    }
    public void registrarPersona(Persona persona){
        try {
            ContentValues valores = new ContentValues();
            valores.put("dniPer",persona.getDni());
            valores.put("nomPer",persona.getNombres());
            database.insert(Constantes.NOMBRETABLA,null,valores);
        }catch (Exception e){

        }
    }

    public void modificarPersona(Persona persona){
        try {
            ContentValues valores = new ContentValues();
            valores.put("dniPer",persona.getDni());
            valores.put("nomPer",persona.getNombres());
            database.update(Constantes.NOMBRETABLA,valores,"id="+persona.getId(),null);
        }catch (Exception e){

        }
    }

    public void eliminarPersona(int id){
        try{
            database.delete(Constantes.NOMBRETABLA,"id="+id,null);
        }catch (Exception e){

        }
    }

    public ArrayList<Persona> getPersonas(){
        ArrayList<Persona> listaPer = new ArrayList<>();
        try{
            Cursor c = database.rawQuery("SELECT * FROM "+Constantes.NOMBRETABLA,null);
            while (c.moveToNext()){
                listaPer.add(new Persona(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2)));
            }
        }catch (Exception e){

        }
        return listaPer;
    }
}

















