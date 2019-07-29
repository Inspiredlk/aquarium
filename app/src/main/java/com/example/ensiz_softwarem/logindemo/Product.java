//Product Details
//Created by: Tharuka Sandaru
//Date : 3/22/2019


package com.example.ensiz_softwarem.logindemo;

import android.graphics.drawable.Drawable;


public class Product {

    public String id;
    public String title;
    public Drawable productImage;
    public String description;
    public double price;
    public int quantity;
    public boolean selected;

    public Product(String id, String title, Drawable productImage, String description,
                   double price, int quantity) {
        this.id = id;
        this.title = title;
        this.productImage = productImage;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }

    public Drawable getImg(){
        return this.productImage;
    }

    public double getPrice() {
        return this.price;
    }

}
