package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.model.Plato;

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

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent i = new Intent(CrearItemActivity.this, PruebaActivity.this);
                precioDouble = Double.parseDouble(precioPlato.getText().toString());

                if (tituloPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo título esta vacío.", Toast.LENGTH_SHORT).show();
                } else if (descripcionPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo descripción esta vacío.", Toast.LENGTH_SHORT).show();
                } else if (precioPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo precio esta vacío.", Toast.LENGTH_SHORT).show();
                } else if (precioDouble <= 0 ){
                    Toast.makeText(getApplicationContext(), "El precio debe ser mayor a $0.", Toast.LENGTH_SHORT).show();
                } else if (caloriasPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo calorías esta vacío.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Plato Guardado!", Toast.LENGTH_SHORT).show();
                    String titulo = tituloPlato.getText().toString();
                    String descripcion = descripcionPlato.getText().toString();
                    String calorias = caloriasPlato.getText().toString();

                    Plato nuevoPlato = new Plato(titulo, descripcion, precioDouble, calorias);
                    // INTENT
                    Intent i = new Intent(AltaItemActivity.this, ListaPlatosActivity.class);
                    i.putExtra("titulo",titulo);
                    i.putExtra("descripcion",descripcion);
                    i.putExtra("precio",precioDouble);
                    i.putExtra("calorias",calorias);
                    i.putExtra("CODIGO_ACTIVIDAD", CODIGO_ACTIVIDAD);
                    startActivity(i);
                    //startActivityForResult(i, CODIGO_ACTIVIDAD);

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