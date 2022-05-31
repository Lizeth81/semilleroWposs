package com.example.serviciospublicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.serviciospublicos.databinding.ActivityInicioSesionBinding;
import com.example.serviciospublicos.entidades.Usuario;
import com.example.serviciospublicos.utilidades.Utilidades;

public class InicioSesion extends AppCompatActivity {
     Utilidades dao;
     ActivityInicioSesionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioSesionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao = new Utilidades(this);
        Regreso();
        IniciarSesion();

    }

    public void Regreso(){
        binding.flechaIzquierda.setOnClickListener(View -> {
            Intent myIntent = new Intent(InicioSesion.this, MainActivity.class);
            startActivity(myIntent);
        });
    }

    public void IniciarSesion(){
        binding.IniciarSesion.setOnClickListener(View -> {
            String cedula = binding.cedula.getText().toString();
            String pass = binding.pass.getText().toString();

            if(cedula.equals("") && pass.equals("")){
                Toast.makeText(this, "CAMPOS VACIOS!!!", Toast.LENGTH_SHORT).show();
            }else if(dao.login(cedula, pass)){
                Usuario ux = dao.getCorreo(cedula, pass);
                Intent i = new Intent(InicioSesion.this, Inicio.class);
                Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                i.putExtra("Id", ux.getId());
                startActivity(i);


            }else{
                Toast.makeText(this, "Correo y/o contraseña incorrectos!!!", Toast.LENGTH_SHORT).show();
                binding.cedula.setText("");
                binding.pass.setText("");
            }
        });

    }
}