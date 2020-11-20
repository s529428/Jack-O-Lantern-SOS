package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SocialGalleryActivity extends AppCompatActivity implements PublicGalleryDialogFragment.PublicGalleryDialogListener{

    private RecyclerView pumkinRV =null;
    private GestureDetectorCompat detector=null;
    private pumkinfaceModel myModel = null;
    private pumkinAdapter pumkinServer =null;
    private ArrayList<String> pumkinData =null;

    @Override
    public void goPrintPumpkin() {
        Intent goPrint = new Intent(this, PrintingActivity.class);
        goPrint.putExtra("LEFT_EYE", 1);
        goPrint.putExtra("RIGHT_EYE", 1);
        goPrint.putExtra("NOSE", 1);
        goPrint.putExtra("MOUTH", 1);
        startActivity(goPrint);

    }

    @Override
    public void goSavePumpkin() {
        //Save the face to the local
        Toast.makeText(getApplicationContext(),"Saved to your gallery!", Toast.LENGTH_SHORT).show();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = pumkinRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                //when a image is clicked a dialog box will pop up with opts on what to do
                RecyclerView.ViewHolder holder = pumkinRV.getChildViewHolder(view);
                if (holder instanceof pumkinAdapter.pumkinViewHolder) {
                    int position = holder.getAdapterPosition();
                    PublicGalleryDialogFragment publicGalleryDialogFragment = new PublicGalleryDialogFragment();
                    publicGalleryDialogFragment.show(getSupportFragmentManager(), "Face Options");
                    return true;
                }
            }
            return false;
        }
    }


    public void redraw() {
        //attempting to redraw the recyler view since it does not get data before it starts
        pumkinRV.swapAdapter(pumkinServer,false);
        pumkinRV.setLayoutManager(null);
        final RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        pumkinRV.setLayoutManager(myManager);
        pumkinServer.notifyDataSetChanged();
        TextView errortv = findViewById(R.id.errortv);
        if(pumkinServer.getItemCount()==0){
            errortv.setTextColor(Color.RED);
            errortv.setText("An error has occured");
        }
        else{
            errortv.setText("");
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
        Button redraw= findViewById(R.id.redrawBTN);
        redraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redraw();
            }
        });



    }


    //RECYCLER VIEW
    //LIKE INSTAGRAM

    //RECYCLER VIEW
    //LIKE INSTAGRAM
}