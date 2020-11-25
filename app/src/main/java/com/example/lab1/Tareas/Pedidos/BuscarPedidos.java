package com.example.lab1.Tareas.Pedidos;

import android.os.AsyncTask;

import com.example.lab1.Daos.PedidoDao;
import com.example.lab1.Helpers.Callback;
import com.example.lab1.model.Pedido;

import java.util.List;

// android.os.AsyncTask<Params, Progress, Result>
public class BuscarPedidos extends AsyncTask<String, Void, List<Pedido>> {

    private PedidoDao dao;
    private Callback callback;

    public BuscarPedidos(PedidoDao dao, Callback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        List<Pedido> pedidos = dao.buscarTodos();
        return pedidos;
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        callback.onCallback(pedidos);
    }
}