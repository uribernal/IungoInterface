package com.example.uri.iungointerface.fragments.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.db.Chat;
import com.example.uri.iungointerface.db.User;
import com.example.uri.iungointerface.db.adapters.ChatAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

import java.util.ArrayList;

/**
 * Created by Uri on 04/11/2016.
 * This Activity ...
 */
public class ChatListFragment extends Fragment {


    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Chat> chat_list;
    private ProgressBar mProgressBar;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.chatslist_fragment, container, false);

        mProgressBar = (ProgressBar) v.findViewById(R.id.chats_progressBar);
        listView = (ListView) v.findViewById(R.id.lv_chats);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                ArrayList ids = chat_list.get(position).getParticipants();
                if (ids.get(0).equals("0")) {
                    //Go to chat
                    ((BaseActivity) getActivity()).go_to_chat_activity(ids.get(1).toString(), chat_list.get(position).getId());
                }else{
                    ((BaseActivity) getActivity()).go_to_chat_activity(ids.get(0).toString(), chat_list.get(position).getId());
                }

            }
        });

        getChats(v);

        return v;
    }


    public void getChats(View v) {
        fakeDbChats fDbC = new fakeDbChats();
        fakeDbUsers fDbU = new fakeDbUsers();
        ArrayList<String> u = fDbC.getUsersWithChat("0");
        ArrayList<User> users = fDbU.getUsers(u);
        chat_list = fDbC.getChatWithUser("0");

        mProgressBar.setVisibility(View.INVISIBLE);
        adapter = new ChatAdapter(v.getContext(), R.layout.item_chat, chat_list, users);
        listView.setAdapter(adapter);
    }
}