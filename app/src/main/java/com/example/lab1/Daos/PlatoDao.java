package com.example.lab1.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab1.model.Plato;

import java.util.List;

@Dao
public interface PlatoDao {
    @Insert
    void insertar(Plato plato);

    @Delete
    void borrar(Plato plato);

    @Update
    void actualizar(Plato plato);

    @Query("SELECT * FROM plato WHERE id = :id LIMIT 1")
    Plato buscar(String id);

    @Query("SELECT * FROM plato")
    List<Plato> buscarTodos();

}
