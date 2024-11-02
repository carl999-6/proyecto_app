package com.example.productosapp.api;

import com.example.productosapp.models.Producto;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    // Definir el endpoint para obtener todos los productos
    @GET("/api/Productos")
    Call<List<Producto>> getProductos();
}

