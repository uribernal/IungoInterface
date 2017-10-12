package com.example.uri.iungointerface.activities.plans;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.db.classes.Chat;
import com.example.uri.iungointerface.db.classes.Plan;
import com.example.uri.iungointerface.db.classes.User;
import com.example.uri.iungointerface.db.adapters.UsersAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbChats;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;
import com.example.uri.iungointerface.fragments.plans.GroupChatFragment;
import com.example.uri.iungointerface.fragments.plans.MapsFragment;
import com.example.uri.iungointerface.fragments.plans.PlanInfoFragment;

import java.util.ArrayList;
import java.util.List;


public class PlanActivity extends BaseActivity {

    private int[] layouts;
    private TextView plan_title;
    private ViewPager viewPager, viewPager2;
    private LinearLayout dotsLayout;
    private TextView plan_number_of_users, plan_price;
    private ImageView profile_photo1, profile_photo2, profile_photo3, plan_im;
    private ImageButton invite;
    private Button get_this_plan;
    private ProgressDialog progress;
    private boolean ya_estas_apuntado;
    private TabLayout tabLayout;
    private Plan plan;
    private ArrayList<String> profiles_urls;

    private PlanActivity.OnFragmentInteractionListener mListener;
    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_plan, null, false);
        drawer.addView(contentView, 0);

        // Initialize Buttons, TextViews, etc
        initControls();

        // On click listener
        plan_number_of_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create Alert dialog to show the users who bought the plan
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(PlanActivity.this);
                builderSingle.setIcon(R.drawable.ic_people_outline_black_24dp);
                builderSingle.setTitle("People in the activity:");

                fakeDbUsers dbUsers = new fakeDbUsers();

                ArrayList<User> friends = dbUsers.getUsers(plan.getUsers_fbid());

                final UsersAdapter adapter = new UsersAdapter(getApplicationContext(), R.layout.item_user, friends, false);
                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String friend_id = plan.getUsers_fbid().get(which);
                        if(getMyInfo().getId().equals(friend_id)){
                            go_to_my_profile();
                        }else {
                            go_to_profile(friend_id);
                        }
                    }
                });
                builderSingle.show();
            }
        });

        // On click listener
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Alert dialog to show your friends
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(PlanActivity.this);
                builderSingle.setIcon(R.drawable.ic_people_outline_black_24dp);
                builderSingle.setTitle("Invite one of your friends:");

                fakeDbUsers dbUsers = new fakeDbUsers();

                final ArrayList<User> friends = dbUsers.getUsers(getMyInfo().getFb_friends());

                final UsersAdapter adapter = new UsersAdapter(getApplicationContext(), R.layout.item_user, friends, false);
                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Check if user is in the plan
                        if(friends.get(which).getPlans().contains(plan.getId())){
                            final Snackbar snackbar = Snackbar.make(getCurrentFocus(), "This friend is already in the plan", Snackbar.LENGTH_LONG)
                                    .setAction("OK", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                        }
                                    });
                            snackbar.show();
                        }else{

                            //Add plan message to conversation and go to chat
                            String chat_id;
                            fakeDbChats fDbC = new fakeDbChats();
                            Chat c = fDbC.getChatBetweenUsers(getMyInfo().getId(), friends.get(which).getId());
                            if (c == null){
                                chat_id = "";
                            }else{
                                chat_id = c.getId();
                            }
                            go_to_chat_activity(friends.get(which).getId(), chat_id);
                        }
                    }


                });
                builderSingle.show();
            }


        });

        // On click listener
        get_this_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(ya_estas_apuntado){
                    ya_estas_apuntado = false;
                    get_this_plan.setText("JOIN THE PLAN");
                    if (plan.getUsers_fbid().get(0).equals(getMyInfo().getId())){
                        profiles_urls.set(0, "");
                        Glide.with(getApplicationContext()).load("").into(profile_photo1);
                    }else if (plan.getUsers_fbid().get(1).equals(getMyInfo().getId())){
                        Glide.with(getApplicationContext()).load("").into(profile_photo2);
                        profiles_urls.set(1, "");
                    }else if (plan.getUsers_fbid().get(2).equals(getMyInfo().getId())){
                        Glide.with(getApplicationContext()).load("").into(profile_photo3);
                        profiles_urls.set(2, "");
                    }

                }else{
                    ya_estas_apuntado = true;
                    get_this_plan.setText("UNSUBSCRIBE");
                    plan.getUsers_fbid().remove(getMyInfo().getId());
                    plan.setUsers_fbid(plan.getUsers_fbid());
                    if (profiles_urls.get(0).equals("")){
                        Glide.with(getApplicationContext()).load(getMyInfo().getPhoto_url()).into(profile_photo1);
                    }else if (profiles_urls.get(0).equals("")){
                        Glide.with(getApplicationContext()).load(getMyInfo().getPhoto_url()).into(profile_photo2);
                    }else if (profiles_urls.get(0).equals("")){
                        Glide.with(getApplicationContext()).load(getMyInfo().getPhoto_url()).into(profile_photo3);
                    }

                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(PlanActivity.this, PlansActivity.class);
        startActivity(back);
        overridePendingTransition(0,0);
    }

    public void initControls() {

        // Initialize views
        get_this_plan = (Button) findViewById(R.id.plan_bt_get_plan);
        invite = (ImageButton) findViewById(R.id.plan_ib_invite);
        viewPager = (ViewPager) findViewById(R.id.vp_plan);
        dotsLayout = (LinearLayout) findViewById(R.id.ll_plan_viewpager_container);
        plan_title = (TextView) findViewById(R.id.plan_tv_title);
        plan_price = (TextView) findViewById(R.id.tv_plan_price);
        plan_number_of_users = (TextView) findViewById(R.id.plan_number_of_users);
        profile_photo1 = (ImageView) findViewById(R.id.profile_image1);
        profile_photo2 = (ImageView) findViewById(R.id.profile_image2);
        profile_photo3 = (ImageView) findViewById(R.id.profile_image3);
        viewPager2 = (ViewPager) findViewById(R.id.vp_plan_infoMapsChat);

        // Set Info, Maps and Chat
        Bundle bundle = getIntent().getExtras();
        setupViewPager(viewPager2, bundle);

        // Set plan images
        tabLayout = (TabLayout) findViewById(R.id.tl_plan_infoMapsChat);
        tabLayout.setupWithViewPager(viewPager2);
        setupTabIcons();

        // Get info of the plan
        plan = getIntent().getExtras().getParcelable(PLAN);
        setParticipantsInfo();

        // Load texts and images
        plan_title.setText(plan.getName());
        plan_price.setText(plan.getPrice()+" €");
        plan_number_of_users.setClickable(true);

        if(plan.getNum_people()>3) {
            plan_number_of_users.setText("... and " + Integer.toString(plan.getNum_people() - 3) + " more");
        }else if(plan.getNum_people()<1) {
            plan_number_of_users.setText("Be the first to join");
            plan_number_of_users.setClickable(false);
        }else {
            plan_number_of_users.setText("View All");
        }

        //Load users' profile photos
        if(profiles_urls!=null && profiles_urls.size()>0){
            if(profiles_urls.size()>2) {
                Glide.with(getApplicationContext()).load(profiles_urls.get(0)).into(profile_photo1);
                Glide.with(getApplicationContext()).load(profiles_urls.get(1)).into(profile_photo2);
                Glide.with(getApplicationContext()).load(profiles_urls.get(2)).into(profile_photo3);
            }else if (profiles_urls.size()>1){
                Glide.with(getApplicationContext()).load(profiles_urls.get(0)).into(profile_photo1);
                Glide.with(getApplicationContext()).load(profiles_urls.get(1)).into(profile_photo2);
                Glide.with(getApplicationContext()).load("").into(profile_photo3);
            }else if (profiles_urls.size()>0){
                Glide.with(getApplicationContext()).load(profiles_urls.get(0)).into(profile_photo1);
                Glide.with(getApplicationContext()).load("").into(profile_photo2);
                Glide.with(getApplicationContext()).load("").into(profile_photo3);
            }
        }else{
            Glide.with(getApplicationContext()).load("").into(profile_photo1);
            Glide.with(getApplicationContext()).load("").into(profile_photo2);
            Glide.with(getApplicationContext()).load("").into(profile_photo3);
            plan_number_of_users.setText("Be the first to join");
            plan_number_of_users.setClickable(false);
        }

        // Load plan photos
        if(plan.getPlan_photos()!=null){
            layouts = new int[plan.getPlan_photos().size()];
            for(int i=0;i<plan.getPlan_photos().size(); i++){
                layouts[i] =  R.layout.plan_photo_slide;
            }
        }

        // Adding bottom dots
        addBottomDots(0);

        // Making notification bar transparent
        changeStatusBarColor();

        // Setting ViewPager for all the plans
        PlanActivity.MyViewPagerAdapter myViewPagerAdapter = new PlanActivity.MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        //Check if user has joined the plan
        ya_estas_apuntado = joinedPlan();
        if(ya_estas_apuntado){
            get_this_plan.setText("UNSUBSCRIBE");
        }else{
            get_this_plan.setText("JOIN THE PLAN");
        }
    }

    public boolean joinedPlan(){
        if (getMyInfo().getPlans().contains(plan.getId())){
            return true;
        }else{
            return false;
        }
    }


    public void setParticipantsInfo(){
        profiles_urls = new ArrayList<>();
        ArrayList<String> ids = plan.getUsers_fbid();
        fakeDbUsers fDbU = new fakeDbUsers();
        profiles_urls = fDbU.getPhotoUrls(ids);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_info);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_maps);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_chat3);

    }

    private void setupViewPager(ViewPager viewPager, Bundle bundle) {
        PlanActivity.ViewPagerAdapter adapter = new PlanActivity.ViewPagerAdapter(getSupportFragmentManager());
        PlanInfoFragment infoFragment = new PlanInfoFragment();
        infoFragment.setArguments(bundle);
        adapter.addFrag(infoFragment, "Info");
        adapter.addFrag(new MapsFragment(), "Maps");
        adapter.addFrag(new GroupChatFragment(), "GroupChat");
        viewPager.setAdapter(adapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[1];
        if (plan.getPlan_photos()!=null){
            dots = new TextView[plan.getPlan_photos().size()];
        }

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }
    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // move to next screen
            viewPager.setCurrentItem(position);
            Glide.with(getApplicationContext()).load(plan.getPlan_photos().get(position)).into(plan_im);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    /**
     * View pager adapter
     */
    private class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        private MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            View view = layoutInflater.inflate(layouts[position], container, false);
            plan_im = (ImageView) view.findViewById(R.id.plan_photo_image);
            Glide.with(getApplicationContext()).load(plan.getPlan_photos().get(0)).into(plan_im);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            int a = 0;
            if(layouts!=null){
                a = layouts.length;
            }
            return a;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);

            // return null to display only the icon
            return null;
        }
    }
}