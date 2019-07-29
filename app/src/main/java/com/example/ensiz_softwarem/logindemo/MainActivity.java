//User Login Activity
//Created by: Tharuka Sandaru
//Date : 3/15/2019


package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    //Declaring variables
    private EditText userName;
    private EditText userPassword;
    private TextView Register;
    ProgressBar progressBar;
    private Button loginUser;
    String goback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);




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


        //Assigning variables to corresponding components
        userName = (EditText)findViewById(R.id.etName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        Register = (TextView)findViewById(R.id.tvRegister);
        progressBar = findViewById(R.id.progressBar);
        loginUser = (Button) findViewById(R.id.btLogin);

        goback = getIntent().getStringExtra("backto");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
                else {
                    Intent xx = new Intent(MainActivity.this, RegistrationActivity.class);
                    xx.putExtra("reg", goback);
                    startActivity(xx);
                }
            }
        });


        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   progressBar.setVisibility(View.VISIBLE);
                   onLogin(v);
                   progressBar.setVisibility(View.GONE);
               }
        });
    }
    public void onLogin(View v){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String prefUname = preferences.getString("uName", null);
        String prefPassword = preferences.getString("uPass", null);

        if(userName.getText().toString().trim().equals(prefUname)
                && userPassword.getText().toString().trim().equals(prefPassword)){


            /*final GlobalClass globalClass = (GlobalClass) getApplicationContext();
            globalClass.setGlobe("true");
            Intent i = new Intent(MainActivity.this, DiscoverActivity.class);
            i.putExtra("username", prefUname);
            Toast.makeText(globalClass, "Login Successfull", Toast.LENGTH_SHORT).show();
            startActivity(i);*/
            
            /*if(!goback.equals("comeback")) {
                final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                globalClass.setGlobe("true");
                Intent i = new Intent(MainActivity.this, DiscoverActivity.class);
                i.putExtra("username", prefUname);
                Toast.makeText(globalClass, "Login Successfull", Toast.LENGTH_SHORT).show();
                startActivity(i);*/
            if(goback.equals("comeback")){
                    final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                    globalClass.setGlobe("true");
                    Intent i = new Intent(MainActivity.this, ShoppingCartActivity.class);
                    i.putExtra("username", prefUname);
                    Toast.makeText(globalClass, "Login Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(i);
            }else{
                final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                globalClass.setGlobe("true");
                Intent i = new Intent(MainActivity.this, DiscoverActivity.class);
                i.putExtra("username", prefUname);
                Toast.makeText(globalClass, "Login Successfull", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        }
        else {
            Toast.makeText(MainActivity.this, "Enter a Valid Username and Password", Toast.LENGTH_LONG).show();
        }


    }



 interface MyCallback {
     void onResult(String result);
 }


 //checking connection
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
    //alert dialog for no internet connection
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accountEdit:
                final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                if(globalClass.getGlobe().equals("true")) {
                    startActivity(new Intent(MainActivity.this, UpdateAccount.class));
                    break;
                }else{
                    Toast.makeText(globalClass, "Login to Edit User Details", Toast.LENGTH_SHORT).show();
                }
            case R.id.loginmenu:
                Toast.makeText(this, "Already in Login Screen", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoutmenu:
                final GlobalClass globalClass1 = (GlobalClass) getApplicationContext();
                globalClass1.setGlobe("false");
                Toast.makeText(globalClass1, "Logged Off", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
                break;
        }

        return true;


    }


}