package com.example.lab1.model;

public class Pedido {
    private String correo;
    private String direccion;
    private String tipoEnvio;

    public Pedido(String correo, String direccion, String tipoEnvio) {
        this.correo = correo;
        this.direccion = direccion;
        this.tipoEnvio = tipoEnvio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }
}
