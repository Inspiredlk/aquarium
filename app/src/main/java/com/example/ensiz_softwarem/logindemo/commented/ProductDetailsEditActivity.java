/*
//Assigning product details
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProductDetailsEditActivity extends AppCompatActivity {

    TextView productTitleTextView;

    TextView ProductfishId;

    String titlename;
    String s;
    TextView showValue;
    int counter = 0;
    String quant;
    TextView textViewCurrentQuantity;
    int quantity;
    int counterArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailsedit);

        showValue = (TextView) findViewById(R.id.counterValue1);


        List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());
        // List<SelectedProduct> catalog = ShoppingCartHelper.getCatalog(getResources());

        //int productIndex = getIntent().getExtras().getInt("f");
        // int productIndex = catalog.get(position);


        //-----------------------------------------
        //  int itemName = getIntent().getExtras().getString("Gold Fish").;

        // final Product selectedProduct = catalog.get(getIntent().getExtras().getInt(ShoppingCartHelper.TITLE));
        // catalog wp : mCartList)
        // final Product selectedProduct;

        final Product selectedProduct =    catalog.get(getIntent().getExtras().getInt(
                ShoppingCartHelper.PRODUCT_INDEX));

//--------------------
        //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //         R.id.tvfishID, R.id.tvTitle, values);

        final ArrayList<String> stringArrayList = new ArrayList<String>();


        //----------------------------

        // Set the proper image and text
        ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct1);

        productImageView.setImageDrawable(selectedProduct.productImage);

        //==================
        ProductfishId = (TextView) findViewById(R.id.tvfishID1);

        ProductfishId.setText("Id." + selectedProduct.id);
        //==========================



        productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle1);

        productTitleTextView.setText(selectedProduct.title);

        TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails1);

        productDetailsTextView.setText(selectedProduct.description);

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewProductPrice1);

        productPriceTextView.setText("$" + selectedProduct.price);






        // Update the current quantity in the cart
        textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart1);

        textViewCurrentQuantity.setText("Currently in Cart: "
                + ShoppingCartHelper.getProductQuantity(selectedProduct));

//        TextView cartnum = (TextView) findViewById(R.id.textViewCurrentlyInCart2);

        // Save a reference to the quantity edit text
//        final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        showValue.setText("" + ShoppingCartHelper.getProductQuantity(selectedProduct));

        showValue.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        counter = ShoppingCartHelper.getProductQuantity(selectedProduct);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart1);

        s = productTitleTextView.getText().toString();

        addToCartButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Check to see that a valid quantity was entered
                quantity = 0;
                try {

                    quantity = Integer.parseInt(showValue.getText()
                            .toString());
                    titlename = productTitleTextView.getText().toString();
                    quant = Integer.toString(quantity);

                    if(quantity > 0) {
                        Intent i = new Intent(ProductDetailsEditActivity.this, ShoppingCartActivity.class);
                        i.putExtra("Quantity", quant);
                        i.putExtra("Titlename", s);
                        Toast.makeText(ProductDetailsEditActivity.this, quant, Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }

                    else if(quantity < 1) {
                        Toast.makeText(ProductDetailsEditActivity.this, "Please enter a quantity higher than 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(),
                            "Please enter a valid quantity",
                            Toast.LENGTH_SHORT).show();


                }

                // If we make it here, a valid quantity was entered
                ShoppingCartHelper.setQuantity(selectedProduct, quantity);


            }
        });




    }

    public void countIN (View view){
        //increase value on click
        counter++;
        showValue.setText(Integer.toString(counter));
    }

    public void countDE (View view){
        //decrease value on click
        counter--;
        if(counter<0) {
            counter = 0;
        }
        showValue.setText(Integer.toString(counter));
    }

    public void resetCount (View view){
        counter = 0;
        showValue.setText(Integer.toString(counter));


        List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(
                ShoppingCartHelper.PRODUCT_INDEX);
        final Product selectedProduct = catalog.get(productIndex);
        ShoppingCartHelper.setQuantity(selectedProduct, 0);
        textViewCurrentQuantity.setText("Currently in Cart: " + quantity);
    }

    //Cart icon on Action Bar
    */
/*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icons,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.idCart){
            Intent xd = new Intent(ProductDetailsEditActivity.this, ShoppingCartActivity.class);
            xd.putExtra("Quantity", quant);
            xd.putExtra("Titlename", s);
            startActivity(xd);
        }
        return super.onOptionsItemSelected(item);
    }*//*


    public class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }



}

*/
