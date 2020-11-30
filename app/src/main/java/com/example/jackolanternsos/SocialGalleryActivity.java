package com.example.jackolanternsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class SocialGalleryActivity extends AppCompatActivity implements PublicGalleryDialogFragment.PublicGalleryDialogListener{

    private RecyclerView pumkinRV =null;
    private GestureDetectorCompat detector=null;
    private pumkinfaceModel myModel = null;
    private pumkinAdapter pumkinServer =null;
    private ArrayList<String> pumkinData =null;
    private String username;

    @Override
    public void goPrintPumpkin(int position) {
        Intent goPrint = new Intent(this, PrintingActivity.class);
        goPrint.putExtra("RIGHT_EYE", myModel.socailfacelist.get(position).righteye);
        goPrint.putExtra("LEFT_EYE", myModel.socailfacelist.get(position).lefteye);
        goPrint.putExtra("NOSE", myModel.socailfacelist.get(position).nose);
        goPrint.putExtra("MOUTH", myModel.socailfacelist.get(position).mouth);
        startActivity(goPrint);

    }

    @Override
    public void goSavePumpkin(int position) {
        //Save the face to the local
        //save to database
        ParseObject privatePumkinFace = new ParseObject("PrivatePumpkinFace");
        privatePumkinFace.put("Username", username);
        privatePumkinFace.put("LeftEye",  myModel.socailfacelist.get(position).lefteye);
        privatePumkinFace.put("RightEye", myModel.socailfacelist.get(position).righteye);
        privatePumkinFace.put("Nose", myModel.socailfacelist.get(position).nose);
        privatePumkinFace.put("Mouth",myModel.socailfacelist.get(position).mouth);
        final int hold = position;
        privatePumkinFace.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d("ERROR", ""+e);
                Toast.makeText(getApplicationContext(),"Saved to your gallery!", Toast.LENGTH_SHORT).show();
                if (e == null) {
                    //if the facelist exists added to the list otherwise when the list is created the element will be added
                    if(pumkinfaceModel.singletonprivate != null) {
                        //add to face list
                        pumkinfaceModel.getpumkinfaceModel(username).faceList.add(new pumkinfaceModel.faceData(myModel.socailfacelist.get(hold).lefteye, myModel.socailfacelist.get(hold).righteye, myModel.socailfacelist.get(hold).nose, myModel.socailfacelist.get(hold).mouth));
                        //notify server
                        pumkinfaceModel.getpumkinfaceModel(username).privateAdaptor.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = pumkinRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                //when a image is clicked a dialog box will pop up with opts on what to do
                RecyclerView.ViewHolder holder = pumkinRV.getChildViewHolder(view);
                if (holder instanceof pumkinAdapter.pumkinViewHolder) {
                    int position = holder.getAdapterPosition();
                    Bundle args = new Bundle();
                    args.putInt("POSITION", position);
                    PublicGalleryDialogFragment publicGalleryDialogFragment = new PublicGalleryDialogFragment();
                    publicGalleryDialogFragment.setArguments(args);
                    publicGalleryDialogFragment.show(getSupportFragmentManager(), "Face Options");
                    return true;
                }
            }
            return false;
        }
    }


    public void redraw() {
        pumkinfaceModel.singletonSocial=null;
        myModel= pumkinfaceModel.getpumkinfaceModelSocial();
        pumkinServer = new pumkinAdapter();
        // Attach it to the RecyclerView
        pumkinRV = findViewById(R.id.pumkinRV);
        pumkinRV.setAdapter(pumkinServer);
        myModel.pumkinAdapter=pumkinServer;
        final RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        pumkinRV.setLayoutManager(myManager);
        pumkinServer.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_gallery);
        getSupportActionBar().hide();
        username = getIntent().getStringExtra(MainActivity.KEY_USER);
        //assigning imstance varibles to values
        myModel= pumkinfaceModel.getpumkinfaceModelSocial();
        pumkinServer = new pumkinAdapter();
        // Attach it to the RecyclerView
        pumkinRV = findViewById(R.id.pumkinRV);
        pumkinRV.setAdapter(pumkinServer);
        myModel.pumkinAdapter=pumkinServer;
        // Make and attach a layoutmanager
        final RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        pumkinRV.setLayoutManager(myManager);
        pumkinServer.notifyDataSetChanged();
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
}