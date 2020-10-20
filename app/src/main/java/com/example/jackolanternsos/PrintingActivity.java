package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrintingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);
        getSupportActionBar().hide();
    }

    public void goToHome(View v){
        finish();
    }

    public void printPreview(){
        //grab the face and put in the preview box
    }
    public void printFace(double height, double width){
        //Start the printing process with a scaled image based on the height and width
    }
}