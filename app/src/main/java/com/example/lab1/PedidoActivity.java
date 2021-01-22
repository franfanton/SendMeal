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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lab1.Repository.Pedido.AppRepository;
//import com.example.lab1.Repository.AppRepository;
import com.example.lab1.Servicios.Pedido.PedidoService;
import com.example.lab1.model.Pedido;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private Button botonAgregarUbicacion;
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
        botonAgregarUbicacion = (Button) findViewById(R.id.botonUbicacion);

        // Create a new object from HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add Interceptor to HttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();


        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.117:3000/")
                // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> callPedidos = pedidoService.getPedidoList();
        callPedidos.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                switch (response.code()){
                    case 200:
                        Log.d("DEBUG", "Returno 200");
                        break;
                    case 401:
                        Log.d("DEBUG", "Returno 401");
                        break;
                    case 403:
                        Log.d("DEBUG", "Returno 403");
                        break;
                    case 500:
                        Log.d("DEBUG", "Returno 500");
                        break;
                    default:
                        Log.d("DEBUG", "Returno default");
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.d("DEBUG", "ERROR: "+t.getMessage());
            }
//                if (response.code() == 200) {
//                    Log.d("DEBUG", "Returno Exitoso");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pedido>> call, Throwable t) {
//                Log.d("DEBUG", "Returno Fallido");
//            }
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

        botonEnvioPedido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                botonAgregarUbicacion.setEnabled(botonEnvioPedido.isChecked());
            }
        });

        botonTakeawayPedido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                direccionPedidoNuevo.setEnabled(true);
                direccionPedidoNuevo.setText("");
            }
        });

        botonAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PedidoActivity.this, MapActivity.class);
                LatLng ubicacion = new LatLng(0,0);
                i.putExtra("ubicacion",ubicacion);
                startActivityForResult(i, CODIGO_ACTIVIDAD);
            }
        });

        botonRealizarPedido.setOnClickListener(new View.OnClickListener() {
            final String emailPattern = getString(R.string.mailCorrecto);
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
                    String tipoEnvio;
                    if (botonEnvioPedido.isChecked()){
                        tipoEnvio = botonEnvioPedido.getText().toString();
                    } else {
                        tipoEnvio = botonTakeawayPedido.getText().toString();
                    }


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


//    public List<Pedido> getPedidoList() {
//        Call<List<Pedido>> invocacionSyn = pedidoService.getPedidoList();
//        Response<List<Pedido>> res = null;
//        try {
//            res = invocacionSyn.execute();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        return res.body();
//    }

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
            else if (data.hasExtra("ubicacion")){
                LatLng ubicacion = data.getParcelableExtra("ubicacion");
                if (ubicacion.longitude != 0 && ubicacion.latitude != 0){
                    direccionPedidoNuevo.setText("Ubicación agregada a través de Google Maps.");
                    direccionPedidoNuevo.setEnabled(false);
                }
                else {
                    Toast.makeText(this, "No se agregó ninguna ubicación, intente nuevamente.", Toast.LENGTH_LONG).show();
                    direccionPedidoNuevo.setText("");
                }
                //direccionPedidoNuevo.setText(ubicacion.toString());

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @SuppressLint("StaticFieldLeak")
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

    @SuppressLint("NonConstantResourceId")
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