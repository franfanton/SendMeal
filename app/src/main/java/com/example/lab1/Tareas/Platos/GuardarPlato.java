package com.example.lab1.Tareas.Platos;

import android.os.AsyncTask;

import com.example.lab1.Daos.PlatoDao;
import com.example.lab1.Helpers.Callback;
import com.example.lab1.model.Plato;


public class GuardarPlato extends AsyncTask<Void, Void, String> {
    PlatoDao platoDAO;
    Plato nuevoPlato;
    Callback<String> callback;

    public GuardarPlato(PlatoDao platoDAO, Callback<String> callback, Plato nuevoPlato) {
        this.platoDAO = platoDAO;
        this.nuevoPlato = nuevoPlato;
        this.callback = callback;
    };

    @Override
    protected String doInBackground(Void... Void) {
        platoDAO.insertar(nuevoPlato);
        return "Exito";
    };

    @Override
    protected void onPostExecute(String result) {
        callback.onCallback("OK");
    }
}
