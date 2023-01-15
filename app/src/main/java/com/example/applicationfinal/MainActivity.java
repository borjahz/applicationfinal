package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*Comentario de prueba*/
/*Prueba sin actualizar*/
/*otro comentario de prueba*/

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton add_button;
    private SwipeRefreshLayout swipeRefreshLayout;

    GestorSQLite GSQL;
    ArrayList<String> proyecto_id, nombre_input, comienzo_input,
            fin_input, unidades_input, factor_input, valor_input, comienzo_input_date;
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

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

        customAdapter = new CustomAdapter(MainActivity.this, this, proyecto_id, nombre_input,
                comienzo_input, fin_input, unidades_input, factor_input, valor_input, comienzo_input_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        /* Creacion y funcionalidad del array de datos de abajo FIN*/

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if(swipeRefreshLayout!=null) {
                            proyecto_id.clear();
                            nombre_input.clear();
                            comienzo_input.clear();
                            fin_input.clear();
                            unidades_input.clear();
                            factor_input.clear();
                            valor_input.clear();
                        }
                        storeDataInArrays();
                        customAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
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

