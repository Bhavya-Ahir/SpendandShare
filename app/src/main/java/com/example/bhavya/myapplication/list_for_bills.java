package com.example.bhavya.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

@SuppressLint("Registered")
public class list_for_bills extends AppCompatActivity {

    public String name;

    public list_for_bills(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
