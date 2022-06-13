package com.example.banco.registro.interfaces;

import org.json.JSONException;

public interface InterfaceRegistro {

    interface view {
        void respuesta(String mensaje, boolean estatus);
    }
    interface presenter {

        void guardar(String nombre, String identificacion, String email, String pass, String estate) throws JSONException;
        void respuesta(String mensaje,  boolean estatus);
    }
    interface repositorio {

        void guardar(String nombre, String identificacion, String email, String pass, String estate) throws JSONException;

    }


}
