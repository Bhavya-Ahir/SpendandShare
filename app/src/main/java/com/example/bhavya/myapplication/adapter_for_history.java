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

public class adapter_for_history extends RecyclerView.Adapter<adapter_for_history.ViewHolder> {

    private List<after_paid_history> listItems_1;
    private Context context;

    public adapter_for_history(List<after_paid_history> listItems, Context context) {
        this.listItems_1 = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public adapter_for_history.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_after_paid_history, viewGroup, false);

        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull final adapter_for_history.ViewHolder viewHolder, int i) {


        final after_paid_history list = listItems_1.get(i);
        viewHolder.name_1.setText(list.getName_1());


    }


    @Override
    public int getItemCount() {
        return listItems_1.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name_1;
        public TextView action;
        public TextView amt;


        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_1 = (TextView) itemView.findViewById(R.id.name);
            action = (TextView) itemView.findViewById(R.id.action);
            amt = (TextView) itemView.findViewById(R.id.amt);

            cardView = itemView.findViewById(R.id.cardview_adapter);
        }
    }
}
