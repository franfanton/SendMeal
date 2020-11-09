package com.example.lab1.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab1.R;

import java.util.List;


public class AdapterListaPlatos extends RecyclerView.Adapter<AdapterListaPlatos.PruebaViewHolder> implements View.OnClickListener {
    private final List<ListaPlatos> listaPrueba;
    private final int CODIGO_ACTIVIDAD;
    private View.OnClickListener listener;

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
    public void onBindViewHolder(@NonNull PruebaViewHolder pruebaViewHolder, final int i) {
        pruebaViewHolder.ivImagen.setImageResource(listaPrueba.get(i).getImagen());
        pruebaViewHolder.tvTitulo.setText(listaPrueba.get(i).getTitulo());
        pruebaViewHolder.tvPrecio.setText(listaPrueba.get(i).getPrecio());
        pruebaViewHolder.tvCalorias.setText(listaPrueba.get(i).getCalorias());
        pruebaViewHolder.tvDescripcion.setText(listaPrueba.get(i).getDescripcion());
        if (CODIGO_ACTIVIDAD == 1) {
            pruebaViewHolder.btnAgregarPlato.setVisibility(View.VISIBLE);
        }
        else {
            pruebaViewHolder.btnAgregarPlato.setVisibility(View.INVISIBLE);
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

    public static class PruebaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvTitulo,tvPrecio,tvCalorias,tvDescripcion,tvUnidades;
        EditText etUnidades;
        Button btnAgregarPlato;

        public PruebaViewHolder(@NonNull final View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.imagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCalorias = itemView.findViewById(R.id.tvCalorias);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvUnidades = itemView.findViewById(R.id.tvUnidades);
            etUnidades = itemView.findViewById(R.id.etUnidades);
            btnAgregarPlato = itemView.findViewById(R.id.botonAgregarPlato);


        }
    }
}
