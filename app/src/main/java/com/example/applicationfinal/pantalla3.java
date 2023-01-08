package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class pantalla3 extends AppCompatActivity {
    private FloatingActionButton add_button2;
    private RecyclerView recyclerView2;
    private TextView proyecto_id, nombre_display, comienzo_display, fin_display, unidades_display, factor_display, valor_display, unidades_display2;

    private String id, nombre, comienzo, fin, unidades, factor, valor;
    GestorSQLite GSQL;
    ArrayList<String> componente_id, nombre_input, tipo_input, numero_input, comienzo_comp_input,
            fin_comp_input, comienzo_compue_input, fin_compue_input, precio_input;
    CustomAdapter2 CustomAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView2 = findViewById(R.id.recyclerView2);


        nombre_display = findViewById(R.id.nombre_display);
        comienzo_display = findViewById(R.id.comienzo_display);
        fin_display = findViewById(R.id.fin_display);
        unidades_display = findViewById(R.id.unidades_display);
        factor_display = findViewById(R.id.factor_display);
        valor_display = findViewById(R.id.valor_display);
        unidades_display2 = findViewById(R.id.unidades_display2);


        getIntentAndSetData();

        /*Nombre barra acción COMIENZO*/
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nombre);
        }
        /*Nombre barra acción FIN*/

        /* Creacion y funcionalidad del boton de abajo COMIENZO*/
        add_button2= findViewById(R.id.add_button2);
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla3.this, Anadir_Componentes.class);
                startActivity(intent);
            }
        });
        /* Creacion y funcionalidad del boton de abajo FIN*/

        /* Creacion y funcionalidad del array de datos de abajo COMIENZO*/
        GSQL = new GestorSQLite(pantalla3.this);
        componente_id = new ArrayList<>();
        nombre_input =new ArrayList<>();
        tipo_input =new ArrayList<>();
        numero_input =new ArrayList<>();
        comienzo_comp_input =new ArrayList<>();
        fin_comp_input =new ArrayList<>();
        comienzo_compue_input =new ArrayList<>();
        fin_compue_input =new ArrayList<>();
        precio_input = new ArrayList<>();

        storeDataInArrays2();

        CustomAdapter2= new CustomAdapter2(pantalla3.this, pantalla3.this, componente_id, nombre_input, tipo_input, numero_input,
                comienzo_comp_input, fin_comp_input, comienzo_compue_input, fin_compue_input, precio_input);
        recyclerView2.setAdapter(CustomAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(pantalla3.this));
        /* Creacion y funcionalidad del array de datos de abajo FIN*/
    }

    /*Método abrir actividad COMIENZO*/
    public void openEditarProyecto() {
        Intent intent = new Intent(this, editarProyecto.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/

    /*Método abrir actividad COMIENZO*/
    public void openAnadirComponentes() {
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
    public void openEditarComponente() {
        Intent intent = new Intent(this, editarComponente.class);
        startActivity(intent);
    }
    /*Método abrir actividad FIN*/

    /*Método metodo recoger COMIENZO*/
    public void getIntentAndSetData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("comienzo") && getIntent().hasExtra("fin") &&
                getIntent().hasExtra("unidades") && getIntent().hasExtra("factor") &&
                getIntent().hasExtra("valor")) {
            /*Cogiendo datos del intent*/
            id = getIntent().getStringExtra("id");
            nombre = getIntent().getStringExtra("nombre");
            comienzo = getIntent().getStringExtra("comienzo");
            fin = getIntent().getStringExtra("fin");
            unidades = getIntent().getStringExtra("unidades");
            factor = getIntent().getStringExtra("factor");
            valor = getIntent().getStringExtra("valor");

            /*Escribir datos de la BD en los text view COMIENZO*/
            nombre_display.setText(nombre);
            comienzo_display.setText(comienzo);
            fin_display.setText(fin);
            unidades_display.setText(unidades);
            factor_display.setText(factor);
            valor_display.setText(valor);
            unidades_display2.setText(unidades);

            /*Escribir datos de la BD en los text view FIN*/

        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /*Método recoger datos FIN*/
    /*Método extraer y exponer datos COMIENZO*/
    void storeDataInArrays2() {
        Cursor cursor = GSQL.readAllData2();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos de componentes", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                componente_id.add(cursor.getString(0));
                nombre_input.add(cursor.getString(1));
                tipo_input.add(cursor.getString(2));
                numero_input.add(cursor.getString(3));
                comienzo_comp_input.add(cursor.getString(4));
                fin_comp_input.add(cursor.getString(5));
                comienzo_compue_input.add(cursor.getString(6));
                fin_compue_input.add(cursor.getString(7));
                precio_input.add(cursor.getString(8));

            }
        }
        /*Método extraer y exponer datos FIN*/
    }
}
