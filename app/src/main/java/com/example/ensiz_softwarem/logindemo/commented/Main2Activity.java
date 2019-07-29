/*
package com.example.ensiz_softwarem.logindemo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView fish_name;
    TextView fish_qty;
    TextView fish_name1;
    TextView fish_qty1;
    TextView fish_name2;
    TextView fish_qty2;
    JSONObject json = null;
    JSONObject json1 = null;
    JSONObject json2 = null;
    String str = "";
    HttpResponse response;
    Context context;
    ProgressBar progressbar;
    Button button;

    Button button2;

    Boolean a;
    Boolean b;
    Boolean c;

    int enteredQty;
    int enteredQty1;
    int enteredQty2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        progressbar = (ProgressBar)findViewById(R.id.progressBar1);
        fish_name = (TextView)findViewById(R.id.fish_nm);
        fish_qty = (TextView)findViewById(R.id.fish_qty);
        fish_name1 = (TextView)findViewById(R.id.fish_nm2);
        fish_qty1 = (TextView)findViewById(R.id.fish_qty2);
        fish_name2 = (TextView)findViewById(R.id.fish_nm3);
        fish_qty2 = (TextView)findViewById(R.id.fish_qty3);
        button = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.btupup);



        final String subTot = getIntent().getStringExtra("Total");
        final String quant = getIntent().getStringExtra("Quantity");
        final String quant1 = getIntent().getStringExtra("Quantity1");
        final String quant2 = getIntent().getStringExtra("Quantity2");

        if(quant != null) {
            enteredQty  = Integer.parseInt(quant);
        }
        else if(quant1 != null) {
            enteredQty1 = Integer.parseInt(quant1);
        }
        else if(quant2 != null) {
            enteredQty2 = Integer.parseInt(quant2);
        }



        progressbar.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

               progressbar.setVisibility(View.VISIBLE);

                new GetTextViewData(context).execute();


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               */
/* progressbar.setVisibility(View.VISIBLE);

                new GetTextViewData(context).execute();*//*


                String fish = fish_name.getText().toString();
                String qty = fish_qty.getText().toString();
                String fish1 = fish_name1.getText().toString();
                String qty1 = fish_qty1.getText().toString();
                String fish2 = fish_name2.getText().toString();
                String qty2 = fish_qty2.getText().toString();
                int q = Integer.parseInt(qty);
                int q1 = Integer.parseInt(qty1);
                int q2 = Integer.parseInt(qty2);
                if(enteredQty < q && enteredQty1 < q1 && enteredQty2 < q2) {
                    Intent i = new Intent(Main2Activity.this, CheckoutActivity.class);
                    i.putExtra("fish_fish", fish);
                    final String Sentq = Integer.toString(enteredQty);
                    i.putExtra("fish_fish_qty", Sentq);
                    i.putExtra("fish_fish1", fish1);
                    final String Sentq1 = Integer.toString(enteredQty1);
                    i.putExtra("fish_fish_qty1", Sentq1);
                    i.putExtra("fish_fish2", fish2);
                    final String Sentq2 = Integer.toString(enteredQty2);
                    i.putExtra("fish_fish_qty2", Sentq2);
                    i.putExtra("Total", subTot);
                    startActivity(i);
                }else if(enteredQty >= q){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                    builder1.setMessage("Only" + q + "Gold Fish Available! More Will be Available Soon");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Buy"+ q + "Gold Fish",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final boolean a = true;
                                }
                            });

                   builder1.setNegativeButton(
                    "Change the Order",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(Main2Activity.this, Catalog.class);
                                    startActivity(i);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    
                    
                }else if(enteredQty1 >= q1){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                    builder1.setMessage("Only" + q1 + "Fighter Fish Available! More Will be Available Soon");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Buy"+ q1 + "Fighter Fish",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final boolean b = true;
                                }
                            });

                    builder1.setNegativeButton(
                            "Change the Order",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(Main2Activity.this, Catalog.class);
                                    startActivity(i);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                    
                    
                }else if(enteredQty2 >= q2){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                    builder1.setMessage("Only" + q2 + "Saree Guppy Available! More Will be Available Soon");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Buy"+ q2 + "Saree Guppy",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final boolean c = true;
                                }
                            });

                    builder1.setNegativeButton(
                            "Change the Order",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(Main2Activity.this, Catalog.class);
                                    startActivity(i);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    
                    
                } else if(a == true || b == true || c == true) {
                    Intent i = new Intent(Main2Activity.this, CheckoutActivity.class);
                    i.putExtra("fish_fish", fish);
                    i.putExtra("fish_fish_qty", qty);
                    i.putExtra("fish_fish1", fish1);
                    i.putExtra("fish_fish_qty1", qty1);
                    i.putExtra("fish_fish2", fish2);
                    i.putExtra("fish_fish_qty2", qty2);
                    i.putExtra("Total", subTot);
                    startActivity(i);


                }else if(enteredQty < q || enteredQty1 < q1 || enteredQty2 < q2) {
                    Intent i = new Intent(Main2Activity.this, CheckoutActivity.class);
                    i.putExtra("fish_fish", fish);
                    final String Sentq = Integer.toString(enteredQty);
                    i.putExtra("fish_fish_qty", Sentq);
                    i.putExtra("fish_fish1", fish1);
                    final String Sentq1 = Integer.toString(enteredQty1);
                    i.putExtra("fish_fish_qty1", Sentq1);
                    i.putExtra("fish_fish2", fish2);
                    final String Sentq2 = Integer.toString(enteredQty2);
                    i.putExtra("fish_fish_qty2", Sentq2);
                    i.putExtra("Total", subTot);
                    startActivity(i);

                }else{
                    Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();
                }

                }
        });

    }


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
            HttpPost myConnection = new HttpPost("http://sunn-aquarium.com/send-data.php");

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
                json = jArray.getJSONObject(0);
                json1 = jArray.getJSONObject(1);
                json2 = jArray.getJSONObject(2);



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

                fish_name.setText(json.getString("fish_name"));
                fish_qty.setText(json.getString("fish_qty"));

                fish_name1.setText(json1.getString("fish_name"));
                fish_qty1.setText(json1.getString("fish_qty"));

                fish_name2.setText(json2.getString("fish_name"));
                fish_qty2.setText(json2.getString("fish_qty"));

                */
/*final String fishName = fish_name.getText().toString();
                final String fishQty = fish_qty.getText().toString();
                final String fishName1 = fish_name1.getText().toString();
                final String fishQty1 = fish_qty1.getText().toString();
                final String fishName2 = fish_name2.getText().toString();
                final String fishQty2 = fish_qty2.getText().toString();
*//*



            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.
            progressbar.setVisibility(View.GONE);

        }
    }


}*/
