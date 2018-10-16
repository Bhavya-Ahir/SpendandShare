package com.example.bhavya.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GroupData extends AppCompatActivity {
    ListView listViewMembers;
    private DatabaseReference groupData;
    List<Settle.Person> memberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_data);
        listViewMembers=(ListView)findViewById(R.id.memberListViewid);
        groupData=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        memberList=new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        groupData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot personSnapshot:dataSnapshot.getChildren()){
                    Settle.Person p= personSnapshot.getValue(Settle.Person.class);
                    memberList.add(p);
                }
                MemberList adapter=new MemberList(GroupData.this,memberList);
                listViewMembers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
