package com.example.uri.iungointerface.db;

import java.util.ArrayList;

/**
 * Created by Uri on 11/03/2017.
 * Els xats en grup estan a dins dels plans i
 * nomes tenen missatges de les persones que
 * estan a la activitat
 */

public class GroupChat {

    private ArrayList<Message> messages;

    public GroupChat(ArrayList<Message> messages){

        this.messages = messages;
    }

}
