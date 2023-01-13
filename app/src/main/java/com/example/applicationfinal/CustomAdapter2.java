package com.example.applicationfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder2> {

    private Context context;
    private Activity activity;
    private ArrayList componente_id, proyecto_id_fk_input, tipo_input, numero_input, comienzo_comp_input, fin_comp_input,
            comienzo_compue_input, fin_compue_input, precio_input; /*Añadir aquí los inputs necesarios para el recycle view row*/

    CustomAdapter2(Activity activity,
                   Context context,
                   ArrayList componente_id,
                   ArrayList proyecto_id_fk_input,
                   ArrayList tipo_input,
                   ArrayList numero_input,
                   ArrayList comienzo_comp_input,
                   ArrayList fin_comp_input,
                   ArrayList comienzo_compue_input,
                   ArrayList fin_compue_input,
                   ArrayList precio_input) {
        this.activity = activity;
        this.context = context;
        this.componente_id = componente_id;
        this.proyecto_id_fk_input = proyecto_id_fk_input;
        this.tipo_input = tipo_input;
        this.numero_input = numero_input;
        this.comienzo_comp_input = comienzo_comp_input;
        this.fin_comp_input = fin_comp_input;
        this.comienzo_compue_input = comienzo_compue_input;
        this.fin_compue_input = fin_compue_input;
        this.precio_input = precio_input;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row_comp, parent, false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.componente_id_txt.setText(String.valueOf(componente_id.get(position)));
        holder.Proyecto_id_fk_txt.setText(String.valueOf(proyecto_id_fk_input.get(position)));
        holder.tipo_txt.setText(String.valueOf(tipo_input.get(position)));
        holder.Numero_txt.setText(String.valueOf(numero_input.get(position)));
        holder.Fecha_Comienzo_Componente_txt.setText(String.valueOf(comienzo_comp_input.get(position)));
        holder.Fecha_Fin_Componente_txt.setText(String.valueOf(fin_comp_input.get(position)));
        holder.Fecha_Comienzo_UE_txt.setText(String.valueOf(comienzo_compue_input.get(position)));
        holder.Fecha_Fin_UE_txt.setText(String.valueOf(fin_compue_input.get(position)));
        holder.Precio_txt.setText(String.valueOf(precio_input.get(position)));

        /*Convertir Recycleview en botón COMIENZO*/
        holder.secondaryLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, editarComponente.class);
                intent.putExtra("id_componente", String.valueOf(componente_id.get(holder.getAdapterPosition())));
                intent.putExtra("id_fk", String.valueOf(proyecto_id_fk_input.get(holder.getAdapterPosition())));
                intent.putExtra("tipo", String.valueOf(tipo_input.get(holder.getAdapterPosition())));
                intent.putExtra("numero", String.valueOf(numero_input.get(holder.getAdapterPosition())));
                intent.putExtra("comienzo_componente", String.valueOf(comienzo_comp_input.get(holder.getAdapterPosition())));
                intent.putExtra("fin_componente", String.valueOf(fin_comp_input.get(holder.getAdapterPosition())));
                intent.putExtra("comienzoue_componente", String.valueOf(comienzo_compue_input.get(holder.getAdapterPosition())));
                intent.putExtra("finue_componente", String.valueOf(fin_compue_input.get(holder.getAdapterPosition())));
                intent.putExtra("precio", String.valueOf(precio_input.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
                return true;
            }
        });
    };

    @Override
    public int getItemCount() {
        return componente_id.size();
    }


    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView componente_id_txt, Proyecto_id_fk_txt, tipo_txt, Numero_txt, Fecha_Comienzo_Componente_txt, Fecha_Fin_Componente_txt,
                Fecha_Comienzo_UE_txt, Fecha_Fin_UE_txt, Precio_txt; /*Mismos nombres que en recycle_view_row_comp*/
        ConstraintLayout secondaryLayout;

        /*Identificar cada uno de los elementos del recycle view COMIENZO*/
        public MyViewHolder2(@NonNull View itemvView) {
            super(itemvView);
            componente_id_txt = itemvView.findViewById(R.id.componente_id_txt);
            Proyecto_id_fk_txt = itemvView.findViewById(R.id.Proyecto_id_fk_txt);
            tipo_txt = itemvView.findViewById(R.id.tipo_txt);
            Numero_txt = itemvView.findViewById(R.id.Numero_txt);
            Fecha_Comienzo_Componente_txt = itemvView.findViewById(R.id.Fecha_Comienzo_Componente_txt);
            Fecha_Fin_Componente_txt = itemvView.findViewById(R.id.Fecha_Fin_Componente_txt);
            Fecha_Comienzo_UE_txt = itemvView.findViewById(R.id.Comienzo_UE_txt);
            Fecha_Fin_UE_txt = itemvView.findViewById(R.id.Fin_UE_txt);
            Precio_txt = itemvView.findViewById(R.id.Precio_txt);
            secondaryLayout = itemvView.findViewById(R.id.secondaryLayout);
        }
        /*Identificar cada uno de los elementos del recycle view FIN*/
    }
}