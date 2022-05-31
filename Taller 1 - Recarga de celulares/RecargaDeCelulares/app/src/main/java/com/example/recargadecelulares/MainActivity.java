package com.example.recargadecelulares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recargadecelulares.entidades.Usuario;
import com.example.recargadecelulares.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText text1, text2;
    Button loguear;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById(R.id.correo);
        text2 = (EditText) findViewById(R.id.pass);
        loguear = (Button) findViewById(R.id.IniciarSesion);

        loguear.setOnClickListener(this);

        dao= new Utilidades(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.IniciarSesion:
                inicioSesion();
                break;
            case R.id.Registro:
                Intent myIntent = new Intent(MainActivity.this, Registro.class);
                startActivity(myIntent);
                break;
        }
    }

//    public void inicioSecion(usuario persona){
        public void inicioSesion(){

        String correo = text1.getText().toString();
        String pass = text2.getText().toString();

        if(correo.equals("") && pass.equals("")){
            Toast.makeText(this, "CAMPOS VACIOS!!!", Toast.LENGTH_SHORT).show();

        }else if(dao.login(correo, pass)==1){

            Usuario ux = dao.getCorreo(correo, pass);

            Intent i = new Intent(MainActivity.this, Inicio.class);
            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
            i.putExtra("Id", ux.getId());
            startActivity(i);
        }else{
            Toast.makeText(this, "Correo y/o contraseña incorrectos!!!", Toast.LENGTH_SHORT).show();
           text1.setText("");
           text2.setText("");
        }

    }
//        String correo = text1.getText().toString();
//        String contrasena = text2.getText().toString();
////        if(correo.equals(persona.getCorreo()) && contrasena.equals(persona.getPassword())){
//        if(correo.equals("lizeth") && contrasena.equals("1234")){
//            Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
//            Intent myIntent = new Intent(MainActivity.this,Inicio.class);
//            startActivity(myIntent);
//        }else{
//            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
//        }
}