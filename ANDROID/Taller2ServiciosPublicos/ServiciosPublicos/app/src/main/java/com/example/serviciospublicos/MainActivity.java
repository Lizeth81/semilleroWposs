package com.example.serviciospublicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button inicio, registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicio = (Button) findViewById(R.id.IniciarSesion);
        registro = (Button) findViewById(R.id.Registro);

        inicio.setOnClickListener(this);
        registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IniciarSesion:
                Intent myIntent1 = new Intent(MainActivity.this, InicioSesion.class);
                startActivity(myIntent1);
                break;
            case R.id.Registro:
                Intent myIntent2 = new Intent(MainActivity.this, Registro.class);
                startActivity(myIntent2);
                break;
        }
    }
}