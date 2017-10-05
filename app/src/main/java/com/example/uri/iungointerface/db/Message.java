package com.example.uri.iungointerface.db;

import com.example.uri.iungointerface.Values;

import java.util.Calendar;


/**
 * Created by Uri on 11/03/2017.
 * Cada misstage esta format per un emissor (name)
 * la seva foto de perfil (photoUrl) i una data.
 * Hi han 2 tipus de missatges:
 *      - TEXT_MESSAGE: Missatge de text normal
 *      - PLAN_MESSAGE: Es una invitació per unir-se a un plan,
 *      està associat a la id d'un plan en concret i aquest
 *      missatges es mostren de manera diferent a la conversació.
 */

public class Message implements Values {

    int type;
    String id, name, photoUrl, text, date, plan;

    public Message(String id, int type, String text, String plan){
        Calendar now = Calendar.getInstance();
        this.type = type;
        date = now.toString();
        this.id = id;
        if (type==TEXT_MESSAGE_LEFT || type==TEXT_MESSAGE_RIGHT){
            this.text = text;
            this.plan = "";
        }else if (type== PLAN_MESSAGE){
            this.plan = plan;
        }else{
            this.text = "";
            this.plan = "";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

}
