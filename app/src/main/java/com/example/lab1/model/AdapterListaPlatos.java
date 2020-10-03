package com.example.lab1.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.R;

import java.util.List;

public class AdapterListaPlatos extends RecyclerView.Adapter<AdapterListaPlatos.PruebaViewHolder> {
    private List<ListaPlatos> listaPrueba;

    public AdapterListaPlatos(List<ListaPlatos> listaPrueba){
        this.listaPrueba = listaPrueba;
    }
    @NonNull
    @Override
    public AdapterListaPlatos.PruebaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_card_view2, viewGroup,false);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PruebaViewHolder pruebaViewHolder, int i) {
        pruebaViewHolder.ivImagen.setImageResource(listaPrueba.get(i).getImagen());
        pruebaViewHolder.tvTitulo.setText(listaPrueba.get(i).getTitulo());
        pruebaViewHolder.tvPrecio.setText(listaPrueba.get(i).getPrecio());
        pruebaViewHolder.tvCalorias.setText(listaPrueba.get(i).getCalorias());
        pruebaViewHolder.tvDescripcion.setText(listaPrueba.get(i).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaPrueba.size();
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImagen;
        TextView tvTitulo,tvPrecio,tvCalorias,tvDescripcion;
        public PruebaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.imagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCalorias = itemView.findViewById(R.id.tvCalorias);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
