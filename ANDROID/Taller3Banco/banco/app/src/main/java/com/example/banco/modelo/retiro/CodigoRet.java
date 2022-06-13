package com.example.banco.modelo.retiro;

public class CodigoRet {
    public String msg;
    public CodeRet data;
    public String status;

    public CodigoRet(String msg, CodeRet data, String status) {
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

    public CodeRet getData() {
        return data;
    }

    public void setData(CodeRet data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
