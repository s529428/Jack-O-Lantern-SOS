package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements PrivateGalleryDialogFragment.PrivateGalleryDialogListener {
    //instance variables for recycler view
private String json;
private RecyclerView pumkinRV =null;
private GestureDetectorCompat detector=null;
private pumkinfaceModel myModel = null;
private pumkinPrivateAdaptor pumkinServer =null;
private ArrayList<String> pumkinData =null;
//creating varaible to hold the username of the user
private String username;


    @Override
    public void goPrintPumpkin(int position) {
        //Grab the face from facelist at position
        //Send user to the Print activity with that face data
        Intent goPrint = new Intent(this, PrintingActivity.class);
        goPrint.putExtra("RIGHT_EYE", myModel.faceList.get(position).righteye);
        goPrint.putExtra("LEFT_EYE", myModel.faceList.get(position).lefteye);
        goPrint.putExtra("NOSE", myModel.faceList.get(position).nose);
        goPrint.putExtra("MOUTH", myModel.faceList.get(position).mouth);
        startActivity(goPrint);
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = pumkinRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                //when a image is clicked a dialog box will pop up with opts on what to do
                RecyclerView.ViewHolder holder = pumkinRV.getChildViewHolder(view);
                if (holder instanceof pumkinPrivateAdaptor.pumkinViewHolder) {
                    int position = holder.getAdapterPosition();
                    Bundle args = new Bundle();
                    args.putInt("POSITION", position);
                    PrivateGalleryDialogFragment privateGalleryDialogFragment = new PrivateGalleryDialogFragment();
                    privateGalleryDialogFragment.setArguments(args);
                    privateGalleryDialogFragment.show(getSupportFragmentManager(), "Face Options");
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().hide();
        //pulling the username from the intent
        username=getIntent().getStringExtra(MainActivity.KEY_USER);
        //setting up recycler view
        //assigning imstance varibles to values
        myModel= pumkinfaceModel.getpumkinfaceModel(username);
        pumkinServer = new pumkinPrivateAdaptor();
        // Attach it to the RecyclerView
        pumkinRV = findViewById(R.id.pumkinRV);
        pumkinRV.setAdapter(pumkinServer);
        myModel.privateAdaptor=pumkinServer;
        // Make and attach a layoutmanager
        final RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        pumkinRV.setLayoutManager(myManager);
        pumkinServer.notifyDataSetChanged();
        // Make a Listener for taps
        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        // add the listener to the recycler
        pumkinRV.addOnItemTouchListener(new
                                             RecyclerView.SimpleOnItemTouchListener(){
                                                 @Override
                                                 public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                     return detector.onTouchEvent(e);
                                                 }
                                             });
        //adding a button handler incase generate is pressed
        Button generateBTN= findViewById(R.id.GenerateBTN);
        generateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switching to main Actvity
                finish();
            }
        });

    }
    //RECYCLER VIEW
    //LIKE INSTAGRAM


}