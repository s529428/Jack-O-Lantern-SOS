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
        finish();
    }

    public void dialogOption(){
        //Give the user the option btwn print edit and cancel.
        //Dialog box
    }

    public void pullFace(){
        //Save selected face to local gallery
    }

    //RECYCLER VIEW
    //LIKE INSTAGRAM
}