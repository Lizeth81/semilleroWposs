package com.example.serviciospublicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.serviciospublicos.databinding.ActivityIngresarSaldoBinding;
import com.example.serviciospublicos.entidades.Usuario;
import com.example.serviciospublicos.utilidades.Utilidades;


public class IngresarSaldo extends AppCompatActivity {
    ActivityIngresarSaldoBinding binding;
    int id = 0;
    Usuario us;
    Utilidades dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIngresarSaldoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new Utilidades(this);
        us =  dao.getId(id);

        BotonAtras();
        IngresarSaldo();
    }
    public void BotonAtras(){
        binding.flechaIzquierda.setOnClickListener(View -> {
        Intent myIntent = new Intent(IngresarSaldo.this, Inicio.class);
        startActivity(myIntent);
        });
    }
    public void IngresarSaldo(){
        binding.aceptar.setOnClickListener(View -> {
            String saldo = binding.saldoConsignado.getText().toString();

           if(saldo.equals("")){
                Toast.makeText(this, "Campo vacios!", Toast.LENGTH_SHORT).show();
            }else if(!saldo.equals("")){
                int monto = Integer.parseInt(saldo);
                if(monto < 1000){
                    Toast.makeText(this, "El saldo consignado tiene que ser mayor a 1000", Toast.LENGTH_SHORT).show();
                    binding.saldoConsignado.setText("");
                }else{
                    updateMonto(monto);
                }
            }
        });

    }
    public void updateMonto(int monto){
        int nuevoSaldo = us.getMonto();
        int guardarSaldo = monto + nuevoSaldo;
        us.setMonto(guardarSaldo);

        if(dao.ActualizarUsuario(us)){
            Intent myIntent = new Intent(IngresarSaldo.this, Inicio.class);
            myIntent.putExtra("Id", us.getId());
            startActivity(myIntent);
        }
    }
}