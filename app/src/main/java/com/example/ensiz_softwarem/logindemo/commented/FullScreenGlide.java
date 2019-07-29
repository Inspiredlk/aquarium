/*
package com.example.ensiz_softwarem.logindemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ensiz_softwarem.logindemo.Adapters.FullSizeAdapter;

public class FullScreenGlide extends Activity {

    ViewPager viewPager;
    Integer[] images;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_glide);

        if(savedInstanceState == null){
            Intent i = getIntent();
            images = i.getIntExtra("Images");
            position = i.getIntExtra("position",0);
        }

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        FullSizeAdapter fullSizeAdapter = new FullSizeAdapter(this,images);
        viewPager.setAdapter(fullSizeAdapter);
        viewPager.setCurrentItem(position,true);

    }
    public void onPurch(View v){

        Button purchBut;

        purchBut = (Button)findViewById(R.id.btPScreen);

        purchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullScreenGlide.this, Catalog.class));
            }
        });


    }

}
*/
