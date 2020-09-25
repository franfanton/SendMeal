package com.example.lab1.model;

public class PruebaLista {
    private int  imagen;
    private String titulo;
    private String precio;
    private String calorias;

    public PruebaLista(int  imagen, String titulo, String precio, String calorias) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.precio = precio;
        this.calorias = calorias;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }
}
