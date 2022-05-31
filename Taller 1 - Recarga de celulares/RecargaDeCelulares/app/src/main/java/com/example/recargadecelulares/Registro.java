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
        }else if(dao.RegistrarUsuario(us)) {
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
//    public void RegistrarUSuario() {
//        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this, "bd usuario", null, 1);
//        //Se indica que se abre la base de datos para poder empezar a editarla
//        SQLiteDatabase db = conn.getWritableDatabase();
//
//        ContentValues values=new ContentValues();
//
//        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
//        values.put(Utilidades.CAMPO_CORREO, campoCorreo.getText().toString());
//        values.put(Utilidades.CAMPO_PASSWORD, campoPassword.getText().toString());
//
//        //Insertar  a la base de datos
//        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_NOMBRE, values);
////      db.close();
//        Toast.makeText(getApplicationContext(), "Id Registro: "+idResultante, Toast.LENGTH_LONG).show();
//        //Cerrar conexion
//        db.close();
////        Toast.makeText(this, "registro "+ id_String, Toast.LENGTH_LONG).show();
//    }
}