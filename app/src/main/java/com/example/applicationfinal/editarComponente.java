package com.example.applicationfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editarComponente extends AppCompatActivity {
    private Button button, delete_button_comp;
    private EditText proyecto_id_fk, tipo_input, numero_input, comienzo_comp_input, fin_comp_input,
            comienzo_compue_input, fin_compue_input, precio_input;

    private String id_componente, id_fk, tipo, numero, comienzo_comp, fin_comp,
            comienzo_compue, fin_compue, precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_componente);

        /*Nombre barra acción COMIENZO*/
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Editar componente");
        }
        /*Nombre barra acción FIN*/


        proyecto_id_fk = findViewById(R.id.Proyecto_id_fk_edit);
        tipo_input = findViewById(R.id.tipo_Componente_edit);
        numero_input = findViewById(R.id.numero_Componente_edit);
        comienzo_comp_input = findViewById(R.id.Fecha_Comienzo_Componente_edit);
        fin_comp_input = findViewById(R.id.Fecha_Fin_Componente_edit);
        comienzo_compue_input = findViewById(R.id.Comienzo_Unidades_componente_edit);
        fin_compue_input = findViewById(R.id.Fin_Unidades_componente_edit);
        precio_input = findViewById(R.id.Precio_Componente_edit);

        delete_button_comp = findViewById(R.id.boton_borrar_componente);

        getIntentAndSetData2();

        /*Dar funcionalidad al botón COMIENZO*/
        button = (Button) findViewById(R.id.boton_editar_componente);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestorSQLite GSQL = new GestorSQLite(editarComponente.this);
                id_fk = proyecto_id_fk.getText().toString().trim();
                tipo = tipo_input.getText().toString().trim();
                numero = numero_input.getText().toString().trim();
                comienzo_comp = comienzo_comp_input.getText().toString().trim();
                fin_comp = fin_comp_input.getText().toString().trim();
                comienzo_compue = comienzo_compue_input.getText().toString().trim();
                fin_compue = fin_compue_input.getText().toString().trim();
                precio = precio_input.getText().toString().trim();
                GSQL.updateData2(id_componente, id_fk, tipo, numero, comienzo_comp, fin_comp, comienzo_compue, fin_compue, precio);
                closeEditarComponentes();
            }
        });
        /*Dar funcionalidad al botón FIN*/

        delete_button_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  confirmDialogComp();

            }
        });
    }
    /*Método cerrar actividad COMIENZO*/
    public void closeEditarComponentes(){
        Intent intent = new Intent(this, Anadir_Componentes.class);
        finish();
    }
    /*Método cerrar actividad FIN*/


    /*Método metodo recoger COMIENZO*/
    public void getIntentAndSetData2(){
        if(getIntent().hasExtra("id_componente") && getIntent().hasExtra("id_fk") &&
                getIntent().hasExtra("tipo") && getIntent().hasExtra("numero") &&
                getIntent().hasExtra("comienzo_componente") && getIntent().hasExtra("fin_componente") &&
                getIntent().hasExtra("comienzoue_componente") && getIntent().hasExtra("finue_componente") &&
                getIntent().hasExtra("precio")){
            /*Cogiendo datos del intent*/
            id_componente = getIntent().getStringExtra("id_componente");
            id_fk = getIntent().getStringExtra("id_componente");
            tipo = getIntent().getStringExtra("tipo");
            numero = getIntent().getStringExtra("numero");
            comienzo_comp = getIntent().getStringExtra("comienzo_componente");
            fin_comp = getIntent().getStringExtra("fin_componente");
            comienzo_compue = getIntent().getStringExtra("comienzoue_componente");
            fin_compue = getIntent().getStringExtra("finue_componente");
            precio = getIntent().getStringExtra("precio");

            /*Escribir datos de la BD en los edit text COMIENZO*/
            proyecto_id_fk.setText(id_fk);
            tipo_input.setText(tipo);
            numero_input.setText(numero);
            comienzo_comp_input.setText(comienzo_comp);
            fin_comp_input.setText(fin_comp);
            comienzo_compue_input.setText(comienzo_compue);
            fin_compue_input.setText(fin_compue);
            precio_input.setText(precio);
            /*Escribir datos de la BD en los edit text FIN*/

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    /*Método recoger datos FIN*/

    /*Aviso borrar COMIENZO*/
    void confirmDialogComp() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("¿Borrar " + tipo + numero + "?");
        builder2.setMessage("¿Seguro que quieres borrar el componente " + tipo + numero + "?");
        builder2.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfaceComp, int i) {
                GestorSQLite GSQL = new GestorSQLite(editarComponente.this);
                GSQL.deleteOneRow2(id_componente);
                finish();
            }
        });
        builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfaceComp, int i) {

            }
        });
        builder2.create().show();
    }
    /*Aviso borrar FIN*/
}



