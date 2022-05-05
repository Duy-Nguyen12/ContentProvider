package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowBookmart extends AppCompatActivity {

    Button btnBack;
    ListView listViewBookmart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bookmart);

        btnBack = (Button) findViewById(R.id.btnBackk);
        listViewBookmart = (ListView) findViewById(R.id.listBookmart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ShowBookmart1();
    }

    private void ShowBookmart1() {
        Uri uri = Uri.parse("content://browser/bookmarks");
        String[] history = new String[]{
                "_ID",
                "URL",
                "TITLE",

        };
        List<String> list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(uri, history, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String s = "Test";
            s += cursor.getString(1) + " - " + cursor.getString(2);
            list.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listViewBookmart.setAdapter(adapter);
    }
}