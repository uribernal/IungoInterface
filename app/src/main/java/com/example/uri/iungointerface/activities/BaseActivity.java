package com.example.uri.iungointerface.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.Values;
import com.example.uri.iungointerface.activities.chat.ChatActivity;
import com.example.uri.iungointerface.activities.chat.FriendsActivity;
import com.example.uri.iungointerface.activities.plans.MyPlansActivity;
import com.example.uri.iungointerface.activities.plans.PlansActivity;
import com.example.uri.iungointerface.activities.profile.MyProfileActivity;
import com.example.uri.iungointerface.activities.profile.ProfileActivity;
import com.example.uri.iungointerface.activities.settings.SettingsActivity;
import com.example.uri.iungointerface.db.classes.Item;
import com.example.uri.iungointerface.db.classes.User;
import com.example.uri.iungointerface.db.adapters.ItemAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;


public class BaseActivity extends AppCompatActivity implements Values, NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    private ImageView profileButton;
    private TextView tv_name, tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        // Initialize Buttons, TextViews, etc
        initControls();

        // Load active user
        //setUser( (User) getIntent().getParcelableExtra(USER));

        // get Current user if Null
        //if (getUser() == null) {
            //getUserFromDataBase();
        //}

        // Set Data to NavigationDrawer
        setInfo();


        // On click profile image
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!this.getClass().getSimpleName().equals("MyProfileActivity")) {
                    go_to_my_profile();
                }
            }
        });

    }

    private void initControls() {
        // Initialize toolbar and drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null); //Color to icons
        View headerLayout = navigationView.getHeaderView(0);

        // Initialize views inside Navigation
        tv_name = (TextView) headerLayout.findViewById(R.id.profile_name);
        tv_age = (TextView) headerLayout.findViewById(R.id.profile_age);
        profileButton = (ImageView) headerLayout.findViewById(R.id.imageView_myprofile);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_plans) {
            if(!this.getClass().getSimpleName().equals("PlansActivity")){
                Intent intent = new Intent(getApplicationContext(), PlansActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_myplans) {
            if(!this.getClass().getSimpleName().equals("MyPlansActivity")) {
                Intent intent = new Intent(getApplicationContext(), MyPlansActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_friends) {
            if(!this.getClass().getSimpleName().equals("AmigosActivity")) {
                go_to_friends_activity();
                //Intent intent = new Intent(getApplicationContext(), AmigosActivity.class);
                //intent.putExtra("FB_ID", Profile.getCurrentProfile().getId().toString());
                //startActivity(intent);
            }
        } else if (id == R.id.nav_settings) {
            if(!this.getClass().getSimpleName().equals("SettingsActivity")){
                got_to_settings();
            }
        } else if (id == R.id.nav_share) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(BaseActivity.this);
            builderSingle.setIcon(R.drawable.ic_socialnetworks);
            builderSingle.setTitle("Follow us:");
            Item[] redes_sociales = new Item[3];

            redes_sociales[0] = new Item(R.drawable.ic_facebook,"Facebook");
            redes_sociales[1] = new Item(R.drawable.ic_instagram,"Instagram");
            redes_sociales[2] = new Item(R.drawable.ic_twitter,"Twitter");

            final ItemAdapter adapter = new ItemAdapter(getApplicationContext(), R.layout.item_item, redes_sociales);
            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = adapter.getItem(which).getName();
                    AlertDialog.Builder builderInner = new AlertDialog.Builder(BaseActivity.this);
                    builderInner.setMessage(strName);
                    builderInner.setTitle("Your Selected Item is");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builderInner.show();
                }
            });
            builderSingle.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

    }


    public void got_to_settings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent = set_me_in_intent(intent);
        startActivity(intent);
    }

    public void go_to_my_profile(){
        Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
        intent = set_me_in_intent(intent);
        startActivity(intent);
        finish();
    }

    public void go_to_profile(String friend_id){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent = set_me_in_intent(intent);
        intent = set_friend_in_intent(intent, friend_id);
        startActivity(intent);
        //finish();
    }

    public void go_to_friends_activity() {
        Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
        intent = set_me_in_intent(intent);
        startActivity(intent);
        finish();
    }

    public void go_to_chat_activity(String friend_id, String chat_id) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent = set_me_in_intent(intent);
        intent = set_friend_in_intent(intent, friend_id);
        intent.putExtra("CHAT_ID", chat_id);
        startActivity(intent);
    }

    public Intent set_me_in_intent(Intent intent){
        intent.putExtra(ME, getMyInfo());
        return intent;
    }

    public Intent set_friend_in_intent(Intent intent, String friend_id){
        intent.putExtra(USER, friend_id);
        return intent;
    }

    // Set the name, age and profile picture
    public void setInfo()  {
        User me = getMyInfo();

        tv_name.setText(me.getName());
        tv_age.setText(me.getAge() + " years");
        Glide.with(getApplicationContext()).load(me.getPhoto_url()).into(profileButton);
    }

    // Get the information of the current user
    public User getMyInfo(){
        fakeDbUsers fDB = new fakeDbUsers();
        return fDB.getMe();
    }



}

