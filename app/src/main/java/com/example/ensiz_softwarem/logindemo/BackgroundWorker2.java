//Connection to MYSQL database to insert User Registration Data
//Created by: Tharuka Sandaru
//Date : 3/15/2019


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


public class BackgroundWorker2 extends AsyncTask<String,Void,String>{

    Context context;
    AlertDialog alertDialog;
    private final RegistrationActivity.MyCallback2 myCallback2;
    //Constructor
    BackgroundWorker2(Context ctx, RegistrationActivity.MyCallback2 myCallback2) {
        context = ctx;
        this.myCallback2 = myCallback2;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String register_url = "http://inspiredlk.com/register.php";
        if(type.equals("register")) {
            try {
                //Create connection
                String first_name = params[1];
                String sur_name = params[2];
                String email = params[3];
                String user_name = params[4];
                String password = params[5];
                URL url = new URL(register_url);
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
    protected void onPostExecute(String result1) {

        myCallback2.onResult(result1);

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
