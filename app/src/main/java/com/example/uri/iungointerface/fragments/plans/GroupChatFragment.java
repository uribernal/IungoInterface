package com.example.uri.iungointerface.fragments.plans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.activities.plans.PlanActivity;
import com.example.uri.iungointerface.db.adapters.MessagesAdapter;
import com.example.uri.iungointerface.db.classes.Chat;
import com.example.uri.iungointerface.db.classes.Message;
import com.example.uri.iungointerface.db.classes.Plan;
import com.example.uri.iungointerface.db.classes.User;
import com.example.uri.iungointerface.db.fakeDB.fakeDbChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbGroupChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbPlans;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

import static com.example.uri.iungointerface.Values.ME;
import static com.example.uri.iungointerface.Values.PLAN;


/**
 * Created by Uri on 04/11/2016.
 * This Fragment ...
 */
public class GroupChatFragment extends Fragment {
    private MessagesAdapter messagesAdapter;

    private Button mSendButton;
    private ListView listView;
    private EditText chatText;
    private Chat chat;
    private User friend;

    public GroupChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.activity_chat, container, false);

        // Get Info
        Plan p = getActivity().getIntent().getExtras().getParcelable(PLAN);
        final String chat_id = p.getId();
        fakeDbUsers fDbU = new fakeDbUsers();
        User me = fDbU.getMe();

        // Get Data
        prepareMessages(chat_id);

        // Initialize
        mSendButton = (Button) v.findViewById(R.id.send);
        listView = (ListView) v.findViewById(R.id.msgview);
        chatText = (EditText) v.findViewById(R.id.msg);
        if (chat_id != null && !chat_id.equals("") && chat != null) {
            messagesAdapter = new MessagesAdapter(getContext(), R.layout.item_message_left, me.getId(), me.getName(), me.getPhoto_url(), null, p.getId(), chat.getMessages(), true);
        }else{
            messagesAdapter = new MessagesAdapter(getContext(), R.layout.item_message_left, me.getId(), me.getName(), me.getPhoto_url(), null, p.getId(), null, true);
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

        // On click go to Plan Invitation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (messagesAdapter.getItem(position).getType() == Values.PLAN_MESSAGE) {
                    Intent intent = new Intent(getContext(), PlanActivity.class);
                    intent.putExtra(PLAN, getPlan(messagesAdapter.getItem(position).getPlan()));
                    startActivity(intent);
                }
            }
        });
        return v;
    }

    private void prepareMessages(String chat_id) {
        fakeDbGroupChats fDbC = new fakeDbGroupChats();

        chat = fDbC.getChat(chat_id);
    }

    private void sendChatMessage() {
        if (chatText.getText().toString().equals("")){
            return;
        }
        messagesAdapter.add(new Message("0", Values.TEXT_MESSAGE_RIGHT, chatText.getText().toString(), null));
    }

    private Plan getPlan(String plan) {
        fakeDbPlans fDbP = new fakeDbPlans();
        return fDbP.getPlan(plan);
    }


}