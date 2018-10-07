package com.example.dima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

public class ShopOnline extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner productCat;
    private ImageButton home, shoppingCart;
    private ListView productsView;

    private String[] prodCat = {"All categories","trees", "Evergreen and Natives"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_online);

        buttons();

        productCat = findViewById(R.id.ProductCat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prodCat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCat.setAdapter(adapter);
        productCat.setOnItemSelectedListener(this);

        productsView = findViewById(R.id.ProductList);
        ProductAdapter productAdapter = new ProductAdapter(this, MainActivity.productList);
        productsView.setAdapter(productAdapter);

    }

    public void buttons()
    {
        home = findViewById(R.id.Home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shoppingCart = findViewById(R.id.ShoppingCart);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(".ShoppingCartActivity");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
