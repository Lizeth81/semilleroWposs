package com.example.banco.transaccion.interfaces;

import com.example.banco.modelo.Transaction.Usuario;

import org.json.JSONException;

import java.util.ArrayList;

public interface InterfaceTransaccion {
    interface view {
        void respuesta(String mensaje, ArrayList<Usuario> data, boolean estado);
    }

    interface presenter {
        void transaccion(String numeroCuenta, String token) throws JSONException;
        void respuesta(String mensaje, ArrayList<Usuario> data, boolean estado);
    }

    interface repositorio {
        void transaccion(String numeroCuenta, String token) throws JSONException;
    }
}
