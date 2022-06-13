package com.example.banco.retiro.presenter;

import com.example.banco.retiro.interfaces.InterfaceRetiro;
import com.example.banco.retiro.repository.Repository;

import org.json.JSONException;

public class PresenterRetiro implements InterfaceRetiro.presenter{
    InterfaceRetiro.view view;
    InterfaceRetiro.repositorio repositorio;

    public PresenterRetiro(InterfaceRetiro.view view) {
        this.view = view;
        this.repositorio = new Repository(this);
    }

    @Override
    public void retiro(String codiAut, String numeroCuenta, String tipoTrans, int monto, String token) throws JSONException {
        repositorio.retiro(codiAut, numeroCuenta, tipoTrans, monto, token);
    }

    @Override
    public void respuesta(String mensaje, boolean estatus) {
        view.respuesta(mensaje, estatus);
    }
}
