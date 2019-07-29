/*
//Gallery in the mainpage
//Created by: Tharuka Sandaru
//Date : 3/22/2019



package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {


    //Image titles
    private final String image_titles[] = {
            "platyred",
            "clownfish",
            "goldfish",
            "sareegappi",
            "gappifish",
            "tropicalfish",
            "koifish",
            "lionfish",
            "sareegappi new",
            "gourami",
            "discus",
            "fighter",

    };

    //getting images from the drawable directory
    private final Integer image_ids[] = {
            R.drawable.platyred,
            R.drawable.clownfish,
            R.drawable.goldfish,
            R.drawable.sareegappi,
            R.drawable.gappifish,
            R.drawable.tropicalfish,
            R.drawable.koifish,
            R.drawable.lionfish,
            R.drawable.sareegappi2,
            R.drawable.gourami,
            R.drawable.discus,
            R.drawable.fighter

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<Cell> cells = prepareData();
        MyAdapter adapter = new MyAdapter(getApplicationContext(), cells);
        recyclerView.setAdapter(adapter);


        */
/*//*
/Bottom Navigation view
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNav_Bar);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.mnHome:
                        Intent home = new Intent(GalleryActivity.this, DiscoverActivity.class);
                        startActivity(home);
                        break;
                    case R.id.mnAccount:
                        Intent account = new Intent(GalleryActivity.this, MainActivity.class);
                        startActivity(account);
                        break;
                    case R.id.mnGallery:
                        Intent gallery = new Intent(GalleryActivity.this, GalleryActivity.class);
                        startActivity(gallery);
                        break;
                }


                return true;
            }
        });
*//*




    }


    //Calling cell class to assign images
    private ArrayList<Cell> prepareData(){
        ArrayList<Cell> theimage = new ArrayList<>();
        for(int i = 0; i < image_titles.length;i++){
            Cell cell = new Cell();
            cell.setTitle(image_titles[i]);
            cell.setImg(image_ids[i]);
            theimage.add(cell);
        }
        return theimage;
    }

}
*/
