package com.example.dima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartActivity extends AppCompatActivity
{
    private ImageButton homeButton;
    private Button conShopping;
    private static ListView listView;
    private static ShoppingCartAdapter adapter;
    private static TextView savings, total;
    private static float totalPrice, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        listView = findViewById(R.id.sopping_cart_list);
        adapter = new ShoppingCartAdapter(this, MainActivity.shoppingCart);

        savings = findViewById(R.id.savings);
        total = findViewById(R.id.total);

        Update();

        onClick();
    }

    //on click listener from CreditCard button
    public void CreditCard(View view)
    {
        if(MainActivity.shoppingCart.getSize()>0)
        {
            Intent intent = new Intent(".CreditCardActivity");
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        }else
        {
            Toast.makeText(ShoppingCartActivity.this,"Nothing to buy...", Toast.LENGTH_SHORT).show();
        }

    }

    //on click listener for PayPal button
    public void PayPal(View view)
    {
        if(MainActivity.shoppingCart.getSize() > 0)
        {
            Intent intent = new Intent(".PayPallActivity");
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        }else
        {
            Toast.makeText(ShoppingCartActivity.this,"Nothing to buy...", Toast.LENGTH_SHORT).show();
        }

    }

    //on click listeners for Home and Shopping buttons
    public void onClick()
    {
        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        conShopping = findViewById(R.id.Con_shopping);
        conShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(".ShopOnline");
                startActivity(intent);
            }
        });
    }

    //Updates shoppingCart list view
    public static void Update()
    {
        listView.setAdapter(adapter);
        Total();
    }

    //calculates total price and total savings
    //and sets them to related fields
    private static void Total()
    {
        int quantity;
        float productPrice, normalPrice;
        float totalNormalPrice = 0;
        totalPrice = 0;
        save = 0;

        for(int i = 0; i < MainActivity.shoppingCart.getSize(); i++)
        {
            for(Product product: MainActivity.productList)
            {
                if(product.getName().equals(MainActivity.shoppingCart.getProduct(i).getName()))
                {
                    normalPrice = Float.valueOf(product.getPrice());
                    quantity = MainActivity.shoppingCart.getQuantity(i);
                    totalNormalPrice += normalPrice*quantity;
                    break;
                }
            }
            productPrice = Float.valueOf(MainActivity.shoppingCart.getProduct(i).getPrice());
            quantity = MainActivity.shoppingCart.getQuantity(i);
            totalPrice += quantity*productPrice;
        }
        save = totalNormalPrice - totalPrice;
        total.setText("$ " + String.format("%.2f", totalPrice));
        savings.setText("$ " + String.format("%.2f",save));
    }
}
