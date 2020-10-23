package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SocialGalleryActivity extends AppCompatActivity {

    private String json;
    private RecyclerView pumkinRV =null;
    private GestureDetectorCompat detector=null;
    private pumkinfaceModel myModel = null;
    private pumkinAdapter pumkinServer =null;
    private ArrayList<String> pumkinData =null;

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = pumkinRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                //when a image is clicked a dialog box will pop up with opts on what to do
                RecyclerView.ViewHolder holder = pumkinRV.getChildViewHolder(view);
                if (holder instanceof pumkinAdapter.pumkinViewHolder) {
                    int position = holder.getAdapterPosition();
                    dialogOption();
                    return true;
                }
            }
            return false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_gallery);
        getSupportActionBar().hide();
        //assigning imstance varibles to values
        myModel= pumkinfaceModel.getpumkinfaceModelSocial();
        pumkinServer = new pumkinAdapter();
        // Attach it to the RecyclerView
        pumkinRV = findViewById(R.id.pumkinRV);
        pumkinRV.setAdapter(pumkinServer);
        // Make and attach a layoutmanager
        final RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        pumkinRV.setLayoutManager(myManager);
        // Make a Listener for taps
        detector = new GestureDetectorCompat(this,
                new SocialGalleryActivity.RecyclerViewOnGestureListener());
        // add the listener to the recycler
        pumkinRV.addOnItemTouchListener(new
                                                RecyclerView.SimpleOnItemTouchListener(){
                                                    @Override
                                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                        return detector.onTouchEvent(e);
                                                    }
                                                });
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