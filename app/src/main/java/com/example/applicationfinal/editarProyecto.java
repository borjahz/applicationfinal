package com.example.applicationfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editarProyecto extends AppCompatActivity {

    private Button button, delete_button;
    private EditText nombre_input, comienzo_input, fin_input, unidades_input, factor_input, valor_input;

    private String id, nombre, comienzo, fin, unidades, factor, valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_proyecto);
        /*Botón de volver COMIENZO*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*Botón de volver FIN*/




        nombre_input = findViewById(R.id.Nombre_Proyecto_edit);
        comienzo_input = findViewById(R.id.Fecha_Comienzo_edit);
        fin_input = findViewById(R.id.Fecha_Fin_edit);
        unidades_input = findViewById(R.id.Unidades_edit);
        factor_input = findViewById(R.id.Factor_edit);
        valor_input = findViewById(R.id.Valor_edit);
        button = findViewById(R.id.boton_editar);
        delete_button = findViewById(R.id.boton_borrar);


        /*Escribir datos existentes COMIENZO*/
        getIntentAndSetData();
        /*Escribir datos existentes FIN*/

        /*Nombre barra acción COMIENZO*/
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nombre);
        }
        /*Nombre barra acción FIN*/

        /*Dar funcionalidad al botón editar COMIENZO*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Sobreescribir datos existentes COMIENZO*/
                GestorSQLite myDB = new GestorSQLite(editarProyecto.this);
                nombre = nombre_input.getText().toString().trim();
                comienzo = comienzo_input.getText().toString().trim();
                fin = fin_input.getText().toString().trim();
                unidades = unidades_input.getText().toString().trim();
                factor = factor_input.getText().toString().trim();
                valor = valor_input.getText().toString().trim();
                myDB.updateData(id, nombre, comienzo, fin, unidades, factor, valor);
                /*Sobreescribir datos existentes FIN*/

                closeEditarProyecto();
            }
        });
        /*Dar funcionalidad al botón editar FIN*/

        /*Dar funcionalidad al botón borrar COMIENZO*/
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        /*Dar funcionalidad al botón borrar FIN*/



    }
    /*Método cerrar actividad COMIENZO*/
    public void closeEditarProyecto(){
        Intent intent = new Intent(this, editarProyecto.class);
        finish();
    }
    /*Método cerrar actividad FIN*/

    /*Método metodo recoger COMIENZO*/
    public void getIntentAndSetData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("comienzo") && getIntent().hasExtra("fin") &&
                getIntent().hasExtra("unidades") && getIntent().hasExtra("factor")){
            /*Cogiendo datos del intent*/
            id = getIntent().getStringExtra("id");
            nombre = getIntent().getStringExtra("nombre");
            comienzo = getIntent().getStringExtra("comienzo");
            fin = getIntent().getStringExtra("fin");
            unidades = getIntent().getStringExtra("unidades");
            factor = getIntent().getStringExtra("factor");
            valor = getIntent().getStringExtra("valor");

            /*Escribir datos de la BD en los edit text COMIENZO*/
            nombre_input.setText(nombre);
            comienzo_input.setText(comienzo);
            fin_input.setText(fin);
            unidades_input.setText(unidades);
            factor_input.setText(factor);
            valor_input.setText(valor);
            /*Escribir datos de la BD en los edit text FIN*/

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    /*Método recoger datos FIN*/

    /*Aviso borrar COMIENZO*/
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Borrar " + nombre + "?");
        builder.setMessage("¿Seguro que quieres borrar el proyecto" + nombre + "?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GestorSQLite myDB = new GestorSQLite(editarProyecto.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    /*Aviso borrar FIN*/
}