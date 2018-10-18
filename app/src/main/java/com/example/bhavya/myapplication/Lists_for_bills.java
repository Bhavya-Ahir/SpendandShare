package com.example.bhavya.myapplication;

import java.io.Serializable;

public class Lists_for_bills implements Serializable {

    public String name;
    public String amt;

    public Lists_for_bills(String name) {
        this.name = name;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getName() {
        return name;
    }

    public String getAmt() {
        return amt;
    }

}
