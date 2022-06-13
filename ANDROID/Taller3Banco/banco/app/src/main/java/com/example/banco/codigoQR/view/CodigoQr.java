package com.example.banco.codigoQR.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.example.banco.Inicio;
import com.example.banco.codigoQR.interfaces.InterfaceQr;
import com.example.banco.codigoQR.presenter.PresenterQr;
import com.example.banco.codigoRetiro.view.CodigoRetiro;
import com.example.banco.databinding.ActivityCodigoQrBinding;

import org.json.JSONException;

public class CodigoQr extends AppCompatActivity implements InterfaceQr.view {
    ActivityCodigoQrBinding binding;
    InterfaceQr.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodigoQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new PresenterQr(this);

        Bundle b = getIntent().getExtras();
        String nombre = b.getString("nombre");
        String token = b.getString("token");
        String numCuenta = b.getString("numFactura");

        binding.textNombre.setText(nombre);

        try {
            presenter.codigoQr(numCuenta, token);
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
    public void respuesta(String mensaje, String img, boolean estado) {
        String qr = img;
        Log.e("", "imagen"+img);
        if (estado) {
            byte[] imageBytes = Base64.decode(img, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            binding.qr.setImageBitmap(decodedImage);
        }

    }
}