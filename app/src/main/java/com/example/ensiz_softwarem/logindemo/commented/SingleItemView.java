/*
package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItemView extends AppCompatActivity {

    // Declare Variables
    TextView txtname;
    TextView txtdescription;
    TextView txtprice;
    ImageView imgfish;
    String name;
    String description;
    String price;
    int fish;

    int counter = 0;
    TextView showValue;
    int quantity;
    String fix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of name
        name = i.getStringExtra("name");
        // Get the results of description
        description = i.getStringExtra("description");
        // Get the results of price
        price = i.getStringExtra("price");
        // Get the results of fish
        fish = i.getIntExtra("fish", fish);

        // Locate the TextViews in singleitemview.xml
        txtname = (TextView) findViewById(R.id.name);
        txtdescription = (TextView) findViewById(R.id.description);
        txtprice = (TextView) findViewById(R.id.price);

        // Locate the ImageView in singleitemview.xml
        imgfish = (ImageView) findViewById(R.id.fish);

        // Load the results into the TextViews
        txtname.setText(name);
        txtdescription.setText(description);
        txtprice.setText(price);

        // Load the image into the ImageView
        imgfish.setImageResource(fish);


        showValue = (TextView) findViewById(R.id.counterValue);
        showValue.setFilters(new InputFilter[]{ new SingleItemView.InputFilterMinMax("0", "100")});


        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(showValue.getText()
                        .toString());
                fix = showValue.getText().toString();
                if(quantity < 1 ){
                    Toast.makeText(SingleItemView.this, "Please enter a value of zero or above", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(SingleItemView.this, DiscoverActivity.class);
                    i.putExtra("x", name);
                    i.putExtra("y", price);
                    i.putExtra("z", fix);
                    startActivityForResult(i,1);

                }
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

    public void resetCount (View view) {
        counter = 0;
        showValue.setText(Integer.toString(counter));

    }



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
