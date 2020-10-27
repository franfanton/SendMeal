package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PedidoActivity extends AppCompatActivity {

    private EditText correo, direccion;
    private RadioButton envio, takeAway;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nuevo_pedido);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        final Button botonAgregarPlato = findViewById(R.id.botonAgregarPlato);
        final Button botonRealizarPedido = findViewById(R.id.botonRealizarPedido);

        correo = findViewById(R.id.correo);
        direccion = findViewById(R.id.direccionEnvio);
        envio = findViewById(R.id.envio);
        takeAway = findViewById(R.id.takeAway);

        botonAgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(PedidoActivity.this, ListaPlatosActivity.class);
                startActivity(i);
            }
        });

        botonRealizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Pedido Realizado!", Toast.LENGTH_SHORT).show();
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
        switch (item.getItemId()) {
            case R.id.itemRegistrar:
                Toast.makeText(this, "Selecciono Registrarse", Toast.LENGTH_SHORT).show();
                i = new Intent(PedidoActivity.this, AltaUsuarioActivity.class);
                startActivity(i);
                break;

            case R.id.itemCrear:
                Toast.makeText(this, "Selecciono Crear Item", Toast.LENGTH_SHORT).show();
                i = new Intent(PedidoActivity.this, AltaItemActivity.class);
                startActivity(i);
                break;

            case R.id.itemListar:
                Toast.makeText(this, "Selecciono ver Lista de Items", Toast.LENGTH_SHORT).show();
                i = new Intent(PedidoActivity.this, ListaPlatosActivity.class);
                startActivity(i);
                break;

            case R.id.itemNuevoPedido:
                Toast.makeText(this, "Selecciono Realizar un Nuevo Pedido", Toast.LENGTH_SHORT).show();
                i = new Intent(PedidoActivity.this, PedidoActivity.class);
                startActivity(i);
                break;

        }
        return true;
    }

}
