package com.example.applicationfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class editarProyecto extends AppCompatActivity {

    private Button button, delete_button;
    ImageButton buttonc;
    ImageButton buttonf;
    Calendar c;
    Calendar c2;
    DatePickerDialog dpd1;
    DatePickerDialog dpd2;
    private EditText nombre_input, comienzo_input, fin_input, unidades_input, factor_input, valor_input;
    long epoch;
    long epoch2;

    private String id, nombre, comienzo, fin, unidades, factor, valor;
    private String recognizedText;
    int comienzo_input2;
    int fin_input2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_proyecto);
        /*Botón de volver COMIENZO*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*Botón de volver FIN*/




        nombre_input = findViewById(R.id.Nombre_Proyecto_edit);
        comienzo_input = findViewById(R.id.Fecha_Comienzo_edit);
        fin_input = findViewById(R.id.Fecha_Fin_edit);
        unidades_input = findViewById(R.id.Unidades_edit);
        factor_input = findViewById(R.id.Factor_edit);
        valor_input = findViewById(R.id.Valor_edit);
        button = findViewById(R.id.boton_editar);
        delete_button = findViewById(R.id.boton_borrar);
        /*Escribir datos existentes COMIENZO*/
        buttonc = findViewById(R.id.botton_fechacomienzo3);
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dpd1 = new DatePickerDialog(editarProyecto.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                            int mMonth2;
                            String cucu;
                            comienzo_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                            String s = "00:00:00";
                            mMonth2 = mMonth + 1;
                            cucu = (mMonth2 + "/" + mDay + "/" + mYear + " " + s);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                LocalDate comienzo_local = LocalDate.of(mYear, mMonth2, mDay);

                                System.out.println(comienzo_local);

                                try {
                                    epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(cucu).getTime() / 1000;
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println(epoch);

                            }
                        }
                    }, day, month, year);
                    dpd1.show();
                }
            }
            });



            buttonf = findViewById(R.id.botton_fechafinal3);
        buttonf.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    c2 = Calendar.getInstance();
                    int day = c2.get(Calendar.DAY_OF_MONTH);
                    int month = c2.get(Calendar.MONTH);
                    int year = c2.get(Calendar.YEAR);
                    dpd2 = new DatePickerDialog(editarProyecto.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int mYear2, int mMonth2, int mDay2) {
                            fin_input.setText(mDay2 + "/" + (mMonth2 + 1) + "/" + mYear2);
                            int mMonth3;
                            String cucu;
                            String s = "00:00:00";
                            mMonth3=mMonth2+1;
                            cucu=(mMonth3 + "/" + mDay2 + "/" + mYear2 + " "  + s );
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                LocalDate comienzo_local2 = LocalDate.of(mYear2, mMonth3, mDay2);

                                System.out.println(comienzo_local2);

                                try {
                                    epoch2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(cucu).getTime() / 1000;
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println(epoch2);

                            }

                        }
                    }, day, month, year);
                    dpd2.show();

                }
        });







                                       getIntentAndSetData();
            /*Escribir datos existentes FIN*/

            /*Nombre barra acción COMIENZO*/
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setTitle(nombre);
            }
            /*Nombre barra acción FIN*/

            /*Dar funcionalidad al botón editar COMIENZO*/
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Sobreescribir datos existentes COMIENZO*/
                    GestorSQLite myDB = new GestorSQLite(editarProyecto.this);
                    nombre = nombre_input.getText().toString().trim();
                    comienzo = comienzo_input.getText().toString().trim();
                    fin =fin_input.getText().toString().trim();;
                    unidades = unidades_input.getText().toString().trim();
                    factor = factor_input.getText().toString().trim();
                    valor = valor_input.getText().toString().trim();
                  /*  String[] values = comienzo.split("-");
                    int day = Integer.parseInt(values[0]);
                    int month = Integer.parseInt(values[1]);
                    int year = Integer.parseInt(values[2]);
                    String s = "00:00:00";
                    String fechas;
                    fechas=(month + "/" + day + "/" + year + " "  + s );
                    comienzo_input.setText(day + "/" + month + "/" + year);
                    try {
                        epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(fechas).getTime() / 1000;
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    String[] values2 = fin.split("-");
                    int day2 = Integer.parseInt(values2[0]);
                    int month2 = Integer.parseInt(values2[1]);
                    int year2 = Integer.parseInt(values2[2]);
                    String s2 = "00:00:00";
                    String fechas2;
                    fechas2=(month2 + "/" + day2 + "/" + year2 + " "  + s2 );
                    try {
                        epoch2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(fechas2).getTime() / 1000;
                    } catch (ParseException e) {

                        throw new RuntimeException(e);
                    }
                    System.out.println("AQUIIIIIIIIIIIII");
                    System.out.println(epoch2);*/
                    myDB.updateData(id, nombre,
                            (int) epoch  ,(int) epoch2, unidades, factor, valor);
                    /*Sobreescribir datos existentes FIN*/

                    closeEditarProyecto();
                }
            });
            /*Dar funcionalidad al botón editar FIN*/

            /*Dar funcionalidad al botón borrar COMIENZO*/
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmDialog();
                }
            });
            /*Dar funcionalidad al botón borrar FIN*/



        }
        /*Método cerrar actividad COMIENZO*/
        public void closeEditarProyecto(){
            Intent intent = new Intent(this, editarProyecto.class);
            finish();
        }
        /*Método cerrar actividad FIN*/

        /*Método metodo recoger COMIENZO*/
        public void getIntentAndSetData(){
            if(getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                    getIntent().hasExtra("comienzo") && getIntent().hasExtra("fin") &&
                    getIntent().hasExtra("unidades") && getIntent().hasExtra("factor")){
                /*Cogiendo datos del intent*/
                id = getIntent().getStringExtra("id");
                nombre = getIntent().getStringExtra("nombre");
                comienzo = getIntent().getStringExtra("comienzo");
                fin = getIntent().getStringExtra("fin");
                unidades = getIntent().getStringExtra("unidades");
                factor = getIntent().getStringExtra("factor");
                valor = getIntent().getStringExtra("valor");

                /*Escribir datos de la BD en los edit text COMIENZO*/
                nombre_input.setText(nombre);
                comienzo_input.setText(comienzo);
                fin_input.setText(fin);
                unidades_input.setText(unidades);
                factor_input.setText(factor);
                valor_input.setText(valor);
                /*Escribir datos de la BD en los edit text FIN*/

            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        /*Método recoger datos FIN*/

        /*Aviso borrar COMIENZO*/
        void confirmDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Borrar " + nombre + "?");
            builder.setMessage("¿Seguro que quieres borrar el proyecto" + nombre + "?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    GestorSQLite myDB = new GestorSQLite(editarProyecto.this);
                    myDB.deleteOneRow(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
        /*Aviso borrar FIN*/
    }
