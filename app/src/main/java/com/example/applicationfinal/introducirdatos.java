package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class introducirdatos extends AppCompatActivity {
    private Button button;
    private EditText nombre_input, comienzo_input, fin_input, unidades_input, factor_input, valor_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducirdatos);

        /*Dar funcionalidad edit text COMIENZO*/
        nombre_input = findViewById(R.id.Nombre_Proyecto);
        comienzo_input = findViewById(R.id.Fecha_Comienzo);
        fin_input = findViewById(R.id.Fecha_Fin);
        unidades_input = findViewById(R.id.Unidades);
        factor_input = findViewById(R.id.Factor);
        valor_input = findViewById(R.id.Valor);

        /*Dar funcionalidad edit text FIN*/


        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_crear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestorSQLite GSQL = new GestorSQLite(introducirdatos.this);
                GSQL.anadirProyecto(nombre_input.getText().toString().trim(),
                        Integer.valueOf(comienzo_input.getText().toString().trim()),
                        Integer.valueOf(fin_input.getText().toString().trim()),
                        unidades_input.getText().toString().trim(),
                        Integer.valueOf(factor_input.getText().toString().trim()),
                        Integer.valueOf(valor_input.getText().toString().trim())
                );
            }
        });
        /*Dar funcionalidad al botón FIN*/

    }
}
