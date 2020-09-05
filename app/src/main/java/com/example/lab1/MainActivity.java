package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View boton = findViewById(R.id.switchCargaInicial);
        boton.setOnClickListener(this);
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
}