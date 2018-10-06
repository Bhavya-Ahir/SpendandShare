package com.example.bhavya.myapplication.Recycler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bhavya.myapplication.R;

import java.util.List;

public class array_adapter extends RecyclerView.Adapter<array_adapter.ViewHolder> {

    private List<item_list> listItems;
    private Context context;

    public array_adapter(List<item_list> listItems,Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public array_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup ,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final array_adapter.ViewHolder viewHolder, int i) {

        final item_list listItem=listItems.get(i);

        viewHolder.name.setText(listItem.getName());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(".GroupData");
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView) itemView.findViewById(R.id.name);
            cardView=itemView.findViewById(R.id.cardview_acapter);
        }
    }
}
