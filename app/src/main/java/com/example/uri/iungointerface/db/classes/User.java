package com.example.uri.iungointerface.db.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Uri on 26/12/2016.
 */

public class User implements Parcelable {


    String id, name, email, photo_url, age, gender;
    boolean datos;
    ArrayList<String> fb_friends, chats, plans;
    ArrayList<Boolean> preferences;

    public User(){
    }

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(name);
        out.writeString(email);
        out.writeString(photo_url);
        out.writeString(age);
        out.writeString(gender);
        out.writeInt(datos ? 1 : 0);
        out.writeSerializable(fb_friends);
        out.writeSerializable(chats);
        out.writeSerializable(plans);
        out.writeSerializable(preferences);

    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private User(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        photo_url = in.readString();
        age = in.readString();
        gender = in.readString();
        datos = in.readInt() == 1;
        fb_friends = (ArrayList<String>) in.readSerializable();
        chats = (ArrayList<String>)  in.readSerializable();
        plans = (ArrayList<String>)  in.readSerializable();
        preferences =  (ArrayList<Boolean>)  in.readSerializable();

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }


    public ArrayList<String> getChats() {
        return chats;
    }

    public ArrayList<Boolean> getPreferences() {
        return preferences;
    }

    public ArrayList<String> getPlans() {
        return plans;
    }


    public boolean getDatos() {
        return datos;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFb_friends(ArrayList<String> fb_friends) {
        this.fb_friends = fb_friends;
    }

    public void setChats(ArrayList<String> chats) {
        this.chats = chats;
    }

    public void setPreferences(ArrayList<Boolean> preferences) {
        this.preferences = preferences;
    }

    public void setDatos(boolean datos) {
        this.datos = datos;
    }

    public void addfriend(String id){
        if (!fb_friends.contains(id)) {
            fb_friends.add(id);
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoto_url(String photo) {
        this.photo_url = photo;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPlans(ArrayList<String> plans) {
        this.plans = plans;
    }

    public ArrayList<String> getFb_friends() {
        return fb_friends;
    }



}
