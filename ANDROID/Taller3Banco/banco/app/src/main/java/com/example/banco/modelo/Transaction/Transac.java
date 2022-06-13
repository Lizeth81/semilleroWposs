package com.example.banco.modelo.Transaction;

import java.util.ArrayList;

public class Transac {
    private String msg;
    private ArrayList<Usuario> data;
    private boolean status;

    public Transac(String msg, ArrayList<Usuario> data, boolean status) {
        this.msg = msg;
        this.data = data;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Usuario> getData() {
        return data;
    }

    public void setData(ArrayList<Usuario> data) {
        this.data = data;
    }

    public boolean isstatus() {
        return status;
    }

    public void setstatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transac{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
