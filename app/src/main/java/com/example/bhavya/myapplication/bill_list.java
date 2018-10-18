package com.example.bhavya.myapplication;

public class bill_list {


    public String name;
    public String amount;

    public bill_list(String name, String amount) {
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

}
