package com.example.bhavya.myapplication.GROUPS;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bhavya.myapplication.R;

import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class createGroup extends AppCompatActivity {

    Button save;
    ArrayList<String> addarray=new ArrayList<String>();
    ArrayList<Double> addamt=new ArrayList<Double>();
    EditText txt;
    EditText amt;
    ListView show;
    ListView show1;
    Button newactivity;
    String groupName;
    Spinner spinner;
    ArrayAdapter<CharSequence> spinneradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        newactivity=(Button)findViewById(R.id.btnSettle);
        txt=(EditText)findViewById(R.id.txtInput);
        amt=(EditText)findViewById(R.id.amtInput);
        show = (ListView)findViewById(R.id.listView);
        show1 = (ListView)findViewById(R.id.listView1);
        save=(Button)findViewById(R.id.btnSave);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinneradapter=ArrayAdapter.createFromResource(this,R.array.Expenditure_type,android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);
        groupName = getIntent().getStringExtra("Group Name");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput;
                double getamt;
                if(txt.getText().toString().length()==0||txt.getText().equals(" ")) {
                    Toast.makeText(createGroup.this, "Please enter a Name", Toast.LENGTH_LONG).show();
                    getInput = NULL;
                }
                else {
                    getInput=txt.getText().toString();
                }

                  if(amt.getText().toString().length()==0||txt.getText().equals(" "))
                      getamt=0;
                  else
                      getamt=Double.parseDouble(amt.getText().toString());


                addarray.add(getInput);
                addamt.add(getamt);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(createGroup.this,
                        android.R.layout.simple_list_item_1, addarray);
                ArrayAdapter<Double> amtadapter = new ArrayAdapter<Double>(createGroup.this,
                        android.R.layout.simple_list_item_1, addamt);
                show.setAdapter(adapter);
                show1.setAdapter(amtadapter);
                ((EditText)findViewById(R.id.txtInput)).setText(" ");
                ((EditText)findViewById(R.id.amtInput)).setText(" ");
            }
        });

        newactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createGroup.this, Settle.class);
                intent.putExtra("Group Name", groupName);
                intent.putExtra("name", addarray);
                intent.putExtra("paid", addamt);
                finish();
                startActivity(intent);
            }
                                       }

        );
    }


}
