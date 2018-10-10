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

    public int getSize()
    {
        return listOfItems.size();
    }

    //replace product quantity if it is already in shopping cart
    //or add new product to the shopping cart
    public void addItem(Product product, int quantity)
    {
        int result = checkProduct(product);

        if(result >= 0)
        {
            changeProductQuantity(result, this.quantity.get(result) + quantity);
        }else
        {
            this.listOfItems.add(product);
            this.quantity.add(quantity);
        }
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

    public void changeProductQuantity(int position, int newQuantity)
    {
        this.quantity.add(position, newQuantity);
        this.quantity.remove(position+1);
    }

    //Checks if product already in shopping cart
    private int checkProduct(Product product)
    {
        int res = -1;

        if(listOfItems.size() > 0)
        {
            for(int i = 0; i < listOfItems.size(); i++)
            {
                if(getProduct(i).equals(product))
                {
                    res = i;
                    break;
                }
            }
        }
        return res;
    }
}
