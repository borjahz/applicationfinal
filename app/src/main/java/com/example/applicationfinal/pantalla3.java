package com.example.applicationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pantalla3 extends AppCompatActivity {
    private Button button;
    private TextView nombre_display, comienzo_display, fin_display, unidades_display, factor_display, valor_display, unidades_display2;

    private String id, nombre, comienzo, fin, unidades, factor, valor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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

    /*Método metodo recoger COMIENZO*/
    public void getIntentAndSetData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("comienzo") && getIntent().hasExtra("fin") &&
                getIntent().hasExtra("unidades") && getIntent().hasExtra("factor") &&
                getIntent().hasExtra("valor")){
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

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    /*Método recoger datos FIN*/
}
