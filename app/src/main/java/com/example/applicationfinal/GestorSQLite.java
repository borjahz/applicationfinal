/* Todo lo que esta aqui escrito es para hacer la base de datos*/
package com.example.applicationfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestorSQLite extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME ="Proyectos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME ="Libreria";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE= "Nombre_Proyecto";
    private static final String COLUMN_COMIENZO= "Fecha_Comienzo";
    private static final String COLUMN_FINAL=  "Fecha_Final";
    private static final String COLUMN_UNIDADES= "Unidades_Extra";
    private static final String COLUMN_FACTOR= "Factor_Unidades";



    public GestorSQLite( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + "TEXT, " +
                COLUMN_COMIENZO + "DATE, " +
                COLUMN_FINAL + "DATE, " +
                COLUMN_UNIDADES + " INTEGER, " +
                COLUMN_FACTOR + "TEXT); ";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
