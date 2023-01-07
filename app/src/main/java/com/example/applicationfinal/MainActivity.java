package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*Comentario de prueba*/
/*Prueba sin actualizar*/
/*otro comentario de prueba*/

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    private Button button;

    GestorSQLite GSQL;
    ArrayList<String> proyecto_id, nombre_input, comienzo_input,
            fin_input, unidades_input, factor_input, valor_input;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        /* Creacion y funcionalidad del boton de abajo COMIENZO*/
        add_button= findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, introducirdatos.class);
                startActivity(intent);
            }
        });
        /* Creacion y funcionalidad del boton de abajo FIN*/

        /* Creacion y funcionalidad del array de datos de abajo COMIENZO*/
        GSQL = new GestorSQLite(MainActivity.this);
        proyecto_id =new ArrayList<>();
        nombre_input =new ArrayList<>();
        comienzo_input =new ArrayList<>();
        fin_input =new ArrayList<>();
        unidades_input =new ArrayList<>();
        factor_input =new ArrayList<>();
        valor_input =new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, proyecto_id, nombre_input,
                comienzo_input, fin_input, unidades_input, factor_input, valor_input);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        /* Creacion y funcionalidad del array de datos de abajo FIN*/

        /*Dar funcionalidad al botón test COMIENZO*/
        button = (Button) findViewById(R.id.boton2_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();

            }
        });
        /*Dar funcionalidad al botón test FIN*/
    }

    /*Método extraer y exponer datos COMIENZO*/
    void  storeDataInArrays(){
        Cursor cursor = GSQL.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                proyecto_id.add(cursor.getString(0));
                nombre_input.add(cursor.getString(1));
                comienzo_input.add(cursor.getString(2));
                fin_input.add(cursor.getString(3));
                unidades_input.add(cursor.getString(4));
                factor_input.add(cursor.getString(5));
                valor_input.add(cursor.getString(6));
            }
        }
    }
    /*Método extraer y exponer datos FIN*/

    /*Método abrir actividad COMIENZO*/
    public void openActivity3(){
        Intent intent = new Intent(this, pantalla3.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/


    }

