package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lab1.model.MyNotificationPublisher;
import com.example.lab1.model.Pedido;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {
    private EditText correoPedidoNuevo, direccionPedidoNuevo;
    private RadioButton botonEnvioPedido,botonTakeawayPedido;
  //  BotonAsyncTask botonAsyncTask;
    public static int CODIGO_ACTIVIDAD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_pedido);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        final Button botonAgregarPlato = findViewById(R.id.listadoPlatos);
        final Button botonRealizarPedido = findViewById(R.id.botonGuardarPlato);

        correoPedidoNuevo = (EditText) findViewById(R.id.correoPedidoNuevo);
        direccionPedidoNuevo = (EditText) findViewById(R.id.direccionPedidoNuevo);
        botonEnvioPedido = (RadioButton) findViewById(R.id.botonEnvioPedido);
        botonTakeawayPedido = (RadioButton) findViewById(R.id.botonTakeawayPedido);
  //      botonAsyncTask = new BotonAsyncTask();

        botonAgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(PedidoActivity.this, ListaPlatosActivity.class);
                startActivityForResult(i,0);
              //  startActivity(i);
            }
        });

        botonRealizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correoPedidoNuevo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo de correo electronico esta vacío.", Toast.LENGTH_SHORT).show();
                }else if (direccionPedidoNuevo.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "El campo direccion esta vacío.", Toast.LENGTH_SHORT).show();
                }else if(!(botonEnvioPedido.isChecked() || botonTakeawayPedido.isChecked())){
                        Toast.makeText(getApplicationContext(), "Seleccione el tipo de envio.", Toast.LENGTH_SHORT).show();
                }else {


                    Toast.makeText(getApplicationContext(), "Pedido Realizado!", Toast.LENGTH_SHORT).show();
                    String correo = correoPedidoNuevo.getText().toString();
                    String direccion = direccionPedidoNuevo.getText().toString();
                    String tipoEnvio = botonEnvioPedido.getText().toString();

                    Pedido nuevoPedido = new Pedido(correo, direccion, tipoEnvio);
                    // INTENT
                    //Intent i = new Intent(AltaItemActivity.this, ListaPlatosActivity.class);
                    //i.putExtra("correo",correo);
                    //i.putExtra("direccion",direccion);
                    //i.putExtra("tipoEnvio",tipoEnvio);
                    //startActivity(i);
                    //startActivityForResult(i, CODIGO_ACTIVIDAD);

                }
            }
        });
    }

    class Tarea1 extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    /*class BotonAsyncTask extends AsyncTask<String, String>{

        private ArrayList<String> alarmManager;

        protected String doInBackground(String... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Pedido Realizado";
        }

        protected void onPostExecute(String result) {

            Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
            // ...
            alarmManager.set(Integer.parseInt("Hola"), "Chau");
        }
    }
*/
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

            case R.id.altaPedido:
                Toast.makeText(this, "Selecciono Realizar Pedido", Toast.LENGTH_SHORT).show();
                i = new Intent(PedidoActivity.this, PedidoActivity.class);
                startActivity(i);
                break;

        }
        return true;
    }
}