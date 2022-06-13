package com.example.banco.registro.presenter;

import com.example.banco.registro.interfaces.InterfaceRegistro;
import com.example.banco.registro.repository.Repository;

import org.json.JSONException;

public class PresenterRegistro implements InterfaceRegistro.presenter {

    InterfaceRegistro.view  view;
    InterfaceRegistro.repositorio repositorio;

    public PresenterRegistro(InterfaceRegistro.view view) {
        this.view = view;
        repositorio = new Repository(this);
    }

    @Override
    public void guardar(String nombre, String identificacion, String email, String pass, String estate) throws JSONException {
    repositorio.guardar(nombre, identificacion, email, pass, estate);
    }

    @Override
    public void respuesta(String mensaje, boolean estatus) {
    view.respuesta(mensaje, estatus);
    }
}
