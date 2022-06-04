package com.example.serviciospublicos;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.serviciospublicos.databinding.ActivityRegistroBinding;
import com.example.serviciospublicos.entidades.Usuario;
import com.example.serviciospublicos.utilidades.Utilidades;

public class Registro extends AppCompatActivity {
    ActivityRegistroBinding binding;
    Utilidades dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao=new Utilidades(this);

        binding.flechaIzquierda.setOnClickListener(View -> {
            Intent myIntent = new Intent(Registro.this, MainActivity.class);
            startActivity(myIntent);
        });

        binding.aceptar.setOnClickListener(View -> {
            Usuario us= new Usuario();
            String nombre = binding.editNombre.getText().toString();
            String cedula = binding.editCedula.getText().toString();
            String pass = binding.editContrasena.getText().toString();
            int monto = 0;

            //Validar campos
            us.setNombre(nombre);
            us.setCedula(cedula);
            us.setPass(pass);

            boolean contrasena = validarContraseña(pass);
            Log.e("TAG", "contraseña"+contrasena);

            //Validar valores
            if(nombre.equals("") || cedula.equals("") || pass.equals("")){
                Toast.makeText(this, "Campos vacios!!!", Toast.LENGTH_LONG).show();
                binding.editNombre.requestFocus();
            }else if (binding.editCedula.length() != 10){
                Toast.makeText(this,"Cedula incorrecta!!!",Toast.LENGTH_SHORT).show();
                binding.editCedula.requestFocus();
            }else if(contrasena == false) {
                Toast.makeText(this, "Para mayor seguridad la contraseña debe contener letras y numeros!!!", Toast.LENGTH_SHORT).show();
                binding.editContrasena.requestFocus();
            }else if(dao.RegistrarUsuario(us)) {
                Toast.makeText(getApplicationContext(), "Registro exitoso!!!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Registro.this, InicioSesion.class);
                startActivity(myIntent);
            }else{
                Toast.makeText(this, "Usuario ya registrado!!! por favor inicio sesión", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(Registro.this, MainActivity.class);
                startActivity(myIntent);
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