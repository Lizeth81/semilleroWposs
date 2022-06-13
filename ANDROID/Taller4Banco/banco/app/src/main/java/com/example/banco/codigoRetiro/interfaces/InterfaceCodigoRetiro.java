package com.example.banco.codigoRetiro.interfaces;

import com.example.banco.modelo.retiro.CodeRet;

import org.json.JSONException;

public interface InterfaceCodigoRetiro {
    interface view {
        void respuesta(String mensaje, CodeRet data, boolean estatus);
    }

    interface presenter {
        void numberBill(String numBill, String token) throws JSONException;
        void respuesta(String mensaje, CodeRet data, boolean estatus);
    }

    interface repositorio {
        void numberBill(String numeroCuenta, String token) throws JSONException;
    }
}
