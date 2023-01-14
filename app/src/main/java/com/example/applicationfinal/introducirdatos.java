package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class introducirdatos extends AppCompatActivity  {
    private Button button;
    ImageButton buttonc;
    ImageButton buttonf;

    Calendar c;
    Calendar c2;

    DatePickerDialog dpd1;
    DatePickerDialog dpd2;
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


        buttonc = findViewById(R.id.Botton_fechacomienzo);
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dpd1 = new DatePickerDialog(introducirdatos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        comienzo_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd1.show();
            }

        });


        buttonf = findViewById(R.id.botton_fechafinal);
        buttonf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                c2 = Calendar.getInstance();
                int day = c2.get(Calendar.DAY_OF_MONTH);
                int month = c2.get(Calendar.MONTH);
                int year = c2.get(Calendar.YEAR);
                dpd2 = new DatePickerDialog(introducirdatos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        fin_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd2.show();
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
                finish();
            }
        });
    }}
        /*Dar funcionalidad al botón FIN*/





