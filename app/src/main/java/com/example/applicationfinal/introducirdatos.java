package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import java.sql.Timestamp;


import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class introducirdatos extends AppCompatActivity  {
    private Button button;
    ImageButton buttonc;
    ImageButton buttonf;

    Calendar c;
    Calendar c2;

    DatePickerDialog dpd1;
    DatePickerDialog dpd2;
    long epoch;
    long epoch2;
    int comienzo_input_unix;
    int fin_input_unix;
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
                        int mMonth2;
                        String cucu;
                        comienzo_input.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                        String s = "00:00:00";
                        mMonth2=mMonth+1;
                        cucu=(mMonth2 + "/" + mDay + "/" + mYear + " "  + s );
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

/*
                        if (mYear%4 == 1 && mMonth == 1) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400 + 31536000 + 2678400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 2) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400 + 31536000 + 5097600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 3) {
                            comienzo_input_unix = (((mYear-1- 1970) / 4) * 126230400+ 31536000  + 7776000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 4) {
                            comienzo_input_unix = (((mYear-1- 1970) / 4) * 126230400+ 31536000  + 10368000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 5) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 13046400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 6) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 15638400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 7) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 18316800 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 8) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 20995200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 9) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 23587200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 10) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400+ 31536000  + 26265600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 11) {
                            comienzo_input_unix = (((mYear-1 - 1970) / 4) * 126230400 + 31536000 + 28857600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 1 && mMonth == 0) {
                            comienzo_input_unix = (((mYear - 1 - 1970) / 4) * 126230400 + 31536000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 1) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400 + 63072000 + 2678400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 2) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400 + 63072000 + 5097600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 3) {
                            comienzo_input_unix = (((mYear-2- 1970) / 4) * 126230400+ 63072000  + 7776000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 4) {
                            comienzo_input_unix = (((mYear-2- 1970) / 4) * 126230400+ 63072000  + 10368000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 5) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 13046400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 6) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 15638400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 7) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 18316800 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 8) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 20995200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 9) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 23587200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 10) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400+ 63072000  + 26265600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 11) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400 + 63072000 + 28857600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 2 && mMonth == 0) {
                            comienzo_input_unix = (((mYear-2 - 1970) / 4) * 126230400 + 63072000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 1) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400 + 94608000 + 2678400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 2) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400 + 94608000 + 5097600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 3) {
                            comienzo_input_unix = (((mYear-3- 1970) / 4) * 126230400+ 94608000  + 7776000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 4) {
                            comienzo_input_unix = (((mYear-3- 1970) / 4) * 126230400+ 94608000  + 10368000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 5) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 13046400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 6) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 15638400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 7) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 18316800 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 8) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 20995200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 9) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 23587200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 10) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400+ 94608000  + 26265600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 11) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400 + 94608000 + 28857600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 3 && mMonth == 0) {
                            comienzo_input_unix = (((mYear-3 - 1970) / 4) * 126230400 + 94608000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 1) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 2678400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 2) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 5184000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 3) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 7862400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 4) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 10454400 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 5) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 13132800 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 6) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 15724800 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 7) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 18403200 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 8) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 21081600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 9) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 23673600 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 10) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 26352000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 11) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400 + 28944000 + (mDay-1) * 86400);
                        } else if (mYear%4 == 0 && mMonth == 0) {
                            comienzo_input_unix = (((mYear - 1970) / 4) * 126230400  + (mDay-1) * 86400);
                        }
                        */


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
                    public void onDateSet(DatePicker datePicker, int mYear2, int mMonth2, int mDay2) {
                        fin_input.setText(mDay2 + "/" + (mMonth2 + 1) + "/" + mYear2);
                        int mMonth3;
                        String cucu;
                        String s = "00:00:00";
                        mMonth3=mMonth2+1;
                        cucu=(mMonth3 + "/" + mDay2 + "/" + mYear2 + " "  + s );
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDate comienzo_local2 = LocalDate.of(mYear2, mMonth2, mDay2);

                            System.out.println(comienzo_local2);

                            try {
                                epoch2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(cucu).getTime() / 1000;
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(epoch2);

                        }
/*
                        if (mYear2%4 == 1 && mMonth2 == 1) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400 + 31536000 + 2678400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 2) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400 + 31536000 + 5097600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 3) {
                            fin_input_unix = (((mYear2-1- 1970) / 4) * 126230400+ 31536000  + 7776000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 4) {
                            fin_input_unix = (((mYear2-1- 1970) / 4) * 126230400+ 31536000  + 10368000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 5) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 13046400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 6) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 15638400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 7) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 18316800 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 8) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 20995200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 9) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 23587200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 10) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400+ 31536000  + 26265600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 11) {
                            fin_input_unix = (((mYear2-1 - 1970) / 4) * 126230400 + 31536000 + 28857600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 1 && mMonth2 == 0) {
                            fin_input_unix = (((mYear2 - 1 - 1970) / 4) * 126230400 + 31536000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 1) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400 + 63072000 + 2678400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 2) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400 + 63072000 + 5097600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 3) {
                            fin_input_unix = (((mYear2-2- 1970) / 4) * 126230400+ 63072000  + 7776000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 4) {
                            fin_input_unix = (((mYear2-2- 1970) / 4) * 126230400+ 63072000  + 10368000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 5) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 13046400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 6) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 15638400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 7) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 18316800 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 8) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 20995200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 9) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 23587200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 10) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400+ 63072000  + 26265600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 11) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400 + 63072000 + 28857600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 2 && mMonth2 == 0) {
                            fin_input_unix = (((mYear2-2 - 1970) / 4) * 126230400 + 63072000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 1) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400 + 94608000 + 2678400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 2) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400 + 94608000 + 5097600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 3) {
                            fin_input_unix = (((mYear2-3- 1970) / 4) * 126230400+ 94608000  + 7776000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 4) {
                            fin_input_unix = (((mYear2-3- 1970) / 4) * 126230400+ 94608000  + 10368000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 5) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 13046400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 6) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 15638400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 7) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 18316800 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 8) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 20995200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 9) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 23587200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 10) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400+ 94608000  + 26265600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 11) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400 + 94608000 + 28857600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 3 && mMonth2 == 0) {
                            fin_input_unix = (((mYear2-3 - 1970) / 4) * 126230400 + 94608000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 1) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 2678400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 2) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 5184000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 3) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 7862400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 4) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 10454400 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 5) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 13132800 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 6) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 15724800 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 7) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 18403200 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 8) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 21081600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 9) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 23673600 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 10) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 26352000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 11) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + 28944000 + (mDay2-1) * 86400);
                        } else if (mYear2%4 == 0 && mMonth2 == 0) {
                            fin_input_unix = (((mYear2 - 1970) / 4) * 126230400 + (mDay2-1) * 86400);
                        }*/
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
                        (int)epoch,
                        (int) epoch2,
                        unidades_input.getText().toString().trim(),
                        Integer.valueOf(factor_input.getText().toString().trim()),
                        Integer.valueOf(valor_input.getText().toString().trim())
                );
                finish();
            }
        });
    }
    /*Dar funcionalidad al botón FIN*/

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
    public static String longToString(long l) {
        return String.valueOf(l);
    }

}
        /*Dar funcionalidad al botón FIN*/





