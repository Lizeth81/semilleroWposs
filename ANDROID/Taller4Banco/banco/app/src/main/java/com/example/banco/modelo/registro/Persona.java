package com.example.banco.modelo.registro;

public class Persona {
    private int user_id;
    private String user_name;
    private String user_identification;
    private String user_email;
   private Factura bill;

    public Persona() {
    }

    public Persona(int user_id, String user_name, String user_identification, String user_email, Factura bill) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_identification = user_identification;
        this.user_email = user_email;
        this.bill = bill;
    }

    public Factura getBill() {
        return bill;
    }
    public void setBill(Factura bill) {
        this.bill = bill;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_identification() {
        return user_identification;
    }

    public void setUser_identification(String user_identification) {
        this.user_identification = user_identification;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


}
