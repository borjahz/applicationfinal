package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editarProyecto extends AppCompatActivity {

    private Button button;
    private EditText nombre_input, comienzo_input, fin_input, unidades_input, factor_input;

    private String id, nombre, comienzo, fin, unidades, factor;


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


        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_editar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeEditarProyecto();
            }
        });
        /*Dar funcionalidad al botón FIN*/
        getIntentAndSetData();

    }
    /*Método cerrar actividad COMIENZO*/
    public void closeEditarProyecto(){
        Intent intent = new Intent(this, editarProyecto.class);
        finish();
    }
    /*Método cerrar actividad FIN*/

    /*Método editar COMIENZO*/
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

            /*Meter datos nuevos en la BD*/
            nombre_input.setText(nombre);
            comienzo_input.setText(comienzo);
            fin_input.setText(fin);
            unidades_input.setText(unidades);
            factor_input.setText(factor);
        }else{
            Toast.makeText(this, "No ha hecho cambios", Toast.LENGTH_SHORT).show();
        }
    }
    /*Método editar FIN*/
}