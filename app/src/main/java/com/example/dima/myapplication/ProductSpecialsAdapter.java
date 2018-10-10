package com.example.dima.myapplication;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductSpecialsAdapter extends BaseAdapter
{
    private ArrayList<Product> list;
    private LayoutInflater li;

    public ProductSpecialsAdapter(Context context, ArrayList<Product> list)
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
            view = li.inflate(R.layout.specials_view_list, parent, false);
        }

        final Product product = getProduct(position);

        TextView name = view.findViewById(R.id.Name);
        name.setText(product.getName());

        TextView description = view.findViewById(R.id.Description);
        description.setText(product.getDescription());

        TextView normalPrice = view.findViewById(R.id.NormalPrice);
        for(Product prod: MainActivity.productList)
        {
            if(prod.getName().equals(product.getName()))
            {
                normalPrice.setText("$ " + prod.getPrice());
                normalPrice.setPaintFlags(normalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                break;
            }
        }

        TextView specialPrice = view.findViewById(R.id.SpecialPrice);
        specialPrice.setText("$ " + product.getPrice());

        Button addToCart = view.findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MainActivity.shoppingCart.addItem(product, 1);
            }
        });
        return view;
    }

    private Product getProduct(int position)
    {
        return (Product) getItem(position);
    }
}
