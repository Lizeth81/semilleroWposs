package com.example.banco.transferencia.presenter;

import com.example.banco.transferencia.interfaces.InterfaceTransferencia;
import com.example.banco.transferencia.repository.Repository;

import org.json.JSONException;

public class PresenterTransferencia implements InterfaceTransferencia.presenter {
    InterfaceTransferencia.view view;
    InterfaceTransferencia.repositorio repositorio;

    public PresenterTransferencia(InterfaceTransferencia.view view) {
        this.view = view;
        this.repositorio = new Repository(this);
    }

    @Override
    public void transfer(String numeroCuenta, String tipoTranferencia, int monto, String cuentaDestiny, String token) throws JSONException {
        repositorio.transfer(numeroCuenta, tipoTranferencia, monto, cuentaDestiny, token);
    }

    @Override
    public void respuesta(String mensaje, boolean estatus) {
        view.respuesta(mensaje, estatus);

    }
}
