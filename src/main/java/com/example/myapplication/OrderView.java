package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OrderView extends AppCompatActivity {
    TextView nameView, addressView, orderView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameView = (TextView) findViewById(R.id.name_order);
        addressView = (TextView) findViewById(R.id.address_order);
        orderView = (TextView) findViewById(R.id.order_order);


        Intent intent = getIntent();
        receivedOrder = (ItemView) intent.getExtras().getSerializable(MainActivity.data);
        serverUrl = (String) intent.getStringExtra(MainActivity.SERVER_URL_EXTRA);
    }



}
