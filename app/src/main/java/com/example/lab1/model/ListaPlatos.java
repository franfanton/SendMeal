package com.example.lab1.model;

public class ListaPlatos {
    private int  imagen;
    private String titulo;
    private String descripcion;
    private String precio;
    private String calorias;
    private String unidades;

    public ListaPlatos(int  imagen, String titulo, String descripcion, String precio, String calorias, String unidades) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.unidades = unidades;
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
