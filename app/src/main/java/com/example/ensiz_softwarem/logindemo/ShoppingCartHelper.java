//Details of the product hardcode
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;


import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
    public static final String TITLE = "title";
    public static  int positionIndex ;

    private static List<Product> catalog;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();


    //Assigning hard code values to product details
    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<Product>();
// adding elements into the enumeration
          //  Enumeration e = catalog..elements();

            catalog.add(new Product("1","Gold Fish", res
                    .getDrawable(R.drawable.goldfish),
                    "The goldfish is a freshwater fish in the family Cyprinidae of order Cypriniformes. " +
                            "It is one of the most commonly kept aquarium fish. A relatively small member of the carp family, " +
                            "the goldfish is native to East Asia.", 29.99, 50));


            catalog.add(new Product("2", "Fighter Fish", res
                    .getDrawable(R.drawable.fighter),
                    "The Siamese fighting fish, also known as the betta, is a popular fish in the aquarium trade. " +
                            "Bettas are a member of the gourami family and are known to be highly territorial.", 24.99, 100));


            catalog.add(new Product("3", "Rainbow Fish", res
                    .getDrawable(R.drawable.sareegappi),
                    "The guppy, also known as millionfish and rainbow fish, is one of the world's most widely distributed" +
                            " tropical fish, and one of the most popular freshwater aquarium fish species. It is a member of the family " +
                            "Poeciliidae and, like almost all American members of the family, is live-bearing.", 14.99, 40));
            catalog.add(new Product("4", "Lion Fish", res
                    .getDrawable(R.drawable.lionfish),
                    "The guppy, also known as millionfish and rainbow fish, is one of the world's most widely distributed" +
                            " tropical fish, and one of the most popular freshwater aquarium fish species. It is a member of the family " +
                            "Poeciliidae and, like almost all American members of the family, is live-bearing.", 42.99, 40));
            catalog.add(new Product("5", "Koi Fish", res
                    .getDrawable(R.drawable.koifish),
                    "The guppy, also known as millionfish and rainbow fish, is one of the world's most widely distributed" +
                            " tropical fish, and one of the most popular freshwater aquarium fish species. It is a member of the family " +
                            "Poeciliidae and, like almost all American members of the family, is live-bearing.", 49.99, 40));
        }

        return catalog;
    }



    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }
    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }

}
