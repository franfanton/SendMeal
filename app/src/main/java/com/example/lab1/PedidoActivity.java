package com.example.lab1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
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


import com.example.lab1.Repository.Pedido.AppRepository;
import com.example.lab1.Servicios.Pedido.PedidoService;
import com.example.lab1.model.Pedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoActivity extends AppCompatActivity {
    // NOTIFICACION
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    // FIN NOTIFICACION
    private EditText correoPedidoNuevo, direccionPedidoNuevo;
    private RadioButton botonEnvioPedido,botonTakeawayPedido;
    private TextView nombrePlato, totalNuevoPedido;
  //  BotonAsyncTask botonAsyncTask;
    private final int CODIGO_ACTIVIDAD = 1;
    // PARTE LAB 4 - PUNTO 3
    private ProgressBar progressBar1;
    //
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
        nombrePlato = (TextView) findViewById(R.id.nombrePlato);
        totalNuevoPedido = (TextView) findViewById(R.id.totalNuevoPedido);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);


        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.117:3001/")
                // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> callPedidos = pedidoService.getPedidoList();
        callPedidos.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.code() == 200) {
                    Log.d("DEBUG", "Returno Exitoso");
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.d("DEBUG", "Returno Fallido");
            }
        });

        AppRepository repository = new AppRepository(this.getApplication());
        botonAgregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(PedidoActivity.this, ListaPlatosActivity.class);
                i.putExtra("CODIGO_ACTIVIDAD", CODIGO_ACTIVIDAD);
                startActivityForResult(i,CODIGO_ACTIVIDAD);
            }
        });

        botonRealizarPedido.setOnClickListener(new View.OnClickListener() {
            String emailPattern = getString(R.string.mailCorrecto);
            @Override
            public void onClick(View view) {

                if (correoPedidoNuevo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo de correo electronico esta vacío.", Toast.LENGTH_SHORT).show();
                }else if(!(correoPedidoNuevo.getText().toString().trim().matches(emailPattern))) {
                    Toast.makeText(getApplicationContext(),"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
                }else if (direccionPedidoNuevo.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "El campo direccion esta vacío.", Toast.LENGTH_SHORT).show();
                }else if(!(botonEnvioPedido.isChecked() || botonTakeawayPedido.isChecked())){
                        Toast.makeText(getApplicationContext(), "Seleccione el tipo de envio.", Toast.LENGTH_SHORT).show();
                }else if(nombrePlato.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Debe seleccionar un plato del menú.", Toast.LENGTH_SHORT).show();
                }
                else {

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
                    new Task().execute();
                    // ALTA DE NOTIFICACION
                    int delay = 8;
                    String tittle = "Send Mail";
                    String content = "Pedido cargado con exito!";

                    scheduleNotification(getNotification(content, tittle), delay);

                    repository.addPedido(nuevoPedido, new com.example.lab1.Helpers.Callback<String>() {
                        @Override
                        public void onCallback(String s) {
                            Log.i("info","Pedido agregado correctamente");
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Pedido Guardado en ROOM!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODIGO_ACTIVIDAD) {
            assert data != null;
            if (data.hasExtra("titulo")) {
                String tituloPlato = "- "+data.getStringExtra("titulo")+" x"+data.getStringExtra("unidades");
                int unidad, costo, total;
                unidad = Integer.parseInt(Objects.requireNonNull(data.getStringExtra("unidades")));
                costo = Integer.parseInt(Objects.requireNonNull(data.getStringExtra("precio")));
                total = unidad*costo;
                nombrePlato.setText(tituloPlato);
                totalNuevoPedido.setText(total+"");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    class Task extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
            botonEnvioPedido.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "salida";
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar1.setVisibility(View.INVISIBLE);
            botonEnvioPedido.setEnabled(true);
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

    // FUNCIONES NOTIFICACION

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content, String tittle) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle(!tittle.isEmpty() ? tittle : "Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }
}