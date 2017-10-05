package com.example.uri.iungointerface.activities.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.db.classes.Chat;
import com.example.uri.iungointerface.db.classes.Message;
import com.example.uri.iungointerface.db.classes.User;
import com.example.uri.iungointerface.db.adapters.MessagesAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

public class ChatActivity extends BaseActivity implements Values {
    private MessagesAdapter messagesAdapter;

    private Button mSendButton;
    private ListView listView;
    private EditText chatText;
    private Chat chat;
    private User friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_chat, null, false);
        drawer.addView(contentView, 0);

        // Get Info
        Bundle b = getIntent().getExtras();
        final String chat_id = b.getString("CHAT_ID");
        final String friend_id = b.getString(USER);
        final User me = b.getParcelable(ME);

        // Get Data
        prepareMessages(chat_id, friend_id);

        // Initialize
        mSendButton = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.msgview);
        chatText = (EditText) findViewById(R.id.msg);
        if (chat_id != null && !chat_id.equals("")) {
            messagesAdapter = new MessagesAdapter(getApplicationContext(), R.layout.item_message_left, me.getId(), me.getName(), me.getPhoto_url(), friend.getName(), friend.getPhoto_url(), chat.getMessages());
        }else{
            messagesAdapter = new MessagesAdapter(getApplicationContext(), R.layout.item_message_left, me.getId(), me.getName(), me.getPhoto_url(), friend.getName(), friend.getPhoto_url(), null);
        }

        // If no messages ... display no messages, until.. progessBar load
        mSendButton.setClickable(false);

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(messagesAdapter);


        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
                chatText.setText("");
            }
        });
    }

    private void sendChatMessage() {
        if (chatText.getText().toString().equals("")){
            return;
        }
        messagesAdapter.add(new Message("0", Values.TEXT_MESSAGE_RIGHT, chatText.getText().toString(), null));
    }

    private void prepareMessages(String chat_id, String friend_id) {
        fakeDbUsers fDbU = new fakeDbUsers();
        fakeDbChats fDbC = new fakeDbChats();

        friend = fDbU.getUser(friend_id);
        if (chat_id != null && !chat_id.equals("")) {
            chat = fDbC.getChat(chat_id);
        }


    }


}
