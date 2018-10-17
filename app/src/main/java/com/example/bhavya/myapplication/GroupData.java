package com.example.bhavya.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class GroupData extends AppCompatActivity {

    private FloatingActionButton FAB;
    private String groupName;
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

    }
}
