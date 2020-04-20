package com.test.proyectocvd.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPersona extends SQLiteOpenHelper {
    public DBPersona(Context context){
        super(context,Constantes.NOMBREBD,null,Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Constantes.NOMBRETABLA+""+
                "(id integer Primary key autoincrement,"+
                "dniPer text not null,"+
                "nomPer text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

