package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SocialGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_gallery);
        getSupportActionBar().hide();
    }

    public void goToHome(View v){
        Intent goHome = new Intent(this, MainActivity.class);
        startActivity(goHome);
    }
}