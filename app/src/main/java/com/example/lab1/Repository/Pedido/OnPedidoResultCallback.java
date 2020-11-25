package com.example.lab1.Repository.Pedido;

import com.example.lab1.model.Plato;

import java.util.List;

public interface OnPedidoResultCallback {
    void onResult(List<Plato> plato);
}
