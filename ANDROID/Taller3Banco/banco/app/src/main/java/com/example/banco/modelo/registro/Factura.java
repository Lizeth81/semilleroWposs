package com.example.banco.modelo.registro;

public class Factura {
    private String bill_number;
    private int bill_amount;

    public Factura(String bill_number, int bill_amount) {
        this.bill_number = bill_number;
        this.bill_amount = bill_amount;
    }

    public String getBill_number() {
        return bill_number;
    }

    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public int getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(int bill_amount) {
        this.bill_amount = bill_amount;
    }
}
