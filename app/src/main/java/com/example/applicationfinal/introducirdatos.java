package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class introducirdatos extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
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

        findViewById(R.id.Botton_fechacomienzo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDatePickerDailog();
            }

        });
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
    }
        /*Dar funcionalidad al botón FIN*/
    private void showDatePickerDailog(){
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          String date=+dayOfMonth+"/"+month+"/"+year;
         comienzo_input.setText(date);
    }
}
