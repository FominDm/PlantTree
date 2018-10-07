package com.example.dima.myapplication;

import java.util.ArrayList;

public class ShoppingCart
{
    private ArrayList<Product> listOfItems;
    private ArrayList<Integer> quantity;

    public ShoppingCart()
    {
        listOfItems = new ArrayList<>();
        quantity = new ArrayList<>();
    }

    public void addItem(Product product, int quantity)
    {
        listOfItems.add(product);
        this.quantity.add(quantity);
    }

    public void deleteItem(int position)
    {
        listOfItems.remove(position);
        quantity.remove(position);
    }

    public Product getProduct(int position)
    {
        return listOfItems.get(position);
    }

    public int getQuantity(int position)
    {
        return quantity.get(position);
    }
}
