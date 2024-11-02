package com.example.productosapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Referencias a los elementos de la interfaz
        ImageView productImage = findViewById(R.id.detailProductImage);
        TextView productName = findViewById(R.id.detailProductName);
        TextView productBrand = findViewById(R.id.detailProductBrand);
        TextView productPrice = findViewById(R.id.detailProductPrice);
        TextView productCost = findViewById(R.id.detailProductCost);
        TextView productExistence = findViewById(R.id.detailProductExistence);
        TextView productDate = findViewById(R.id.detailProductDate);
        TextView productDescription = findViewById(R.id.detailProductDescription);

        // Recibir los datos del Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("productName");
        String brand = intent.getStringExtra("productBrand");
        double price = intent.getDoubleExtra("productPrice", 0);
        double cost = intent.getDoubleExtra("productCost", 0);
        int existence = intent.getIntExtra("productExistence", 0);
        String date = intent.getStringExtra("productDate");
        String description = intent.getStringExtra("productDescription");
        String imageUrl = intent.getStringExtra("productImage");

        // Asignar los valores recibidos a los elementos de la interfaz
        productName.setText(name);
        productBrand.setText("Marca: " + brand);
        productPrice.setText("Precio de Venta: Q" + price);
        productCost.setText("Precio de Costo: Q" + cost);
        productExistence.setText("Existencia: " + existence);
        productDate.setText("Fecha de Ingreso: " + date);
        productDescription.setText(Html.fromHtml("<b>Descripción:</b> " + description));


        // Cargar la imagen del producto usando Glide
        Glide.with(this).load(imageUrl).into(productImage);
    }
}
