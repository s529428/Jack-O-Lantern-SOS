package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//hello Group Mates
    //Hi there!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hello world", "jello");
    }

    public void goToGallery(View v){
        Intent goGallery = new Intent(this, GalleryActivity.class);
        startActivityForResult(goGallery, 1);
    }
}