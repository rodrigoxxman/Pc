package com.rsalas.sugarormapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsalas.sugarormapp.R;
import com.rsalas.sugarormapp.models.Product;

import java.util.List;

/**
 * Created by Alumno on 19/04/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> products;

    public ProductAdapter(List<Product> products){
        this.products = products;
    }

    public void setProducts(List<Product> products) {this.products = products;}

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nombre;
        public TextView precio;
        public TextView descuento;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre =  itemView.findViewById(R.id.name_text);
            precio =  itemView.findViewById(R.id.precio_text);
            descuento =  itemView.findViewById(R.id.desc_text);
        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int position) {
        Product product = this.products.get(position);
        viewHolder.nombre.setText(product.getNombre());
        viewHolder.precio.setText(product.getPrecio());
        viewHolder.descuento.setText(product.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return this.products.size();
    }

}
