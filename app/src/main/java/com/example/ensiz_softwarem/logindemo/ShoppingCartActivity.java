//Shopping cart
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;

import java.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import android.widget.TextView;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.DialogInterface;
import android.widget.Toast;

import com.google.gson.JsonObject;


public class ShoppingCartActivity extends AppCompatActivity {


    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;
    private List<Product> mProductList;
    ArrayList<Product> arraylist = new ArrayList<Product>();
    private Button chkoutprc;
    private Button shopMore;
    Context context;
    JSONObject jary = null;
    String str = "";
    HttpResponse response;
    ProgressBar progressbar;
    Button button;
    int i;





    double subTotalround = 0;

    boolean a = false;
    boolean b = false;
    boolean c = false;
    boolean d = false;
    boolean e = false;



    int finQ;
    String fullTot;


    String fishName = "";
    String fishQty = "";

    String finalQuantity;

    String finalDb;

    ListView listViewCatalog;

    TextView productPriceTextView;
    String log;

    double priceVal;
    String priceValString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcart);


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
        
        mProductList = ShoppingCartHelper.getCatalog(getResources());


        mCartList = ShoppingCartHelper.getCartList();


        chkoutprc = (Button) findViewById(R.id.btCheckout);
        shopMore = (Button) findViewById(R.id.btShopMore);




        // Make sure to clear the selections
       for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }


        // Create the list
        listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);
        listViewCatalog.setLongClickable(true);


        listViewCatalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int Pos  = position;



                Product selctedProd = mCartList.get(position);

                priceVal = selctedProd.getPrice();
                priceValString = Double.toString(priceVal);


                    Intent productDetailsEditIntent = new Intent(ShoppingCartActivity.this, EditAct.class);

                    productDetailsEditIntent.putExtra("Pos",position);
                    productDetailsEditIntent.putExtra("TitleS",selctedProd.getName());
                    productDetailsEditIntent.putExtra("Desc",selctedProd.getDescription());
                    productDetailsEditIntent.putExtra("price", priceValString);

                    startActivity(productDetailsEditIntent);
                }

        });


       //checkout button click event
        chkoutprc.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                GlobalClass globalClass = (GlobalClass) getApplicationContext();
                if(globalClass.getGlobe()=="false") {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ShoppingCartActivity.this);
                    builder1.setMessage("Please Login First");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    log = "comeback";
                                    Intent logg = new Intent(ShoppingCartActivity.this, MainActivity.class);
                                    logg.putExtra("backto",log);
                                    startActivity(logg);

                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else if(!isConnected(ShoppingCartActivity.this)) {
                    buildDialog(ShoppingCartActivity.this).show();

                }else if(mCartList.size()<=0 || subTotalround == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ShoppingCartActivity.this);
                    builder1.setMessage("Cart is Empty");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(ShoppingCartActivity.this, Catalog.class));

                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else{

                    new GetTextViewData(context).execute();
                }

            }
        });

        //continue shopping button click event
        shopMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShoppingCartActivity.this, Catalog.class));
            }
        });

    }


    //Adapter
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }

        double subTotal = 0;

        for(Product p : mCartList) {
            int quantity = ShoppingCartHelper.getProductQuantity(p);
            subTotal += p.price * quantity;
            subTotalround = (double) Math.round(subTotal * 100) / 100;

        }

        productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
        productPriceTextView.setText("Subtotal: $" + subTotalround);


        fullTot = Double.toString(subTotalround);

    }

    //setting up db connection
    private class GetTextViewData extends AsyncTask<Void, Void, Void>
    {
        public Context context;


        public GetTextViewData(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

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


            try{
                JSONArray jArray = new JSONArray(str);

                for ( i=0; i < jArray.length(); i++) {
                    jary = jArray.getJSONObject(i);
                    fishName = jary.getString("fish_name");
                    fishQty = jary.getString("fish_qty");
                }




            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {
                JSONArray jArray = new JSONArray(str);



                String[] nameArray = new String[mCartList.size()];
                String[] qtyArray = new String[mCartList.size()];
                int row = 0;
                int col = 0;

                for ( i=0; i < jArray.length(); i++) {
                    jary = jArray.getJSONObject(i);
                    fishName = jary.getString("fish_name");
                    fishQty = jary.getString("fish_qty");
                    int dbQty = Integer.parseInt(fishQty);





                    for (Product p : mCartList) {


                        int quantity = ShoppingCartHelper.getProductQuantity(p);
                        String name = p.title;

                        //#2a767a





                        if (name.equals(fishName)) {
                            finQ = dbQty - quantity;
                            finalQuantity = Integer.toString(quantity);

                            finalDb = Integer.toString(finQ);

                              nameArray[row] = name;
                              qtyArray[col] = finalQuantity;
                              row++;
                              col++;

                                String type = "buy";
                                BackgroundWorker5 backgroundWorker5 = new BackgroundWorker5(getApplicationContext(), new MyCallback5() {

                                    @Override
                                    public void onResult(String resulti) {
                                        if (resulti.equals("Record Updated Successfully")) {
                                            Toast.makeText(ShoppingCartActivity.this, "DB Updated", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ShoppingCartActivity.this, "Error Update DB", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                backgroundWorker5.execute(type, fishName, finalDb);



                        } else {
                            //nothing happens here
                        }


                    }

                }
                Intent shp = new Intent(ShoppingCartActivity.this, CheckoutActivity.class);
                shp.putExtra("finale1", nameArray);
                shp.putExtra("finale2", qtyArray);
                String roundedVal = Double.toString(subTotalround);
                shp.putExtra("totAmount", roundedVal);
                mCartList.clear();
                mCartList.removeAll(mCartList);
                mProductAdapter.notifyDataSetChanged();
                shp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(shp);


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

interface MyCallback5 {
    void onResult(String resulti);
}

}
