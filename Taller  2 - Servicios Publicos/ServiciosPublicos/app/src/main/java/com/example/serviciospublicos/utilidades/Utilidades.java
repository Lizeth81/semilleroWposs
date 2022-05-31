package com.example.serviciospublicos.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.serviciospublicos.entidades.Usuario;

import java.util.ArrayList;

public class Utilidades {
   Context c;
   Usuario per;
   ArrayList<Usuario> lista;
   SQLiteDatabase db;
   String bd = "BdUsuarioServicios";
   String tabla = "create table if not exists usuario(id integer primary key autoincrement, nombre text, cedula text, pass text, monto integer)";


    //Metodo contructor
    public Utilidades(Context c){
        this.c=c;
        db=c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        db.execSQL(tabla);
        per = new Usuario();
    }

    //Metodos
    public boolean RegistrarUsuario(Usuario persona) {
//        Log.e("persona", "Persona"+persona.getCedula());
        if (buscar(persona.getCedula())==false){
            ContentValues values = new ContentValues();
            values.put("nombre", persona.getNombre());
            values.put("cedula", persona.getCedula());
            values.put("pass", persona.getPass());
            values.put("monto", persona.getMonto());

          Long idResultado = db.insert("usuario", null, values);
            return (idResultado > 0);
        } else {
            return false;
        }
    }

    public boolean buscar(String persona) {
        boolean rep = false;
        lista = SelectUsuario();
        for(Usuario us : lista){
            if(us.getCedula().equals(persona)){
                rep = true;
                break;
            }
        }
//            for (int i = 0; i <= lista.size(); i++) {
//                if (lista.get(i).getCedula().equals(persona)) {
//                    return true;
//                } else {
//                    break;
//                }
//            }
        return rep;
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
                u.setCedula(cr.getString(2));
                u.setPass(cr.getString(3));
                u.setMonto(cr.getInt(4));
                Lista.add(u);
            } while (cr.moveToNext());
        }
        return Lista;
    }

    public boolean login(String cedula, String pass){
        Cursor cr = db.rawQuery("select * from usuario", null);
        boolean respust =false;
        try{
            if(cr != null){
                while (cr.moveToNext()){
                    if((cr.getString(2)).equalsIgnoreCase(cedula) && (cr.getString(3)).equalsIgnoreCase(pass)){
                        respust= true;
                        break;
                    }
                }
            }

        }catch (Exception e){

        }
        return respust;
    }

    public Usuario getCorreo(String correo, String pass){
        lista = SelectUsuario();
        for(Usuario us:lista){
                if(us.getCedula().equals(correo) && us.getPass().equals(pass)){
                return us;
            }
        }
        return null;
    }
    public Usuario getId(int id){
        lista = SelectUsuario();
        for(Usuario us:lista){
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }

    public boolean ActualizarUsuario(Usuario persona){

        ContentValues values = new ContentValues();
        values.put("monto", persona.getMonto());

        int idResultado = db.update("usuario",values,"id="+persona.getId(),  null);
        return (idResultado > 0);

    }
}
