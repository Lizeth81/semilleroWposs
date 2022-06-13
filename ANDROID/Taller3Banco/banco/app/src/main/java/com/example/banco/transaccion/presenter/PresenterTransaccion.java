package com.example.banco.transaccion.presenter;

import com.example.banco.modelo.Transaction.Usuario;
import com.example.banco.transaccion.interfaces.InterfaceTransaccion;
import com.example.banco.transaccion.repository.RepositoryTrans;

import org.json.JSONException;

import java.util.ArrayList;

public class PresenterTransaccion implements InterfaceTransaccion.presenter {
    InterfaceTransaccion.view view;
    InterfaceTransaccion.repositorio repositorio;

    public PresenterTransaccion(InterfaceTransaccion.view view) {
        this.view = view;
        repositorio = new RepositoryTrans(this);
    }

    @Override
    public void transaccion(String numeroCuenta, String token) throws JSONException {
        repositorio.transaccion(numeroCuenta, token);
    }

    @Override
    public void respuesta(String mensaje, ArrayList<Usuario> data, boolean estado) {
        view.respuesta(mensaje, data, estado);
    }
}
