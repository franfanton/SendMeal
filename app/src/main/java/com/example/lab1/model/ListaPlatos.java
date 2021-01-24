package com.example.lab1.model;

import android.widget.ImageView;

public class ListaPlatos {
    private ImageView imagen;
    private String titulo;
    private String descripcion;
    private String precio;
    private String calorias;
    private String unidades;

    public ListaPlatos(ImageView  imagen, String titulo, String descripcion, String precio, String calorias, String unidades) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.unidades = unidades;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }
}
