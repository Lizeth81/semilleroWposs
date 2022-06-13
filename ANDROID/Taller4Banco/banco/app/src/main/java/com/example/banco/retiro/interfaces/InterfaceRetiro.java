package com.example.banco.retiro.interfaces;

import org.json.JSONException;

public interface InterfaceRetiro {
    interface view {
        void respuesta(String mensaje, boolean estatus);
    }

    interface presenter {
        void retiro(String codeAut, String numeroCuenta, String tipoTrans, int monto, String token) throws JSONException;
        void respuesta(String mensaje, boolean estatus);
    }

    interface repositorio {
        void retiro(String codeAut, String numeroCuenta, String tipoTrans, int monto, String token) throws JSONException;
    }
}
