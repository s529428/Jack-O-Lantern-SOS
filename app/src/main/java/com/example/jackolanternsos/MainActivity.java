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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SaveDialogFragment.SaveCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Touch that database baby!
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        //Let's check that it touched lol
        //ParseInstallation.getCurrentInstallation().saveInBackground();

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
        ImageView leftEyeIV = findViewById(R.id.leftEyeIV);
        ImageView rightEyeIV = findViewById(R.id.rightEyeIV);
        ImageView noseIV = findViewById(R.id.noseIV);
        ImageView mouthIV = findViewById(R.id.mouthIV);
        //if private, write to private
        if(optionIndex == 1){
            //write to the JSON
        }
        //if public, write to public
        else if(optionIndex == 0) {
            ParseObject pumpkinFace = new ParseObject("PublicPumkinFace");
            pumpkinFace.put("LeftEye", String.valueOf(leftEyeIV.getDrawable()));
            pumpkinFace.put("RightEye", String.valueOf(rightEyeIV.getDrawable()));
            pumpkinFace.put("Nose", String.valueOf(noseIV.getDrawable()));
            pumpkinFace.put("Mouth", String.valueOf(mouthIV.getDrawable()));
            pumpkinFace.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("ERROR", ""+e);
                }
            });
        }
    }
}