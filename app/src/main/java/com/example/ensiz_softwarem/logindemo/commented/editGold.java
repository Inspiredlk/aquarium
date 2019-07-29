/*
package com.example.ensiz_softwarem.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editGold extends AppCompatActivity {

    private EditText value;
    private Button change;

    String passingQty;
    String newval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gold);

        value = (EditText) findViewById(R.id.etval);
        change = (Button) findViewById(R.id.btchg);

        passingQty = getIntent().getStringExtra("currentval");

        value.setText(passingQty);



    }

    public void onGchange(View v){

        Intent i = new Intent(editGold.this, dummy.class);
        newval = value.getText().toString();
        i.putExtra("newVal", newval);
        startActivity(i);
    }

}
*/
