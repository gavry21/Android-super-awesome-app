package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter{

    Context context;
    ArrayList<ItemView> arrayList;

    public MessageListAdapter(Context context, ArrayList<ItemView> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public  View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_message, parent, false);
        }
        TextView name, address, order;
        name = (TextView) convertView.findViewById(R.id.name);
        address = (TextView) convertView.findViewById(R.id.order);
        order = (TextView) convertView.findViewById(R.id.address);
        name.setText(arrayList.get(position).getName());
        address.setText(arrayList.get(position).getAddress());
        order.setText(arrayList.get(position).getOrder());

        return convertView;
    }

}
