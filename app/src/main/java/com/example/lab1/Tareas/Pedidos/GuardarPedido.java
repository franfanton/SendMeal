package com.example.lab1.Tareas.Pedidos;

import android.os.AsyncTask;

import com.example.lab1.Daos.PedidoDao;
import com.example.lab1.Helpers.Callback;
import com.example.lab1.model.Pedido;


public class GuardarPedido extends AsyncTask<Void, Void, String> {
    PedidoDao pedidoDAO;
    Pedido nuevoPedido;
    Callback<String> callback;

    public GuardarPedido(PedidoDao pedidoDAO, Callback<String> callback, Pedido nuevoPedido) {
        this.pedidoDAO = pedidoDAO;
        this.nuevoPedido = nuevoPedido;
        this.callback = callback;
    };

    @Override
    protected String doInBackground(Void... Void) {
        pedidoDAO.insertar(nuevoPedido);
        return "Exito";
    };

    @Override
    protected void onPostExecute(String result) {
        callback.onCallback("OK");
    }
}
