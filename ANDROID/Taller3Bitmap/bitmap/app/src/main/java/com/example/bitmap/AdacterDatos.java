package com.example.bitmap;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitmap.modelo.ItemByte;

import java.util.ArrayList;

public class AdacterDatos extends RecyclerView.Adapter<AdacterDatos.ViewHolderDatos> {

  ArrayList<ItemByte> lista;
  EditText text;


  public AdacterDatos(ArrayList<ItemByte> lista, EditText text) {

      this.lista = lista;
      this.text = text;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position).getNombre(), lista.get(position).getEstado());
        holder.asignar(position);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        CheckBox item;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.itemCheckBox);
        }

        public void asignarDatos(String nombre, String estado) {
            item.setText(nombre);
            if(estado.equals("1")){
                item.setChecked(true);
            }else
                item.setChecked(false);
            }

        public void asignar(int position) {
            item.setOnClickListener(v -> {
                if(item.isChecked()){
                   lista.get(position).setNuevaCadena("1");
                }else{
                    lista.get(position).setNuevaCadena("0");
                }
                String con = "";
                for (int i = 0; i < lista.size(); i++) {
                    con = con + lista.get(i).getNuevaCadena();
                }
                con = Global.binarioHexadecimal(con);
                Log.e("", "bit:"+con);
                text.setText(con);
            });
        }
    }


}
