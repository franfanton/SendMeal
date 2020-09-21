package com.example.lab1;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.model.AdapterPruebaLista;
import com.example.lab1.model.PruebaLista;

import java.util.ArrayList;
import java.util.List;

public class PruebaActivity extends AppCompatActivity{
    //PRUEBA
    List<PruebaLista> listaPruebas;
    private RecyclerView rvPruebas;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    // FIN PRUEBA
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_card_view);

        // PRUEBA
        rvPruebas = findViewById(R.id.rvPruebas);
        listaPruebas = new ArrayList<PruebaLista>();
        listaPruebas.add(new PruebaLista(R.drawable.vale,"Pizza","120"));
        listaPruebas.add(new PruebaLista(R.drawable.vale,"Pasta","135"));
        listaPruebas.add(new PruebaLista(R.drawable.vale,"Canelones","270"));

        rvPruebas.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rvPruebas.setLayoutManager(lManager);
        adapter = new AdapterPruebaLista(listaPruebas);
        rvPruebas.setAdapter(adapter);
        // FIN PRUEBA
    }
}
