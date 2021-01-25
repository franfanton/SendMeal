package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.Repository.Plato.AppRepository;
import com.example.lab1.Servicios.Plato.PlatoService;
import com.example.lab1.model.Plato;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaItemActivity extends AppCompatActivity {
    private TextView nuevoPlato;
    private EditText tituloPlato, descripcionPlato, precioPlato, caloriasPlato;
    private Double precioDouble;
    ImageView fotoPlato;
    public static int CODIGO_ACTIVIDAD = 0;
    static final int CAMARA_REQUEST = 1;
    static final int GALERIA_REQUEST = 2;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_item);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        Button guardar = findViewById(R.id.botonGuardarPlato);
        Button camara = findViewById(R.id.botonSacarFoto);
        Button galeria = findViewById(R.id.botonBuscarGaleria);

        tituloPlato = findViewById(R.id.tituloPlato);
        descripcionPlato = findViewById(R.id.descripcionPlato);
        precioPlato = findViewById(R.id.precioPlato);
        caloriasPlato = findViewById(R.id.caloriasPlato);

        fotoPlato = findViewById(R.id.imageViewPlato);

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

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tituloPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Primero debe cargar el nombre del plato.", Toast.LENGTH_SHORT).show();
                } else lanzarCamara();
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tituloPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Primero debe cargar el nombre del plato.", Toast.LENGTH_SHORT).show();
                } else abrirGaleria();
            }
        });

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
                } else if (fotoPlato.getTag().equals("PlatoVacio")) {
                    Toast.makeText(getApplicationContext(), "Debe cargar una foto del nuevo plato.", Toast.LENGTH_SHORT).show();
                } else{
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

    private void lanzarCamara() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, CAMARA_REQUEST);
    }

    private void abrirGaleria() {
        Intent galeriaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeriaIntent, GALERIA_REQUEST);
    }

    @SuppressLint({"SetTextI18n", "MissingSuperCall"})
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && (requestCode == CAMARA_REQUEST || requestCode == GALERIA_REQUEST)){
            assert data != null;
            Bundle extras = data.getExtras();
            fotoPlato = findViewById(R.id.imageViewPlato);
            if (requestCode == CAMARA_REQUEST) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray(); // Imagen en arreglo de bytes
                fotoPlato.setImageBitmap(imageBitmap);

                StorageReference storageRef = storage.getReference();

                // Creamos una referencia a 'images/plato_id.jpg'
                StorageReference platosImagesRef = storageRef.child("images/"+tituloPlato.getText().toString()+".jpeg");

                // Cual quiera de los tres métodos tienen la misma implementación, se debe utilizar el que corresponda
                UploadTask uploadTask = platosImagesRef.putBytes(bytes);
                // UploadTask uploadTask = platosImagesRef.putFile(file);
                // UploadTask uploadTask = platosImagesRef.putStream(stream);

                // Registramos un listener para saber el resultado de la operación
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }

                        // Continuamos con la tarea para obtener la URL
                        return platosImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // URL de descarga del archivo
                            Uri downloadUri = task.getResult();
                        } else {
                            // Fallo
                            Toast.makeText(getApplicationContext(),"FALLÓ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Uri imageUri = data.getData();
                fotoPlato.setImageURI(imageUri);

                StorageReference storageRef = storage.getReference();

                // Creamos una referencia a 'images/plato_id.jpg'
                StorageReference platosImagesRef = storageRef.child("images/"+tituloPlato.getText().toString()+".jpeg");

                // Cual quiera de los tres métodos tienen la misma implementación, se debe utilizar el que corresponda
                //UploadTask uploadTask = platosImagesRef.putBytes(bytes);
                UploadTask uploadTask = platosImagesRef.putFile(imageUri);
                // UploadTask uploadTask = platosImagesRef.putStream(stream);

                // Registramos un listener para saber el resultado de la operación
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }

                        // Continuamos con la tarea para obtener la URL
                        return platosImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // URL de descarga del archivo
                            Uri downloadUri = task.getResult();
                        } else {
                            // Fallo
                            Toast.makeText(getApplicationContext(),"FALLÓ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            fotoPlato.setTag("PlatoNuevo");


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