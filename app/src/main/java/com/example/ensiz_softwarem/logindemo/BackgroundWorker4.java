// Connection to MYSQL database to insert User FishOrder Data
// Created by: Tharuka Sandaru
// 5-9-2019



package com.example.ensiz_softwarem.logindemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundWorker4 extends AsyncTask<String,Void,String>{

    Context context;
    AlertDialog alertDialog;
    private final CheckoutActivity.MyCallback4 myCallback4;
    //Constructor
    BackgroundWorker4(Context ctx, CheckoutActivity.MyCallback4 myCallback4) {
        context = ctx;
        this.myCallback4 = myCallback4;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String update1_url = "http://inspiredlk.com/orderdata.php";
        if(type.equals("shipp")) {
            try {
                //Create connection
                String name = params[1];
                String telephone = params[2];
                String optional_tp = params[3];
                String address = params[4];
                String city = params[5];
                String order_data = params[6];
                URL url = new URL(update1_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                //Send request
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                        +URLEncoder.encode("telephone", "UTF-8")+"="+URLEncoder.encode(telephone, "UTF-8")+"&"
                        +URLEncoder.encode("optional_tp", "UTF-8")+"="+URLEncoder.encode(optional_tp, "UTF-8")+"&"
                        +URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"
                        +URLEncoder.encode("city", "UTF-8")+"="+URLEncoder.encode(city, "UTF-8")+"&"
                        +URLEncoder.encode("order_data", "UTF-8")+"="+URLEncoder.encode(order_data, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //Get response
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String resultf) {

        myCallback4.onResult(resultf);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
