package com.example.lab1.Repository.Plato;

import android.app.Application;
import android.util.Log;

import com.example.lab1.Daos.PlatoDao;
import com.example.lab1.Tareas.BuscarPlatoById;
import com.example.lab1.Tareas.BuscarPlatos;
import com.example.lab1.model.Plato;

import java.util.List;

public class AppRepository implements OnPlatoResultCallback {
    private PlatoDao platoDao;
    private OnResultCallback callback;

    public AppRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
        callback = context;
    }

    public void insertar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.insertar(plato);
            }
        });
    }

    public void borrar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.borrar(plato);
            }
        });
    }

    public void actualizar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.actualizar(plato);
            }
        });
    }

    /*public void buscar(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }*/

    public void buscarTodos() {
        new BuscarPlatos(platoDao, this).execute();
    }

    @Override
    public void onResult(List<Plato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onResult(platos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
