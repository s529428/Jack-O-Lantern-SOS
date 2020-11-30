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
    public ArrayList<faceData> socailfacelist;
    public pumkinPrivateAdaptor privateAdaptor =null;
    public pumkinAdapter pumkinAdapter =null;
    //constructor for private gallery
    private pumkinfaceModel(int num, String username) {
        if(num ==0){
            faceList = new ArrayList<faceData>();
           privateLoad(username);
        }
        if(num ==1){
            socailfacelist=new ArrayList<faceData>();
            socialLoad();
        }
    }
    private void privateLoad(String username){
        //pulling in private faces
        Log.d("username",username);
        //pulling private faces
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PrivatePumpkinFace");
        query.whereEqualTo("Username",username);
        query.findInBackground(new FindCallback<ParseObject>() {
            //pulling all the faces from the public table
            public void done(List<ParseObject> pumkinList, ParseException e) {
                if (e == null) {
                    //sucess branch
                    String result = "";
                    Log.d("username",""+pumkinList.size());
                    for (int i = 0; i < pumkinList.size(); i++) {
                        ParseObject single = pumkinList.get(i);
                        faceList.add(new faceData(single.getString("LeftEye"),single.getString("RightEye"),single.getString("Nose"), single.getString("Mouth")));
                    }
                    if(privateAdaptor!=null){
                        privateAdaptor.notifyDataSetChanged();
                    }
                } else {
                    //fail branch
                    Log.d("face", "ERROR; " + e.getMessage());
                }
            }
        });
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
                        socailfacelist.add(new faceData(single.getString("LeftEye"),single.getString("RightEye"), single.getString("Nose"), single.getString("Mouth")));
                    }
                    if(pumkinAdapter!=null){
                        pumkinAdapter.notifyDataSetChanged();
                    }
                } else {
                    //fail branch
                    Log.d("face", "ERROR; " + e.getMessage());
                }
            }
        });
    }
    //creating singletons for private and public database
    public static pumkinfaceModel singletonSocial =null;
    public static pumkinfaceModel singletonprivate =null;

    public static pumkinfaceModel getpumkinfaceModelSocial() {
        if (singletonSocial == null) {
            singletonSocial = new pumkinfaceModel(1,"");
        }
        return singletonSocial;
    }
    public static pumkinfaceModel getpumkinfaceModel(String username){
        if(singletonprivate==null){
            singletonprivate= new pumkinfaceModel(0, username);
        }
        return singletonprivate;
    }
}
