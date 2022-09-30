package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * 单例
 */
public class Test2Activity extends AppCompatActivity {
   private  GestureDetector detector;
   private  MyGesture myGesture;
   private int offset=200;//偏移量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        myGesture=new MyGesture();
        detector=new GestureDetector(Test2Activity.this,myGesture);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e1.getY()-e2.getY()>offset){
                startActivity(new Intent(Test2Activity.this,MainActivity.class));
                Toast.makeText(Test2Activity.this, "create new activity", Toast.LENGTH_SHORT).show();
            }else if(e1.getY()-e2.getY()<offset){
                Toast.makeText(Test2Activity.this, "close activity", Toast.LENGTH_SHORT).show();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
