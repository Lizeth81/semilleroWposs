package com.example.banco.modelo.registro;

public class RegistroModel {
    private String msg;
    private boolean status;

    public RegistroModel(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
