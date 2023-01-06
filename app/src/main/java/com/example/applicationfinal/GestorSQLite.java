/* Todo lo que esta aqui escrito es para hacer la base de datos*/
package com.example.applicationfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GestorSQLite extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME ="Proyectos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME ="Libreria";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE= "Nombre_Proyecto";
    private static final String COLUMN_COMIENZO= "Fecha_Comienzo";
    private static final String COLUMN_FINALIZACION=  "Fecha_Final";
    private static final String COLUMN_UNIDADES= "Unidades_Extra";
    private static final String COLUMN_FACTOR= "Factor_Unidades";



    public GestorSQLite(@Nullable/*Añadido "Nullable"*/ Context context
            /* Borrado ", String name, SQLiteDatabase.CursorFactory factory, int version" (no está en el vídeo y daba error)*/) {
        super(context, DATABASE_NAME, null /*"null" sustituye a "factory"*/, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + "TEXT, " +
                COLUMN_COMIENZO + "INTEGER, " +     /*Comienzo y finalizacion integer para fecha tiempo UNIX*/
                COLUMN_FINALIZACION + "INTEGER, " + /*Cambio de final a finalización porque más adelante se utiliza
                                                    final y android studio lo incterpreta como algo que pone en naranja*/
                COLUMN_UNIDADES + " TEXT, " +       /*Unidades texto (por ejemplo "km")*/
                COLUMN_FACTOR + "INTEGER); ";       /*Factor integer (por ejemplo "100" para resultar 100km entre los dos)*/
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void anadirProyecto(String nombre, int comienzo, int finalizacion, String unidades, int factor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_COMIENZO, comienzo);
        cv.put(COLUMN_FINALIZACION, finalizacion);
        cv.put(COLUMN_UNIDADES, unidades);
        cv.put(COLUMN_FACTOR, factor);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error al añadir proyecto", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Añandido correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            db.rawQuery(query, null);
        }
    return cursor;
    }
}
