package com.example.bhavya.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Tab3 extends Fragment {

    View rootView;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<after_paid_history> listItems_1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab3, container, false);
        super.onCreate(savedInstanceState);


        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_10);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems_1 = new ArrayList<>();

        adapter = new adapter_for_history(listItems_1, getActivity());
        recyclerView.setAdapter(adapter);
    }
}

