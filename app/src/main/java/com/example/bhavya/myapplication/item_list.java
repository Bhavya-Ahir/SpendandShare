package com.example.bhavya.myapplication.Recycler;

public class item_list {

    public String name;
    onItemClick onitemClick;

    public item_list(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOniItemClick(onItemClick onitemClick)

    {
        this.onitemClick = onitemClick;
    }


}
