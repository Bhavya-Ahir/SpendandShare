package com.example.bhavya.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
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
    private String groupName;
    ListView listViewMembers;
    private DatabaseReference groupData;
    List<Settle.Person> memberList;
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
    }

    private void FABpressed() {

        Intent i = new Intent(".Add_Bill");
        i.putExtra("Group Name", groupName);
        startActivity(i);

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
