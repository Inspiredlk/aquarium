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
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    //Declaring variables
    protected EditText firstName;
    private EditText lastName;
    private EditText userEmail;
    private EditText userName;
    private EditText userPassword;
    private TextView userLogin;
    private Button signUp;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    String regback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Assigning variables to corresponding components
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etSurName);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        userLogin = (TextView) findViewById(R.id.tvLoginUser);
        signUp = (Button) findViewById(R.id.btSignUp);

        regback = getIntent().getStringExtra("reg");



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












        //Switch to User Registration to User Login Screen
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(RegistrationActivity.this, MainActivity.class);
                kk.putExtra("backto", regback);
                startActivity(kk);
            }
        });

        //Sign Up button click event
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(!isConnected(RegistrationActivity.this)) {
                    buildDialog(RegistrationActivity.this).show();*/

                    if(!isConnected(RegistrationActivity.this)) {
                        buildDialog(RegistrationActivity.this).show();

                    } else if (TextUtils.isEmpty(firstName.getText())) {
                        Toast.makeText(RegistrationActivity.this, "First Name Empty", Toast.LENGTH_SHORT).show();

                        firstName.setError("First Name is Required");

                    } else if (TextUtils.isEmpty(lastName.getText())) {
                        Toast.makeText(RegistrationActivity.this, "Last Name Empty", Toast.LENGTH_SHORT).show();

                        lastName.setError("Last Name is Required");

                    } else if (TextUtils.isEmpty(userEmail.getText())) {
                        Toast.makeText(RegistrationActivity.this, "Email Empty", Toast.LENGTH_SHORT).show();

                        userEmail.setError("User Email is Required");

                    } else if (TextUtils.isEmpty(userName.getText())) {
                        Toast.makeText(RegistrationActivity.this, "User Name Empty", Toast.LENGTH_SHORT).show();

                        userName.setError("User Name is Required");

                    } else if (TextUtils.isEmpty(userPassword.getText())) {
                        Toast.makeText(RegistrationActivity.this, "Password Empty", Toast.LENGTH_SHORT).show();

                        userPassword.setError("User Password is Required");

                    } else if (checkForEmail()) {
                        OnReg(v);

                    } else {
                        Toast.makeText(RegistrationActivity.this, "Connect to internet please", Toast.LENGTH_SHORT).show();
                    }
                }


       });

    }
    //Registration Function to Connect with BackgroundWorker2 class
    public void OnReg(View view) {

        final String firstName1 = firstName.getText().toString().trim();
        final String lastName1 = lastName.getText().toString().trim();
        final String userEmail1 = userEmail.getText().toString().trim();
        final String userName1 = userName.getText().toString().trim();
        final String userPassword1 = userPassword.getText().toString().trim();

        String type = "register";
        BackgroundWorker2 backgroundWorker2 = new BackgroundWorker2(getApplicationContext(), new MyCallback2() {

            @Override
            public void onResult(String result1) {
               if (result1.equals("Insert_Success")) {
                   Toast.makeText(RegistrationActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RegistrationActivity.this);
                   SharedPreferences.Editor editor = preferences.edit();
                   editor.putString("uName", userName1);
                   editor.putString("uPass", userPassword1);
                   editor.apply();
                   Intent jj = new Intent(RegistrationActivity.this, MainActivity.class);
                   jj.putExtra("backto", regback);
                   startActivity(jj);
               }else{
                   userName.setError("Username is Already in Use");
                    Toast.makeText(RegistrationActivity.this, "Username is Already in Use", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backgroundWorker2.execute(type, firstName1, lastName1, userEmail1, userName1, userPassword1);

    }

    //callback from backgroundworker2
    interface MyCallback2 {
        void onResult(String result1);
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
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        String mStrEmail = userEmail.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mStrEmail).matches()) {
            return true;
        }
        userEmail.setError("Enter a Valid Email Address");
        Toast.makeText(RegistrationActivity.this, "Email not Valid", Toast.LENGTH_SHORT).show();
        return false;
    }

}