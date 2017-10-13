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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.activities.plans.PlanActivity;
import com.example.uri.iungointerface.db.classes.Plan;
import com.example.uri.iungointerface.db.classes.User;
import com.example.uri.iungointerface.db.adapters.PlansAdapter;
import com.example.uri.iungointerface.db.adapters.UsersAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbPlans;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private PlansAdapter adapter;
    private ImageView profile_photo, profile;
    private RelativeLayout amigos;
    private TextView edad, friends, nombre, planes_apuntados;
    private ArrayList<User> my_friends;
    private String fbid;
    private List<Plan> planList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_my_profile, null, false);
        drawer.addView(contentView, 0);

        // Initialize Buttons, TextViews, etc
        initControls();

        // Set Data to ImageViews, TextViews, etc
        setData();

        // Set Names and Photos from friends
        setFriendsInfo();


        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((GlideBitmapDrawable) profile.getDrawable().getCurrent()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] by = baos.toByteArray();
                Intent fullScreenIntent = new Intent(getApplicationContext(), ShowFullScreenImage.class);
                fullScreenIntent.putExtra("IMAGE", by);
                startActivity(fullScreenIntent);
            }
        });

        amigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getMyInfo().getFb_friends() != null) {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(MyProfileActivity.this);
                    builderSingle.setIcon(R.drawable.ic_people_outline_black_24dp);
                    builderSingle.setTitle("Amigos:");

                    final UsersAdapter adapter = new UsersAdapter(getApplicationContext(), R.layout.item_user, my_friends, false);
                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            go_to_profile(getMyInfo().getFb_friends().get(which));
                        }


                    });
                    builderSingle.show();
                }
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

    private void setData() {
        User u = getMyInfo();
        if (u.getFb_friends() == null) {
            friends.setText("0");
        } else {
            friends.setText(Integer.toString(u.getFb_friends().size()));
        }

        Glide.with(getApplicationContext()).load(u.getPhoto_url()).into(profile);
        nombre.setText(u.getName());

        if (u.getAge() != null) {
            edad.setText(u.getAge() + " years");
        }
    }

    private void setFriendsInfo() {
        fakeDbUsers fDbU = new fakeDbUsers();
        my_friends = new ArrayList<User>();
        if(getMyInfo().getFb_friends() != null) {
            for (int k = 0; k < getMyInfo().getFb_friends().size(); k++) {
                my_friends.add(fDbU.getUser(getMyInfo().getFb_friends().get(k)));
            }
        }
    }

    public void initControls() {

        planList = new ArrayList<Plan>();
        fbid = getMyInfo().getId();
        profile = (ImageView) findViewById(R.id.profile_iv_profile_image);
        nombre = (TextView) findViewById(R.id.profile_tv_name);
        edad = (TextView)findViewById(R.id.profile_tv_edad);
        friends = (TextView)findViewById(R.id.tv_myprofile_number_friends);
        amigos = (RelativeLayout)findViewById(R.id.ll_myprofile_amigos);
        profile_photo = (ImageView) findViewById(R.id.profile_iv_profile_image);
        planes_apuntados = (TextView)findViewById(R.id.tv_planes_apuntados);
        planList = new ArrayList<>();

        adapter = new PlansAdapter(getApplicationContext(), planList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_fotos_perfil);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3); //IMAGES
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2); //Plans

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MyProfileActivity.GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        preparePlans();

        if (planList != null) {
            planes_apuntados.setText(Integer.toString(planList.size()));
        }

    }



    public void preparePlans(){
        fakeDbPlans fDbP = new fakeDbPlans();
        if(planList != null ) {
            planList.clear();
            planList.addAll(fDbP.getPlans(getMyInfo().getPlans()));
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
