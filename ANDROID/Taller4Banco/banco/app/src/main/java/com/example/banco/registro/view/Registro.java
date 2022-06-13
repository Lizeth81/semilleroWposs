package com.example.banco.registro.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.banco.login.view.MainActivity;
import com.example.banco.databinding.ActivityRegistroBinding;
import com.example.banco.registro.interfaces.InterfaceRegistro;
import com.example.banco.registro.presenter.PresenterRegistro;

import org.json.JSONException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements InterfaceRegistro.view {
    ActivityRegistroBinding binding;

    InterfaceRegistro.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new PresenterRegistro(this);
        BotonAtras();
        guardarDatos();
    }

    public void BotonAtras() {
        binding.flechaIzquierda.setOnClickListener(View -> {
            Intent a = new Intent(Registro.this, MainActivity.class);
            startActivity(a);
        });
    }

    @Override
    public void respuesta(String mensaje, boolean estatus) {
        if (estatus) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
            alerta.setMessage("Se a registrado exitosamente!!! \nPor favor inicie sesión.")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        Intent a = new Intent(Registro.this, MainActivity.class);
                        startActivity(a);
                        finish();
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();

        } else {
            AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
            alerta.setMessage("Error al registrar el usuario.")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        binding.editNombre.setText("");
                        binding.editCedula.setText("");
                        binding.editNEmail.setText("");
                        binding.editPass.setText("");
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("BANCO WPOSS");
            titulo.show();
            Toast.makeText(this, "fallo en el registro" + mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarDatos() {
        binding.aceptar.setOnClickListener(View -> {
            String nombre = binding.editNombre.getText().toString();
            String cedula = binding.editCedula.getText().toString();
            String email = binding.editNEmail.getText().toString();
            String pass = binding.editPass.getText().toString();
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher mather = pattern.matcher(binding.editNEmail.getText());
            boolean contrasena = validarContraseña(pass);
            Log.e("TAG", "contraseña"+contrasena);
            try {
                if (nombre.equals("") || cedula.equals("") || email.equals("") || pass.equals("")) {
                    Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
                    binding.editNombre.requestFocus();
                } else if (mather.find() == false) {
                    Toast.makeText(this, "Correo invalido!!!", Toast.LENGTH_SHORT).show();
                    binding.editNEmail.requestFocus();
                } else if (contrasena == false) {
                    Toast.makeText(this, "La contraseña debe de tener letras y numeros!!!", Toast.LENGTH_SHORT).show();
                    binding.editPass.requestFocus();
                } else {
                    presenter.guardar(nombre, cedula, email, pass, "ACTIVO");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean validarContraseña(String pass) {
        boolean e = false;
        boolean letras = false;
        boolean numeros = false;
        char c;

        for (int i = 0; i < pass.length(); i++) {
            c = pass.charAt(i);
            if (Character.isDigit(c))
                numeros = true;
            if (Character.isLetter(c))
                letras = true;
        }
        if (numeros && letras) {
            e = true;
        } else {
            e = false;
        }
        return e;
    }
}