package com.example.bhavya.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment {

    View rootView;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<item_list> listItems;
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView= inflater.inflate(R.layout.tab2, container, false);
       if(rootView==null)
       {
           Log.i("HARSH","null");
       }

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        button=(Button) view.findViewById(R.id.add_button);


        listItems=new ArrayList<>();

        for(int i=0;i<15;i++)
        {
            item_list list = new item_list(
                    "heading"+(i+1));
            listItems.add(list);

        }
        adapter=new array_adapter(listItems,getActivity());
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent i=new Intent(".create_group");
                                          startActivity(i);
                                      }
                                  }
        );
    }
}
