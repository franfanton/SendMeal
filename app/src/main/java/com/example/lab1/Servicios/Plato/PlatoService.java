package com.example.lab1.Servicios.Plato;

import com.example.lab1.model.Plato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlatoService {
    @GET("plato/{id}")
    Call<Plato> getPlato(@Path("id") String id);

    @GET("plato/")
    Call<List<Plato>> getPlatoList();

    @POST("plato/")
    Call<Plato> createPlato(@Body Plato plato);
}
