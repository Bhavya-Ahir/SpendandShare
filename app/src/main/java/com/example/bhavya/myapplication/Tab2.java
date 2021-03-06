package com.example.bhavya.myapplication;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment {

    View rootView;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<item_list> listItems;

    private FloatingActionButton button;
    private DatabaseReference user_groups;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView= inflater.inflate(R.layout.tab2, container, false);
       if(rootView==null)
       {
           Log.i("HARSH","null");
       }
        user_groups = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        button = (FloatingActionButton) view.findViewById(R.id.add_button);
        listItems=new ArrayList<>();


        adapter=new array_adapter(listItems,getActivity());

        user_groups.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot groups : dataSnapshot.getChildren()) {
                    String g1 = groups.toString();
                    g1 = g1.substring(g1.indexOf("P") + 2, g1.indexOf(","));
                    item_list list = new item_list(g1);
                    listItems.add(list);


                }
                array_adapter g = new array_adapter(listItems, getActivity());
                recyclerView.setAdapter(g);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          openDialog();

                                      }
                                  }
        );
    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View subView = inflater.inflate(R.layout.dialog_layout, null);
         final EditText subEditText = (EditText)subView.findViewById(R.id.dialogEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ADD GROUP");
        builder.setMessage("Give name to the Group");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(subEditText.getText().toString().length()==0) {

                    Toast.makeText(getActivity(), "Invalid", Toast.LENGTH_LONG).show();

                }
                else{
                    item_list list = new item_list(
                            subEditText.getText().toString());

                    listItems.add(list);

                    Intent i = new Intent(".createGroup");
                    i.putExtra("Group Name", subEditText.getText().toString());

                    startActivity(i,
                            ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }

}
