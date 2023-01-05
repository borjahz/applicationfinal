package com.example.applicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editarComponente extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_componente);

        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_editar_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeEditarComponentes();
            }
        });
        /*Dar funcionalidad al botón FIN*/

    }
    /*Método cerrar actividad COMIENZO*/
    public void closeEditarComponentes(){
        Intent intent = new Intent(this, Anadir_Componentes.class);
        finish();
    }
    /*Método cerrar actividad FIN*/
}