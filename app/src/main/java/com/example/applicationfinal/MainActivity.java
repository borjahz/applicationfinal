package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*Comentario de prueba*/
/*Prueba sin actualizar*/
/*otro comentario de prueba*/

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button= findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, introducirdatos.class);
                startActivity(intent);
            }
        });


        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton2_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
        /*Dar funcionalidad al botón FIN*/
    }
    /*Método abrir actividad COMIENZO*/
    public void openActivity3(){
        Intent intent = new Intent(this, pantalla3.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/


    }

/* Creacion de los boton de abajo*/