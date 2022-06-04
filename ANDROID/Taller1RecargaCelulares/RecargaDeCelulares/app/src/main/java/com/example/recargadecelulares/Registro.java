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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText campoNombre, campoCorreo, campoPassword;
    Button aceptar;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoNombre = (EditText) findViewById(R.id.EditNombre);
        campoCorreo= (EditText) findViewById(R.id.EditCorreo);
        campoPassword = (EditText) findViewById(R.id.EditContrasena);
        dao=new Utilidades(this);
        aceptar = (Button) findViewById(R.id.Aceptar);

        aceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flechaIzquierda:
                Intent myIntent = new Intent(Registro.this,MainActivity.class);
                startActivity(myIntent);
                break;
            case R.id.Aceptar:
                Registrar();
                break;
        }
    }

    //Metodo de registrar
    public void Registrar(){
        Usuario us= new Usuario();
        //Validar campos
        us.setNombre(campoNombre.getText().toString());
        us.setCorreo(campoCorreo.getText().toString());
        us.setPassword(campoPassword.getText().toString());
        String nombre = campoNombre.getText().toString();

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(us.getCorreo());
        //Validar valores
        if(nombre.equals("")){
            Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
            campoNombre.requestFocus();
        }else if (mather.find()==false){
            Toast.makeText(this,"Correo invalido!!!",Toast.LENGTH_SHORT).show();
            campoCorreo.requestFocus();
        }else if (campoPassword.length()<8) {
            Toast.makeText(this,"Por favor ingrese una contraseña mayor a 8",Toast.LENGTH_SHORT).show();
            campoPassword.requestFocus();
        }else if(campoPassword.length()>8){
            String pass = campoPassword.getText().toString();
            validarPass(pass);
        } else if(dao.RegistrarUsuario(us)) {
            Toast.makeText(this, "Registro exitoso!!!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(Registro.this, MainActivity.class);
            startActivity(myIntent);
            campoNombre.setText("");
            campoCorreo.setText("");
            campoPassword.setText("");
        }else{
            Toast.makeText(this, "Usuario ya registrado!!! por favor inicio sesión", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Registro.this, MainActivity.class);
            startActivity(myIntent);
            campoNombre.setText("");
            campoCorreo.setText("");
            campoPassword.setText("");
        }
    }

    public boolean validarPass(String pass){
        boolean rtn = true;
        int seguidos = 0;
        char ultimo = 0xFF;

        int minuscula = 0;
        int mayuscula = 0;
        int numero = 0;
        int especial = 0;
        boolean espacio = false;
//        if(pass.length() < 8) return false; // tamaño
        for(int i=0;i<pass.length(); i++){
            char c = pass.charAt(i);
            if(c <= ' ' || c > '~' ){
                rtn = false; //Espacio o fuera de rango
                break;
            }
            if( (c > ' ' && c < '0') || (c >= ':' && c < 'A') || (c >= '[' && c < 'a') || (c >= '{' && c < 127) ){
                especial++;
            }
            if(c >= '0' && c < ':') numero++;
            if(c >= 'A' && c < '[') mayuscula++;
            if(c >= 'a' && c < '{') minuscula++;

            seguidos = (c==ultimo) ? seguidos + 1 : 0;
            if(seguidos >= 2){
                rtn = false; // 3 seguidos
                break;
            }
            ultimo = c;
        }
        rtn = rtn && especial > 0 && numero > 0 && minuscula > 0 && mayuscula > 0;
        return rtn;
    }

}