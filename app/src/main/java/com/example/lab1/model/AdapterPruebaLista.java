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

public class AdapterPruebaLista extends RecyclerView.Adapter<AdapterPruebaLista.PruebaViewHolder> {
    private List<PruebaLista> listaPrueba;

    public AdapterPruebaLista(List<PruebaLista> listaPrueba){
        this.listaPrueba = listaPrueba;
    }
    @NonNull
    @Override
    public AdapterPruebaLista.PruebaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_card_view, viewGroup,false);
        return new PruebaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PruebaViewHolder pruebaViewHolder, int i) {
        pruebaViewHolder.ivImagen.setImageResource(listaPrueba.get(i).getImagen());
        pruebaViewHolder.tvTitulo.setText(listaPrueba.get(i).getTitulo());
        pruebaViewHolder.tvPrecio.setText(listaPrueba.get(i).getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaPrueba.size();
    }

    public class PruebaViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImagen;
        TextView tvTitulo,tvPrecio;
        public PruebaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.imagen);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
