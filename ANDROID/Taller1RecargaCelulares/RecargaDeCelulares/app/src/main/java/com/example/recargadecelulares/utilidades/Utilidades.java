package com.example.recargadecelulares.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.recargadecelulares.entidades.Usuario;

import java.util.ArrayList;

public class Utilidades {
    Context c;
    Usuario persona;
    ArrayList<Usuario> lista;
    SQLiteDatabase db;
    String bd = "BDUsuario";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, nombre text, correo text, password text)";
//    //Constantes campos tabla usuario
//    public static final String TABLA_USUARIO = "usuario";
//    public static final String CAMPO_ID = "id";
//    public static final String CAMPO_NOMBRE = "nombre";
//    public static final String CAMPO_CORREO = "correo";
//    public static final String CAMPO_PASSWORD = "password";

    //Constante String
//    public static final String tabla = "CREATE TABLE" +TABLA_USUARIO+" ("+CAMPO_ID+" PRIMARY KEY AUTOINCREMENT, " +CAMPO_NOMBRE+" TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_PASSWORD+" TEST)";

    //Metodo contructor
    public Utilidades(Context c) {
        this.c = c;
        db = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        db.execSQL(tabla);
        //Objeto persona
        persona = new Usuario();
    }

    //Metodos
    public boolean RegistrarUsuario(Usuario persona) {
        if (buscar(persona.getCorreo()) == 0) {
            ContentValues values = new ContentValues();
            values.put("nombre", persona.getNombre());
            values.put("correo", persona.getCorreo());
            values.put("password", persona.getPassword());

            Long idResultante = db.insert("usuario", null, values);
//            db.close();
            return (idResultante > 0);
        } else {
            return false;
        }
    }

    public int buscar(String persona) {
        int n = 0;
        lista = SelectUsuario();
        for (Usuario us : lista) {
            if (us.getCorreo().equals(persona)) {
                n++;
            }
        }
        return n;
    }

    public ArrayList<Usuario> SelectUsuario() {
        ArrayList<Usuario> Lista = new ArrayList<>();
        Lista.clear();
        Cursor cr = db.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setCorreo(cr.getString(2));
                u.setPassword(cr.getString(3));
                Lista.add(u);
            } while (cr.moveToNext());
        }
        return Lista;
    }

    public int login(String correo, String pass) {
        int a = 0;
        Cursor cr = db.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(2).equals(correo) && cr.getString(3).equals(pass)) {
                    a++;
                }
            } while (cr.moveToNext());
        }
        return a;
    }
    public Usuario getCorreo(String correo, String pass){
        lista = SelectUsuario();
        for(Usuario us: lista){
            if(us.getCorreo().equals(correo) && us.getPassword().equals(pass)){
                return us;
            }
        }
        return null;
    }
    public Usuario getId(int id){
        lista = SelectUsuario();
        for(Usuario us: lista){
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }
}
