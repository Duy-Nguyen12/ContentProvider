package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {

    Button btnBack;
    ListView listViewContact;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        initWidgets();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

//        ShowContact1();
        ShowContact2();
    }


    private void ShowContact1() {
        Uri uri = Uri.parse("content://contacts/people");

        //getcontentresolver
        Cursor cursor = getContentResolver().query(uri, null, null,null,null);

        cursor.moveToFirst(); //taoj layout listview co anh, co gi nua y, bai ve nha nhe
        while (!cursor.isAfterLast()){
            String s = "";
            String idColumnName = ContactsContract.Contacts._ID;
            int idIndex = cursor.getColumnIndex(idColumnName);
            s = cursor.getString(idIndex) + " - ";
            String nameColumName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = cursor.getColumnIndex(nameColumName);
            s = s + cursor.getString(nameIndex);
            cursor.moveToNext();
            list.add(s);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listViewContact.setAdapter(adapter);

    }

    private void ShowContact2() {
        Uri uri = Uri.parse("content://contacts/people");

        CursorLoader loader = new CursorLoader(this, uri, null, null, null, null);
        Cursor cursor = loader.loadInBackground();

        cursor.moveToFirst(); //taoj layout listview co anh, co gi nua y, bai ve nha nhe
        while (!cursor.isAfterLast()){
            String s = "";
            String imgColumnName = ContactsContract.Contacts._ID;
            int imgIndex = cursor.getColumnIndex(imgColumnName);
            s = cursor.getString(imgIndex) + " - ";
            String nameColumName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = cursor.getColumnIndex(nameColumName);
            s = s + cursor.getString(nameIndex);
            cursor.moveToNext();
            list.add(s);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listViewContact.setAdapter(adapter);
    }

    private void initWidgets() {
        btnBack = (Button) findViewById(R.id.btnBack);
        listViewContact = (ListView) findViewById(R.id.listViewContact);
    }

}