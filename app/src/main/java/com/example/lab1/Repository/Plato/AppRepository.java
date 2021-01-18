package com.example.lab1.Repository.Plato;

import android.app.Application;
import android.util.Log;

import com.example.lab1.Daos.PlatoDao;
import com.example.lab1.Helpers.Callback;
import com.example.lab1.Tareas.Platos.BuscarPlatoById;
import com.example.lab1.Tareas.Platos.BuscarPlatos;
import com.example.lab1.Tareas.Platos.GuardarPlato;
import com.example.lab1.model.Plato;

import java.util.List;

public class AppRepository {
    private PlatoDao platoDao;
    private Callback callback;

    public AppRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
    }

//    public void insertar(final Plato plato){
//        AppDatabase.databaseWriteExecutor.execute(() -> platoDao.insertar(plato));
//    }

    public void addPlato(Plato plato, Callback<String> callback) {
        (new GuardarPlato(this.platoDao, callback, plato)).execute();
    };

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

//    public void buscar(String id) {
//        new BuscarPlatoById(platoDao, this).execute(id);
//    }

    public void buscarTodos(Callback<Plato> callback) {
        new BuscarPlatos(platoDao, callback).execute();
    }

    public void onResult(List<Plato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onCallback(platos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
