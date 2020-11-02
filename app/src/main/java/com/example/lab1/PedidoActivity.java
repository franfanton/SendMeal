package com.example.lab1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lab1.model.MyNotificationPublisher;

import static android.app.AlarmManager.RTC;
import static android.app.AlarmManager.RTC_WAKEUP;

public class PedidoActivity extends AppCompatActivity {

    private EditText correo, direccion;
    private RadioButton envio, takeAway;
    private TareaPedido tareaPedido;
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
        tareaPedido = new TareaPedido();

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
                ProgressBar cargaPedido;
                cargaPedido = findViewById(R.id.cargaPedido);
                cargaPedido.setVisibility(View.VISIBLE);
                //           tareaPedido.onPostExecute("Chau");
                if(tareaPedido.getStatus().equals(AsyncTask.Status.RUNNING)){
                    tareaPedido.cancel(true);
                } else {
                    Toast.makeText(PedidoActivity.this,"NO SE ESTA RUNNING",Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(getApplicationContext(), "Pedido Realizado!", Toast.LENGTH_SHORT).show();
                tareaPedido.doInBackground("Hola");
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);
        return true;
    }

    class TareaPedido extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Retorno";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tv_pedido = findViewById(R.id.tv_pedido);
            Intent notificationIntent = new Intent(PedidoActivity.this, MyNotificationPublisher.class);
            // ...
            PendingIntent contentIntent = PendingIntent.getActivity(PedidoActivity.this, 0,
                    notificationIntent, 0);
            tv_pedido.setText(result);
            Toast.makeText(PedidoActivity.this, result,Toast.LENGTH_LONG).show();
            AlarmManager alarmManager = null;
            alarmManager.set(RTC, 5000, contentIntent);

            //alarmManager = new AlarmManager();
            //alarmManager.set();
        }
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
