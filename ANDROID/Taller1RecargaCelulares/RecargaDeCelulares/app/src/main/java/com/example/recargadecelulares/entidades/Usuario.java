package com.example.recargadecelulares.entidades;

public class Usuario {
    private Integer id;
    private String nombre;
    private String correo;
    private String password;

    public Usuario() {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean camposVacios(){
        if(nombre.equals("") && correo.equals("") && password.equals("")){
            return false;
        }else{
            return true;
        }

    }
}
