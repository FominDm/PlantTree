package com.example.dima.myapplication;

public class Product
{
    private String name;
    private String description;
    private String category;
    private String price;

    public Product(String name, String description, String category, String price)
    {
        setName(name);
        setDescription(description);
        setCategory(category);
        setPrice(price);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return this.category;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPrice()
    {
        return this.price;
    }
}
