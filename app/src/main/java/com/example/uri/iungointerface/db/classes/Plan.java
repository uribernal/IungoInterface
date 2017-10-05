package com.example.uri.iungointerface.db.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Uri on 13/01/2017.
 *
 */

public class Plan implements Parcelable {
    private String id;
    private String name;
    private String price;
    private String description;
    private String direction_activity;
    private String direction_meeting;
    private String date;
    private String hour;
    private int num_people, max_people, min_people;
    private ArrayList<String> users_fbid, plan_photos;

    public Plan(){
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
        out.writeString(price);
        out.writeString(description);
        out.writeString(direction_activity);
        out.writeString(direction_meeting);
        out.writeString(date);
        out.writeString(hour);
        out.writeInt(num_people);
        out.writeInt(max_people);
        out.writeInt(min_people);
        out.writeSerializable(users_fbid);
        out.writeSerializable(plan_photos);


    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Plan(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        description = in.readString();
        direction_activity = in.readString();
        direction_meeting = in.readString();
        date = in.readString();
        hour = in.readString();
        num_people = in.readInt();
        max_people = in.readInt();
        min_people = in.readInt();
        users_fbid = (ArrayList<String>) in.readSerializable();
        plan_photos = (ArrayList<String>)  in.readSerializable();


    }

    public Plan(String id, String name, String price, String description, String direction_activity,
                String direction_meeting, String date, String hour, int num_people, int max_people,
                int min_people, ArrayList<String> users_fbid, ArrayList<String> plan_photos) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.direction_activity = direction_activity;
        this.direction_meeting = direction_meeting;
        this.date = date;
        this.hour = hour;
        this.num_people = num_people;
        this.max_people = max_people;
        this.min_people = min_people;
        this.users_fbid = users_fbid;
        this.plan_photos = plan_photos;

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDirection_activity() {
        return direction_activity;
    }

    public String getDirection_meeting() {
        return direction_meeting;
    }

    public int getNum_people() {
        return num_people;
    }

    public int getMax_people() {
        return max_people;
    }

    public int getMin_people() {
        return min_people;
    }

    public ArrayList<String> getUsers_fbid() {
        return users_fbid;
    }

    public ArrayList<String> getPlan_photos() {
        return plan_photos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirection_activity(String direction_activity) {
        this.direction_activity = direction_activity;
    }

    public void setDirection_meeting(String direction_meeting) {
        this.direction_meeting = direction_meeting;
    }

    public void setNum_people(int num_people) {
        this.num_people = num_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public void setMin_people(int min_people) {
        this.min_people = min_people;
    }

    public void setUsers_fbid(ArrayList<String> users_fbid) {
        this.users_fbid = users_fbid;
    }

    public void setPlan_photos(ArrayList<String> plan_photos) {
        this.plan_photos = plan_photos;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
