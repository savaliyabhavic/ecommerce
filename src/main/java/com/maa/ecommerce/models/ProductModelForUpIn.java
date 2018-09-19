package com.maa.ecommerce.models;

public class ProductModelForUpIn
{
    private int  id;
    private String name;
    private String descrption;
    private int qty;
    private int price;
    private int category;

    public int getCategory()
    {
        return category;
    }

    public void setCategory(int category)
    {
        this.category = category;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescrption()
    {
        return descrption;
    }

    public void setDescrption(String descrption)
    {
        this.descrption = descrption;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
}
