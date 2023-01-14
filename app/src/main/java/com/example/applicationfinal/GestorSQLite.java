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
    private static final String DATABASE_NAME = "Proyectos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Libreria";
    private static final String TABLE_NAME2 = "Componentes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE = "Nombre_Proyecto";
    private static final String COLUMN_COMIENZO = "Fecha_Comienzo";
    private static final String COLUMN_FINALIZACION = "Fecha_Final";
    private static final String COLUMN_UNIDADES = "Unidades_Extra";
    private static final String COLUMN_FACTOR = "Factor_Unidades";
    private static final String COLUMN_VALOR = "Valor_Unidades";
    private static final String COLUMN_ID2 = "_id_componente";
    private static final String COLUMN_IDFK = "_id_fk";
    private static final String COLUMN_TIPO = "Tipo_Componente";
    private static final String COLUMN_NUMERO = "Numero_Componente";
    private static final String COLUMN_COM_COMP = "Comienzo_Componente";
    private static final String COLUMN_FIN_COMP = "Fin_Componente";
    private static final String COLUMN_COMUE_COMP = "Comienzo_Componente_UE";
    private static final String COLUMN_FINUE_COMP = "Fin_Componente_UE";
    private static final String COLUMN_PRECIO = "Precio_Componente";
    String id= DataHolder2.getInstance().getData();
    GestorSQLite(@Nullable/*Añadido "Nullable"*/ Context context
            /* Borrado ", String name, SQLiteDatabase.CursorFactory factory, int version" (no está en el vídeo y daba error)*/) {
        super(context, DATABASE_NAME, null /*"null" sustituye a "factory"*/, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_COMIENZO + " INTEGER, " +     /*Comienzo y finalizacion integer para fecha tiempo UNIX*/
                COLUMN_FINALIZACION + " INTEGER, " + /*Cambio de final a finalización porque más adelante se utiliza
                                                    final y android studio lo incterpreta como algo que pone en naranja*/
                COLUMN_UNIDADES + " TEXT, " +       /*Unidades texto (por ejemplo "km")*/
                COLUMN_FACTOR + " INTEGER, " +       /*Unidades texto (por ejemplo "km")*/
                COLUMN_VALOR + " INTEGER); ";       /*Factor integer (por ejemplo "100" para resultar 100km entre los dos)*/
        db.execSQL(query);

        String table2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IDFK + " INTEGER NOT NULL," +
                COLUMN_TIPO + " TEXT NOT NULL, " +
                COLUMN_NUMERO + " INTEGER, " +
                COLUMN_COM_COMP + " INTEGER, " +
                COLUMN_FIN_COMP + " INTEGER, " +
                COLUMN_COMUE_COMP + " INTEGER, " +
                COLUMN_FINUE_COMP + " INTEGER, " +
                COLUMN_PRECIO + " INTEGER," +
                " FOREIGN KEY ("+COLUMN_IDFK+") REFERENCES " + TABLE_NAME + "("+COLUMN_ID+"));";
        db.execSQL(table2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    void anadirProyecto(String nombre, int comienzo, int finalizacion, String unidades, int factor, int valor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_COMIENZO, comienzo);
        cv.put(COLUMN_FINALIZACION, finalizacion);
        cv.put(COLUMN_UNIDADES, unidades);
        cv.put(COLUMN_FACTOR, factor);
        cv.put(COLUMN_VALOR, valor);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error al añadir proyecto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Añadido correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    void anadirComponente(String id_fk, String tipo_componente, int numero, int comienzo_componente, int fin_componente,
                          int comienzo_componente_ue, int fin_componente_ue, int precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println(tipo_componente + id_fk + String.valueOf(numero) + String.valueOf(comienzo_componente) + String.valueOf(fin_componente)+ String.valueOf(comienzo_componente_ue) + String.valueOf(fin_componente_ue) + String.valueOf(precio));

        cv.put(COLUMN_IDFK, id_fk);
        cv.put(COLUMN_TIPO, tipo_componente);
        cv.put(COLUMN_NUMERO, numero);
        cv.put(COLUMN_COM_COMP, comienzo_componente);
        cv.put(COLUMN_FIN_COMP, fin_componente);
        cv.put(COLUMN_COMUE_COMP, comienzo_componente_ue);
        cv.put(COLUMN_FINUE_COMP, fin_componente_ue);
        cv.put(COLUMN_PRECIO, precio);

        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error al añadir componente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Añandido correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println(db);
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllData2() {

        String query = "SELECT * FROM "  + TABLE_NAME2 + " WHERE _id_fk = '" + id + "';";

        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("miaumiau");
        System.out.println(id);
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    /*Para leer los costes de cada componente*/
    Cursor readAllData3(){
        String query = "SELECT Precio_Componente FROM "  + TABLE_NAME2 + " WHERE _id_fk = '" + id + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }



    void updateData(String row_id, String nombre, String comienzo,
                    String finalizacion, String unidades, String factor, String valor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_COMIENZO, comienzo);
        cv.put(COLUMN_FINALIZACION, finalizacion);
        cv.put(COLUMN_UNIDADES, unidades);
        cv.put(COLUMN_FACTOR, factor);
        cv.put(COLUMN_VALOR, valor);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Error al editar proyecto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Editado correctamente", Toast.LENGTH_SHORT).show();
        }

    }    void updateData2(String row_id_comp, String row_id_fk, String tipo, String numero, String comienzo_comp,
                          String fin_comp,  String comienzo_compue, String fin_compue, String precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IDFK, row_id_fk);
        cv.put(COLUMN_TIPO, tipo);
        cv.put(COLUMN_NUMERO, numero);
        cv.put(COLUMN_COM_COMP, comienzo_comp);
        cv.put(COLUMN_FIN_COMP, fin_comp);
        cv.put(COLUMN_COMUE_COMP, comienzo_compue);
        cv.put(COLUMN_FINUE_COMP, fin_compue);
        cv.put(COLUMN_PRECIO, precio);

        long result = db.update(TABLE_NAME2, cv, "_id_componente=?", new String[]{row_id_comp});
        if (result == -1) {
            Toast.makeText(context, "Error al editar componente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Componente editado correctamente", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result ==-1)

        {
            Toast.makeText(context, "Error al borrar proyecto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow2(String row_id_comp) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, "_id_componente=?", new String[]{row_id_comp});
        if(result ==-1)

        {
            Toast.makeText(context, "Error al borrar componente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Componente borrado correctamente", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }



}

