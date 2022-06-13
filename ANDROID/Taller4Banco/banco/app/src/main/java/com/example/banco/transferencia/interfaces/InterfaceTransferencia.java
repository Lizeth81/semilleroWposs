package com.example.banco.transferencia.interfaces;

import com.example.banco.modelo.retiro.CodeRet;

import org.json.JSONException;

public interface InterfaceTransferencia {
    interface view {
        void respuesta(String mensaje, boolean estatus);
    }

    interface presenter {
        void transfer(String numeroCuenta, String tipoTranferencia, int monto, String cuentaDestiny, String token) throws JSONException;
        void respuesta(String mensaje, boolean estatus);
    }

    interface repositorio {
        void transfer(String numeroCuenta, String tipoTranferencia, int monto, String cuentaDestiny, String token) throws JSONException;
    }
}
