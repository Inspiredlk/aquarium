/*
package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class dummy extends AppCompatActivity {

    String Hnames;
    String Hnames1;

    static String App;
    static String Aqq;

    String Geditedval;

    static String App1;
    static String Aqq1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);




        Geditedval = getIntent().getStringExtra("newVal");


        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
        App = preferences1.getString("dummyqty", null);
        Aqq = preferences1.getString("dummyp", null);

        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
        App1 = preferences2.getString("dummyqty1", null);
        Aqq1 = preferences2.getString("dummyp1", null);


*/
/*        Hnames = getIntent().getStringExtra("Titlename");
        final String pp = getIntent().getStringExtra("Price");
        final String qq = getIntent().getStringExtra("Quantity");

        Hnames1 = getIntent().getStringExtra("Titlename1");
        final String pp1 = getIntent().getStringExtra("Price1");
        final String qq1 = getIntent().getStringExtra("Quantity1");*//*



*/
/*        App = pp;
        Aqq = qq;
        App1 = pp1;
        Aqq1 = qq1;*//*





        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                if(user.getName().equals("Gold Fish")){
                    Intent f = new Intent(dummy.this, editGold.class);
                    String quantity = user.getDesignation();
                    f.putExtra("currentval", quantity);
                    startActivity(f);
                }
                Toast.makeText(dummy.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList getListData() {
        ArrayList<ListItem> results = new ArrayList<>();
        if(Geditedval == null){
            ListItem user1 = new ListItem();
            user1.setName("Gold Fish");
            user1.setDesignation(App);
            user1.setLocation(Aqq);
            results.add(user1);
        }else{
            ListItem user1 = new ListItem();
            user1.setName("Gold Fish");
            user1.setDesignation(Geditedval);
            user1.setLocation(Aqq);
            results.add(user1);
        }

        ListItem user2 = new ListItem();
        user2.setName("Fighter Fish");
        user2.setDesignation(App1);
        user2.setLocation(Aqq1);
        results.add(user2);
        return results;

//        user2.setName("Rohini Alavala");
//        user2.setDesignation("Agricultural Officer");
//        user2.setLocation("Guntur");
//        results.add(user2);
//        ListItem user3 = new ListItem();
//        user3.setName("Trishika Dasari");
//        user3.setDesignation("Charted Accountant");
//        user3.setLocation("Guntur");
//        results.add(user3);

    }

    public void onBang(View v){
        Intent i = new Intent(dummy.this, Catalog.class);
        startActivity(i);
    }

}
*/
