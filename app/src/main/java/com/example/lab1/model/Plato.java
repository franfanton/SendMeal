package com.example.lab1.model;

public class Plato {
    //preguntar si va un id
    private String titulo;
    private String descripcion;
    private Double precio;
    private String calorias; //integer

    public Plato(String titulo, String descripcion, Double precio, String calorias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
    }

    //getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getCalorias() {
        return calorias;
    }

    //setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }
}