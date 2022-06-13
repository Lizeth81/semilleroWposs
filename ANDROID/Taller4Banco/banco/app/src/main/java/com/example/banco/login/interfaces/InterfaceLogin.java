package com.example.banco.login.interfaces;

import com.example.banco.modelo.registro.Persona;

import org.json.JSONException;

public interface InterfaceLogin {

    interface view {
        void respuesta(boolean status, Persona data, String token);
    }
    interface presenter {
        void login(String correo, String pass) throws JSONException;
        void respuesta(boolean status, Persona data, String token);
    }
    interface repositorio {
        void login(String correo, String pass) throws JSONException;
    }
}
