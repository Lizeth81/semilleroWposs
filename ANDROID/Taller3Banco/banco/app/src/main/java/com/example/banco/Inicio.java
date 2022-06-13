package com.example.banco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.banco.codigoQR.view.CodigoQr;
import com.example.banco.codigoRetiro.view.CodigoRetiro;
import com.example.banco.databinding.ActivityInicioBinding;
import com.example.banco.login.interfaces.InterfaceLogin;
import com.example.banco.login.presenter.PresenterLogin;
import com.example.banco.login.view.MainActivity;
import com.example.banco.modelo.registro.Persona;
import com.example.banco.retiro.view.Retiro;
import com.example.banco.transaccion.view.Transacciones;
import com.example.banco.transferencia.view.Transferencia;

import org.json.JSONException;

public class Inicio extends AppCompatActivity implements InterfaceLogin.view {
    ActivityInicioBinding binding;
    InterfaceLogin.presenter presenter;
    String correo;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        String nombre = b.getString("nombre");
        String numeroCuenta = b.getString("numFactura");
        int monto = b.getInt("monto");
        String token = b.getString("token");
        correo = b.getString("correo");
        pass = b.getString("pass");

        presenter = new PresenterLogin(this);
        try {
            presenter.login(correo, pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.editNombre.setText(nombre);
        binding.editNumCuenta.setText(numeroCuenta);
        binding.editSaldo.setText(String.valueOf(monto));

        CerrarSesion();
        CodigoRet(numeroCuenta, token);
        Retiros(token);
        Transferencias(token);
        CodigoQr(nombre, numeroCuenta, token);
        Transacciones(numeroCuenta, token);
    }

    public void CerrarSesion() {
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
            titulo.setTitle("Banco WPOSS");
            titulo.show();
        });
    }

    public void CodigoRet(String numeroCuenta, String token) {
        binding.Codigoretiros.setOnClickListener(View -> {
            Intent intent1 = new Intent(Inicio.this, CodigoRetiro.class);
            intent1.putExtra("numFactura", numeroCuenta);
            intent1.putExtra("token", token);
            startActivity(intent1);

        });
    }

    public void Retiros(String token) {
        binding.retiro.setOnClickListener(View -> {
            Intent intent2 = new Intent(Inicio.this, Retiro.class);
            intent2.putExtra("token", token);
            startActivity(intent2);


        });
    }

    public void Transferencias(String token) {
        binding.transferir.setOnClickListener(View -> {
            Intent intent3 = new Intent(Inicio.this, Transferencia.class);
            intent3.putExtra("token", token);
            startActivity(intent3);


        });
    }

    public void CodigoQr(String nombre, String numeroCuenta, String token) {
        binding.qr.setOnClickListener(View -> {
            Intent intent4 = new Intent(Inicio.this, CodigoQr.class);
            intent4.putExtra("nombre", nombre);
            intent4.putExtra("numFactura", numeroCuenta);
            intent4.putExtra("token", token);
            startActivity(intent4);

        });
    }

    public void Transacciones(String numeroCuenta, String token) {
        binding.transaccion.setOnClickListener(View -> {
            Intent intent5 = new Intent(Inicio.this, Transacciones.class);
            intent5.putExtra("numFactura", numeroCuenta);
            intent5.putExtra("token", token);
            startActivity(intent5);

        });
    }


    @Override
    public void respuesta(boolean status, Persona data, String token) {
        binding.editNumCuenta.setText(data.getBill().getBill_number());
        binding.editSaldo.setText(String.valueOf(data.getBill().getBill_amount()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            presenter.login(correo, pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}