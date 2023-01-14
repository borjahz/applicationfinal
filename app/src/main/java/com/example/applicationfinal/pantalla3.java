package com.example.applicationfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class pantalla3 extends AppCompatActivity {
    private FloatingActionButton add_button2;
    private RecyclerView recyclerView2;
    private TextView proyecto_id, nombre_display, comienzo_display, fin_display, unidades_display, factor_display, valor_display, unidades_display2;
    private SwipeRefreshLayout swipeRefreshLayout2;


    private String id, nombre, comienzo, fin, unidades, factor, valor;
    GestorSQLite GSQL;
    ArrayList<String> componente_id;
    ArrayList<String> proyecto_id_fk_input;
    ArrayList<String> tipo_input;
    ArrayList<String> numero_input;
    ArrayList<String> comienzo_comp_input;
    ArrayList<String> fin_comp_input;
    ArrayList<String> comienzo_compue_input;
    ArrayList<String> fin_compue_input;
    ArrayList<String> precio_input;
    ArrayList<String> precio;
    CustomAdapter2 CustomAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);

        recyclerView2 = findViewById(R.id.recyclerView2);
        swipeRefreshLayout2 = findViewById(R.id.swipeRefresh2);


        proyecto_id = findViewById(R.id.id_proyecto_display);
        nombre_display = findViewById(R.id.nombre_display);
        comienzo_display = findViewById(R.id.comienzo_display);
        fin_display = findViewById(R.id.fin_display);
        unidades_display = findViewById(R.id.unidades_display);
        factor_display = findViewById(R.id.factor_display);
        valor_display = findViewById(R.id.valor_display);
        unidades_display2 = findViewById(R.id.unidades_display2);

        System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        getIntentAndSetData();
        System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println(precio);

        /*Nombre barra acción COMIENZO*/
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nombre);
        }
        /*Nombre barra acción FIN*/

        /* Creacion y funcionalidad del boton de abajo COMIENZO*/
        add_button2 = findViewById(R.id.add_button2);
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
        proyecto_id_fk_input = new ArrayList<>();
        tipo_input = new ArrayList<>();
        numero_input = new ArrayList<>();
        comienzo_comp_input = new ArrayList<>();
        fin_comp_input = new ArrayList<>();
        comienzo_compue_input = new ArrayList<>();
        fin_compue_input = new ArrayList<>();
        precio_input = new ArrayList<>();
        storeDataInArrays2();
        precio = new ArrayList<>();
        Leernumeros();
        sumanumeros();


        CustomAdapter2 = new CustomAdapter2(pantalla3.this, this, componente_id, proyecto_id_fk_input, tipo_input, numero_input,
                comienzo_comp_input, fin_comp_input, comienzo_compue_input, fin_compue_input, precio_input);
        recyclerView2.setAdapter(CustomAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(pantalla3.this));
        /* Creacion y funcionalidad del array de datos de abajo FIN*/

        swipeRefreshLayout2.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (swipeRefreshLayout2 != null) {
                            componente_id.clear();
                            proyecto_id_fk_input.clear();
                            tipo_input.clear();
                            numero_input.clear();
                            comienzo_comp_input.clear();
                            fin_comp_input.clear();
                            comienzo_compue_input.clear();
                            fin_compue_input.clear();
                            precio_input.clear();
                            precio.clear();
                        }
                        getIntentAndSetData();
                        storeDataInArrays2();
                        Leernumeros();
                        sumanumeros();
                        CustomAdapter2.notifyDataSetChanged();
                        swipeRefreshLayout2.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }


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
            DataHolder2.getInstance().setData(String.valueOf(id));

            /*Escribir datos de la BD en los text view COMIENZO*/
            proyecto_id.setText(id);
            nombre_display.setText(nombre);
            comienzo_display.setText(comienzo);
            fin_display.setText(fin);
            unidades_display.setText(unidades);
            factor_display.setText(factor);
            valor_display.setText(valor);
            unidades_display2.setText(unidades);

            /*Escribir datos de la BD en los text view FIN*/

        } else {
            Toast.makeText(this, "Error al recoger datos de proyecto", Toast.LENGTH_SHORT).show();
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
                proyecto_id_fk_input.add(cursor.getString(1));
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

    void Leernumeros() {
        Cursor cursor = GSQL.readAllData3();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos de componentes", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                precio.add(cursor.getString(0));
            }
        }
    }

    void sumanumeros() {
        int[] Arr= new int[precio.size()];
    int  sum=0;
        for (int i = 0; i < precio.size(); i++) {
            Arr[i]=Integer.parseInt(String.valueOf(precio.get(i)));
            sum = sum + Arr[i];
        }
        System.out.println("Sum of all the elements of an array: " + sum);

    }


}
