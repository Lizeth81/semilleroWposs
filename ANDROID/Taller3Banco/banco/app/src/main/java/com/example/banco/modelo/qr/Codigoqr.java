package com.example.banco.modelo.qr;

public class Codigoqr {
    private String mng;
    private String img;
    private boolean estado;

    public Codigoqr(String mng, String img, boolean estado) {
        this.mng = mng;
        this.img = img;
        this.estado = estado;
    }

    public String getMng() {
        return mng;
    }

    public void setMng(String mng) {
        this.mng = mng;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
