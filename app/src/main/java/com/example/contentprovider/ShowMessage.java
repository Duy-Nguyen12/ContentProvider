package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMessage extends AppCompatActivity {

    ListView listMess;
    ArrayList<String> messList = new ArrayList<String>();
    ArrayAdapter<String> adapterMess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        listMess = (ListView) findViewById(R.id.listMess);
        adapterMess = new ArrayAdapter<String>(ShowMessage.this, android.R.layout.simple_list_item_1, messList);
        listMess.setAdapter(adapterMess);

        readMessage();
    }

    private void readMessage() {
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        messList.clear();
        while (cursor.moveToNext()){
            int index_phone = cursor.getColumnIndex("address");
            int index_date = cursor.getColumnIndex("date");
            int index_body = cursor.getColumnIndex("body");
            String phone = cursor.getString(index_phone);
            String date_ = cursor.getString(index_date);
            String body_ = cursor.getString(index_body);
            messList.add(phone + "\n" + date_ + "\n" + body_);
        }
        cursor.close();
        adapterMess.notifyDataSetChanged();
    }
}