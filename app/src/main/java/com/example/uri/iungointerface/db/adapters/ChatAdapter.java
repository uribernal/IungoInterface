package com.example.uri.iungointerface.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.db.Chat;
import com.example.uri.iungointerface.db.User;

import java.util.ArrayList;

/**
 * Created by Uri on 29/12/2016.
 */

public class ChatAdapter extends ArrayAdapter<Chat> {

    Context myContext;
    int myLayoutResourceID;
    ArrayList<Chat> myChats;
    ArrayList<User> users;

    public ChatAdapter(Context context, int myLayoutResourceID, ArrayList<Chat> myChats, ArrayList<User> users) {
        super(context, myLayoutResourceID, myChats);

        this.myContext = context;
        this.myLayoutResourceID = myLayoutResourceID;
        this.myChats = myChats;
        this.users = users;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ChatAdapter.ChatHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(myLayoutResourceID, parent, false);

            holder = new ChatAdapter.ChatHolder();
            holder.profile_picture = (ImageView) row.findViewById(R.id.profile_image_amigo_chats);
            holder.name = (TextView) row.findViewById(R.id.amigo_name_chats);
            holder.message = (TextView) row.findViewById(R.id.first_message);
            holder.hour = (TextView) row.findViewById(R.id.tv_chats_time);

            row.setTag(holder);

        } else {
            holder = (ChatAdapter.ChatHolder) row.getTag();
        }

        Chat chat = myChats.get(position);
        User user = users.get(position);
        Glide.with(myContext).load(user.getPhoto_url()).into(holder.profile_picture);
        holder.name.setText(user.getName());
        holder.message.setText(chat.getLast());
        holder.hour.setText(chat.getTime());

        return row;
    }


    static class ChatHolder {
        ImageView profile_picture;
        TextView name, message, hour;
    }
}
