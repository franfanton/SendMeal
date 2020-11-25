package com.example.lab1.Repository.Pedido;

import android.app.Application;
import android.util.Log;

import com.example.lab1.Daos.PedidoDao;
import com.example.lab1.Helpers.Callback;
import com.example.lab1.Repository.Pedido.AppDatabase;
import com.example.lab1.Tareas.Pedidos.BuscarPedidos;
import com.example.lab1.Tareas.Pedidos.GuardarPedido;
import com.example.lab1.model.Pedido;

import java.util.List;

public class AppRepository {
    private PedidoDao pedidoDao;
    private Callback callback;

    public AppRepository(Application application){
        com.example.lab1.Repository.Pedido.AppDatabase db = com.example.lab1.Repository.Pedido.AppDatabase.getInstance(application);
        pedidoDao = db.pedidoDao();
    }


    public void addPedido(Pedido pedido, Callback<String> callback) {
        (new GuardarPedido(this.pedidoDao, callback, pedido)).execute();
    };

    public void borrar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.borrar(pedido);
            }
        });
    }

    public void actualizar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.actualizar(pedido);
            }
        });
    }

    /*public void buscar(String id) {
        new BuscarPedidoById(pedidoDao, this).execute(id);
    }*/

    public void buscarTodos(Callback<String> callback) {
        new BuscarPedidos(pedidoDao, callback).execute();
    }

    public void onResult(List<Pedido> pedidos) {
        Log.d("DEBUG", "Pedido found");
        callback.onCallback(pedidos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
