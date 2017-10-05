package com.example.uri.iungointerface.db.classes;


import java.util.ArrayList;

/**
 * Created by Uri on 11/03/2017.
 * Els xats normals són sempre de 2 persones (user1 i user2)
 * last indica l'ultim missatge enviat (per preview en llista de chats)
 * time indica l'hora de l'ultim missatge
 */

public class Chat {

    private String id;
    private ArrayList<String> participants_ids;
    private String last;
    private String time;
    private ArrayList<Message> messages;

    public Chat(String id, ArrayList<String> participants_ids, String last, String time, ArrayList<Message> messages){

        this.id = id;
        this.participants_ids = participants_ids;
        this. last = last;
        this.time = time;
        this.messages = messages;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public ArrayList<Message> getMessages(){
        return this.messages;
    }

    public ArrayList<String> getParticipants() {
        return participants_ids;
    }

    public void setParticipants(ArrayList<String> participants_ids) {
        this.participants_ids = participants_ids;
    }


    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

}
