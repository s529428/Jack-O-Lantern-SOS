package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements SaveDialogFragment.SaveCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hide the top bar, that's precious space
        getSupportActionBar().hide();

        //Save Button function call
        Button saveBTN = findViewById(R.id.saveBTN);
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDialogFragment saveDialogFragment = new SaveDialogFragment();
                saveDialogFragment.show(getSupportFragmentManager(), "Save Options");
            }
        });
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

    public void rerollFace(){
        //Check which features are unlocked
        Switch leftEyeSW = findViewById(R.id.leftEyeSW);
        Switch rightEyeSW = findViewById(R.id.rightEyeSW);
        Switch noseSW = findViewById(R.id.noseSW);
        Switch mouthSW = findViewById(R.id.mouthSW);
        //Randomly select image from the features image folder
        if (leftEyeSW.isChecked()){
            //Randomly select and image from the Eye image options
        }
    }


    @Override
    public void cancelBTNpressed() {
        Log.d("Dialog", "User changed their mind about saving");
    }

    @Override
    public void selectedOption(int optionIndex) {
        //if private, write to private JSON file
        if(optionIndex == 1){
            //write to the JSON
        }
        //if public, write to public JSON file
        else if(optionIndex == 0) {
            //magic goes here
        }
    }
}