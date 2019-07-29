//Home Page with Categories
//Created by: Tharuka Sandaru
//Date : 3/15/2019


package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoverActivity extends AppCompatActivity {

    //Declaring variables
    private ImageView categoryImage1;
    private ImageView categoryImage2;
    private ImageView categoryImage3;

    String fix = "fixme";

    String currentUname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);



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


        //implementing variables to components
        categoryImage1 = (ImageView)findViewById(R.id.catImage1);
        categoryImage2 = (ImageView)findViewById(R.id.catImage2);
        categoryImage3 = (ImageView)findViewById(R.id.catImage3);

        currentUname = getIntent().getStringExtra("username");

        /**********Show description when category image clicked********************/
        categoryImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, Catalog.class));
            }
        });

        categoryImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, Catalog.class));
            }
        });

        categoryImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiscoverActivity.this, Catalog.class));
            }
        });
        /*********************************************************************/


        //Bottom Navigation for Home, Account and Settings
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNav_Bar);
        final Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.mnHome:
//                        Intent home = new Intent(DiscoverActivity.this, DiscoverActivity.class);
//                        startActivity(home);
                        break;
                    case R.id.mnAccount:

                        GlobalClass globalClass = (GlobalClass) getApplicationContext();
                        if(globalClass.getGlobe()=="true") {
                            menu.getItem(1).setEnabled(true);
                            Intent account = new Intent(DiscoverActivity.this, UpdateAccount.class);
                            account.putExtra("username", currentUname);
                            startActivity(account);
                            break;
                        }else{

                            menu.getItem(1).setEnabled(false);
                            Intent account = new Intent(DiscoverActivity.this, DiscoverActivity.class);
                            Toast.makeText(DiscoverActivity.this, "Login To Edit Account", Toast.LENGTH_SHORT).show();
                            startActivity(account);
                            break;
                        }
                    case R.id.mnGallery:
                        globalClass = (GlobalClass) getApplicationContext();
                        if(globalClass.getGlobe()=="true") {
                            Intent gallery = new Intent(DiscoverActivity.this, GlideActivity.class);
                            gallery.putExtra("username", currentUname);
                            startActivity(gallery);
                            break;
                        }
                        else {
                            Intent gallery = new Intent(DiscoverActivity.this, GlideActivity.class);
                            startActivity(gallery);
                            break;
                        }

                }


                return true;
            }
        });


    }


    //settings menu
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
                GlobalClass globalClass = (GlobalClass) getApplicationContext();
                if(globalClass.getGlobe()=="true"){
                    Intent i = new Intent(DiscoverActivity.this, UpdateAccount.class);
                    i.putExtra("username", currentUname);
                    startActivity(i);
                    break;
                }
                else{
                    Toast.makeText(globalClass, "Login First", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DiscoverActivity.this, MainActivity.class);
                    i.putExtra("backto", fix);
                    startActivity(i);
                    break;
                }
            case R.id.loginmenu:
                GlobalClass globalClass1 = (GlobalClass) getApplicationContext();
                if(globalClass1.getGlobe()=="true") {
                    Toast.makeText(globalClass1, "Log off to Login again!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(DiscoverActivity.this, MainActivity.class);
                    i.putExtra("backto", fix);
                    startActivity(i);
                    break;
                }
            case R.id.logoutmenu:
                GlobalClass globalClass2 = (GlobalClass) getApplicationContext();
                if(globalClass2.getGlobe()=="true"){
                    globalClass2.setGlobe("false");
                    Toast.makeText(globalClass2, "Logged Off", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DiscoverActivity.this, DiscoverActivity.class));
                    break;
                }
                else if(globalClass2.getGlobe()=="false"){
                    item.setEnabled(false);
                    break;
                }
        }

        return true;


    }

    //checking internet connection
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

}
