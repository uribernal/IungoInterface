package com.example.uri.iungointerface.activities.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.activities.plans.PlanActivity;
import com.example.uri.iungointerface.db.Chat;
import com.example.uri.iungointerface.db.Plan;
import com.example.uri.iungointerface.db.User;
import com.example.uri.iungointerface.db.adapters.PlansAdapter;
import com.example.uri.iungointerface.db.adapters.UsersAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbPlans;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class ProfileActivity extends BaseActivity {
    private ArrayList<User> common_friends;
    private Bitmap[] profile_photos;
    private RecyclerView recyclerView;
    private ImageButton bt_add_friend, bt_chat, bt_invite_plan;
    private TextView tv_add_friend, tv_chat, tv_invite_plan;
    private RelativeLayout rl_amigos_comun, rl_planes_apuntados;
    private PlansAdapter adapter;
    private TextView nombre, tv_edad, amigosComun;
    private ImageView profile_photo, profile;
    private ArrayList<String> friends_of_my_friend;
    private ArrayList<Plan> planList;
    private User friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        drawer.addView(contentView, 0);

        // Initialize Buttons, TextViews, etc
        initControls();

        // Get Friend's data
        friend = getProfileUser(getIntent().getStringExtra(USER));

        // Set Data to ImageViews, TextViews, etc
        setData();

        // Set Names and Photos from common friends
        setCommonFriendsInfo();

        //LISTENERS
        rl_planes_apuntados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rl_amigos_comun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (common_friends != null && common_friends.size() > 0) {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(ProfileActivity.this);
                    builderSingle.setIcon(R.drawable.ic_people_outline_black_24dp);
                    builderSingle.setTitle("Amigos en común:");

                    final UsersAdapter adapter = new UsersAdapter(getApplicationContext(), R.layout.item_user, common_friends, false);
                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            go_to_profile(common_friends.get(which).getId());
                        }

                    });
                    builderSingle.show();
                }
            }
        });

        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: chec if there is no image or it is not loaded yet
                    Bitmap bitmap = ((GlideBitmapDrawable) profile.getDrawable().getCurrent()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] by = baos.toByteArray();
                    Intent fullScreenIntent = new Intent(getApplicationContext(), ShowFullScreenImage.class);
                    fullScreenIntent.putExtra("IMAGE", by);
                    startActivity(fullScreenIntent);

            }
        });

        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to chat
                String chat_id;
                fakeDbChats fDbC = new fakeDbChats();
                Chat c = fDbC.getChatBetweenUsers(getMyInfo().getId(), friend.getId());
                if (c == null){
                    chat_id = "";
                }else{
                    chat_id = c.getId();
                }
                go_to_chat_activity(friend.getId(), chat_id);
            }
        });
        recyclerView.addOnItemTouchListener(new PlansAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new PlansAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                intent.putExtra(PLAN, planList.get(position));
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    private User getProfileUser(String id) {
        fakeDbUsers fDbU = new fakeDbUsers();
        return fDbU.getUser(id);
    }


    public void initControls(){

        profile = (ImageView) findViewById(R.id.profile_iv_profile_image);
        nombre = (TextView) findViewById(R.id.profile_tv_name);
        amigosComun = (TextView) findViewById(R.id.tv_amigos_comun);
        rl_planes_apuntados = (RelativeLayout) findViewById(R.id.rl_planes_apuntados);
        rl_amigos_comun = (RelativeLayout) findViewById(R.id.rl_amigos_comun);
        tv_edad = (TextView) findViewById(R.id.profile_tv_edad);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_fotos_perfil);
        bt_chat = (ImageButton) findViewById(R.id.ib_chat_profile);
        bt_invite_plan = (ImageButton) findViewById(R.id.ib_invite_plan);
        profile_photo = (ImageView) findViewById(R.id.profile_iv_profile_image);
        tv_add_friend = (TextView) findViewById(R.id.tv_add_friend);
        bt_add_friend = (ImageButton) findViewById(R.id.ib_add_friend);
        planList = new ArrayList<>();

        adapter = new PlansAdapter(getApplicationContext(), planList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2); //num columnes
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ProfileActivity.GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setCommonFriendsInfo() {
        fakeDbUsers fDbU = new fakeDbUsers();
        common_friends= new ArrayList<User>();
        friends_of_my_friend = friend.getFb_friends();
        ArrayList<String> fb_friends = getMyInfo().getFb_friends();
        for (int k=0; k<fb_friends.size(); k++){
            for (int i=0; i<friends_of_my_friend.size(); i++) {
                if (fb_friends.get(k).equals(friends_of_my_friend.get(i))){
                    common_friends.add(fDbU.getUser(friends_of_my_friend.get(i)));
                }
            }
        }

        if(common_friends !=null && common_friends.size() > 0) {
            amigosComun.setText(Integer.toString(common_friends.size()));
        }else{
            amigosComun.setText("0");
            rl_amigos_comun.setClickable(false);

        }
    }

    private void setData() {

        Glide.with(getApplicationContext()).load(friend.getPhoto_url()).into(profile);
        nombre.setText(friend.getName());

        if (friend.getAge() != null) {
            tv_edad.setText(friend.getAge() + " years");
        }

        preparePlans();


    }


    public void preparePlans(){
        fakeDbPlans fDbP = new fakeDbPlans();
        if(planList != null ) {
            planList.clear();
            planList.addAll(fDbP.getPlans(friend.getPlans()));
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}




