package com.example.banco.retiro.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.banco.Inicio;
import com.example.banco.codigoRetiro.view.CodigoRetiro;
import com.example.banco.databinding.ActivityRetiroBinding;
import com.example.banco.modelo.registro.Persona;
import com.example.banco.retiro.interfaces.InterfaceRetiro;
import com.example.banco.retiro.presenter.PresenterRetiro;

import org.json.JSONException;

public class Retiro extends AppCompatActivity implements InterfaceRetiro.view {
    ActivityRetiroBinding binding;
    InterfaceRetiro.presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRetiroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new PresenterRetiro(this);

        Bundle b = getIntent().getExtras();
//        String nombre = b.getString("nombre");
//        String numeroCuenta = b.getString("numFactura");
//        int monto = b.getInt("monto");
        String token = b.getString("token");

        flechaRetroceso();
        Aceptar(token);
    }

      @Override
    public void respuesta(String mensaje, boolean estatus) {
          Log.e("", "mensaje "+mensaje);
          Log.e("", "estado "+estatus);

          AlertDialog.Builder alerta = new AlertDialog.Builder(Retiro.this);
          if (estatus) {
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
              alerta.setMessage(mensaje)
                      .setCancelable(true)
                      .setPositiveButton("Aceptar", (dialog, which) -> {
                          binding.editCodigoAut.setText("");
                          binding.editNumeroCuenta.setText("");
                          binding.editMonto.setText("");
                      });
              AlertDialog titulo = alerta.create();
              titulo.setTitle("BANCO WPOSS");
              titulo.show();
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
    public void Aceptar(String token){
        binding.buttonAceptar.setOnClickListener(View ->{
            String codAut = binding.editCodigoAut.getText().toString();
            String numeroCuenta = binding.editNumeroCuenta.getText().toString();
            String tipoTrans = binding.editTipoTranferencia.getText().toString();
            String montol = binding.editMonto.getText().toString();

            try {
                if(codAut.equals("") || numeroCuenta.equals("") || montol.equals("")){
                    Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
                    binding.editCodigoAut.requestFocus();
                }else{
                    int monto = Integer.valueOf(montol);
                    presenter.retiro(codAut, numeroCuenta, tipoTrans, monto, token);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }
}