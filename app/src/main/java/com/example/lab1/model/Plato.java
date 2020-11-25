package com.example.lab1.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plato {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String titulo;
    private String descripcion;
    private String precio;
    private String calorias;
    private int imagen;
    private String unidades;

    public Plato(int imagen, String titulo, String descripcion, String precio, String calorias, String unidades) {

        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        this.unidades = unidades;

    }


    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    //getters
    public Long getId() { return id; }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public String getCalorias() {
        return calorias;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }
}