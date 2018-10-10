package com.example.dima.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter
{
    private ArrayList<Product> list;
    private LayoutInflater li;
    private int quant;

    public ProductAdapter(Context context, ArrayList<Product> list)
    {
        this.list = list;
        li = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            view = li.inflate(R.layout.shop_view_list, parent, false);
        }

        Product product = getProduct(position);

        boolean discounted = false;
        String normalPrice = "";

        for(Product prod: MainActivity.specialsList)
        {
            if(product.getName().equals(prod.getName()))
            {
                discounted = true;
                normalPrice = product.getPrice();
                product = prod;
            }
        }
        final Product productToAdd = product;

        TextView name = view.findViewById(R.id.Name);
        name.setText(product.getName());

        TextView description = view.findViewById(R.id.Description);
        description.setText(product.getDescription());

        TextView price = view.findViewById(R.id.Price);
        TextView discountPrice = view.findViewById(R.id.DiscountPrice);

        if(discounted)
        {
            price.setText("$" + normalPrice);
            price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            discountPrice.setText("$ " + product.getPrice());
            discountPrice.setVisibility(View.VISIBLE);
        }else
        {
            price.setText("$" + product.getPrice());
            discountPrice.setVisibility(View.INVISIBLE);
        }

        EditText quantity = view.findViewById(R.id.Quantity);
        quant = 1;
        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                quant  = 1;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if(!s.toString().isEmpty())
                {
                    if (Integer.parseInt(s.toString()) <= 0)
                        quant = 1;
                    else
                        quant = Integer.parseInt(s.toString());
                }
            }
        });

        Button addToCart = view.findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.shoppingCart.addItem(productToAdd, quant);
            }
        });
        return view;
    }

    private Product getProduct(int position)
    {
        return (Product) getItem(position);
    }
}
