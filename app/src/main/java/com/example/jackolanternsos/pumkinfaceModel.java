package com.example.jackolanternsos;

import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class pumkinfaceModel {
    public static class faceData {
        //making the face
        public String lefteye;
        public String righteye;
        public String nose;
        public String mouth;

        public faceData(String lefteye, String righteye, String nose, String mouth) {
            this.lefteye = lefteye;
            this.righteye = righteye;
            this.nose = nose;
            this.mouth = mouth;
        }
    }

    //instance variables and methods
    public ArrayList<faceData> faceList;

    //constructor for private gallery
    private pumkinfaceModel(int num) {
        faceList = new ArrayList<faceData>();
        if(num ==0){
            privateLoad();
        }
        if(num ==1){
            socialLoad();
        }
    }
    private void privateLoad(){
        //pulling in private faces
    }
    private void socialLoad() {
        //pulling in public stored faces
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PublicPumkinFace");
        query.findInBackground(new FindCallback<ParseObject>() {
            //pulling all the faces from the public table
            public void done(List<ParseObject> pumkinList, ParseException e) {
                if (e == null) {
                    //sucess branch
                    String result = "";
                    for (int i = 0; i < pumkinList.size(); i++) {
                        ParseObject single = pumkinList.get(i);
                        Log.d("table",""+single.getString("RightEye"));
                        faceList.add(new faceData(single.getString("RightEye"), single.getString("LeftEye"), single.getString("Nose"), single.getString("Mouth")));
                    }
                } else {
                    //fail branch
                    Log.d("face", "ERROR; " + e.getMessage());
                }
            }
        });
    }
    //creating singletons for private and public database
    private static pumkinfaceModel singletonSocial =null;
    private static pumkinfaceModel singletonprivate =null;
    private static pumkinfaceModel singletonError =null;

    public static pumkinfaceModel getpumkinfaceModelSocial(int num) {
        if (singletonprivate == null && num == 0) {
            singletonprivate = new pumkinfaceModel(0);
        }
        if (num == 0) {
            return singletonprivate;
        }
        if (singletonSocial == null && num == 1) {
            singletonSocial = new pumkinfaceModel(1);
        }
        if (num == 1) {
            return singletonSocial;
        }
        return singletonError;
    }
}
