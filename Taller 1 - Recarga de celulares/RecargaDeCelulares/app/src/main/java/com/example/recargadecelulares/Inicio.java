package com.example.recargadecelulares;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.recargadecelulares.entidades.Usuario;
import com.example.recargadecelulares.utilidades.Utilidades;

public class Inicio extends AppCompatActivity implements View.OnClickListener{
    //Inicializamos los componentes
    TextView  nombre;
    EditText celular1, celular2, val1, val2;
    Spinner spinner;
    Button aceptar;

    int id = 0;
    Usuario u;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        spinner = (Spinner) findViewById(R.id.Spinner);
        nombre = (TextView) findViewById(R.id.nombre);
        celular1 = (EditText) findViewById(R.id.celular);
        celular2 = (EditText) findViewById(R.id.celular2);
        val1 = (EditText) findViewById(R.id.valor);
        val2 = (EditText) findViewById(R.id.valor2);

        aceptar = (Button) findViewById(R.id.aceptar);
        aceptar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new Utilidades(this);
        u =  dao.getId(id);
        nombre.setText((u.getNombre()));

    //Extrae los Sprint de los value y los almacena en un array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operador,android.R.layout.simple_spinner_item);
        //Muestra los sprint en el spinner
        spinner.setAdapter(adapter);

        //Muestra en el EditView el spinner seleccionado
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                operador.setText("Operador: "+adapterView.getItemAtPosition(i).toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cerrarSesion:
                MensajeSalir();
                break;
            case R.id.aceptar:
                ValidarCampos();
                break;
        }
    }
    public void ValidarCampos() {
        String cel1 = celular1.getText().toString();
        String cel2 = celular2.getText().toString();
        String monto = val1.getText().toString();
        String monto2 = val2.getText().toString();
        int s = spinner.getSelectedItemPosition();


        if (cel1.equals("") || cel2.equals("") || monto.equals("") ||  monto2.equals("") || s == 0) {
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_LONG).show();
        } else if (cel1.length() != 10) {
            Toast.makeText(this, "Ingresar un numero telefonico valido!!!", Toast.LENGTH_LONG).show();
        }else if(!cel1.equals(cel2)){
            Toast.makeText(this, "El numero telefonico no coincide!!!", Toast.LENGTH_LONG).show();
        }else if (!monto.equals("") || !monto2.equals("")) {
            int montoInt = Integer.parseInt(monto);
            int montoInt2 = Integer.parseInt(monto2);
            if (montoInt < 1000) {
                Toast.makeText(this, "Valor de la recarga incorrecto! Tiene que ser mayor a 1000", Toast.LENGTH_LONG).show();
            }else if(!monto.equals(monto2)){
                Toast.makeText(this, "El valor de la recarga no coinciden!!!", Toast.LENGTH_LONG).show();
            }else{
                MensajeAceptar();
            }
        }
    }

    public void MensajeAceptar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
        alerta.setMessage("Recarga exitosa!!!")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        celular1.setText("");
                        celular2.setText("");
                        val1.setText("");
                        val2.setText("");
                        spinner.setSelection(0);
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Recarga de celular");
        titulo.show();
    }
    public void MensajeSalir(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(Inicio.this);
        alerta.setMessage("¿Deseas cerrar sesión?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(Inicio.this, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Recarga de celular");
        titulo.show();
    }
}