package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Test3Activity extends AppCompatActivity {
    private GestureOverlayView gView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);


        gView=findViewById(R.id.gView);
        gView.setGestureColor(Color.RED);
        gView.setGestureStrokeWidth(5);
        gView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {


                View view = getLayoutInflater().from(Test3Activity.this).inflate(R.layout.dialog_save,null,false);
                final EditText etName = view.findViewById(R.id.etName);
                ImageView ivShow = view.findViewById(R.id.ivShow);
                Bitmap bitmap=gesture.toBitmap(128,128,10,Color.RED);
                ivShow.setImageBitmap(bitmap);
                new AlertDialog.Builder(Test3Activity.this)
                        .setView(view)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GestureLibrary libraries= GestureLibraries.fromFile("/mnt/sdcard/mygesture");
                                libraries.addGesture(etName.getText().toString(),gesture);
                                libraries.save();
                                Toast.makeText(Test3Activity.this, "save success！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });


    }
}
