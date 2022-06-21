package com.example.bitmap.modelo;

public class ItemByte {
    private String nombre;
    private String estado;
    private String nuevaCadena;

    public ItemByte(String nombre, String estado, String nuevaCadena) {
        this.nombre = nombre;
        this.estado = estado;
        this.nuevaCadena = nuevaCadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNuevaCadena() {
        return nuevaCadena;
    }

    public void setNuevaCadena(String nuevaCadena) {
        this.nuevaCadena = nuevaCadena;
    }
}
