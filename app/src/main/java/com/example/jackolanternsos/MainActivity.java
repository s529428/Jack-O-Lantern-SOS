package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Log.d("hello world", "jello");
    }

    public void goToGallery(View v){
        Intent goGallery = new Intent(this, GalleryActivity.class);
        startActivity(goGallery);
    }

    public void goToPrint(View v){
        Intent goPrint = new Intent(this, PrintingActivity.class);
        startActivity(goPrint);
    }

    public void goToSocialGallery(View v){
        Intent goSocialGallery = new Intent(this, SocialGalleryActivity.class);
        startActivity(goSocialGallery);
    }
}