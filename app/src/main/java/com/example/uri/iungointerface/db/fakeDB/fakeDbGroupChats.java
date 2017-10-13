package com.example.uri.iungointerface.db.fakeDB;

import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.db.classes.Chat;
import com.example.uri.iungointerface.db.classes.Message;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Uri on 26/09/2017.
 */

public class fakeDbGroupChats implements Values{

    private ArrayList<Chat> chats;

    public fakeDbGroupChats() {
        createChats();
    }

    private void createChats() {
        chats = new ArrayList<Chat>();
        ArrayList<Message> messages = new ArrayList<>();
        messages = new ArrayList<>();
        messages.add(new Message("1", TEXT_MESSAGE_RIGHT, "Is anyone going by car?", null));
        messages.add(new Message("2", TEXT_MESSAGE_RIGHT, "Me! I have 3 seats.", null));
        messages.add(new Message("3", TEXT_MESSAGE_RIGHT, "I'd join if possible", null));
        messages.add(new Message("4", TEXT_MESSAGE_RIGHT, "Me too", null));
        messages.add(new Message("1", TEXT_MESSAGE_RIGHT, "Yeah!", null));
        chats.add(new Chat("3", new ArrayList<>(Arrays.asList("1", "2", "3", "4")), "Yeah!", "14:00", messages));
    }


    public Chat getChat(String id){
        Chat c = null;
        for (Chat chat:chats) {
            if (chat.getId().equals(id)) {
                c = chat;
            }
        }
        return c;    }


}
