package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.model.Plato;

public class CrearItemActivity extends AppCompatActivity {
    private TextView nuevoPlato;
    private EditText tituloPlato, descripcionPlato, precioPlato, caloriasPlato;
    private Double precioDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearitem);
        Button guardar = findViewById(R.id.botonGuardarPlato);

        tituloPlato = (EditText) findViewById(R.id.tituloPlato);
        descripcionPlato = (EditText) findViewById(R.id.descripcionPlato);
        precioPlato = (EditText) findViewById(R.id.precioPlato);
        caloriasPlato = (EditText) findViewById(R.id.caloriasPlato);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                }
            }
        });
    }
}