package com.example.bhavya.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_Bill extends AppCompatActivity {

    View rootView;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Lists_for_bills> listItems;
    private Button button;
    public static final String TAG = "GDFB";


    DatabaseReference databaseReference;
    private String groupName;

    static Spinner spinner;
    ArrayAdapter<CharSequence> spinneradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__bill);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinneradapter = ArrayAdapter.createFromResource(this, R.array.Expenditure_type, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_bill);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        adapter = new array_adapter_for_bills(listItems, this);


        groupName = getIntent().getStringExtra("Group Name");
/*
        groupName=groupName.substring(0);
*/

        Toast.makeText(this, groupName, Toast.LENGTH_SHORT).show();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP " + groupName).child("Members");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot members : dataSnapshot.getChildren()) {
                    String g1 = members.getKey();
                    Lists_for_bills list = new Lists_for_bills(g1);
                    listItems.add(list);


                }
                array_adapter_for_bills g = new array_adapter_for_bills(listItems, Add_Bill.this);
                recyclerView.setAdapter(g);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        button = findViewById(R.id.Settle);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, Lists_for_bills.class.getName());
            }
        });



    }
}
