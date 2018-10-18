package com.example.bhavya.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MemberList extends ArrayAdapter<Settle.Person> {
    private Activity context;
    private List<Settle.Person> memberList;

    public MemberList(Activity context, List<Settle.Person> memberList) {
        super(context, R.layout.datalist_layout, memberList);
        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewitem1 = inflater.inflate(R.layout.datalist_layout, null, true);
            TextView textViewName = (TextView) listViewitem1.findViewById(R.id.memberName);
            TextView textViewamt = (TextView) listViewitem1.findViewById(R.id.amtPaid);
            Settle.Person p = memberList.get(position);
            textViewName.setText(p.getName());
            textViewamt.setText(p.getName());
            return listViewitem1;
        }
        return super.getView(position, convertView, parent);
    }
}
