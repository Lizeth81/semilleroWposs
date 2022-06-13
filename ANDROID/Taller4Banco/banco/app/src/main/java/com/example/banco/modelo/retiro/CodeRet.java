package com.example.banco.modelo.retiro;

public class CodeRet {
    public String code;
    public String numberBill;

    public CodeRet(String code, String numberBill) {
        this.code = code;
        this.numberBill = numberBill;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumberBill() {
        return numberBill;
    }

    public void setNumberBill(String numberBill) {
        this.numberBill = numberBill;
    }
}
