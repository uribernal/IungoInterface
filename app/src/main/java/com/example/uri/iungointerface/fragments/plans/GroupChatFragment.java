package com.example.uri.iungointerface.fragments.plans;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uri.iungointerface.R;


/**
 * Created by Uri on 04/11/2016.
 * This Fragment ...
 */
public class GroupChatFragment extends Fragment {


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


        return v;
    }

}