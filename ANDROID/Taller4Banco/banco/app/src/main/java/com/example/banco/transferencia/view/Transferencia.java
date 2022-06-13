package com.example.banco.transferencia.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.banco.Inicio;
import com.example.banco.databinding.ActivityTransferenciaBinding;
import com.example.banco.transferencia.interfaces.InterfaceTransferencia;
import com.example.banco.transferencia.presenter.PresenterTransferencia;

import org.json.JSONException;

public class Transferencia extends AppCompatActivity implements InterfaceTransferencia.view {
    ActivityTransferenciaBinding binding;
    InterfaceTransferencia.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransferenciaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.presenter = new PresenterTransferencia(this);

        Bundle b = getIntent().getExtras();
        String token = b.getString("token");

        flechaRetroceso();
        aceptar(token);
    }
   @Override
    public void respuesta(String mensaje, boolean estatus) {
        if (estatus) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Transferencia.this);
            alerta.setMessage(mensaje)
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        flechaRetroceso();
                        finish();
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();

        } else {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Transferencia.this);
            alerta.setMessage(mensaje)
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        binding.editNumeroCuenta.setText("");
                        binding.editTipoTranferencia.setText("");
                        binding.editMonto.setText("");
                        binding.editDestinatario.setText("");
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();
            Toast.makeText(this, "fallo en el registro" + mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void flechaRetroceso(){
        binding.FechaRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void aceptar(String token){
        binding.buttonAceptar.setOnClickListener(View ->{
            String numCuenta = binding.editNumeroCuenta.getText().toString();
            String tipoTrans = binding.editTipoTranferencia.getText().toString();
            String montol = binding.editMonto.getText().toString();
            String numDestino = binding.editDestinatario.getText().toString();

            try {
                if (numCuenta.equals("") || tipoTrans.equals("") || montol.equals("") || numDestino.equals("")) {
                    Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
                }else {
                    int monto = Integer.valueOf(montol);
                    presenter.transfer(numCuenta, tipoTrans, monto, numDestino, token);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}