package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
        ImageView printIV = findViewById(R.id.printIV);
        final Button printBTN = findViewById(R.id.printBTN);

        //Take out and replace with printPreview() once we get JSON stuff figured out
                printIV.setImageResource(R.drawable.eye1);

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
        //Fix when we get JSON stuff figured out
                //printIV.setImageResource(R.drawable.*insert wanted photo name*);
    }
    public void printFace(int height, int width) {
        ImageView printIV = findViewById(R.id.printIV);
        //Start the printing process with a scaled image based on the height and width
        PrintHelper photoPrinter = new PrintHelper(PrintingActivity.this);
        //Change this to use height and width eventually
        //photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap image = Bitmap.createScaledBitmap(((BitmapDrawable) printIV.getDrawable()).getBitmap(), height, width, false);
        photoPrinter.printBitmap("eye1.png - test print", image);
    }
}