package com.example.banco.login.presenter;

import com.example.banco.login.interfaces.InterfaceLogin;
import com.example.banco.login.reposiroty.Repository;
import com.example.banco.modelo.registro.Persona;

import org.json.JSONException;

public class PresenterLogin implements InterfaceLogin.presenter {

    InterfaceLogin.view view;
    InterfaceLogin.repositorio repositorio;

    public PresenterLogin(InterfaceLogin.view view) {
        this.view = view;
        repositorio = new Repository(this);
    }

    @Override
    public void login(String correo, String pass) throws JSONException {
       repositorio.login(correo, pass);
    }

    @Override
    public void respuesta(boolean status, Persona data, String token) {
        view.respuesta(status, data, token);
    }

}
