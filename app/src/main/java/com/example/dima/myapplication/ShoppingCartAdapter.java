package com.example.dima.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ShoppingCartAdapter extends BaseAdapter
{
    private ShoppingCart shoppingCart;
    private LayoutInflater li;

    public ShoppingCartAdapter(Context context,ShoppingCart shoppingCart)
    {
        this.shoppingCart = shoppingCart;
        li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return shoppingCart.getSize();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCart.getProduct(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            view = li.inflate(R.layout.shopping_cart_list, parent, false);
        }

        Product product = getProduct(position);
        int qunt = getQuantity(position);
        float cost = Float.valueOf(product.getPrice());
        boolean discounted = false;
        String discountedPrice = "";
        for(Product prod: MainActivity.specialsList)
        {
            if(prod.getName().equals(product.getName()))
            {
                discounted = true;
                discountedPrice = prod.getPrice();
                cost = Float.valueOf(discountedPrice);
            }
        }
        float total = qunt*cost;

        TextView name = view.findViewById(R.id.name);
        name.setText(product.getName());

        TextView price = view.findViewById(R.id.price);
        if(discounted)
        {
            price.setText("$ " + discountedPrice);
            price.setTextColor(Color.RED);
        }else
        {
            price.setText("$ " + product.getPrice());

        }

        TextView totalCost = view.findViewById(R.id.total_cost);
        totalCost.setText("$ " + String.format("%.2f", total));

        EditText quantity = view.findViewById(R.id.quantity);
        quantity.setText(String.valueOf(qunt));
        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty())
                {
                    if(Integer.parseInt(s.toString()) == 0)
                    {
                        RemoveItem(position);
                    }else
                    {
                        shoppingCart.changeProductQuantity(position, Integer.parseInt(s.toString()));
                        ShoppingCartActivity.Update();
                    }
                }else
                {
                    RemoveItem(position);
                }
            }
        });

        Button remove = view.findViewById(R.id.remove_btn);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveItem(position);
            }
        });
        return view;
    }

    private Product getProduct(int position)
    {
        return (Product) getItem(position);
    }

    private int getQuantity(int position)
    {
        return shoppingCart.getQuantity(position);
    }

    private void RemoveItem(int position)
    {
        shoppingCart.deleteItem(position);
        ShoppingCartActivity.Update();
    }
}
