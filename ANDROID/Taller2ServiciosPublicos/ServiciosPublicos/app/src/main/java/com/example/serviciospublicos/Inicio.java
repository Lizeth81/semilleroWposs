package com.example.serviciospublicos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.serviciospublicos.databinding.ActivityInicioBinding;
import com.example.serviciospublicos.entidades.Usuario;
import com.example.serviciospublicos.utilidades.Utilidades;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Inicio extends AppCompatActivity {
    ActivityInicioBinding binding;
    int id = 0;
    Usuario u;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new Utilidades(this);
        u =  dao.getId(id);

      //Mostrar Fecha
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        binding.horaFecha.setText(strDate.toString());
        //Mostrar nombre y saldo
        binding.nombre.setText((u.getNombre()+ " \nMonto: " + u.getMonto()));

        saldo();
        pagarFactura();
        salir();
    }

    private void saldo(){
        binding.ingresarSaldo.setOnClickListener(View -> {
            Intent myIntent = new Intent(Inicio.this, IngresarSaldo.class);
            myIntent.putExtra("Id", u.getId());
            startActivity(myIntent);
        });

    }
    private void pagarFactura(){
        binding.pagar.setOnClickListener(View -> {
            Intent myIntent = new Intent(Inicio.this, PagarServicio.class);
            myIntent.putExtra("Id", u.getId());
            startActivity(myIntent);
        });

    }
    private void salir(){
        binding.cerrarSesion.setOnClickListener(View -> {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
            alerta.setMessage("¿Deseas cerrar sesión?")
                    .setCancelable(true)
                    .setPositiveButton("Si", (dialog, which) -> {
                           Intent myIntent = new Intent(Inicio.this, MainActivity.class);
                           startActivity(myIntent);
                           finish();
                       })
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel());
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Pago de servicios publicos");
            titulo.show();
        });
    }
}