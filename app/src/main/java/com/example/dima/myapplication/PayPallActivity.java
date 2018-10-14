package com.example.dima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PayPallActivity extends AppCompatActivity
{
    private TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pall);

        //sets total price from extras
        total = findViewById(R.id.totalView);
        total.setText("$ " + String.format("%.2f", getIntent().getFloatExtra("totalPrice", 0)));
    }

    //on click event for Log in button
    public void LogIn(View view)
    {
        MainActivity.shoppingCart.clearCart();
        finish();
        ShoppingCartActivity.Update();
    }
}
