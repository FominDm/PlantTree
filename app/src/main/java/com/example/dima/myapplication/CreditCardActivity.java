package com.example.dima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CreditCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner monthSpinner, yearSpinner;
    private TextView amountToPay;

    private String[] month = {" ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private String[] year = {" ", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        //adapts month array in to month spinner
        monthSpinner = findViewById(R.id.month);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, month);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(this);

        //adapts year array in to year spinner
        yearSpinner = findViewById(R.id.year);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapt);
        yearSpinner.setOnItemSelectedListener(this);

        //sets total price from extras
        amountToPay = findViewById(R.id.pay_amount);
        amountToPay.setText("$ " + String.format("%.2f" , getIntent().getFloatExtra("totalPrice", 0)));
    }

    //on click event for Pay button
    public void Pay(View view)
    {
        MainActivity.shoppingCart.clearCart();
        finish();
        ShoppingCartActivity.Update();
    }

    //on click event for Cancel button
    public void Cancel(View view)
    {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
