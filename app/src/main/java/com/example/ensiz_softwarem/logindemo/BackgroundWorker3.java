// Connection to MYSQL database to insert User Update Data
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


public class BackgroundWorker3 extends AsyncTask<String,Void,String>{

    Context context;
    AlertDialog alertDialog;
    private final UpdateAccount.MyCallback3 myCallback3;
    //Constructor
    BackgroundWorker3(Context ctx, UpdateAccount.MyCallback3 myCallback3) {
        context = ctx;
        this.myCallback3 = myCallback3;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String update_url = "http://inspiredlk.com/update.php";
        if(type.equals("update")) {
            try {
                //Create connection
                String first_name = params[1];
                String sur_name = params[2];
                String email = params[3];
                String user_name = params[4];
                String password = params[5];
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                //Send request
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("first_name", "UTF-8")+"="+URLEncoder.encode(first_name, "UTF-8")+"&"
                        +URLEncoder.encode("sur_name", "UTF-8")+"="+URLEncoder.encode(sur_name, "UTF-8")+"&"
                        +URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        +URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
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
    protected void onPostExecute(String result3) {

        myCallback3.onResult(result3);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
