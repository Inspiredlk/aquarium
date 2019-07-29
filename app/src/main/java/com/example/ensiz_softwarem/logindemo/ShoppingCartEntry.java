//Shopping cart
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;

public class ShoppingCartEntry {

    private Product mProduct;
    private int mQuantity;

    public ShoppingCartEntry(Product product, int quantity) {
        mProduct = product;
        mQuantity = quantity;
    }

    public Product getProduct() {
        return mProduct;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;


    }
}
