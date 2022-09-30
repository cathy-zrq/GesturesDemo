package com.example.gesturesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ContentResolver contentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentResolver=this.getContentResolver();
    }
    public void demo1(View view) {
       // startActivity(new Intent(MainActivity.this,FristActivity.class));
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","测试44444");
        contentResolver.insert(Uri.parse("content://com.example.test.provider.msprovider/test"),contentValues);
        Toast.makeText(this, "insert success!", Toast.LENGTH_SHORT).show();
    }


    public void demo2(View view) {
      // startActivity(new Intent(MainActivity.this,SecondActivity.class));

        Cursor cursor=contentResolver.query(Uri.parse("content://com.example.test.provider.msprovider/query/3"),
                null,null,null,null,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String name=cursor.getString(cursor.getColumnIndex("name"));

                Log.d("TT", "queryData: "+id+"----"+name);
            }
            cursor.close();
        }
    }


    public void demo3(View view) {
        startActivity(new Intent(MainActivity.this,ThridActivity.class));

    }
    public void demo4(View view) {
        startActivity(new Intent(MainActivity.this,FActivity.class));

    }
}
