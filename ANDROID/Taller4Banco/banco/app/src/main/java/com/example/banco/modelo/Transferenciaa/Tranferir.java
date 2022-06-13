package com.example.banco.modelo.Transferenciaa;

public class Tranferir {
    private String msg;
    private boolean status;

    public Tranferir(String msg, boolean status) {
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
