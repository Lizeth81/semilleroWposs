package com.example.serviciospublicos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.serviciospublicos.databinding.ActivityPagarServicioBinding;
import com.example.serviciospublicos.entidades.Usuario;
import com.example.serviciospublicos.utilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PagarServicio extends AppCompatActivity {
    ActivityPagarServicioBinding binding;
    int id = 0;
    Usuario us;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagarServicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new Utilidades(this);
        us =  dao.getId(id);

        //Extrae los Sprint de los value y los almacena en un array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.factura,android.R.layout.simple_spinner_item);
        //Muestra los sprint en el spinner
        binding.spinner.setAdapter(adapter);

        botonAtras();
        pagar();
    }
    public void botonAtras(){
        binding.flechaIzquierda.setOnClickListener(View -> {
        Intent myIntent = new Intent(PagarServicio.this, Inicio.class);
        startActivity(myIntent);
        });
    }
    public void pagar(){
        binding.aceptar.setOnClickListener(View ->{
            int numSpinner = binding.spinner.getSelectedItemPosition();
            String numerofactura = binding.numeroFactura.getText().toString();
            String monto = binding.monto1.getText().toString();
            String confirmarMonto = binding.monto2.getText().toString();

            if(numSpinner == 0 || numerofactura.equals("") || monto.equals("") || confirmarMonto.equals("")){
                Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_SHORT).show();
            }else if(numerofactura.length() != 10){
                Toast.makeText(this, "Numero de factura incorrecto!!!", Toast.LENGTH_SHORT).show();
            }else if(!monto.equals("")){
                int monto1 =  Integer.valueOf(monto);
                if(monto1 < 1000){
                    Toast.makeText(this, "El valor del monto tiene que ser minimo de 1000", Toast.LENGTH_SHORT).show();
                }else if(!monto.equals(confirmarMonto)){
                    Toast.makeText(this, "Los valores del monto no coinciden!!!", Toast.LENGTH_SHORT).show();
                }else{
                    SaldoSuficiente(monto1);
                }
            }
        });
    }
    public void SaldoSuficiente(int montoIngresado){
        int monto = us.getMonto();
        if(monto >= montoIngresado){
             SaldoNuevo(monto, montoIngresado);
        }else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(PagarServicio.this);
            alerta.setMessage("¡Saldo insuficiente!\n Ingresa un nuevo saldo.")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        Intent myIntent = new Intent(PagarServicio.this, Inicio.class);
                        myIntent.putExtra("Id", us.getId());
                        startActivity(myIntent);
                        finish();
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("Pago de servicios publicos");
            titulo.show();
        }

    }

    public void SaldoNuevo(int monto1, int monto2){
        int newSaldo = monto1 - monto2;
        us.setMonto(newSaldo);

        if(dao.ActualizarUsuario(us)){
            AlertDialog.Builder alerta = new AlertDialog.Builder(PagarServicio.this);
            alerta.setMessage("Su pago se realizo con exito!!!. \nSu nuevo saldo es: "+us.getMonto())
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", (dialog, which) -> {
                        Intent myIntent = new Intent(PagarServicio.this, Inicio.class);
                        myIntent.putExtra("Id", us.getId());
                        startActivity(myIntent);
                        finish();
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("Pago de servicios publicos");
            titulo.show();
        }
    }
}