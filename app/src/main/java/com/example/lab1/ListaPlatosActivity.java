package com.example.lab1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.lab1.Repository.Plato.AppRepository;
import com.example.lab1.Servicios.Plato.PlatoService;
import com.example.lab1.model.AdapterListaPlatos;
import com.example.lab1.model.Plato;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.recyclerview.widget.RecyclerView.*;

public class ListaPlatosActivity extends AppCompatActivity implements AppRepository.OnResultCallback {
    //PRUEBA
    List<Plato> listaPruebas;
    private int CODIGO_ACTIVIDAD = -1;
    private Button btnAgregarPlato;
    String titulo, precio, unidades;
    // FIN PRUEBA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_card_view);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        // LAB 4
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3001/")
                // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PlatoService platoService = retrofit.create(PlatoService.class);
        Call<List<Plato>> callPlatos = platoService.getPlatoList();
        callPlatos.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if (response.code() == 200) {
                    Log.d("DEBUG", "Returno Exitoso");
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("DEBUG", "Returno Fallido");
            }
        });


        AppRepository repository = new AppRepository(this.getApplication());
        // FIN LAB 4

        // PRUEBA
        final RecyclerView rvPruebas = findViewById(R.id.rvPruebas);
        //listaPruebas = (List<Plato>)callPlatos;
        listaPruebas = new ArrayList<>();
        listaPruebas.add(new Plato(R.drawable.pizza,"Pizza","Pizza casera con salsa de tomate, finas hierbas y queso cremoso.","350","750", "0"));
        listaPruebas.add(new Plato(R.drawable.tallarines,"Tallarines","Pasta casera con queso y salsa de tomates.","300","400", "0"));
        listaPruebas.add(new Plato(R.drawable.pollo,"Pollo","Pollo a la parrilla con salsa de chimichurri.","450","450", "0"));
        listaPruebas.add(new Plato(R.drawable.burger,"Hamburguesa","Hamburguesa con medallon de carne con tomate y lechuga.","480","1500", "0"));
        listaPruebas.add(new Plato(R.drawable.milaconpure,"Milanesa con Pure","Milanesa de caballo con pure de papas.","275","650", "0"));
        listaPruebas.add(new Plato(R.drawable.papasfritascheddard,"Papas fritas con chedar y panceta","Papas fritas con salsa chedar y panceta.","230","740", "0"));
        listaPruebas.add(new Plato(R.drawable.sushi,"Sushi","Rol de sushi con relleno de verduras y salmon.","1300","350", "0"));
        listaPruebas.add(new Plato(R.drawable.tacos,"Tacos","Rellenos con carne cortada a cuchillo y verduras salteadas.","175","684", "0"));

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
//            getActivity(extras, CODIGO_ACTIVIDAD, adapter, rvPruebas,platoService);
            getActivity(extras, CODIGO_ACTIVIDAD, adapter, rvPruebas);
        }
        else{
            adapter = new AdapterListaPlatos(listaPruebas, CODIGO_ACTIVIDAD);
            rvPruebas.setAdapter(adapter);
        }
    }
    //LAB 4
    @Override
    public void onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalize
        Toast.makeText(ListaPlatosActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
    }
    // FIN LAB 4

    @Override
    public void finish() {
        Intent datos = new Intent();
        datos.putExtra("titulo",getTitulo());
        datos.putExtra("precio",getPrecio());
        datos.putExtra("unidades",getUnidades());
        setResult(RESULT_OK, datos);
        super.finish();
    }

    public void getActivity(Bundle extras, final int CODIGO_ACTIVIDAD, final AdapterListaPlatos adapter, final RecyclerView rvPruebas){
            switch (CODIGO_ACTIVIDAD){
                case 0:
                    String datoTitulo = extras.getString("titulo");
                    String datoDescripcion = extras.getString("descripcion");
                    String datoPrecio = extras.getString("precio");
                    String datoCalorias = extras.getString("calorias");
                    listaPruebas.add(new Plato(R.drawable.plato,datoTitulo,datoDescripcion, datoPrecio,datoCalorias,null));
                    //Plato nuevoPlato = new Plato(R.drawable.plato, datoTitulo,datoDescripcion, datoPrecio,datoCalorias,"0");
                    //Call<Plato> createPlato = platoService.createPlato(nuevoPlato);
                    break;

                case 1:
                   adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setTitulo(listaPruebas.get(rvPruebas.getChildAdapterPosition(view)).getTitulo());
                            setPrecio(listaPruebas.get(rvPruebas.getChildAdapterPosition(view)).getPrecio());
                            setUnidades(listaPruebas.get(rvPruebas.getChildAdapterPosition(view)).getUnidades());
                            if (unidades.equals("0")){
                                Toast.makeText(getApplicationContext(),"Debe encargar al menos una unidad. Unidades: "+unidades,Toast.LENGTH_SHORT).show();
                            }
                            else {
                                finish();
                            }
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

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
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
