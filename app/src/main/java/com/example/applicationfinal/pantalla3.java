package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class pantalla3 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*Dar funcionalidad al botón test COMIENZO*/
        button = (Button) findViewById(R.id.boton_test_editar_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditarComponente();
            }
        });
        /*Dar funcionalidad al botón test FIN*/
    }
    /*Método abrir actividad COMIENZO*/
    public void openEditarProyecto(){
        Intent intent = new Intent(this, editarProyecto.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/

    /*Método abrir actividad COMIENZO*/
    public void openAnadirComponentes(){
        Intent intent = new Intent(this, Anadir_Componentes.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/

    /*Creación menú COMIENZO*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_proyecto, menu);
        return true;
    }
    /*Creación menú FIN*/

    /*Funcionalidad del menú COMIENZO*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                openEditarProyecto();

                return true;
            case R.id.item2:
                openAnadirComponentes();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    /*Funcionalidad del menú FIN*/


    /*Método abrir actividad COMIENZO*/
    public void openEditarComponente(){
        Intent intent = new Intent(this, editarComponente.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/

}