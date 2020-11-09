package com.example.lab1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.model.AdapterListaPlatos;
import com.example.lab1.model.ListaPlatos;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class ListaPlatosActivity extends AppCompatActivity{
    //PRUEBA
    List<ListaPlatos> listaPruebas;
    private int CODIGO_ACTIVIDAD = -1;
    private Button btnAgregarPlato;
    String titulo;
    String precio;

    // FIN PRUEBA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_card_view);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        // PRUEBA
        final RecyclerView rvPruebas = findViewById(R.id.rvPruebas);
        listaPruebas = new ArrayList<>();
        listaPruebas.add(new ListaPlatos(R.drawable.pizza,"Pizza","Pizza casera con salsa de tomate, finas hierbas y queso cremoso.","350","750"));
        listaPruebas.add(new ListaPlatos(R.drawable.tallarines,"Tallarines","Pasta casera con queso y salsa de tomates.","300","400"));
        listaPruebas.add(new ListaPlatos(R.drawable.pollo,"Pollo","Pollo a la parrilla con salsa de chimichurri.","450","450"));
        listaPruebas.add(new ListaPlatos(R.drawable.burger,"Hamburguesa","Hamburguesa con medallon de carne con tomate y lechuga.","480","1500"));
        listaPruebas.add(new ListaPlatos(R.drawable.milaconpure,"Milanesa con Pure","Milanesa de caballo con pure de papas.","275","650"));
        listaPruebas.add(new ListaPlatos(R.drawable.papasfritascheddard,"Papas fritas con chedar y panceta","Papas fritas con salsa chedar y panceta.","230","740"));
        listaPruebas.add(new ListaPlatos(R.drawable.sushi,"Sushi","Rol de sushi con relleno de verduras y salmon.","1300","350"));
        listaPruebas.add(new ListaPlatos(R.drawable.tacos,"Tacos","Rellenos con carne cortada a cuchillo y verduras salteadas.","175","684"));

        rvPruebas.setHasFixedSize(true);
        LayoutManager lManager = new LinearLayoutManager(this);
        rvPruebas.setLayoutManager(lManager);
        // FIN PRUEBA

        Bundle extras = getIntent().getExtras();
        final AdapterListaPlatos adapter;
        if(extras != null) {
            CODIGO_ACTIVIDAD = extras.getInt("CODIGO_ACTIVIDAD");
            adapter = new AdapterListaPlatos(listaPruebas, CODIGO_ACTIVIDAD);
            rvPruebas.setAdapter(adapter);
            getActivity(extras, CODIGO_ACTIVIDAD, adapter, rvPruebas);
        }
        else{
            adapter = new AdapterListaPlatos(listaPruebas, CODIGO_ACTIVIDAD);
            rvPruebas.setAdapter(adapter);
        }
    }

    @Override
    public void finish() {
            Intent datos = new Intent();
            String untitulo;
            untitulo = getTitulo();
            datos.putExtra("retorno",getTitulo());
            setResult(RESULT_OK, datos);
            super.finish();
    }

    public void getActivity(Bundle extras, final int CODIGO_ACTIVIDAD, AdapterListaPlatos adapter, final RecyclerView rvPruebas){
            switch (CODIGO_ACTIVIDAD){
                case 0:
                    String datoTitulo = extras.getString("titulo");
                    String datoDescripcion = extras.getString("descripcion");
                    double datoPrecio = extras.getDouble("precio");
                    String datoCalorias = extras.getString("calorias");
                    listaPruebas.add(new ListaPlatos(R.drawable.plato,datoTitulo,datoDescripcion, Double.toString(datoPrecio),datoCalorias));
                    break;

                case 1:

                    btnAgregarPlato = findViewById(R.id.botonAgregarPlato);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setTitulo(listaPruebas.get(rvPruebas.getChildAdapterPosition(view)).getTitulo());
                            Toast.makeText(ListaPlatosActivity.this,"plato"+titulo,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"TITULO: "+titulo,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
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
