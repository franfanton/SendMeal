package com.example.lab1.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab1.model.Pedido;

import java.util.List;

@Dao
public interface PedidoDao {
    @Insert
    void insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedido WHERE id = :id LIMIT 1")
    Pedido buscar(String id);

    @Query("SELECT * FROM pedido")
    List<Pedido> buscarTodos();

}
