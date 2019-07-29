//Main Page
//Created by: Tharuka Sandaru
//Date : 3/15/2019


package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class TourActivity extends AppCompatActivity {

    private Button startTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);


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


        startTour = (Button) findViewById(R.id.btTour);


        startTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass globalClass = (GlobalClass) getApplicationContext();
                globalClass.setGlobe("false");
                startActivity(new Intent(TourActivity.this, DiscoverActivity.class));
            }
        });
    }
}
