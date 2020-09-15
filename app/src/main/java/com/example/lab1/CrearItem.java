package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1.model.Plato;

public class CrearItem extends AppCompatActivity {
    private TextView nuevoPlato;
    private EditText tituloPlato, descripcionPlato, precioPlato, caloriasPlato;
    private Double precioDouble = Double.parseDouble(precioPlato.getText().toString());

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.crearitem);
        Button guardar = findViewById(R.id.botonGuardarPlato);

        tituloPlato = (EditText) findViewById(R.id.tituloPlato);
        descripcionPlato = (EditText) findViewById(R.id.descripcionPlato);
        precioPlato = (EditText) findViewById(R.id.precioPlato);
        caloriasPlato = (EditText) findViewById(R.id.caloriasPlato);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Plato Guardado!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_LONG).show();
                /*if (tituloPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo titulo esta vacio.", Toast.LENGTH_SHORT).show();
                } else if (descripcionPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo descripcion esta vacio.", Toast.LENGTH_SHORT).show();
                } else if (precioPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo precio esta vacio.", Toast.LENGTH_SHORT).show();
                } else if (caloriasPlato.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo calorias esta vacio.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Plato Guardado!", Toast.LENGTH_SHORT).show();
                    Plato nuevoPlato = new Plato(tituloPlato.getText().toString(), descripcionPlato.getText().toString(), precioDouble, caloriasPlato.getText().toString());
                }*/
            }
        });
    }
}