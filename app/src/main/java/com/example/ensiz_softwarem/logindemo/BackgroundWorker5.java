// Connection to MYSQL database to insert User FishUpdate Data
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


public class BackgroundWorker5 extends AsyncTask<String,Void,String>{

    Context context;
    AlertDialog alertDialog;
    private final ShoppingCartActivity.MyCallback5 myCallback5;
    //Constructor
    BackgroundWorker5(Context ctx, ShoppingCartActivity.MyCallback5 myCallback5) {
        context = ctx;
        this.myCallback5 = myCallback5;
    }
    @Override
    protected String doInBackground(String... params) {
//--------------------------------
        String[] fish_quantity = new String[params.length];
        String[] fish_nametype = new String[params.length];
//-----------------------------
        String type = params[0];
        String update1_url = "http://inspiredlk.com/updatefish.php";
        if(type.equals("buy")) {
            try {

                //Create connection
                String fish_type = params[1];
                String fish_qty = params[2];


                URL url = new URL(update1_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                //Send request
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //----------------------------------------------
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("fish_qty", "UTF-8")+"="+URLEncoder.encode(fish_qty, "UTF-8")+"&"
                        +URLEncoder.encode("fish_type", "UTF-8")+"="+URLEncoder.encode(fish_type, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                //------------------------------------------------
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
    protected void onPostExecute(String resulti) {

        myCallback5.onResult(resulti);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
