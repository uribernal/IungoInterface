package com.example.uri.iungointerface.fragments.chat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.db.User;
import com.example.uri.iungointerface.db.adapters.UsersAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

import java.util.ArrayList;

/**
 * Created by Uri on 04/11/2016.
 * caca
 */
public class FriendListFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<User> friends;
    private ProgressBar mProgressBar;
    private int amigos_comun;

    public FriendListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.friendslist_fragment, container, false);


        mProgressBar = (ProgressBar) v.findViewById(R.id.friends_progressBar);
        listView = (ListView) v.findViewById(R.id.lv_amigos);

        // Compute friends
        getAmigos();

        // On click go to friend profile
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                ((BaseActivity) getActivity()).go_to_profile(friends.get(position).getFbid());

            }
        });

        return v;
    }

    public void getAmigos() {
        //per cada ID->Get Name i photo
        fakeDbUsers fDbU = new fakeDbUsers();
        ArrayList<String> ids = fDbU.getMe().getFb_friends();
        friends = fDbU.getUsers(ids);

        mProgressBar.setVisibility(View.INVISIBLE);
        adapter = new UsersAdapter(this.getContext(), R.layout.item_user, friends, false);
        listView.setAdapter(adapter);
    }



    public int computeCommonFriends(final String friend) {
        int res = 0;
       /* mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        amigos_comun = 0;
                        ArrayList<String> friends2 = (ArrayList<String>) dataSnapshot.child("users").child(friend).child("fb_friends").getValue();
                        common_friends = new ArrayList<String>();

                        for (int k=0; k<fb_friends.size(); k++){
                            for (int i=0; i<friends2.size(); i++) {
                                if (fb_friends.get(k).equals(friends2.get(i))){
                                    amigos_comun = amigos_comun + 1;
                                    common_friends.add(friends2.get(i));
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // ...
                    }
                });*/
        res = amigos_comun;
        return res;

    }
}
