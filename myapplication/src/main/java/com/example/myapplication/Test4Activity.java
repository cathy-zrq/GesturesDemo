package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Test4Activity extends AppCompatActivity {

    private GestureOverlayView overlayView;
    private GestureLibrary library;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);


        overlayView=findViewById(R.id.overlayView);
        library= GestureLibraries.fromFile("/mnt/sdcard/mygesture");
        if(library.load()){
            Toast.makeText(this, "存在", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "不存在", Toast.LENGTH_SHORT).show();

        }
        overlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> list=library.recognize(gesture);
                ArrayList<String> result=new ArrayList<>();
                for(Prediction p:list){
                    if(p.score>0.2){
                        Toast.makeText(Test4Activity.this, "与"+p.name+"似度"+p.score, Toast.LENGTH_SHORT).show();
                        result.add("与"+p.name+"似度"+p.score);
                    }
                }
                
                
                if(result.size()>0){
                    ArrayAdapter<Object> arrayAdapter=new ArrayAdapter<Object>(Test4Activity.this,android.R.layout.simple_dropdown_item_1line,result.toArray());
                    AlertDialog show = new AlertDialog.Builder(Test4Activity.this).setAdapter(arrayAdapter,null).setPositiveButton("OK",null).show();
                }else{
                    Toast.makeText(Test4Activity.this, "无法找到匹配的手势", Toast.LENGTH_SHORT).show();
                }

              
            }
        });

    }
}
