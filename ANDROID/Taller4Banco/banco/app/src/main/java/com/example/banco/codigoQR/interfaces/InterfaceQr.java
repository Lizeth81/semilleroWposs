package com.example.banco.codigoQR.interfaces;

import org.json.JSONException;

public interface InterfaceQr {
    interface view {
        void respuesta(String mensaje, String img, boolean estado);
    }

    interface presenter {
        void codigoQr(String numeroCuenta, String token) throws JSONException;
        void respuesta(String mensaje, String img, boolean estado);
    }

    interface repositorio {
        void codigoQr(String numeroCuenta, String token) throws JSONException;
    }
}
