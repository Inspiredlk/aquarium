//Global class to check logged in or not
//Created by: Tharuka Sandaru
//Date : 3/15/2019


package com.example.ensiz_softwarem.logindemo;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

public class GlobalClass extends Application {
    private String globe;

    public String getGlobe() {
        return globe;
    }

    public void setGlobe(String globe) {
        this.globe = globe;
    }
}
