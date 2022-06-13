package com.example.banco.codigoQR.presenter;

import com.example.banco.codigoQR.interfaces.InterfaceQr;
import com.example.banco.codigoQR.repository.RepositoryQr;

import org.json.JSONException;

public class PresenterQr implements InterfaceQr.presenter {
    InterfaceQr.view view;
    InterfaceQr.repositorio repositorio;


    public PresenterQr(InterfaceQr.view view) {
        this.view = view;
        repositorio = new RepositoryQr(this);
    }

    @Override
    public void codigoQr(String numeroCuenta, String token) throws JSONException {
        repositorio.codigoQr(numeroCuenta, token);
    }

    @Override
    public void respuesta(String mensaje, String img, boolean estado) {
        view.respuesta(mensaje, img, estado);
    }
}
