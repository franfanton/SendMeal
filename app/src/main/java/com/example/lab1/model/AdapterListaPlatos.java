package com.example.lab1.model;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.ListaPlatosActivity;
import com.example.lab1.R;

import java.util.List;


public class AdapterListaPlatos extends RecyclerView.Adapter<AdapterListaPlatos.PruebaViewHolder> implements View.OnClickListener {
    private final List<ListaPlatos> listaPrueba;
    private final int CODIGO_ACTIVIDAD;
    private View.OnClickListener listener;
    private String unidades;

    public AdapterListaPlatos(List<ListaPlatos> listaPrueba, int CODIGO_ACTIVIDAD){
        this.listaPrueba = listaPrueba;
        this.CODIGO_ACTIVIDAD = CODIGO_ACTIVIDAD;
    }

    @NonNull
    @Override
    public AdapterListaPlatos.PruebaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_card_view2, viewGroup,false);
        v.setOnClickListener(this);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PruebaViewHolder pruebaViewHolder, final int i) {
        pruebaViewHolder.ivImagen.setImageResource(listaPrueba.get(i).getImagen());
        pruebaViewHolder.tvTitulo.setText(listaPrueba.get(i).getTitulo());
        pruebaViewHolder.tvPrecio.setText(listaPrueba.get(i).getPrecio());
        pruebaViewHolder.tvCalorias.setText(listaPrueba.get(i).getCalorias());
        pruebaViewHolder.tvDescripcion.setText(listaPrueba.get(i).getDescripcion());
        if (CODIGO_ACTIVIDAD == 1) {
            pruebaViewHolder.etUnidades.setVisibility(View.VISIBLE);
            pruebaViewHolder.tvUnidades.setVisibility(View.VISIBLE);
            pruebaViewHolder.etUnidades.setText(listaPrueba.get(i).getUnidades());
            pruebaViewHolder.etUnidades.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!(pruebaViewHolder.etUnidades.getText().toString().equals("0") || pruebaViewHolder.etUnidades.getText().toString().isEmpty())) {
                        pruebaViewHolder.tvAgregarPlato.setEnabled(true);
                    }
                    else{
                        pruebaViewHolder.tvAgregarPlato.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!(pruebaViewHolder.etUnidades.getText().toString().equals("0") || pruebaViewHolder.etUnidades.getText().toString().isEmpty())) {
                        unidades = pruebaViewHolder.etUnidades.getText().toString();
                        listaPrueba.get(i).setUnidades(unidades);
                        pruebaViewHolder.tvAgregarPlato.setVisibility(View.VISIBLE);
                    }
                    else {
                        pruebaViewHolder.tvAgregarPlato.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
        else {
            pruebaViewHolder.etUnidades.setVisibility(View.INVISIBLE);
            pruebaViewHolder.tvUnidades.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listaPrueba.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo,tvPrecio,tvCalorias,tvDescripcion,tvUnidades, tvAgregarPlato;
        EditText etUnidades;

        public PruebaViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.imagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCalorias = itemView.findViewById(R.id.tvCalorias);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvUnidades = itemView.findViewById(R.id.tvUnidades);
            etUnidades = itemView.findViewById(R.id.etUnidades);
            tvAgregarPlato = itemView.findViewById(R.id.tvAgregarPlato);

        }
    }

}
