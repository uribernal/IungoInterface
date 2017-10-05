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
    private ArrayList<String> ids, names, urls, chats, ids1, ids2, messages, time;
    private ProgressBar mProgressBar;
    private String fbid;

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

                //Go to chat
                ((BaseActivity) getActivity()).go_to_chat_activity(chat_list.get(position).getUser2(), chat_list.get(position).getId());

            }
        });

        getChats(v);



        /*mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        chats = (ArrayList) dataSnapshot.child("users").child(fbid).child("chats").getValue();
                        if (chats != null) {
                            chat_list = new ArrayList<Chat>(chats.size());
                            ids = new ArrayList<String>(chats.size());
                            ids1 = new ArrayList<String>(chats.size());
                            ids2 = new ArrayList<String>(chats.size());
                            names = new ArrayList<String>(chats.size());
                            urls = new ArrayList<String>(chats.size());
                            messages = new ArrayList<String>(chats.size());
                            time = new ArrayList<String>(chats.size());

                            for (int i = 0; i < chats.size(); i++) {
                                ids1.add((String) dataSnapshot.child("chats").child(chats.get(i)).child("id1").getValue());
                                ids2.add((String) dataSnapshot.child("chats").child(chats.get(i)).child("id2").getValue());
                                if (ids1.get(i).equals(fbid)) {
                                    ids.add(ids2.get(i));
                                } else {
                                    ids.add(ids1.get(i));
                                }
                                names.add((String) dataSnapshot.child("users").child(ids.get(i)).child("name").getValue());
                                urls.add((String) dataSnapshot.child("users").child(ids.get(i)).child("url").getValue());
                                messages.add((String) dataSnapshot.child("chats").child(chats.get(i)).child("last").getValue());
                                time.add((String) dataSnapshot.child("chats").child(chats.get(i)).child("time").getValue());
                                Chat c = new Chat(urls.get(i), names.get(i), messages.get(i), time.get(i));
                                chat_list.add(c);

                            }
                            mProgressBar.setVisibility(View.INVISIBLE);
                            adapter = new ChatAdapter(v.getContext(), R.layout.item_chat, chat_list);
                            listView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });*/


        return v;
    }


    public void getChats(View v) {
        ArrayList<User> u = new ArrayList<>();
        fakeDbChats fDbC = new fakeDbChats();
        fakeDbUsers fDbU = new fakeDbUsers();
        chat_list = fDbC.getChatWithUser("0");
        for (Chat chat:chat_list){
            if (chat.getUser1().equals("0")){
                u.add(fDbU.getUser(chat.getUser2()));
            }else{
                u.add(fDbU.getUser(chat.getUser1()));
            }
        }
        mProgressBar.setVisibility(View.INVISIBLE);
        adapter = new ChatAdapter(v.getContext(), R.layout.item_chat, chat_list, u);
        listView.setAdapter(adapter);
    }
}