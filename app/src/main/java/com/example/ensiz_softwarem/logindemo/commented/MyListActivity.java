/*
package com.example.ensiz_softwarem.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MyListActivity extends AppCompatActivity {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] name;
    String[] description;
    String[] price;
    int[] fish;
    ArrayList<FishProduct> arraylist = new ArrayList<FishProduct>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        // Generate sample data
        name = new String[] { "gold fish", "Fighter Fish", "Gappi Fish" };

        description = new String[] { "This is a Fish Description", "This is a Fish Description", "This is a Fish Description" };

        price = new String[] { "Rs.200", "Rs.130", "Rs.180" };

        fish = new int[] { R.drawable.goldfish, R.drawable.fighter,
                R.drawable.gappifish };

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < name.length; i++)
        {
            FishProduct wp = new FishProduct(name[i], description[i],
                    price[i], fish[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}
*/
