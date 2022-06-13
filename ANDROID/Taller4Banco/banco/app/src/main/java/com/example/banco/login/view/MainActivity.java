package com.example.banco.login.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.banco.Inicio;
import com.example.banco.databinding.ActivityMainBinding;
import com.example.banco.login.interfaces.InterfaceLogin;
import com.example.banco.login.presenter.PresenterLogin;
import com.example.banco.modelo.registro.Persona;
import com.example.banco.registro.view.Registro;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements InterfaceLogin.view {
  ActivityMainBinding binding;
  InterfaceLogin.presenter presenter;
  Persona persona;
    String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new PresenterLogin(this);
        registro();
        Aceptar();
    }

    public void registro(){
        binding.buttonRegistro.setOnClickListener(View ->{
            Intent a = new Intent(MainActivity.this, Registro.class);
            startActivity(a);
        });
    }

    @Override
    public void respuesta(boolean status, Persona data, String token) {
        Log.e("", "Estado: "+status);
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        if (status) {
            alerta.setMessage("Bienvenido al banco WPOSS!!! ")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        Intent a = new Intent(MainActivity.this, Inicio.class);
                        a.putExtra("correo", data.getUser_email());
                        a.putExtra("pass", pass);
                        a.putExtra("nombre", data.getUser_name());
                        a.putExtra("numFactura", data.getBill().getBill_number());
                        a.putExtra("monto", data.getBill().getBill_amount());
                        Log.e("", "monto del inicio sesion: " +data.getBill().getBill_amount());
                        a.putExtra("token", token);
                        startActivity(a);
                        finish();
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();
            Log.e("", "token: "+token);

        }
        else {

            alerta.setMessage("Usuario y/o contraseña incorrecta.")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        binding.editCorreo.setText("");
                        binding.editPass.setText("");
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();
        }
    }
    public void Aceptar(){
        binding.IniciarSesion.setOnClickListener(View ->{
            String correo =  binding.editCorreo.getText().toString();
            pass = binding.editPass.getText().toString();

            try {
                if(correo.equals("") || pass.equals("")){
                    Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
                }else {
                    presenter.login(correo, pass);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }
}