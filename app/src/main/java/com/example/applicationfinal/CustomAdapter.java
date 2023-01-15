package com.example.applicationfinal;

import static java.lang.Long.parseLong;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList proyecto_id, nombre_input, comienzo_input, fin_input,
            unidades_input, factor_input, valor_input; /*Añadir aquí los inputs necesarios para el recycle view row*/


    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList proyecto_id,
                  ArrayList nombre_input,
                  ArrayList comienzo_input,
                  ArrayList fin_input,
                  ArrayList unidades_input,
                  ArrayList factor_input,
                  ArrayList valor_input) {
        this.activity = activity;
        this.context = context;
        this.proyecto_id = proyecto_id;
        this.nombre_input = nombre_input;
        this.comienzo_input = comienzo_input;
        this.fin_input = fin_input;
        this.unidades_input = unidades_input;
        this.factor_input = factor_input;
        this.valor_input = valor_input;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new MyViewHolder(view);

    }

    /*Aquí le dices el campo donde tiene que ir cada input en el recycleview COMIENZO*/
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        long[] Arr = new long[comienzo_input.size()];
        for (int i = 0; i < comienzo_input.size(); i++) {
            Arr[i] = Long.parseLong(String.valueOf(comienzo_input.get(i)));
            System.out.println(comienzo_input);
            System.out.println(Arr[i] + " JAMOOOOOOOOOOOEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            Date date = new java.util.Date(Arr[i] * 1000);
            // the format of your date
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yy");
            // give a timezone reference for formatting (see comment at the bottom)
            String comienzo_input_date = sdf.format(date);
            holder.Fecha_Comienzo_txt.setText(comienzo_input_date);
            System.out.println(comienzo_input_date);


        }
        long[] Arr2 = new long[fin_input.size()];
        for (int i = 0; i < fin_input.size(); i++) {
            Arr2[i] = Long.parseLong(String.valueOf(fin_input.get(i)));
            System.out.println(Arr2[i] + " WEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            Date date = new java.util.Date(Arr2[i] * 1000);
            // the format of your date
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yy");
            // give a timezone reference for formatting (see comment at the bottom)
            String fin_input_date = sdf.format(date);
            holder.Fecha_Fin_txt.setText(fin_input_date);
            System.out.println(fin_input_date);
        }

        holder.proyecto_id_txt.setText(String.valueOf(proyecto_id.get(position)));
        holder.Nombre_Proyecto_txt.setText(String.valueOf(nombre_input.get(position)));
        holder.Fecha_Comienzo_txt.setText(String.valueOf(comienzo_input.get(position)));
        holder.Fecha_Fin_txt.setText(String.valueOf(fin_input.get(position)));
        holder.Unidades_txt.setText(String.valueOf(unidades_input.get(position)));
        holder.Factor_txt.setText(String.valueOf(factor_input.get(position)));
        holder.Valor_txt.setText(String.valueOf(valor_input.get(position)));
        holder.Unidades_txt2.setText(String.valueOf(unidades_input.get(position)));
        /*Aquí le dices el campo donde tiene que ir cada input en el recycleview FIN*/


        /*Convertir Recycleview en botón COMIENZO*/
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, editarProyecto.class);
                intent.putExtra("id", String.valueOf(proyecto_id.get(holder.getAdapterPosition())));
                intent.putExtra("nombre", String.valueOf(nombre_input.get(holder.getAdapterPosition())));
                intent.putExtra("comienzo", String.valueOf(comienzo_input.get(holder.getAdapterPosition())));
                intent.putExtra("fin", String.valueOf(fin_input.get(holder.getAdapterPosition())));
                intent.putExtra("unidades", String.valueOf(unidades_input.get(holder.getAdapterPosition())));
                intent.putExtra("factor", String.valueOf(factor_input.get(holder.getAdapterPosition())));
                intent.putExtra("valor", String.valueOf(valor_input.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
                return true;
            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, pantalla3.class);
                intent.putExtra("id", String.valueOf(proyecto_id.get(holder.getAdapterPosition())));
                intent.putExtra("nombre", String.valueOf(nombre_input.get(holder.getAdapterPosition())));
                intent.putExtra("comienzo", String.valueOf(comienzo_input.get(holder.getAdapterPosition())));
                intent.putExtra("fin", String.valueOf(fin_input.get(holder.getAdapterPosition())));
                intent.putExtra("unidades", String.valueOf(unidades_input.get(holder.getAdapterPosition())));
                intent.putExtra("factor", String.valueOf(factor_input.get(holder.getAdapterPosition())));
                intent.putExtra("valor", String.valueOf(valor_input.get(holder.getAdapterPosition())));
                context.startActivity(intent);
                unixToDate();
                System.out.println("PERRRRROOOOOOOO");

            }
        });
        /*Convertir Recycleview en botón FIN*/

    }

    @Override
    public int getItemCount() {
        return proyecto_id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView proyecto_id_txt, Nombre_Proyecto_txt, Fecha_Comienzo_txt, Fecha_Fin_txt, Unidades_txt, Factor_txt, Valor_txt, Unidades_txt2; /*Mismos nombres que en recycle_view_row*/
        ConstraintLayout mainLayout;

        /*Identificar cada uno de los elementos del recycle view COMIENZO*/
        public MyViewHolder(@NonNull View itemvView) {
            super(itemvView);
            proyecto_id_txt = itemvView.findViewById(R.id.proyecto_id_txt);
            Nombre_Proyecto_txt = itemvView.findViewById(R.id.Nombre_Proyecto_txt);
            Fecha_Comienzo_txt = itemvView.findViewById(R.id.Fecha_Comienzo_txt);
            Fecha_Fin_txt = itemvView.findViewById(R.id.Fecha_Fin_txt);
            Unidades_txt = itemvView.findViewById(R.id.Unidades_txt);
            Factor_txt = itemvView.findViewById(R.id.Factor_txt);
            Valor_txt = itemvView.findViewById(R.id.Valor_txt);
            Unidades_txt2 = itemvView.findViewById(R.id.unidades_txt2);
            mainLayout = itemvView.findViewById(R.id.mainLayout);
        }
        /*Identificar cada uno de los elementos del recycle view FIN*/

    }

    public void unixToDate() {
        String s = String.valueOf(comienzo_input);
        long l = Long.valueOf(s);
        Date date = new java.util.Date(l * 1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-0"));
        String formattedDate = sdf.format(date);
        System.out.println("PERRRRROOOOOOOO");
        System.out.println(formattedDate);
    }


}
