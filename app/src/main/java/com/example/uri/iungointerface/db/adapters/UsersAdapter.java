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
import com.example.uri.iungointerface.db.User;

import java.util.ArrayList;


/**
 * Created by Uri on 12/09/2016.
 */
public class UsersAdapter extends ArrayAdapter<User> {

    Context myContext;
    int myLayoutResourceID;
    ArrayList<User> users = null;
    boolean chat;

    public UsersAdapter(Context context, int myLayoutResourceID, ArrayList<User> users, boolean chat) {
        super(context, myLayoutResourceID, users);

        this.myContext = context;
        this.myLayoutResourceID = myLayoutResourceID;
        this.users = users;
        this.chat = chat;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AmigosHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(myLayoutResourceID, parent, false);

            holder = new  AmigosHolder();
            holder.profile_picture = (ImageView) row.findViewById(R.id.profile_image_amigo);
            holder.name = (TextView) row.findViewById(R.id.amigo_name);
            holder.chat = (ImageView) row.findViewById(R.id.iv_status_amigo);

            row.setTag(holder);

        } else {
            holder = ( AmigosHolder) row.getTag();
        }

        User user = users.get(position);
        Glide.with(myContext).load(user.getUrl()).into(holder.profile_picture);
        //holder.profile_picture.setImageBitmap(amigo.profile_image);
        holder.name.setText(user.getName());
        if(chat) {
            holder.chat.setImageResource(R.drawable.ic_chat2);
        }else{
            holder.chat.setImageResource(0);
        }

        return row;
    }


    static class AmigosHolder {
        ImageView profile_picture, chat;
        TextView name;
    }
}
