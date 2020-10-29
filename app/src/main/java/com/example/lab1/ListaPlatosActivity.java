package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.model.AdapterListaPlatos;
import com.example.lab1.model.ListaPlatos;

import java.util.ArrayList;
import java.util.List;

public class ListaPlatosActivity extends AppCompatActivity{
    //PRUEBA
    List<ListaPlatos> listaPruebas;
    private RecyclerView rvPruebas;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    // FIN PRUEBA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_card_view);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        // PRUEBA
        rvPruebas = findViewById(R.id.rvPruebas);
        listaPruebas = new ArrayList<ListaPlatos>();
        listaPruebas.add(new ListaPlatos(R.drawable.pizza,"Pizza","Pizza casera con salsa de tomate, condimentada con finas hiernas.Con queso cremoso.","350","750"));
        listaPruebas.add(new ListaPlatos(R.drawable.tallarines,"Tallarines","Pasta casera con queso y salsa de tomates.","300","400"));
        listaPruebas.add(new ListaPlatos(R.drawable.pollo,"Pollo","Pollo a la parrilla con salsa de chimichurri.","450","450"));
        listaPruebas.add(new ListaPlatos(R.drawable.burger,"Hamburguesa","Hamburguesa con medallon de carne con tomate y lechuga.","480","1500"));
        listaPruebas.add(new ListaPlatos(R.drawable.milaconpure,"Milanesa con Pure","Milanesa de caballo con pure de papas.","275","650"));
        listaPruebas.add(new ListaPlatos(R.drawable.papasfritascheddard,"Papas fritas con cheddard y panceta","Papas fritas con salsa cheddard y fetas de panceta.","230","740"));
        listaPruebas.add(new ListaPlatos(R.drawable.sushi,"Sushi","Rol de sushi con relleno de verduras y salmon.","1300","350"));
        listaPruebas.add(new ListaPlatos(R.drawable.tacos,"Tacos","Tacos con tapas caseras de harina , relleno con carne cortada a cuchillo y verduras salteadas.","175","684"));

        rvPruebas.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rvPruebas.setLayoutManager(lManager);
        adapter = new AdapterListaPlatos(listaPruebas);
        rvPruebas.setAdapter(adapter);
        // FIN PRUEBA


        recibirDatosPlato();

    }
    public void recibirDatosPlato(){

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String datoTitulo = extras.getString("titulo");
            String datoDescripcion = extras.getString("descripcion");
            Double datoPrecio = extras.getDouble("precio");
            String datoCalorias = extras.getString("calorias");
            listaPruebas.add(new ListaPlatos(R.drawable.plato,datoTitulo,datoDescripcion,datoPrecio.toString(),datoCalorias));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch(item.getItemId()){
            case R.id.itemRegistrar:
                Toast.makeText(this, "Selecciono Registrarse", Toast.LENGTH_SHORT).show();
                i = new Intent(ListaPlatosActivity.this, AltaUsuarioActivity.class);
                startActivity(i);
                break;

            case R.id.itemCrear:
                Toast.makeText(this, "Selecciono Crear Item", Toast.LENGTH_SHORT).show();
                i = new Intent(ListaPlatosActivity.this, AltaItemActivity.class);
                startActivity(i);
                break;

            case R.id.itemListar:
                Toast.makeText(this, "Selecciono ver Lista de Items", Toast.LENGTH_SHORT).show();
                i = new Intent(ListaPlatosActivity.this, ListaPlatosActivity.class);
                startActivity(i);
                break;

            case R.id.altaPedido:
                Toast.makeText(this, "Selecciono Realizar Pedido", Toast.LENGTH_SHORT).show();
                i = new Intent(ListaPlatosActivity.this, PedidoActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}
