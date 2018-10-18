package com.example.bhavya.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BILL_DATA extends AppCompatActivity {


    private String billname;
    public RecyclerView recyclerView;

    private DatabaseReference groupData;


    private Context context = this;
    private RecyclerView.Adapter adapter;
    private List<bill_list> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill__dat);

        billname = getIntent().getStringExtra("Bill Name");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_for_bill_data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        adapter = new array_adapter_for_datalist(listItems, this);


        groupData = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP " + GroupData.groupName).child("_BILLS").child(billname);

        groupData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot bills : dataSnapshot.getChildren()) {

                    bills.getChildren();
                    String name = bills.getKey();
                    bills.getChildren();

                    String amount = bills.getValue().toString();
                    amount = amount.substring(1, amount.indexOf(","));

                    bill_list list = new bill_list(name, amount);
                    listItems.add(list);
                }
                array_adapter_for_datalist g = new array_adapter_for_datalist(listItems, context);
                recyclerView.setAdapter(g);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
