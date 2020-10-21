package com.example.jackolanternsos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class pumkinAdapter extends RecyclerView.Adapter<pumkinAdapter.pumkinViewHolder> {
    public static class pumkinViewHolder extends RecyclerView.ViewHolder {
        //creating view holder that will hold the pumkin images
        public pumkinViewHolder(View v) {
        super(v);
    }
    }
    private pumkinfaceModel myModel;
    public pumkinAdapter(){
        myModel= pumkinfaceModel.getpumkinfaceModel();
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
        //finding all the elements of the face
        ImageView leftEye = holder.itemView.findViewById(R.id.leftEyeIV5);
        ImageView rightEye = holder.itemView.findViewById(R.id.rightEyeIV5);
        ImageView nose = holder.itemView.findViewById(R.id.noseIV5);
        ImageView mouth = holder.itemView.findViewById(R.id.mouthIV5);
        String json =myModel.faceList.get(position).jsonstring;
        //i need to break up the json string
        //i need to set each elemt to a part of that string

        //leftEye.setImageDrawable(R.drawable.lefteye);

    }
    public int getItemCount() {
      //getting size
        return myModel.faceList.size();
    }
}
