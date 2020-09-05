package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private EditText nombre,apellido,contrasenia1,contrasenia2,correo,numeroTarjeta,numeroCCV;
    private RadioButton tarjetaDebito,tarjetaCredito;
    private Spinner diaVencimiento,anioVencimiento;
    private CheckBox terminosycondiciones;
    private Switch switchCargaInicial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View boton = findViewById(R.id.switchCargaInicial);
        boton.setOnClickListener(this);

        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        contrasenia1 = (EditText) findViewById(R.id.contrasenia1);
        contrasenia2 = (EditText) findViewById(R.id.contrasenia2);
        correo = (EditText) findViewById(R.id.correo);
        numeroTarjeta = (EditText) findViewById(R.id.numeroTarjeta);
        numeroCCV = (EditText) findViewById(R.id.codigoCCV);
        tarjetaDebito = (RadioButton) findViewById(R.id.botonDebito);
        tarjetaCredito = (RadioButton) findViewById(R.id.botonCredito);
        diaVencimiento = (Spinner) findViewById(R.id.diaVencimiento);
        anioVencimiento = (Spinner) findViewById(R.id.anioVencimiento);
        terminosycondiciones = (CheckBox) findViewById(R.id.terminosycondiciones);
        switchCargaInicial = (Switch) findViewById(R.id.switchCargaInicial);
        diaVencimiento.setEnabled(false);
        anioVencimiento.setEnabled(false);

        numeroTarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!(numeroTarjeta.getText().toString().isEmpty())){
                    diaVencimiento.setEnabled(true);
                    anioVencimiento.setEnabled(true);
                    numeroCCV.setEnabled(true);
                }
                if(numeroTarjeta.getText().toString().isEmpty()){
                    diaVencimiento.setEnabled(false);
                    anioVencimiento.setEnabled(false);
                    numeroCCV.setEnabled(false);
                }
            }
        });

        textView = (TextView) findViewById(R.id.textoCargaInicial);
        seekBar = (SeekBar) findViewById(R.id.seekBarCargaInicial);
        textView.setText("Credito Inicial: "+ seekBar.getProgress()+"/"+seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("Credito Inicial: " + progress + "/" +seekBar.getMax());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == findViewById(R.id.switchCargaInicial).getId()){
            if(textView.getVisibility() == View.GONE){
                textView.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);
            }
            else{
                textView.setVisibility(View.GONE);
                seekBar.setVisibility(View.GONE);
            }
        }
    }

    public void Registrar(View view){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(nombre.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo nombre esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if(apellido.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo apellido esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if(contrasenia1.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo contraseña esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if(contrasenia2.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo repetir contraseña esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if(!(contrasenia1.getText().toString().equals(contrasenia2.getText().toString()))){
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
        }
        else if(correo.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo correo esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if (!(correo.getText().toString().trim().matches(emailPattern))) {
            Toast.makeText(getApplicationContext(),"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
        }
        else if(!(tarjetaDebito.isChecked() || tarjetaCredito.isChecked())){
            Toast.makeText(this, "Seleccione el tipo de tarjeta.", Toast.LENGTH_SHORT).show();
        }
        else if(numeroTarjeta.getText().toString().isEmpty()){
            Toast.makeText(this, "El campo de numero de tarjeta esta vacio.", Toast.LENGTH_SHORT).show();
        }
        else if(diaVencimiento.getSelectedItem().toString().equals(":: Seleccione ::")){
            Toast.makeText(this, "Seleccione el dia de vencimiento.", Toast.LENGTH_SHORT).show();
        }
        else if(anioVencimiento.getSelectedItem().toString().equals(":: Seleccione ::")){
            Toast.makeText(this, "Seleccione el año de vencimiento.", Toast.LENGTH_SHORT).show();
        }
        else if(!(terminosycondiciones.isChecked())){
            Toast.makeText(this, "Debe aceptar los terminos y condiciones.", Toast.LENGTH_SHORT).show();
        }
        else if(switchCargaInicial.isChecked()){
            int value = seekBar.getProgress();
            if(value == 0){
                Toast.makeText(this, "Si realiza un credito inicial debe seleccionar un monto.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Formulario cargado con exito.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Formulario cargado con exito.", Toast.LENGTH_SHORT).show();
        }
    }
}