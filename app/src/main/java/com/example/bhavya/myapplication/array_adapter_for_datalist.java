package com.example.bhavya.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class array_adapter_for_datalist extends RecyclerView.Adapter<array_adapter_for_datalist.ViewHolder> {

    private List<bill_list> listItems;
    private Context context;

    public array_adapter_for_datalist(List<bill_list> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public array_adapter_for_datalist.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.datalist_layout, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final array_adapter_for_datalist.ViewHolder viewHolder, int i) {

        final bill_list listItem = listItems.get(i);


        viewHolder.name1.setText(listItem.getName());
        viewHolder.amount1.setText(listItem.getAmount());

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name1;
        public CardView cardView;
        public TextView amount1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            amount1 = (TextView) itemView.findViewById(R.id.amtPaid);
            name1 = (TextView) itemView.findViewById(R.id.memberName);
            cardView = itemView.findViewById(R.id.carr);
        }
    }
}
