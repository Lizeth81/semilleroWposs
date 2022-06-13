package com.example.banco.transaccion.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.banco.databinding.ActivityTransaccionesBinding;
import com.example.banco.modelo.Transaction.Transac;
import com.example.banco.modelo.Transaction.Usuario;
import com.example.banco.transaccion.interfaces.InterfaceTransaccion;
import com.example.banco.transaccion.presenter.PresenterTransaccion;
import com.example.banco.transaccion.repository.RepositoryTrans;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Transacciones extends AppCompatActivity implements InterfaceTransaccion.view {
    ActivityTransaccionesBinding binding;
    InterfaceTransaccion.presenter presenter;
    AdacterDatos adapter;
    public static Transac listDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransaccionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new PresenterTransaccion(this);
        Bundle b = getIntent().getExtras();
        String token = b.getString("token");
        String numCuenta = b.getString("numFactura");
        try {
            presenter.transaccion(numCuenta, token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        flechaRetroceso();


    }

    public void flechaRetroceso() {
        binding.FechaRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void respuesta(String mensaje, ArrayList<Usuario> data, boolean estado) {
        Log.e("", "Estado" + estado);

        AlertDialog.Builder alerta = new AlertDialog.Builder(Transacciones.this);
        if (estado) {
            alerta.setMessage(mensaje);
            alerta.setCancelable(true);
            alerta.setPositiveButton("Aceptar", (dialog, which) -> {
            });
            adapter = new AdacterDatos(data);
            binding.RecycleList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.RecycleList.setHasFixedSize(true);
            binding.RecycleList.setAdapter(adapter);

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();


        }

    }
}