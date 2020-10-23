package com.example.jackolanternsos;

import java.util.ArrayList;

public class pumkinfaceModel {
    public static class faceData {
        public String jsonstring;

        public faceData(String jsonstring) {
            this.jsonstring = jsonstring;
        }
    }

    //instance variables and methods
    public ArrayList<faceData> faceList;

    //constructor
    private pumkinfaceModel() {
        faceList = new ArrayList<faceData>();
        load();
    }
    private void load(){
        //pulling in stored faces
    }
    private static pumkinfaceModel singleton = null;
    //creating another singleton for the social gallery
    private static pumkinfaceModel singleton1 =null;

    public static pumkinfaceModel getpumkinfaceModel() {
        if (singleton == null) {
            singleton = new pumkinfaceModel();
        }
        return singleton;
    }
    public static pumkinfaceModel getpumkinfaceModelSocial() {
        if (singleton1 == null) {
            singleton1 = new pumkinfaceModel();
        }
        return singleton1;
    }
}
