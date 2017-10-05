package com.example.uri.iungointerface.db.fakeDB;

import com.example.uri.iungointerface.db.Chat;
import com.example.uri.iungointerface.db.Message;
import com.example.uri.iungointerface.db.Plan;

import java.util.ArrayList;

/**
 * Created by Uri on 26/09/2017.
 */

public class fakeDbChats {

    private ArrayList<Chat> chats;

    public fakeDbChats() {
        createChats();
    }

    private void createChats() {
        chats = new ArrayList<Chat>();
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("0", 1, "hola", null));
        messages.add(new Message("1", 1, "hola!", null));
        messages.add(new Message("1", 1, "que tal?!", null));
        chats.add(new Chat("0", "0", "1", "que tal?", "12:00", messages));

        messages = new ArrayList<>();
        messages.add(new Message("2", 1, "Hi", null));
        messages.add(new Message("0", 1, "Hi!", null));
        messages.add(new Message("0", 1, "How r u?", null));
        chats.add(new Chat("1", "0", "2", "How r u?", "13:00", messages));

        messages = new ArrayList<>();
        messages.add(new Message("2", 1, "2", null));
        messages.add(new Message("1", 1, "1", null));
        chats.add(new Chat("2", "1", "2", "1", "14:00", messages));
    }

    public ArrayList<Chat> getChatWithUser(String id){
        ArrayList<Chat> c = new ArrayList<>();
        for (Chat chat:chats){
            if (chat.getUser1().equals("0") || chat.getUser2().equals("0")){
                c.add(chat);
            }
        }
        return c;
    }

    public Chat getChatBetweenUsers(String id1, String id2){
        Chat c;
        for (Chat chat:chats){
            if ((chat.getUser1().equals(id1) && chat.getUser2().equals(id2))||(chat.getUser1().equals(id2) && chat.getUser2().equals(id1))){
                return chat;
            }
        }
        return null;
    }

    public Chat getChat(String id){

        return chats.get(Integer.parseInt(id));
    }
}
