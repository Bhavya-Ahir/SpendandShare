package com.example.bhavya.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupData extends AppCompatActivity {

    private FloatingActionButton FAB;
    public static String groupName;

    public RecyclerView recyclerView;

    private Context context = this;
    static DatabaseReference groupData;
    private RecyclerView.Adapter adapter;
    private List<list_for_bills> listItems;
    private ArrayList<String> selectedItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_data);

        groupName = getIntent().getStringExtra("Group Name");

        Toast.makeText(this, groupName, Toast.LENGTH_SHORT).show();
        FAB = (FloatingActionButton) findViewById(R.id.FAB);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FABpressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_for_bills);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();


        ListView ch1 = (ListView) findViewById(R.id.listc);
        ch1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.checked_list, R.id.message1);
        ch1.setAdapter(adapter1);
        ch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();

                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else
                    selectedItems.add(selectedItem);
            }
        });

        groupData = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP " + groupName).child("_BILLS");



        adapter = new array_adapter2(listItems, this);

        groupData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot bills : dataSnapshot.getChildren()) {

                    String g = bills.getKey();
                    list_for_bills list = new list_for_bills(g.toString());
                    listItems.add(list);
                }
                array_adapter2 g = new array_adapter2(listItems, context);
                recyclerView.setAdapter(g);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void FABpressed() {


        groupData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot t : dataSnapshot.getChildren()) {

                    t.getChildren();
                    if (t.getKey().toString().equals("transaction")) {
                        Log.d("ABC", t.toString());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Intent i = new Intent(".Add_Bill");
        i.putExtra("Group Name", groupName);
        startActivity(i);

    }

    }