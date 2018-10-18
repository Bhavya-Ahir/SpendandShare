
package com.example.bhavya.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class array_adapter2 extends RecyclerView.Adapter<array_adapter2.ViewHolder> {

    private List<list_for_bills> listItems;
    private Context context;

    public array_adapter2(List<list_for_bills> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public array_adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_for_bills, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final array_adapter2.ViewHolder viewHolder, int i) {

        final list_for_bills listItem = listItems.get(i);


        viewHolder.name.setText(listItem.getName());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, BILL_DATA.class);
                Log.d("Abc", listItem.getName());
                i.putExtra("Bill Name", listItem.getName());
                context.startActivity(i);


            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardview_acapter);
        }
    }
}
