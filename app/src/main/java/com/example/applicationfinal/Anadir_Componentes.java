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

public class Anadir_Componentes extends AppCompatActivity {
    private Button button;
    private EditText proyecto_id_fk_input, tipo_input, numero_input, comienzo_comp_input, fin_comp_input,
            comienzo_compue_input, fin_compue_input, precio_input;
    ImageButton buttonc;
    ImageButton buttonf;

    Calendar c;
    Calendar c2;

    DatePickerDialog dpd1;
    DatePickerDialog dpd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_componentes);



        proyecto_id_fk_input = findViewById(R.id.Proyecto_id_fk);
        tipo_input = findViewById(R.id.tipo_Componente);
        numero_input = findViewById(R.id.numero_Componente);
        comienzo_comp_input = findViewById(R.id.Fecha_Comienzo_Componente);
        fin_comp_input = findViewById(R.id.Fecha_Fin_Componente);
        comienzo_compue_input = findViewById(R.id.Comienzo_Unidades);
        fin_compue_input = findViewById(R.id.Fin_Unidades);
        precio_input = findViewById(R.id.Precio_Componente);

        buttonc = findViewById(R.id.botton_fechacomienzo1);
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dpd1 = new DatePickerDialog(Anadir_Componentes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        comienzo_comp_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd1.show();
            }

        });


        buttonf = findViewById(R.id.botton_fechafinal1);
        buttonf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                c2 = Calendar.getInstance();
                int day = c2.get(Calendar.DAY_OF_MONTH);
                int month = c2.get(Calendar.MONTH);
                int year = c2.get(Calendar.YEAR);
                dpd2 = new DatePickerDialog(Anadir_Componentes.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        fin_compue_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd2.show();
            }
        });

        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_crear_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestorSQLite GSQL = new GestorSQLite(Anadir_Componentes.this);
                GSQL.anadirComponente (proyecto_id_fk_input.getText().toString().trim(),
                                        tipo_input.getText().toString().trim(),
                        Integer.valueOf(numero_input.getText().toString().trim()),
                        Integer.valueOf(comienzo_comp_input.getText().toString().trim()),
                        Integer.valueOf(fin_comp_input.getText().toString().trim()),
                        Integer.valueOf(comienzo_compue_input.getText().toString().trim()),
                        Integer.valueOf(fin_compue_input.getText().toString().trim()),
                        Integer.valueOf(precio_input.getText().toString().trim())
                );
                finish();
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