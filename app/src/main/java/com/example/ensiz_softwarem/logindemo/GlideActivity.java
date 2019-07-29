package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ensiz_softwarem.logindemo.Adapters.GalleryImageAdapter;
import com.example.ensiz_softwarem.logindemo.interfaces.IRecyclerViewClickListener;

import java.util.Random;

public class GlideActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String currentUname;
    String fix1 = "fixme";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);





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

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        currentUname = getIntent().getStringExtra("username");


        Random random = new Random();
        final Integer[] images = new Integer[10];
        for(int i=0; i<images.length;i++) {
            images[0] = R.drawable.fighter;
            images[1] = R.drawable.goldfish;
            images[2] = R.drawable.gappifish;
            images[3] = R.drawable.tropicalfish;
            images[4] = R.drawable.koifish;
            images[5] = R.drawable.lionfish;
            images[6] = R.drawable.sareegappi;
            images[7] = R.drawable.sareegappi2;
            images[8] = R.drawable.gourami;
            images[9] = R.drawable.clownfish;
        }
        /*+random.nextInt(1000+1);*/

        final IRecyclerViewClickListener listener = new IRecyclerViewClickListener() {
            @Override
            public void onclick(View view, int position) {

                //open the activity fullscreen
                Intent i = new Intent(getApplicationContext(),Catalog.class);
                /*i.putExtra("Images",images);
                i.putExtra("position", position);*/
                startActivity(i);

            }
        };


        GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(this,images,listener);
        recyclerView.setAdapter(galleryImageAdapter);


        //Bottom Navigation view
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNav_Bar);
        final Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.mnHome:
                        Intent home = new Intent(GlideActivity.this, DiscoverActivity.class);
                        startActivity(home);
                        break;
                    case R.id.mnAccount:
                        final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                        if(globalClass.getGlobe()=="true") {
                            Intent account = new Intent(GlideActivity.this, UpdateAccount.class);
                            account.putExtra("username", currentUname);
                            startActivity(account);
                            break;
                        }
                        else{
                            Toast.makeText(GlideActivity.this, "Login To Edit Account", Toast.LENGTH_SHORT).show();
                            menu.getItem(1).setEnabled(false);
                        }
                    case R.id.mnGallery:
                        Intent gallery = new Intent(GlideActivity.this, GlideActivity.class);
                        startActivity(gallery);
                        break;
                }


                return true;
            }
        });




    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //bottom navigation view
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accountEdit:
                GlobalClass globalClass = (GlobalClass) getApplicationContext();
                if(globalClass.getGlobe()=="true"){
                    Intent i = new Intent(GlideActivity.this, UpdateAccount.class);
                    i.putExtra("username", currentUname);
                    startActivity(i);
                    break;
                }
                else{
                    Toast.makeText(globalClass, "Login First", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(GlideActivity.this, MainActivity.class);
                    i.putExtra("backto", fix1);
                    startActivity(i);
                    break;
                }
            case R.id.loginmenu:
                Intent i = new Intent(GlideActivity.this, MainActivity.class);
                i.putExtra("backto", fix1);
                startActivity(i);
                break;
            case R.id.logoutmenu:
                GlobalClass globalClass1 = (GlobalClass) getApplicationContext();
                if(globalClass1.getGlobe()=="true"){
                    globalClass1.setGlobe("false");
                    Toast.makeText(globalClass1, "Logged Off", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(GlideActivity.this, DiscoverActivity.class));
                    break;
                }
                else if(globalClass1.getGlobe()=="false"){
                    item.setEnabled(false);
                    break;
                }
        }

        return true;


    }
}
