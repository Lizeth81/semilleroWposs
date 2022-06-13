package com.example.banco.codigoRetiro.presenter;

import com.example.banco.codigoRetiro.interfaces.InterfaceCodigoRetiro;
import com.example.banco.codigoRetiro.repository.Repository;
import com.example.banco.modelo.retiro.CodeRet;

import org.json.JSONException;

public class PresenterCodigoRetiro implements InterfaceCodigoRetiro.presenter{

    InterfaceCodigoRetiro.view view;
    InterfaceCodigoRetiro.repositorio repositorio;

    public PresenterCodigoRetiro(InterfaceCodigoRetiro.view view) {
        this.view = view;
        this.repositorio = new Repository(this);
    }

    @Override
    public void numberBill(String numBill, String token) throws JSONException {
        repositorio.numberBill(numBill, token);
    }

    @Override
    public void respuesta(String mensaje, CodeRet data, boolean estatus) {
        view.respuesta(mensaje, data, estatus);
    }
}
