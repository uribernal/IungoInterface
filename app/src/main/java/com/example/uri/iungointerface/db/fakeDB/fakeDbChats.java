package com.example.uri.iungointerface.db.fakeDB;

import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.db.classes.Chat;
import com.example.uri.iungointerface.db.classes.Message;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Uri on 26/09/2017.
 */

public class fakeDbChats implements Values{

    private ArrayList<Chat> chats;

    public fakeDbChats() {
        createChats();
    }

    private void createChats() {
        chats = new ArrayList<Chat>();
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("0", TEXT_MESSAGE_LEFT, "hola", null));
        messages.add(new Message("1", TEXT_MESSAGE_RIGHT, "hola!", null));
        messages.add(new Message("1", TEXT_MESSAGE_RIGHT, "que tal?!", null));

        chats.add(new Chat("0", new ArrayList<>(Arrays.asList("0", "1")), "que tal?", "12:00", messages));

        messages = new ArrayList<>();
        messages.add(new Message("2", TEXT_MESSAGE_RIGHT, "Hi", null));
        messages.add(new Message("0", TEXT_MESSAGE_LEFT, "Hi!", null));
        messages.add(new Message("0", TEXT_MESSAGE_LEFT, "How r u?", null));
        chats.add(new Chat("1", new ArrayList<>(Arrays.asList("0", "2")), "How r u?", "13:00", messages));

        messages = new ArrayList<>();
        messages.add(new Message("3", PLAN_MESSAGE, null, "1"));
        messages.add(new Message("0", TEXT_MESSAGE_LEFT, "I can't go, but join my plan!", null));
        messages.add(new Message("0", PLAN_MESSAGE, null, "2"));
        chats.add(new Chat("2", new ArrayList<>(Arrays.asList("0", "3")), "Plan invitation", "13:00", messages));

        messages = new ArrayList<>();
        messages.add(new Message("2", TEXT_MESSAGE_RIGHT, "2", null));
        messages.add(new Message("1", TEXT_MESSAGE_RIGHT, "1", null));
        chats.add(new Chat("3", new ArrayList<>(Arrays.asList("1", "2")), "1", "14:00", messages));

    }

    public ArrayList<Chat> getChatWithUser(String id){
        ArrayList<Chat> c = new ArrayList<>();
        for (Chat chat:chats){
            for (String id_user: chat.getParticipants())
            if ((id_user.equals(id) || id_user.equals(id))){
                c.add(chat);
            }
        }
        return c;
    }

    public ArrayList<String> getUsersWithChat(String id_user){
        ArrayList<String> c = new ArrayList<>();
        for (Chat chat:chats){
            if (chat.getParticipants().size() < 3) {
                String id_1 = chat.getParticipants().get(0);
                String id_2 = chat.getParticipants().get(1);
                if (id_user.equals(id_1)){
                    c.add(id_2);
                }else if (id_user.equals(id_2)){
                    c.add(id_1);
                }
            }
        }
        return c;
    }

    public Chat getChatBetweenUsers(String id1, String id2){
        for (Chat chat:chats) {
            if (chat.getParticipants().size() < 3) {
                String id_1 = chat.getParticipants().get(0);
                String id_2 = chat.getParticipants().get(1);
                if ((id_1.equals(id1) && id_2.equals(id2)) || (id_1.equals(id2) && id_2.equals(id1))) {
                    return chat;
                }
            }
        }

        return null;
    }

    public Chat getChat(String id){

        return chats.get(Integer.parseInt(id));
    }


}
