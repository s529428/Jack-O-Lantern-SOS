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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing);
        getSupportActionBar().hide();

        final EditText heightET = findViewById(R.id.heightET);
        final EditText widthET = findViewById(R.id.widthET);
        final Button printBTN = findViewById(R.id.printBTN);

        printPreview();

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

        Drawable leye = ContextCompat.getDrawable(this, R.drawable.eye1);
        Drawable reye = ContextCompat.getDrawable(this, R.drawable.eye2);
        Drawable nose = ContextCompat.getDrawable(this, R.drawable.nose2);
        Drawable mouth = ContextCompat.getDrawable(this, R.drawable.mouth5);
        //Place holder image to keep ImageView max size (Scaling won't work without it)
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