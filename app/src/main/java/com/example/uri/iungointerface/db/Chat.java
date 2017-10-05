package com.example.uri.iungointerface.db;


import java.util.ArrayList;

/**
 * Created by Uri on 11/03/2017.
 * Els xats normals són sempre de 2 persones (user1 i user2)
 * last indica l'ultim missatge enviat (per preview en llista de chats)
 * time indica l'hora de l'ultim missatge
 */

public class Chat {


    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String user1;
    private String user2;
    private String last;
    private String time;
    private ArrayList<Message> messages;

    public Chat(String id, String user1, String user2, String last, String time, ArrayList<Message> messages){

        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this. last = last;
        this.time = time;
        this.messages = messages;
    }

    public ArrayList<Message> getMessages(){
        return this.messages;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String id1) {
        this.user1 = id1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String id2) {
        this.user2 = id2;
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
