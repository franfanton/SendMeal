package com.example.lab1.model;

public class Usuario {
    private long id;
    private String nombre;
    private String clave;
    private String email;
    private Double credito;
    private Tarjeta tarjeta;
    private CuentaBancaria cuentaBancaria;

    public Usuario(String nombre, String clave, String email, Double credito, Tarjeta tarjeta, CuentaBancaria cuentaBancaria) {
        id = System.currentTimeMillis();
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.credito = credito;
        this.tarjeta = tarjeta;
        this.cuentaBancaria = cuentaBancaria;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getEmail() {
        return email;
    }

    public Double getCredito() {
        return credito;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }
}