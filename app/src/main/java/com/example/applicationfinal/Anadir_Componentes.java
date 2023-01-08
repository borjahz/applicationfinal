package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Anadir_Componentes extends AppCompatActivity {
    private Button button;
    private EditText nombre_input, tipo_input, numero_input, comienzo_comp_input, fin_comp_input,
            comienzo_compue_input, fin_compue_input, precio_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_componentes);

        nombre_input = findViewById(R.id.Nombre_Proyecto_componente);
        tipo_input = findViewById(R.id.tipo_Componente);
        numero_input = findViewById(R.id.numero_Componente);
        comienzo_comp_input = findViewById(R.id.Fecha_Comienzo_Componente);
        fin_comp_input = findViewById(R.id.Fecha_Fin_Componente);
        comienzo_compue_input = findViewById(R.id.Comienzo_Unidades);
        fin_compue_input = findViewById(R.id.Fin_Unidades);
        precio_input = findViewById(R.id.Precio_Componente);

        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_crear_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestorSQLite GSQL = new GestorSQLite(Anadir_Componentes.this);
                GSQL.anadirComponente (nombre_input.getText().toString().trim(),
                        tipo_input.getText().toString().trim(),
                        Integer.valueOf(numero_input.getText().toString().trim()),
                        Integer.valueOf(comienzo_comp_input.getText().toString().trim()),
                        Integer.valueOf(fin_comp_input.getText().toString().trim()),
                        Integer.valueOf(comienzo_compue_input.getText().toString().trim()),
                        Integer.valueOf(fin_compue_input.getText().toString().trim()),
                        Integer.valueOf(precio_input.getText().toString().trim())
                );
            }
        });
        /*Dar funcionalidad al botón FIN*/

    }
    /*Método cerrar actividad COMIENZO*/
    public void closeAnadirComponentes(){
        Intent intent = new Intent(this, Anadir_Componentes.class);
        finish();
    }
    /*Método cerrar actividad FIN*/
}