package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Anadir_Componentes extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_componentes);

        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_crear_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAnadirComponentes();
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