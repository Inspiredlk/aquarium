//User Registration Activity
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
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UpdateAccount extends AppCompatActivity {

    //Declaring variables
    protected EditText UfirstName;
    private EditText UlastName;
    private EditText UuserEmail;
    private EditText UuserName;
    private EditText UuserPassword;
    private Button updateUser;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private TextView loggedUname;
    String updateName, updatePass;
    /*SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;*/
    private Button logIn;
    private Button backHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);


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
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setIcon(R.color.colorBlackTransparent);
        abar.setHomeButtonEnabled(true);


        /********************************/
        //Assigning variables to corresponding components
        UfirstName = (EditText) findViewById(R.id.etuFirstName);
        UlastName = (EditText) findViewById(R.id.etuSurName);
        UuserEmail = (EditText) findViewById(R.id.etuUserEmail);
        /*UuserName = (EditText) findViewById(R.id.etuUserName);*/
        UuserPassword = (EditText) findViewById(R.id.etuUserPassword);
        updateUser = (Button) findViewById(R.id.btUpdate);

        loggedUname = (TextView) findViewById(R.id.tvlogged);

        final String currentUname = getIntent().getStringExtra("username").trim();

        loggedUname.setText(currentUname);



        //Sign Up button click event
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isConnected(UpdateAccount.this)) {
                    buildDialog(UpdateAccount.this).show();

                } else if (TextUtils.isEmpty(UfirstName.getText())) {
                    Toast.makeText(UpdateAccount.this, "First Name Empty", Toast.LENGTH_SHORT).show();

                    UfirstName.setError("First Name is Required");

                } else if (TextUtils.isEmpty(UlastName.getText())) {
                    Toast.makeText(UpdateAccount.this, "Last Name Empty", Toast.LENGTH_SHORT).show();

                    UlastName.setError("Last Name is Required");

                } else if (TextUtils.isEmpty(UuserEmail.getText())) {
                    Toast.makeText(UpdateAccount.this, "Email Empty", Toast.LENGTH_SHORT).show();

                    UuserEmail.setError("User Email is Required");

                }else if (TextUtils.isEmpty(UuserPassword.getText())) {
                    Toast.makeText(UpdateAccount.this, "Password Empty", Toast.LENGTH_SHORT).show();

                    UuserPassword.setError("User Password is Required");

                }else if (checkForEmail()) {
                    /*editor.putString("uPass", updatePass);
                    editor.commit();
                    Toast.makeText(UpdateAccount.this, "SharedPref Updated", Toast.LENGTH_SHORT).show();*/
                    OnUpdate(v);

                } else {
                    Toast.makeText(UpdateAccount.this, "Connect to internet please", Toast.LENGTH_SHORT).show();
                }
            }


        });


        //Bottom Navigation view
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNav_Bar);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.mnHome:
                        Intent home = new Intent(UpdateAccount.this, DiscoverActivity.class);
                        home.putExtra("username", currentUname);
                        startActivity(home);
                        break;
                    case R.id.mnAccount:
                            Intent account = new Intent(UpdateAccount.this, UpdateAccount.class);
                            startActivity(account);
                            break;
                    case R.id.mnGallery:
                        Intent gallery = new Intent(UpdateAccount.this, GlideActivity.class);
                        gallery.putExtra("username", currentUname);
                        startActivity(gallery);
                        break;
                }


                return true;
            }
        });




    }


    //Registration Function to Connect with BackgroundWorker2 class
    public void OnUpdate(View view) {

        String currentUname = getIntent().getStringExtra("username");

        final String UfirstName1 = UfirstName.getText().toString().trim();
        final String UlastName1 = UlastName.getText().toString().trim();
        final String UuserEmail1 = UuserEmail.getText().toString().trim();
        final String UuserName1 = currentUname;
        final String UuserPassword1 = UuserPassword.getText().toString().trim();



        String type = "update";
        BackgroundWorker3 backgroundWorker3 = new BackgroundWorker3(getApplicationContext(), new MyCallback3() {

            @Override
            public void onResult(String result3) {
                if (result3.equals("Record Updated Successfully")) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(UpdateAccount.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("uPass", UuserPassword1);
                    editor.commit();
                    Toast.makeText(UpdateAccount.this, "User Details Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateAccount.this, DiscoverActivity.class));
                }
                else if(result3.equals("Something went wrong")) {
                    Toast.makeText(UpdateAccount.this, "Error in Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backgroundWorker3.execute(type, UfirstName1, UlastName1, UuserEmail1, UuserName1, UuserPassword1);

    }


    //callback from backgroundworker3
    interface MyCallback3 {
        void onResult(String result3);
    }

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

    //validation for email
    public boolean checkForEmail() {
        Context c;
        UuserEmail = (EditText) findViewById(R.id.etuUserEmail);
        String mStrEmail = UuserEmail.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mStrEmail).matches()) {
            return true;
        }
        UuserEmail.setError("Enter a Valid Email Address");
        Toast.makeText(UpdateAccount.this, "Email not Valid", Toast.LENGTH_SHORT).show();
        return false;
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
            case R.id.loginmenu:
                startActivity(new Intent(UpdateAccount.this, MainActivity.class));
                break;
            case R.id.logoutmenu:
                final GlobalClass globalClass = (GlobalClass) getApplicationContext();
                globalClass.setGlobe("false");
                Toast.makeText(globalClass, "Logged Off", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateAccount.this, DiscoverActivity.class));
                break;
        }

        return true;


    }



}