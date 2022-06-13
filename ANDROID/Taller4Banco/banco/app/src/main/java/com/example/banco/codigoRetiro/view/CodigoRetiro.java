package com.example.banco.codigoRetiro.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.banco.Inicio;
import com.example.banco.codigoRetiro.interfaces.InterfaceCodigoRetiro;
import com.example.banco.codigoRetiro.presenter.PresenterCodigoRetiro;
import com.example.banco.databinding.ActivityCodigoRetiroBinding;
import com.example.banco.modelo.retiro.CodeRet;

import org.json.JSONException;

public class CodigoRetiro extends AppCompatActivity implements InterfaceCodigoRetiro.view {
    ActivityCodigoRetiroBinding binding;
    InterfaceCodigoRetiro.presenter presenter;

    private CountDownTimer countDownTimer;
    private long timeLestInMiliseconds = 1800000;//30 min
    private boolean timeRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodigoRetiroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new PresenterCodigoRetiro(this);

        Bundle b = getIntent().getExtras();
        String token = b.getString("token");
        String numCuenta = b.getString("numFactura");

//        String numeroCuenta = binding.editNumeroCuenta.getText().toString();
        try {
            presenter.numberBill(numCuenta, token);
//            MostrarCodigo();
            starffTime();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        flechaRetroceso();
//        Aceptar(token);
    }
    public void flechaRetroceso(){
        binding.FechaRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        binding.FechaRetroceso.setOnClickListener(View ->{
//            System.out.println("entra ");
//            onBackPressed();
//        });
//        System.out.println("no entra");
//        return false;
//    }
    @Override
    public void respuesta(String mensaje, CodeRet data, boolean estatus) {
        String cod = data.getCode();
        AlertDialog.Builder alerta = new AlertDialog.Builder(CodigoRetiro.this);
        if (estatus) {
            alerta.setMessage("Se ha generado el codigo de autorización con exito.")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        binding.codigo.setText(String.valueOf(cod));
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();
        }
    }

//    public void Aceptar(String token){
//        binding.buttonAceptar.setOnClickListener(View ->{
//            String numeroCuenta = binding.editNumeroCuenta.getText().toString();
//            try {
//                presenter.numberBill(numeroCuenta, token);
//                MostrarCodigo();
//                starffTime();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//    public void MostrarCodigo(){
//        if(binding.Codigoretiros.getVisibility() == View.GONE){
//            binding.Codigoretiros.setVisibility(View.VISIBLE);
//            binding.editVence.setVisibility(View.VISIBLE);
//            binding.tiempo.setVisibility(View.VISIBLE);
//            binding.mensg.setVisibility(View.VISIBLE);
//            binding.textInputLayout.setVisibility(View.GONE);
//            binding.buttonAceptar.setVisibility(View.GONE);
//        }
//    }
    public void starffTime(){
        countDownTimer = new CountDownTimer(timeLestInMiliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    timeLestInMiliseconds = millisUntilFinished;
                    int minutos = (int) timeLestInMiliseconds/60000;
                    int segundos = (int) timeLestInMiliseconds % 60000 / 1000;
                    
                    String timeLeftText;
                    
                    timeLeftText = "" + minutos;
                    timeLeftText += ":";
                    if(segundos < 30) timeLeftText += "0";
                    timeLeftText += segundos;
                    
                    binding.tiempo.setText(timeLeftText);
            }

            @Override
            public void onFinish() {

            }
        }.start();
        timeRunning = true;
    }
}