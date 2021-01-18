package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.Repository.Plato.AppRepository;
import com.example.lab1.Servicios.Plato.PlatoService;
import com.example.lab1.model.Plato;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaItemActivity extends AppCompatActivity {
    private TextView nuevoPlato;
    private EditText tituloPlato, descripcionPlato, precioPlato, caloriasPlato;
    private Double precioDouble;
    public static int CODIGO_ACTIVIDAD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_item);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        Button guardar = findViewById(R.id.botonGuardarPlato);

        tituloPlato = findViewById(R.id.tituloPlato);
        descripcionPlato = findViewById(R.id.descripcionPlato);
        precioPlato = findViewById(R.id.precioPlato);
        caloriasPlato = findViewById(R.id.caloriasPlato);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
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

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent i = new Intent(CrearItemActivity.this, PruebaActivity.this);


                if (tituloPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo título esta vacío.", Toast.LENGTH_SHORT).show();
                } else if (descripcionPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo descripción esta vacío.", Toast.LENGTH_SHORT).show();
                } else if (precioPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo precio esta vacío.", Toast.LENGTH_SHORT).show();
                } else if ((precioPlato.getText().toString().equals("0")) ){
                    Toast.makeText(getApplicationContext(), "El precio debe ser mayor a $0.", Toast.LENGTH_SHORT).show();
                } else if (caloriasPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo calorías esta vacío.", Toast.LENGTH_SHORT).show();
                } else {
                    String titulo = tituloPlato.getText().toString();
                    String descripcion = descripcionPlato.getText().toString();
                    String calorias = caloriasPlato.getText().toString();
                    String precio = precioPlato.getText().toString();

                    Plato nuevoPlato = new Plato(R.drawable.plato, titulo, descripcion, precio, calorias,"0");
                    repository.addPlato(nuevoPlato, new com.example.lab1.Helpers.Callback<String>() {
                        @Override
                        public void onCallback(String s) {
                            Log.i("info","Pedido agregado correctamente");
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Plato Guardado en ROOM!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AltaItemActivity.this, ListaPlatosActivity.class);
                    i.putExtra("titulo",titulo);
                    i.putExtra("descripcion",descripcion);
                    i.putExtra("precio",precio);
                    i.putExtra("calorias",calorias);
                    i.putExtra("CODIGO_ACTIVIDAD", CODIGO_ACTIVIDAD);
                    startActivity(i);
                }
            }
        });
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
                i = new Intent(AltaItemActivity.this, AltaUsuarioActivity.class);
                startActivity(i);
                break;

            case R.id.itemCrear:
                Toast.makeText(this, "Selecciono Crear Item", Toast.LENGTH_SHORT).show();
                i = new Intent(AltaItemActivity.this, AltaItemActivity.class);
                startActivity(i);
                break;

            case R.id.itemListar:
                Toast.makeText(this, "Selecciono ver Lista de Items", Toast.LENGTH_SHORT).show();
                i = new Intent(AltaItemActivity.this, ListaPlatosActivity.class);
                startActivity(i);
                break;

            case R.id.altaPedido:
                Toast.makeText(this, "Selecciono Realizar Pedido", Toast.LENGTH_SHORT).show();
                i = new Intent(AltaItemActivity.this, PedidoActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}