package com.example.banco.transaccion.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banco.R;
import com.example.banco.modelo.Transaction.Usuario;

import java.util.ArrayList;

public class AdacterDatos extends RecyclerView.Adapter<AdacterDatos.ViewHolderDatos> {

    ArrayList<Usuario> listDatos;

    public AdacterDatos(ArrayList<Usuario> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.iddato.setText(String.valueOf(listDatos.get(position).getTransaction_id()));
        holder.tipoTrans.setText(listDatos.get(position).getTransaction_type());
        holder.monto.setText(String.valueOf(listDatos.get(position).getTransaction_amount()));
        holder.descripcion.setText(listDatos.get(position).getTransaction_description());
        holder.fecha.setText(listDatos.get(position).getTransaction_date());
        holder.estado.setText(String.valueOf(listDatos.get(position).isTransaction_estate()));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        
        TextView iddato, tipoTrans, monto, descripcion, fecha, estado;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            iddato = (TextView) itemView.findViewById(R.id.idDato);
            tipoTrans = (TextView) itemView.findViewById(R.id.tipo);
            monto = (TextView) itemView.findViewById(R.id.monto);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            estado = (TextView) itemView.findViewById(R.id.estado);
        }
    }
}
