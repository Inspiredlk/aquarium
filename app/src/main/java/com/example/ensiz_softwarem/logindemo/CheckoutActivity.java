//Checkout after cart
//Created by: Tharuka Sandaru
//Date : 3/22/2019


package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {



    //variable declaring
    CheckBox chckCash;

    private Button confrmChck;
    private EditText usrName;
    private EditText usrTp;
    private EditText usrAddr;
    private EditText optionaltp;
    private EditText usrCity;

    String sub_order = " ";
    String TotalRounded;

    TextView test;
    String FullTotal;

    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;

    String[][] arrayReceived=null;
    String[] s2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


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


        mCartList = ShoppingCartHelper.getCartList();


        //assigning variable to xml components
        confrmChck = (Button) findViewById(R.id.btConfirm);
        usrName = (EditText) findViewById(R.id.etUsrName);
        usrTp = (EditText) findViewById(R.id.etTelephone);
        usrAddr = (EditText) findViewById(R.id.etAddressline1);
        optionaltp = (EditText) findViewById(R.id.etTelephone2);
        usrCity = (EditText) findViewById(R.id.etCity);
        chckCash = (CheckBox) findViewById(R.id.cashOnDelivery);
        test = (TextView) findViewById(R.id.tvDD);


        //Get data from previous Activity
        //keep
        Bundle extras = getIntent().getExtras();
        String[] subTot = extras.getStringArray("finale1");
        String[] subTot1 = extras.getStringArray("finale2");
        TotalRounded = getIntent().getStringExtra("totAmount");

       /* Bundle b = getIntent().getExtras();
        String[][] list_array = (String[][])b.getSerializable("Array");
*/
        String arrnew = Arrays.toString(subTot);
        String arrnew1 = Arrays.toString(subTot1);

        sub_order = arrnew + " " + arrnew1;


        //Validation of edit text variables
        confrmChck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(usrName.getText())) {
                    Toast.makeText(CheckoutActivity.this, "Name Field Required", Toast.LENGTH_SHORT).show();

                    usrName.setError("Reciever Name Required");
                }else if (TextUtils.isEmpty(usrTp.getText())) {
                    Toast.makeText(CheckoutActivity.this, "Telephone Field Required", Toast.LENGTH_SHORT).show();

                    usrTp.setError("Reciever Telephone Required");
                }else if (TextUtils.isEmpty(usrAddr.getText())) {
                    Toast.makeText(CheckoutActivity.this, "Address Field Required", Toast.LENGTH_SHORT).show();

                    usrAddr.setError("Reciever Address Required");
                }else if (TextUtils.isEmpty(usrCity.getText())) {
                    Toast.makeText(CheckoutActivity.this, "Address Field Required", Toast.LENGTH_SHORT).show();

                    usrCity.setError("Reciever City Required");
                }else if(chckCash.isChecked() && checkForMobile()){
                    Intent chc = (new Intent(CheckoutActivity.this, FinalActivity.class));
                    OnShipp(v);
                    List<Product> catalog = ShoppingCartHelper.getCatalog(getResources());

                    int productIndex = getIntent().getExtras().getInt(
                            ShoppingCartHelper.PRODUCT_INDEX);
                    final Product selectedProduct = catalog.get(productIndex);
                    ShoppingCartHelper.setQuantity(selectedProduct, 0);
                    chc.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                    startActivity(chc);

                }else{
                    Toast.makeText(CheckoutActivity.this, "We only have cash on delivery", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    //function of mobile number validation
    public boolean checkForMobile() {
        Context c;

        usrTp = (EditText) findViewById(R.id.etTelephone);
        String mStrMobile = usrTp.getText().toString();
        if (android.util.Patterns.PHONE.matcher(mStrMobile).matches()) {
            return true;

        }
        usrTp.setError("Enter a valid Phone Number");
        Toast.makeText(CheckoutActivity.this,"Phone No is not valid", Toast.LENGTH_LONG).show();
        return false;
    }



    //Background Worker Class Function for Shipping Details Order
    public void OnShipp(View view) {


        final String name = usrName.getText().toString().trim();
        final String telephone = usrTp.getText().toString().trim();
        final String optional_tp = optionaltp.getText().toString().trim();
        final String address = usrAddr.getText().toString().trim();
        final String city = usrCity.getText().toString().trim();
        final String order_data = sub_order;




            String type = "shipp";
            BackgroundWorker4 backgroundWorker4 = new BackgroundWorker4(getApplicationContext(), new MyCallback4() {

                @Override
                public void onResult(String resultf) {
                    if (resultf.equals("Insert Success")) {
                        Toast.makeText(CheckoutActivity.this, "Your Order Recieved", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CheckoutActivity.this, FinalActivity.class);
                        i.putExtra("TotalVal", TotalRounded);

                        startActivity(i);


                    } else {
                        Toast.makeText(CheckoutActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            backgroundWorker4.execute(type, name, telephone, optional_tp, address, city, order_data);

    }





    //callback function
    interface MyCallback4 {
        void onResult(String resultf);
    }


}
