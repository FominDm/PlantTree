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

import java.util.ArrayList;

public class ShopOnline extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner productCat;
    private ImageButton home, shoppingCart;
    private ListView productsView;
    private ArrayList<Product> listToShow;

    private String[] prodCat = {"All categories","Fruit tree", "Evergreen and Natives"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_online);

        listToShow = new ArrayList<>();

        buttons();

        productsView = findViewById(R.id.ProductList);

        productCat = findViewById(R.id.ProductCat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, prodCat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCat.setAdapter(adapter);
        productCat.setOnItemSelectedListener(this);
    }

    //on click listeners for home and shoppingCart buttons
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
        switch (position)
        {
            case 0:
                adaptProducts(position);
                break;
            case 1:
                adaptProducts(position);
                break;
            case 2:
                adaptProducts(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //sorts products by category
    private void ProductListSorter(ArrayList<Product> list, String sortingName)
    {
        listToShow.clear();
        for(Product product: list)
        {
            if(sortingName.equals(prodCat[0]))
            {
                listToShow.add(product);
            }else
            {
                if(product.getCategory().equals(sortingName))
                {
                    listToShow.add(product);
                }
            }
        }
    }

    //adapts Product list depending on what product category was selected
    private void adaptProducts(int position)
    {
        ProductAdapter productAdapter;

        ProductListSorter(MainActivity.productList, prodCat[position]);
        productAdapter = new ProductAdapter(this, listToShow);
        productsView.setAdapter(productAdapter);
    }
}
