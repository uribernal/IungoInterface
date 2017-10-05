package com.example.uri.iungointerface.db.classes;

/**
 * Created by Uri on 06/01/2017.
 */

public class Item {

    //public Bitmap profile_image;
    public String name;
    public int resource;


    public Item(int image, String name){
        super();
        //this.profile_image = profile_image;
        this.resource = image;
        this.name = name;

    }

    public String getName(){
        return this.name;
    }
    public int getResource(){
        return this.resource;
    }
}
