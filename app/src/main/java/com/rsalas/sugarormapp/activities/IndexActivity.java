package com.rsalas.sugarormapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rsalas.sugarormapp.R;
import com.rsalas.sugarormapp.adapters.ProductAdapter;
import com.rsalas.sugarormapp.models.Product;
import com.rsalas.sugarormapp.repositories.ProductRepository;

import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST = 100;
    private RecyclerView productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        productsList = findViewById(R.id.product_list);
        productsList.setLayoutManager(new LinearLayoutManager(this));
        List<Product> products = ProductRepository.list();
        productsList.setAdapter(new ProductAdapter(products));
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(IndexActivity.this, "Fragment here", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_favorite:
                        Toast.makeText(IndexActivity.this, "Another fragment...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_archive:
                        Toast.makeText(IndexActivity.this, "Another Fragment...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_logout:
                        startActivity(new Intent(IndexActivity.this, MainActivity.class));
                        Toast.makeText(IndexActivity.this, "Vuelva pronto", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, RegisterProductActivity.class), REGISTER_FORM_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ProductAdapter adapter = (ProductAdapter) productsList.getAdapter();

        List<Product>  products = ProductRepository.list();
        adapter.setProducts(products);
        adapter.notifyDataSetChanged();

    }
}
