package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editarProyecto extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_proyecto);
        /*Botón de volver COMIENZO*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*Botón de volver FIN*/


        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_editar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeEditarProyecto();
            }
        });
        /*Dar funcionalidad al botón FIN*/

    }
    /*Método cerrar actividad COMIENZO*/
    public void closeEditarProyecto(){
        Intent intent = new Intent(this, editarProyecto.class);
        finish();
    }
    /*Método cerrar actividad FIN*/
}