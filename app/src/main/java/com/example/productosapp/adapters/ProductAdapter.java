package com.example.productosapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.productosapp.ProductDetailActivity; // Asegúrate de importar la nueva actividad
import com.example.productosapp.R;
import com.example.productosapp.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Producto> productList;
    private List<Producto> productListFull; // Copia completa de la lista

    public ProductAdapter(Context context, List<Producto> productList) {
        this.context = context;
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList); // Inicializa la copia completa
    }

    public void updateProducts(List<Producto> productList) {
        this.productList = productList;
        this.productListFull = new ArrayList<>(productList); // Actualiza la copia completa
        notifyDataSetChanged();
    }

    public void filter(String text) {
        productList.clear();
        if (text.isEmpty()) {
            productList.addAll(productListFull);
        } else {
            for (Producto item : productListFull) {
                if (item.getProducto().toLowerCase().contains(text.toLowerCase())) {
                    productList.add(item);
                }
            }
        }
        if (productList.isEmpty()) {
            // Si no se encuentran productos, agrega un producto con mensaje de error
            productList.add(new Producto(-1, "No existe ese producto", "", "", "", 0, 0, 0, ""));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Producto product = productList.get(position);
        holder.nameTextView.setText(product.getProducto());
        holder.priceTextView.setText("Q " + product.getPrecio_venta());
        holder.brandTextView.setText(product.getMarca());
        holder.descriptionTextView.setText(product.getDescripcion());
        // Cargar la imagen usando Glide
        Glide.with(context)
                .load(product.getImagen())
                .into(holder.productImageView);

        // Configurar el OnClickListener para abrir ProductDetailActivity
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productName", product.getProducto());
            intent.putExtra("productBrand", product.getMarca());
            intent.putExtra("productPrice", product.getPrecio_venta());
            intent.putExtra("productCost", product.getPrecio_costo());
            intent.putExtra("productExistence", product.getExistencia());
            intent.putExtra("productDate", product.getFecha_ingreso());
            intent.putExtra("productDescription", product.getDescripcion());
            intent.putExtra("productImage", product.getImagen());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, brandTextView, descriptionTextView;
        ImageView productImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productName);
            priceTextView = itemView.findViewById(R.id.productPrice);
            brandTextView = itemView.findViewById(R.id.productBrand);
            descriptionTextView = itemView.findViewById(R.id.productDescription);
            productImageView = itemView.findViewById(R.id.productImage);
        }
    }
}
