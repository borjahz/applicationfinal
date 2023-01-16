package com.example.applicationfinal;

import static com.example.applicationfinal.DataHolder2.getInstance;
import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class pantalla3 extends AppCompatActivity {
    private FloatingActionButton add_button2;
    private RecyclerView recyclerView2;
    private TextView proyecto_id, nombre_display, comienzo_display, fin_display, unidades_display, factor_display, valor_display, unidades_display2,
            PrecioDiaComponente_text_display, PrecioDiaComponente_display, PrecioDiaProyecto_text_display, PrecioDiaProyecto_display,unidades_txt4,unidades_txt3,precioUnidadExtra_text_display,unidadespreciouniextra_display2;
    private SwipeRefreshLayout swipeRefreshLayout2;


    private String id, nombre, comienzo, fin, unidades, factor, valor;
    GestorSQLite GSQL;
    ArrayList<String> componente_id;
    ArrayList<String> proyecto_id_fk_input;
    ArrayList<String> tipo_input;
    ArrayList<String> numero_input;
    ArrayList<String> comienzo_comp_input;
    ArrayList<String> fin_comp_input;
    ArrayList<String> fecha_comienzo;
    ArrayList<String> fecha_final;
    ArrayList<String> comienzo_compue_input;
    ArrayList<String> fin_compue_input;
    ArrayList<String> precio_input;
    ArrayList<String> precio;
    CustomAdapter2 CustomAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);

        recyclerView2 = findViewById(R.id.recyclerView2);
        swipeRefreshLayout2 = findViewById(R.id.swipeRefresh2);


        proyecto_id = findViewById(R.id.id_proyecto_display);
        nombre_display = findViewById(R.id.nombre_display);
        comienzo_display = findViewById(R.id.comienzo_display);
        fin_display = findViewById(R.id.fin_display);
        unidades_display = findViewById(R.id.unidades_display);
        factor_display = findViewById(R.id.factor_display);
        valor_display = findViewById(R.id.valor_display);
        unidades_display2 = findViewById(R.id.unidades_display2);
        PrecioDiaComponente_text_display= findViewById(R.id.PrecioDiaComponente_text_display);
        PrecioDiaComponente_display=findViewById(R.id.PrecioDiaComponente_display);
        PrecioDiaProyecto_display=findViewById(R.id.PrecioDiaProyecto_display);
        PrecioDiaProyecto_text_display=findViewById(R.id.PrecioDiaProyecto_text_display);
        precioUnidadExtra_text_display=findViewById(R.id.precioUnidadExtra_text_display);
        unidadespreciouniextra_display2=findViewById(R.id.unidadespreciouniextra_display2);



        System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        getIntentAndSetData();
        System.out.println("WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        System.out.println(precio);

        /*Nombre barra acción COMIENZO*/
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nombre);
        }
        /*Nombre barra acción FIN*/

        /* Creacion y funcionalidad del boton de abajo COMIENZO*/
        add_button2 = findViewById(R.id.add_button2);
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pantalla3.this, Anadir_Componentes.class);
                startActivity(intent);
            }
        });
        /* Creacion y funcionalidad del boton de abajo FIN*/

        /* Creacion y funcionalidad del array de datos de abajo COMIENZO*/
        GSQL = new GestorSQLite(pantalla3.this);
        componente_id = new ArrayList<>();
        proyecto_id_fk_input = new ArrayList<>();
        tipo_input = new ArrayList<>();
        numero_input = new ArrayList<>();
        comienzo_comp_input = new ArrayList<>();
        fecha_comienzo= new ArrayList<>();
        fecha_final=new ArrayList<>();
        fin_comp_input = new ArrayList<>();
        comienzo_compue_input = new ArrayList<>();
        fin_compue_input = new ArrayList<>();
        precio_input = new ArrayList<>();
        storeDataInArrays2();
        precio = new ArrayList<>();
        Leernumeros();
        sumanumeros();
        PrecioDiaComponentes();
        PrecioUnidadExtra();



        CustomAdapter2 = new CustomAdapter2(pantalla3.this, this, componente_id, proyecto_id_fk_input, tipo_input, numero_input,
                fecha_comienzo, fecha_final, comienzo_compue_input, fin_compue_input, precio_input);
        recyclerView2.setAdapter(CustomAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(pantalla3.this));
        /* Creacion y funcionalidad del array de datos de abajo FIN*/

        swipeRefreshLayout2.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (swipeRefreshLayout2 != null) {
                            componente_id.clear();
                            proyecto_id_fk_input.clear();
                            tipo_input.clear();
                            numero_input.clear();
                            fecha_comienzo.clear();
                            fecha_final.clear();
                            comienzo_compue_input.clear();
                            fin_compue_input.clear();
                            precio_input.clear();
                            precio.clear();
                        }
                        getIntentAndSetData();
                        storeDataInArrays2();
                        Leernumeros();
                        sumanumeros();
                        PrecioDiaComponentes();
                        PrecioUnidadExtra();
                        CustomAdapter2.notifyDataSetChanged();
                        swipeRefreshLayout2.setRefreshing(false);
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }


    /*Método metodo recoger COMIENZO*/
    public void getIntentAndSetData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("comienzo") && getIntent().hasExtra("fin") &&
                getIntent().hasExtra("unidades") && getIntent().hasExtra("factor") &&
                getIntent().hasExtra("valor")) {
            /*Cogiendo datos del intent*/
            id = getIntent().getStringExtra("id");
            nombre = getIntent().getStringExtra("nombre");
            comienzo = getIntent().getStringExtra("comienzo");
            fin = getIntent().getStringExtra("fin");
            unidades = getIntent().getStringExtra("unidades");
            factor = getIntent().getStringExtra("factor");
            valor = getIntent().getStringExtra("valor");
            DataHolder2.getInstance().setData(valueOf(id));
            DateHolder3.getInstance().setData(unidades);

            /*Escribir datos de la BD en los text view COMIENZO*/
            proyecto_id.setText(id);
            nombre_display.setText(nombre);
            comienzo_display.setText(comienzo);
            fin_display.setText(fin);
            unidades_display.setText(unidades);
            factor_display.setText(factor);
            valor_display.setText(valor);
            unidades_display2.setText(unidades);



            /*Escribir datos de la BD en los text view FIN*/

        } else {
            Toast.makeText(this, "Error al recoger datos de proyecto", Toast.LENGTH_SHORT).show();
        }
    }

    /*Método recoger datos FIN*/
    /*Método extraer y exponer datos COMIENZO*/
    void storeDataInArrays2() {
        Cursor cursor = GSQL.readAllData2();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos de componentes", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                componente_id.add(cursor.getString(0));
                proyecto_id_fk_input.add(cursor.getString(1));
                tipo_input.add(cursor.getString(2));
                numero_input.add(cursor.getString(3));
                fecha_comienzo.add(cursor.getString(4));
                fecha_final.add(cursor.getString(5));
                comienzo_compue_input.add(cursor.getString(6));
                fin_compue_input.add(cursor.getString(7));
                precio_input.add(cursor.getString(8));
            }
        }
        /*Método extraer y exponer datos FIN*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_proyecto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deleteAllComponentsFromProject){
            /*Toast.makeText(this, "Todos los componentes del proyecto borrados", Toast.LENGTH_SHORT).show();*/
        confirmDialogAllComps();}
        return super.onOptionsItemSelected(item);
    }
    /*Aviso borrar COMIENZO*/
    void confirmDialogAllComps() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("¿Borrar todos los componentes?");
        builder2.setMessage("¿Seguro que quieres borrar todos los componentes?");
        builder2.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterfaceComp, int i) {
                GestorSQLite GSQL = new GestorSQLite(pantalla3.this);
                GSQL.deleteComponentsFromOneProject();
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
    void Leernumeros() {
        Cursor cursor = GSQL.readAllData3();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos de componentes", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                precio.add(cursor.getString(0));
            }
        }
    }

    void sumanumeros() {
        int[] Arr= new int[precio.size()];
    int  sum=0;
        for (int i = 0; i < precio.size(); i++) {
            Arr[i]=Integer.parseInt(valueOf(precio.get(i)));
            sum = sum + Arr[i];
        }
        System.out.println("Sum of all the elements of an array: " + sum);

    }
    void PrecioDiaComponentes() {
        double sum=0;
       String sum2 = null;
        double[] Arr = new double [precio.size()];
        double [] PrecioporDia= new double [precio.size()];
        long [] Fecha1 = new long[fecha_comienzo.size()];
        long[] Fecha2 = new long[fecha_final.size()];
        ArrayList<String> Precio_finalComponente= new ArrayList<String>();
        for (int i = 0; i < precio.size(); i++) {
            Arr[i] = Integer.parseInt(valueOf(precio.get(i)));
            Fecha1[i] = Long.parseLong(fecha_comienzo.get(i));
            System.out.println("AGUAAAAAAAAAAAAAAAAAA");
            System.out.println(Fecha1[i]);
            Fecha2[i]= Long.parseLong(fecha_final.get(i));
            System.out.println(Fecha2[i]);
            long[] difference = new long[fecha_comienzo.size()];
                    difference[i]= Fecha2[i] - Fecha1[i];
            long[] differenceDates = new long[fecha_final.size()];
                    differenceDates[i]=difference[i] /(24 * 60 * 60);
            System.out.println(difference[i]);
            System.out.println("HALOOOOOO");
            System.out.println(differenceDates[i]);
            String[] dayDifference = new String[precio.size()];
                    dayDifference[i]=Long.toString(differenceDates[i]);
                    PrecioporDia[i]= Arr[i]/Integer.parseInt(valueOf(dayDifference[i]));
            System.out.println("AQUI ESSSSSSS");
            System.out.println(Arr[i]);
                    System.out.println(Integer.parseInt(valueOf(dayDifference[i])));
            System.out.println(dayDifference[i]);
            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println(df.format(PrecioporDia[i]));
            sum= PrecioporDia[i]+ sum;
            sum2=df.format(sum);
            Precio_finalComponente.add(i, String.valueOf(df.format(PrecioporDia[i])));
        }
        PrecioDiaComponente_display.setText(String.valueOf(Precio_finalComponente));
        PrecioDiaProyecto_text_display.setText(String.valueOf(sum2));
        System.out.println("AGUAAAAAAAAAAAAAAAAAA");
        System.out.println(sum);
        System.out.println("AGAAAAAU");

    }
    void PrecioUnidadExtra() {
        double sum = 0;
        String sum2 = null;
        double factorP = Double.parseDouble(factor);
        double Uniextra = Double.parseDouble(valor);
        double Coste_nuevo=0;
        double[] Arr = new double[precio.size()];
        String Coste_Unidad_Extra = null;
        for (int i = 0; i < precio.size(); i++) {
            Arr[i] = Integer.parseInt(valueOf(precio.get(i)));
            sum = Arr[i] + sum;
            DecimalFormat df = new DecimalFormat("#.##");
            sum2=df.format(sum);
            Coste_nuevo=sum*factorP/Uniextra;
            Coste_Unidad_Extra = String.valueOf(df.format(sum  * factorP / Uniextra));

        }
        System.out.println("AAQUI ES TMB");
        System.out.println(Coste_Unidad_Extra);
        System.out.println(Coste_nuevo);
        System.out.println(sum);
        precioUnidadExtra_text_display.setText(String.valueOf(Coste_Unidad_Extra));
        unidadespreciouniextra_display2.setText(String.valueOf(unidades) +"/día");

    }


}
