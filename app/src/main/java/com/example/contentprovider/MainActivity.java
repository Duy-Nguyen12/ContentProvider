package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowContact;
    Button btnAccessCallLog;
    Button btnBookmart;
    Button btnMedia;
    Button btnShowMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPersimission();

        btnShowContact = (Button) findViewById(R.id.btnShow);

        btnShowContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowContact.class);
                startActivity(intent);
            }
        });

        btnAccessCallLog = (Button) findViewById(R.id.btnAccess);
        btnAccessCallLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] projectTion = new String[]{
                        CallLog.Calls.DATE,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.DURATION
                };
                Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, projectTion, CallLog.Calls.DURATION + "<?", new String[]{"30"}, CallLog.Calls.DATE + " Asc");
                cursor.moveToFirst();
                String s = "";
                while (!cursor.isAfterLast()){
                    for(int i=0; i < cursor.getColumnCount(); i++){
                        s = s + cursor.getString(i) + " - ";
                    }
                    s = s + "\n";
                    cursor.moveToNext();
                    //them 1 button goi media, bookmark, custom hien listview co anh, thong tin
                }
                cursor.close();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

        btnBookmart = (Button) findViewById(R.id.btnBookmart);
        btnBookmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ShowBookmart.class);
                startActivity(intent1);
            }
        });

        btnMedia = (Button) findViewById(R.id.btnMedia);
        btnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ShowMedia.class);
                startActivity(intent1);
            }
        });

        btnShowMess = (Button) findViewById(R.id.btnShowMessage);
        btnShowMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowMessage.class);
                startActivity(intent);
            }
        });
    }

    private void getPersimission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 999);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 999);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 999);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
        }
    }
}