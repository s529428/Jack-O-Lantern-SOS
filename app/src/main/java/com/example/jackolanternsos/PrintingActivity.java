package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.print.PrintHelper;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PrintingActivity extends AppCompatActivity {

    //Public initialization to help with grabbing values from intents and using in other functions
    public String rightEye;
    public String leftEye;
    public String theNose;
    public String theMouth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);
        getSupportActionBar().hide();

        final EditText heightET = findViewById(R.id.heightET);
        final EditText widthET = findViewById(R.id.widthET);
        final Button printBTN = findViewById(R.id.printBTN);

        //Grabs values for face from other activities
        Intent ini = getIntent();
        rightEye = ini.getStringExtra("RIGHT_EYE");
        leftEye = ini.getStringExtra("LEFT_EYE");
        theNose = ini.getStringExtra("NOSE");
        theMouth = ini.getStringExtra("MOUTH");
        printPreview();

        //Checks for correct values in ET's and starts print
        printBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int height;
                int width;
                try {
                    height = Integer.parseInt(String.valueOf(heightET.getText()));
                    width = Integer.parseInt(String.valueOf(widthET.getText()));
                    printFace(height, width);
                }
                catch (Exception e) {
                    //Toast message for failure or empty values
                    Toast.makeText(PrintingActivity.this, "Height/Width input is invalid or blank. Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToHome(View v){
        finish();
    }

    public void printPreview(){
        //grab the face and put in the preview box
        ImageView printIV = findViewById(R.id.printIV);
        //Initialization for face elements
        Drawable leye;
        Drawable reye;
        Drawable nose;
        Drawable mouth;

        //Switch statements to set face elements to correct ones based off other activities
        switch(leftEye) {
            case "eye1":
                leye = ContextCompat.getDrawable(this, R.drawable.eye1);
                break;
            case "eye2":
                leye = ContextCompat.getDrawable(this, R.drawable.eye2);
                break;
            case "eye3":
                leye = ContextCompat.getDrawable(this, R.drawable.eye3);
                break;
            case "eye4":
                leye = ContextCompat.getDrawable(this, R.drawable.eye4);
                break;
            case "eye5":
                leye = ContextCompat.getDrawable(this, R.drawable.eye5);
                break;
        }
        switch(rightEye) {
            case "eye1":
                reye = ContextCompat.getDrawable(this, R.drawable.eye1);
                break;
            case "eye2":
                reye = ContextCompat.getDrawable(this, R.drawable.eye2);
                break;
            case "eye3":
                reye = ContextCompat.getDrawable(this, R.drawable.eye3);
                break;
            case "eye4":
                reye = ContextCompat.getDrawable(this, R.drawable.eye4);
                break;
            case "eye5":
                reye = ContextCompat.getDrawable(this, R.drawable.eye5);
                break;
        }
        switch(theNose) {
            case "nose1":
                nose = ContextCompat.getDrawable(this, R.drawable.nose1);
                break;
            case "nose2":
                nose = ContextCompat.getDrawable(this, R.drawable.nose2);
                break;
            case "nose3":
                nose = ContextCompat.getDrawable(this, R.drawable.nose3);
                break;
            case "nose4":
                nose = ContextCompat.getDrawable(this, R.drawable.nose4);
                break;
            case "nose5":
                nose = ContextCompat.getDrawable(this, R.drawable.nose5);
                break;
        }
        switch(theMouth) {
            case "mouth1":
                mouth = ContextCompat.getDrawable(this, R.drawable.mouth1);
                break;
            case "mouth2":
                mouth = ContextCompat.getDrawable(this, R.drawable.mouth2);
                break;
            case "mouth3":
                mouth = ContextCompat.getDrawable(this, R.drawable.mouth3);
                break;
            case "mouth4":
                mouth = ContextCompat.getDrawable(this, R.drawable.mouth4);
                break;
            case "mouth5":
                mouth = ContextCompat.getDrawable(this, R.drawable.mouth5);
                break;
        }

        //Place holder image to keep ImageView max size (Scaling/Placement won't work without it)
            Drawable ph = ContextCompat.getDrawable(this, R.drawable.eye3);
            ph.mutate();
            ph.setAlpha(0);

        LayerDrawable finalDrawable = new LayerDrawable(new Drawable[] {leye, reye, nose, mouth, ph});
        leye.mutate();
        reye.mutate();
        nose.mutate();
        mouth.mutate();
        //Scales face elements to fit ImageView
        finalDrawable.setLayerHeight(0, leye.getIntrinsicHeight()/3);
        finalDrawable.setLayerHeight(1, reye.getIntrinsicHeight()/3);
        finalDrawable.setLayerHeight(2, nose.getIntrinsicHeight()/3);
        finalDrawable.setLayerHeight(3, mouth.getIntrinsicHeight()/2);
        finalDrawable.setLayerWidth(0, leye.getIntrinsicWidth()/3);
        finalDrawable.setLayerWidth(1, reye.getIntrinsicWidth()/3);
        finalDrawable.setLayerWidth(2, nose.getIntrinsicWidth()/3);
        finalDrawable.setLayerWidth(3, mouth.getIntrinsicWidth()/2);
        //Places face elements in correct spots
        finalDrawable.setLayerGravity(0, Gravity.LEFT | Gravity.TOP);
        finalDrawable.setLayerGravity(1, Gravity.RIGHT | Gravity.TOP);
        finalDrawable.setLayerGravity(2, Gravity.CENTER);
        finalDrawable.setLayerGravity(3, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

        printIV.setImageDrawable(finalDrawable);
    }

    public void printFace(int height, int width) {
        PrintHelper photoPrinter = new PrintHelper(PrintingActivity.this);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.eye1);
        photoPrinter.printBitmap("test print", image);
    }
}