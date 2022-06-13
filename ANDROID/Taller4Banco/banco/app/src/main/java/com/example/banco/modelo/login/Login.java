package com.example.banco.modelo.login;

import com.example.banco.modelo.registro.Factura;
import com.example.banco.modelo.registro.Persona;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login {
    private String msg;
    private Persona data;
    private boolean status;
    private String token;

    public Login(String msg, Persona data, boolean status, String token) {
        this.msg = msg;
        this.data = data;
        this.status = status;
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Persona getData() {
        return data;
    }

    public void setData(Persona data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
