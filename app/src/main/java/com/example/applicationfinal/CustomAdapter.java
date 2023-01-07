package com.example.applicationfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList proyecto_id, nombre_input, comienzo_input, fin_input, unidades_input, factor_input; /*Añadir aquí los inputs necesarios para el recycle view row*/


    CustomAdapter(Context context,
                  ArrayList proyecto_id,
                  ArrayList nombre_input,
                  ArrayList comienzo_input,
                  ArrayList fin_input,
                  ArrayList unidades_input,
                  ArrayList factor_input){
        this.context = context;
        this.proyecto_id = proyecto_id;
        this.nombre_input = nombre_input;
        this.comienzo_input = comienzo_input;
        this.fin_input = fin_input;
        this.unidades_input = unidades_input;
        this.factor_input = factor_input;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.proyecto_id_txt.setText(String.valueOf(proyecto_id.get(position)));
        holder.Nombre_Proyecto_txt.setText(String.valueOf(nombre_input.get(position)));
        holder.Fecha_Comienzo_txt.setText(String.valueOf(comienzo_input.get(position)));
        holder.Fecha_Fin_txt.setText(String.valueOf(fin_input.get(position)));
        holder.Unidades_txt.setText(String.valueOf(unidades_input.get(position)));
        holder.Factor_txt.setText(String.valueOf(factor_input.get(position)));

    }

    @Override
    public int getItemCount() {
        return proyecto_id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView proyecto_id_txt, Nombre_Proyecto_txt, Fecha_Comienzo_txt, Fecha_Fin_txt, Unidades_txt, Factor_txt; /*Mismos nombres que en recycle_view_row*/

        public MyViewHolder (@NonNull View itemvView) {
            super(itemvView);
            proyecto_id_txt = itemvView.findViewById(R.id.proyecto_id_txt);
            Nombre_Proyecto_txt = itemvView.findViewById(R.id.Nombre_Proyecto_txt);
            Fecha_Comienzo_txt = itemvView.findViewById(R.id.Fecha_Comienzo_txt);
            Fecha_Fin_txt = itemvView.findViewById(R.id.Fecha_Fin_txt);
            Unidades_txt = itemvView.findViewById(R.id.Unidades_txt);
            Factor_txt = itemvView.findViewById(R.id.Factor_txt);
        }
    }

}
