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
        listaPruebas.add(new PruebaLista(R.drawable.pizza,"Pizza","350","750"));
        listaPruebas.add(new PruebaLista(R.drawable.tallarines,"Tallarines","300","400"));
        listaPruebas.add(new PruebaLista(R.drawable.pollo,"Pollo","450","450"));
        listaPruebas.add(new PruebaLista(R.drawable.burger,"Hamburguesa","480","1500"));
        listaPruebas.add(new PruebaLista(R.drawable.milaconpure,"Milanesa con Pure","275","650"));
        listaPruebas.add(new PruebaLista(R.drawable.papasfritascheddard,"Papas fritas con cheddard y panceta","230","740"));
        listaPruebas.add(new PruebaLista(R.drawable.sushi,"Sushi","1300","350"));
        listaPruebas.add(new PruebaLista(R.drawable.tacos,"Tacos","175","684"));

        rvPruebas.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rvPruebas.setLayoutManager(lManager);
        adapter = new AdapterPruebaLista(listaPruebas);
        rvPruebas.setAdapter(adapter);
        // FIN PRUEBA
    }
}
