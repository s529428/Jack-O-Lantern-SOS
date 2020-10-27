package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import org.json.JSONObject;

import java.util.Random;

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

        //Reroll Face button function call
        Button rollBTN = findViewById(R.id.rollBTN);
        rollBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rerollFace();
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
        //Get the image views
        ImageView leftEyeIV = findViewById(R.id.leftEyeIV);
        ImageView rightEyeIV = findViewById(R.id.rightEyeIV);
        ImageView noseIV = findViewById(R.id.noseIV);
        ImageView mouthIV = findViewById(R.id.mouthIV);
        //Check which features are unlocked
        Switch leftEyeSW = findViewById(R.id.leftEyeSW);
        Switch rightEyeSW = findViewById(R.id.rightEyeSW);
        Switch noseSW = findViewById(R.id.noseSW);
        Switch mouthSW = findViewById(R.id.mouthSW);
        //Randomly select image from the features image folder
        Random random = new Random();
        if (!leftEyeSW.isChecked()){
            //Randomly select an image from the Eye image options
            String string = "eye"+(random.nextInt(4)+1);
            leftEyeIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
        }
        if(!rightEyeSW.isChecked()){
            //Randomly select an image from the Eye image options
            String string = "eye"+(random.nextInt(4)+1);
            rightEyeIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
        }
        if(!noseSW.isChecked()){
            //Randomly select an image from the Nose image options
            String string = "nose"+(random.nextInt(4)+1);
            noseIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
        }
        if(!mouthSW.isChecked()){
            //Randomly select an image from the Mouth image options
            String string = "mouth"+(random.nextInt(4)+1);
            mouthIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));

        }
    }

    protected final static int getResourceID(final String resName, final String resType, final Context context){
        final int ResourceID = context.getResources().getIdentifier(resName, resType, context.getApplicationInfo().packageName);
        if(ResourceID == 0){
            throw new IllegalArgumentException("No resource found with name "+resName);
        }else{
            return ResourceID;
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