package com.example.bhavya.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class array_adapter_for_bills extends RecyclerView.Adapter<array_adapter_for_bills.ItemHolder> {


    public static final String TAG = "HI";
    private ArrayList<Lists_for_bills> Lists_bills;
    private Context context;
    DatabaseReference databaseReference;


    public array_adapter_for_bills(ArrayList<Lists_for_bills> Lists_bills, Context context) {
        this.Lists_bills = Lists_bills;
        this.context = context;
    }


    @NonNull
    @Override
    public array_adapter_for_bills.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lists_for_bills, viewGroup, false);
        return new array_adapter_for_bills.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final array_adapter_for_bills.ItemHolder itemHolder, final int i) {

        final Lists_for_bills listItem = Lists_bills.get(i);

        itemHolder.names.setText(listItem.getName());
        itemHolder.amount.setText(listItem.getAmt());

        itemHolder.amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, " " + Lists_bills.get(i).amt + Lists_bills.get(i).name + i);
                Log.d(TAG, " " + listItem.getName() + listItem.getAmt());



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Lists_bills.get(i).amt = s.toString();


            }


        });


    }

    @Override
    public int getItemCount() {
        return Lists_bills.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        TextView names;
        EditText amount;
        public CardView cardView;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            names = (TextView) itemView.findViewById(R.id.namesBILLS);
            amount = (EditText) itemView.findViewById(R.id.amtBILLS);
            cardView = itemView.findViewById(R.id.CardView_bills);

        }
    }
}
