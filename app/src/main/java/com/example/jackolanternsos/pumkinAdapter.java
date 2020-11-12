package com.example.jackolanternsos;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//this is the adaptor for the socail gallery
public class pumkinAdapter extends RecyclerView.Adapter<pumkinAdapter.pumkinViewHolder> {
    public static class pumkinViewHolder extends RecyclerView.ViewHolder {
        //creating view holder that will hold the pumkin images
        public pumkinViewHolder(View v) {
        super(v);
    }
    }
    private pumkinfaceModel myModel;
    public pumkinAdapter(){
        myModel= pumkinfaceModel.getpumkinfaceModelSocial();
    }
    @NonNull
    @Override
    public pumkinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pumkin_list_image_view, parent, false);
        pumkinViewHolder vh = new pumkinViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(@NonNull pumkinViewHolder holder, int position) {
        Log.d("Adaptor","Bind in adaptor");
        //finding all the elements of the face
        ImageView leftEye = holder.itemView.findViewById(R.id.leftEyeIV5);
        ImageView rightEye = holder.itemView.findViewById(R.id.rightEyeIV5);
        ImageView nose = holder.itemView.findViewById(R.id.noseIV5);
        ImageView mouth = holder.itemView.findViewById(R.id.mouthIV5);
        //setting image to previous faces
        String lEye = myModel.socailfacelist.get(position).lefteye;
        //have to brute force images since it is in a model
        if(lEye.equals("eye1")){
            leftEye.setImageResource(R.drawable.eye1);
        }
        else if(lEye.equals("eye2")){
            leftEye.setImageResource(R.drawable.eye2);
        }
        else if(lEye.equals("eye3")){
            leftEye.setImageResource(R.drawable.eye3);
        }
        else if(lEye.equals("eye4")){
            leftEye.setImageResource(R.drawable.eye4);
        }
        else if(lEye.equals("eye5")){
            leftEye.setImageResource(R.drawable.eye5);
        }
        String rEye = myModel.socailfacelist.get(position).righteye;
        if(rEye.equals("eye1")){
            rightEye.setImageResource(R.drawable.eye1);
        }
        else if(rEye.equals("eye2")){
            rightEye.setImageResource(R.drawable.eye2);
        }
        else if(rEye.equals("eye3")){
            rightEye.setImageResource(R.drawable.eye3);
        }
        else if(rEye.equals("eye4")){
            rightEye.setImageResource(R.drawable.eye4);
        }
        else if(rEye.equals("eye5")){
            rightEye.setImageResource(R.drawable.eye5);
        }
        String nosie= myModel.socailfacelist.get(position).nose;
        if(nosie.equals("nose1")){
            nose.setImageResource(R.drawable.nose1);
        }
        else if(nosie.equals("nose2")){
            nose.setImageResource(R.drawable.nose2);
        }
        else if(nosie.equals("nose3")){
            nose.setImageResource(R.drawable.nose3);
        }else if(nosie.equals("nose4")){
            nose.setImageResource(R.drawable.nose4);
        }else if(nosie.equals("nose5")){
            nose.setImageResource(R.drawable.nose5);
        }
        String mouthy= myModel.socailfacelist.get(position).mouth;
        if(mouthy.equals("mouth1")){
            mouth.setImageResource(R.drawable.mouth1);
        }
        else if(mouthy.equals("mouth2")){
            mouth.setImageResource(R.drawable.mouth2);
        }
        else if(mouthy.equals("3")){
            mouth.setImageResource(R.drawable.mouth3);
        }
        else if(mouthy.equals("mouth4")){
            mouth.setImageResource(R.drawable.mouth4);
        }
        else if(mouthy.equals("mouth5")){
            mouth.setImageResource(R.drawable.mouth5);
        }

    }
    public int getItemCount() {
      //getting size
        return myModel.socailfacelist.size();
    }
}
