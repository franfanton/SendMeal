package com.example.lab1.Repository.Pedido;

import com.example.lab1.model.Pedido;

import java.util.List;

public interface OnPedidoResultCallback {
    void onResult(List<Pedido> pedido);
}
