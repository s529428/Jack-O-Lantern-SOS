package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements SaveDialogFragment.SaveCallBack, UsernameSaveDialogFragment.UsernameSaveDialogListener {

    //Global Variables for storing in DB
    String lefteyestring = "eye4";
    String righteyestring ="eye4";
    String nosestring = "nose3";
    String mouthstring = "mouth5";

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the SharedPreferences up
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "default");
        Log.d("return", sharedPreferences.getString("username", "default"));

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
            lefteyestring=string;
        }
        if(!rightEyeSW.isChecked()){
            //Randomly select an image from the Eye image options
            String string = "eye"+(random.nextInt(4)+1);
            rightEyeIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
            righteyestring=string;
        }
        if(!noseSW.isChecked()){
            //Randomly select an image from the Nose image options
            String string = "nose"+(random.nextInt(4)+1);
            noseIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
            nosestring=string;
        }
        if(!mouthSW.isChecked()){
            //Randomly select an image from the Mouth image options
            String string = "mouth"+(random.nextInt(4)+1);
            mouthIV.setImageDrawable(getResources().getDrawable(getResourceID(string, "drawable", getApplicationContext())));
            mouthstring=string;
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
            //write to the DB with the User's Saved name
            if(username == "default"){
                UsernameSaveDialogFragment usernameSaveDialogFragment = new UsernameSaveDialogFragment();
                usernameSaveDialogFragment.show(getSupportFragmentManager(), "Username");
            }
            ParseObject privatePumpkinFace = new ParseObject("PrivatePumpkinFace");
            privatePumpkinFace.put("Username", username);
            privatePumpkinFace.put("LeftEye", lefteyestring);
            privatePumpkinFace.put("RightEye", righteyestring);
            privatePumpkinFace.put("Nose", nosestring);
            privatePumpkinFace.put("Mouth", mouthstring);
            privatePumpkinFace.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("ERROR", ""+e);
                }
            });
        }
        //if public, write to public
        else if(optionIndex == 0) {
            ParseObject pumpkinFace = new ParseObject("PublicPumkinFace");
            pumpkinFace.put("LeftEye", lefteyestring);
            pumpkinFace.put("RightEye",righteyestring);
            pumpkinFace.put("Nose", nosestring);
            pumpkinFace.put("Mouth", mouthstring);
            pumpkinFace.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    Log.d("ERROR", ""+e);
                }
            });
        }
    }

    @Override
    public void onSetUsername(String newUsername) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getPreferences(0).edit();
        username = newUsername;
        editor.putString("username", username);
        editor.commit();
        Log.d("return", "This is what it is after a change" + sharedPreferences.getString("username", "default"));
    }
}