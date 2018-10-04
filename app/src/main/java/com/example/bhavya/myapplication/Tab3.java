package com.example.bhavya.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Tab3 extends Fragment {
    Button save;
    ArrayList<String> addarray=new ArrayList<String>();
    ArrayList<Double> addamt=new ArrayList<Double>();
    EditText txt;
    EditText amt;
    ListView show;
    ListView show1;
    Button newactivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final   View view=inflater.inflate(R.layout.tab3,container,false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        newactivity=(Button)view.findViewById(R.id.btnSettle);
        txt=(EditText)view.findViewById(R.id.txtInput);
        amt=(EditText)view.findViewById(R.id.amtInput);
        show = (ListView)view.findViewById(R.id.listView);
        show1 = (ListView)view.findViewById(R.id.listView1);
        save=(Button)view.findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput=txt.getText().toString();
                double getamt=Double.parseDouble(amt.getText().toString());
                addarray.add(getInput);
                addamt.add(getamt);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, addarray);
                ArrayAdapter<Double> amtadapter = new ArrayAdapter<Double>(getActivity(),
                        android.R.layout.simple_list_item_1, addamt);
                show.setAdapter(adapter);
                show1.setAdapter(amtadapter);
                ((EditText)view.findViewById(R.id.txtInput)).setText(" ");
                ((EditText)view.findViewById(R.id.amtInput)).setText(" ");
            }
        });
        newactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(),Main2Activity.class);
                intent.putExtra("name",addarray);
                intent.putExtra("paid",addamt);
                startActivity(intent);
            }
        });
        return view;
    }
}
