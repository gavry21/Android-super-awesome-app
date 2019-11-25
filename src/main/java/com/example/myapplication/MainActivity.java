package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.request.Request;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.MessagingAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 /*   private TextView textView;
    String[] orders = new String[]{
            "Naruto", "Sasuke", "Sakura"
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.oredersList);
        textView = (TextView) findViewById(R.id.orderView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.simple_list_item, orders);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                String selectedItem = orders[position];
                // установка текста элемента TextView
                textView.setText(selectedItem);



                 Intent MessageList = new Intent(getApplicationContext(),MessageListAdapter.class);
                 Intent.putExtra("order_view",orders.get)
                MessageList.putExtra("OREDER_VIEW",);
                startActivity(MessageList);
            }
        });
        }


        public String loadJSONFromAsset(){
        String json = null;
        try{
            InputStream is = getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;

        }
        */

    ListView listView;
    ArrayList<ItemView> arrayList;
    public final static String CLICKED_ORDER_EXTRA = "ClickedOrder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.oredersList);
        arrayList = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String address = jsonObject.getString("address");
                String order = jsonObject.getString("order");

                ItemView model = new ItemView();
                model.setId(id);
                model.setName("Имя: " + name);
                model.setAddress("Адрес: " + address);
                model.setOrder("Заказ: " + order);
                arrayList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MessageListAdapter adapter = new MessageListAdapter(this, arrayList);
        listView.setAdapter(adapter);
//при нажатии на элемент ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ItemView orderchoose = arrayList.get(position);
                Intent intent = new Intent(MainActivity.this, OrderView.class);
                intent.putExtra(CLICKED_ORDER_EXTRA, orderchoose);
                startActivity(intent);
            }
        });
    }
    public String readJSON() {
        String json = null;
        try {
            // Opening test.json file
            InputStream inputStream = getAssets().open("test.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}
