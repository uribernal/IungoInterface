package com.example.uri.iungointerface.db.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.db.classes.Message;
import com.example.uri.iungointerface.db.fakeDB.fakeDbPlans;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends ArrayAdapter<Message> implements Values {

    private TextView name, message, hour;
    private ImageView profile_picture, iv_plan_image;
    private List<Message> chatMessageList = new ArrayList<Message>();
    private Context context;
    private String myId, myName, myUrl, friendName, friendUrl;

    @Override
    public void add(Message object) {
        if (chatMessageList == null){
            chatMessageList = new ArrayList<Message>();
        }
        chatMessageList.add(object);
        super.add(object);
    }

    public MessagesAdapter(Context context, int textViewResourceId, String myId, String myName, String myUrl, String friendName, String friendUrl, ArrayList<Message> messages) {
        super(context, textViewResourceId);
        this.context = context;
        this.myId = myId;
        this.myName = myName;
        this.myUrl = myUrl;
        this.friendName = friendName;
        this.friendUrl = friendUrl;
        this.chatMessageList = messages;
    }



    public int getCount() {
        if (chatMessageList == null){
            return 0;
        }else {
            return this.chatMessageList.size();
        }
    }

    public Message getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Message chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(chatMessageObj.getType() == Values.PLAN_MESSAGE){
            row = inflater.inflate(R.layout.item_message_plan, parent, false);
            profile_picture = (ImageView) row.findViewById(R.id.messengerImageView);
            iv_plan_image = (ImageView) row.findViewById(R.id.iv_plan_image);
            message = (TextView) row.findViewById(R.id.messageTextView);
            hour = (TextView) row.findViewById(R.id.messengerTextView_time);
            if (chatMessageObj.getId().equals(myId)) {
                Glide.with(context).load(myUrl).into(profile_picture);
            } else {
                Glide.with(context).load(friendUrl).into(profile_picture);
            }
            Glide.with(context).load(getPlanUrl(chatMessageObj.getPlan())).into(iv_plan_image);
            message.setText("Join this plan");
            hour.setText(chatMessageObj.getDate().toString());

        }else {
            if (chatMessageObj.getId().equals(myId)) {
                row = inflater.inflate(R.layout.item_message_left, parent, false);
            } else {
                row = inflater.inflate(R.layout.item_message_right, parent, false);
            }

            profile_picture = (ImageView) row.findViewById(R.id.messengerImageView);
            name = (TextView) row.findViewById(R.id.messengerTextView);
            message = (TextView) row.findViewById(R.id.messageTextView);
            hour = (TextView) row.findViewById(R.id.messengerTextView_time);
            if (chatMessageObj.getId().equals(myId)) {
                Glide.with(context).load(myUrl).into(profile_picture);
                name.setText(myName);
            } else {
                Glide.with(context).load(friendUrl).into(profile_picture);
                name.setText(friendName);
            }
            message.setText(chatMessageObj.getText());
            hour.setText(chatMessageObj.getDate().toString());
        }
        return row;
    }

    public String getPlanUrl(String id){
        fakeDbPlans fDbP = new fakeDbPlans();
        return fDbP.getPlan(id).getPlan_photos().get(0);
    }
}