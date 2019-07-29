//Assigning product details
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class EditAct extends AppCompatActivity {


    TextView productTitleTextView;
    String titlename;
    String s;
    TextView showValue;
    int counter = 0;
    String quant;
    TextView textViewCurrentQuantity;
    int Equantity;
    String Ntitle;
    String NPos;
    String Ndesc;
    String Nprice;
    Product selectedProduct;
    Bundle bundle;


    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;
    private List<Product> mProductList;
    ArrayList<Product> arraylist = new ArrayList<Product>();
    Context context;


    HttpResponse response;
    JSONObject jary = null;
    String str = "";
    String fishName;
    String fishQty;
    int i;
    String getFishname;
    int dbQty;
    Product product = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);





        //Customize the ActionBar
        final ActionBar abar = getSupportActionBar();
        //abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.custom_actionbar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText("AQUARIUM");
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);
        abar.setDisplayShowTitleEnabled(false);
        abar.setDisplayHomeAsUpEnabled(false);
        abar.setIcon(R.color.colorBlackTransparent);
        abar.setHomeButtonEnabled(true);

        showValue = (TextView) findViewById(R.id.counterValue);



        Ntitle = getIntent().getStringExtra("TitleS");
        Ndesc = getIntent().getStringExtra("Desc");
        Nprice = getIntent().getStringExtra("price");
        NPos = getIntent().getStringExtra("Pos");

        List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());


        bundle = this.getIntent().getExtras();

        product = (Product) bundle.getSerializable("product");


        if(Ntitle.equals("Gold Fish")){
            selectedProduct  =    catalog.get(0);
        }else if(Ntitle.equals("Fighter Fish")){
            selectedProduct  =    catalog.get(1);
        }else if(Ntitle.equals("Rainbow Fish")) {
            selectedProduct  =    catalog.get(2);
        }else if(Ntitle.equals("Lion Fish")){
            selectedProduct  =    catalog.get(3);
        }else if(Ntitle.equals("Koi Fish")){
            selectedProduct  =    catalog.get(4);
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        final ArrayList<String> stringArrayList = new ArrayList<String>();



        // Set the proper image and text
        if(Ntitle.equals("Gold Fish")) {
            ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

            productImageView.setImageResource(R.drawable.goldfish);

        }else if(Ntitle.equals("Fighter Fish")){
            ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

            productImageView.setImageResource(R.drawable.fighter);

        }else if(Ntitle.equals("Rainbow Fish")){
            ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

            productImageView.setImageResource(R.drawable.sareegappi);
        }else if(Ntitle.equals("Lion Fish")) {
            ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

            productImageView.setImageResource(R.drawable.lionfish);
        }else if(Ntitle.equals("Koi Fish")) {
            ImageView productImageView = (ImageView) findViewById(R.id.ImageViewProduct);

            productImageView.setImageResource(R.drawable.koifish);
        }else{
            Toast.makeText(this, "None", Toast.LENGTH_SHORT).show();
        }




        //setting the textview/imagedata from arraylist

        productTitleTextView = (TextView) findViewById(R.id.TextViewProductTitle);

        productTitleTextView.setText(Ntitle);

        TextView productDetailsTextView = (TextView) findViewById(R.id.TextViewProductDetails);

        productDetailsTextView.setText(Ndesc);

        TextView productPriceTextView = (TextView) findViewById(R.id.TextViewProductPrice);

        productPriceTextView.setText("$" + Nprice);

        // Update the current quantity in the cart
        textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);

        textViewCurrentQuantity.setText("Currently in Cart: "
                + ShoppingCartHelper.getProductQuantity(selectedProduct));

        showValue.setText("" + ShoppingCartHelper.getProductQuantity(selectedProduct));

        showValue.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});

        counter = ShoppingCartHelper.getProductQuantity(selectedProduct);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);

        s = productTitleTextView.getText().toString();

        addToCartButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!isConnected(EditAct.this)) buildDialog(EditAct.this).show();
                else {
                    new GetTextViewData3(context).execute();
                }



            }
        });




    }

    //count increase button
    public void countIN (View view){
        //increase value on click
        counter++;
        showValue.setText(Integer.toString(counter));
    }

    //count decrease button
    public void countDE (View view){
        //decrease value on click
        counter--;
        if(counter<0) {
            counter = 0;
        }
        showValue.setText(Integer.toString(counter));
    }

    //count reset button and empty the amount
    public void resetCount1 (View view){
        counter = 0;
        showValue.setText(Integer.toString(counter));


        List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(
                ShoppingCartHelper.PRODUCT_INDEX);
        //final Product selectedProduct = catalog.get(productIndex);
        ShoppingCartHelper.setQuantity(selectedProduct, 0);
        textViewCurrentQuantity.setText("Currently in Cart: " + Equantity);
    }

    //Cart icon on Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icons,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.idCart){
            Intent xd = new Intent(EditAct.this, ShoppingCartActivity.class);
            xd.putExtra("Quantity", quant);
            xd.putExtra("Titlename", s);
            startActivity(xd);
        }
        return super.onOptionsItemSelected(item);
    }



    //declaring minimum and maximum value for the buy amount
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


    //Checking the availability of products with the database
    private class GetTextViewData3 extends AsyncTask<Void, Void, Void> {
        public Context context;


        public GetTextViewData3(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            //making the connection with db
            HttpClient myClient = new DefaultHttpClient();
            HttpPost myConnection = new HttpPost("http://inspiredlk.com/send-data.php");

            try {
                response = myClient.execute(myConnection);
                str = EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                //getting the names of the fish with json array
                JSONArray jArray = new JSONArray(str);

                for (i = 0; i < jArray.length(); i++) {
                    jary = jArray.getJSONObject(i);
                    fishName = jary.getString("fish_name");
                    fishQty = jary.getString("fish_qty");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                JSONArray jArray = new JSONArray(str);

                //getting the db data
                for (i = 0; i < jArray.length(); i++) {
                    jary = jArray.getJSONObject(i);
                    fishName = jary.getString("fish_name");
                    fishQty = jary.getString("fish_qty");

                    dbQty = Integer.parseInt(fishQty);
                    Equantity = Integer.parseInt(showValue.getText()
                            .toString());
                    getFishname = productTitleTextView.getText().toString();



                    //checking the arraylist and db values
                    if(fishName.equals(getFishname)){
                        if(dbQty < Equantity){
                            if (dbQty <= 0) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(EditAct.this);
                                builder1.setMessage("No "+ fishName +" Available");
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                showValue.setText(dbQty);
                                                textViewCurrentQuantity.setText("Currently in Cart: " + dbQty);

                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();

                            } else {

                                AlertDialog.Builder builder1 = new AlertDialog.Builder(EditAct.this);
                                builder1.setMessage("Only " + dbQty +  fishName + " Available! More Will be Available Soon");
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Buy " + dbQty +" " +fishName,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Toast.makeText(EditAct.this, "kkk", Toast.LENGTH_SHORT).show();
                                                List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());
                                                int productIndex = getIntent().getExtras().getInt(
                                                        ShoppingCartHelper.PRODUCT_INDEX);
                                                //final Product selectedProduct = catalog.get(productIndex);
                                                try {

                                                    titlename = productTitleTextView.getText().toString();
                                                    quant = Integer.toString(dbQty);

                                                    if(Equantity > 0) {
                                                        Intent i = new Intent(EditAct.this, ShoppingCartActivity.class);
                                                        i.putExtra("Quantity", quant);
                                                        i.putExtra("Titlename", s);
                                                        Toast.makeText(EditAct.this, quant, Toast.LENGTH_SHORT).show();

                                                        startActivity(i);
                                                        finishAffinity();
                                                        finish();
                                                    }

                                                    else if(Equantity < 1) {
                                                        Toast.makeText(EditAct.this, "Please enter a quantity higher than 0", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }

                                                } catch (Exception e) {
                                                    Toast.makeText(getBaseContext(),
                                                            "Please enter a valid quantity",
                                                            Toast.LENGTH_SHORT).show();


                                                }

                                                // If we make it here, a valid quantity was entered
                                                ShoppingCartHelper.setQuantity(product, dbQty);
                                            }
                                        });

                                builder1.setNegativeButton(
                                        "Change the Order",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent i = new Intent(EditAct.this, Catalog.class);
                                                startActivity(i);
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                                break;
                            }
                        }else{
                            List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());
                            int productIndex = getIntent().getExtras().getInt(
                                    ShoppingCartHelper.PRODUCT_INDEX);
                            //final Product selectedProduct = catalog.get(productIndex);

                            Equantity = 0;
                            try {


                                Equantity = Integer.parseInt(showValue.getText()
                                        .toString());
                                titlename = productTitleTextView.getText().toString();
                                quant = Integer.toString(Equantity);

                                if(Equantity > 0) {
                                    Intent i = new Intent(EditAct.this, ShoppingCartActivity.class);
                                    i.putExtra("Quantity", quant);
                                    i.putExtra("Titlename", s);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    finish();
                                    startActivity(i);

                                }

                                else if(Equantity < 1) {
                                    Toast.makeText(EditAct.this, "Please enter a quantity higher than 0", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(),
                                        "Please enter a valid quantity",
                                        Toast.LENGTH_SHORT).show();


                            }

                            // If we make it here, a valid quantity was entered

                            ShoppingCartHelper.setQuantity(selectedProduct,Equantity);

                        }
                    }



                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if(netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }



}

