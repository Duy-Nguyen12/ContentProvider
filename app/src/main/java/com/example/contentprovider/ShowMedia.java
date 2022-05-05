package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMedia extends AppCompatActivity {

    Button btnBack;
    ListView listViewMedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media);

        btnBack = (Button) findViewById(R.id.btnBackkk);
        listViewMedia = (ListView) findViewById(R.id.listMedia);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ShowMedia1();
    }

    private void ShowMedia1() {

        Uri uri = Uri.parse("content://media/internal/images");
        String[] projection = new String[]{
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };
        CursorLoader loader = new CursorLoader(this, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        ArrayList<String> list = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String s = "";
            for (int i=0; i<cursor.getColumnCount(); i++){
                s += cursor.getString(i) + " - ";
            }
            list.add(s);
            cursor.moveToFirst();
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listViewMedia.setAdapter(adapter);
    }
}