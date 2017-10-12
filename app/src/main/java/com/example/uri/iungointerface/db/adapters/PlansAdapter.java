package com.example.uri.iungointerface.db.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.db.classes.Plan;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Uri on 21/09/2016.
 */
public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.MyViewHolder> {

    private Context mContext;
    private List<Plan> planList;
    private ArrayList<String> urls;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, ocupacion_plan, num_users;
        public ImageView profile1, profile2, profile3, plan_photo;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.plan_header);
            num_users = (TextView) view.findViewById(R.id.plan_number_of_users);
            ocupacion_plan = (TextView) view.findViewById(R.id.tv_itemPlan_occupacy);
            profile1 = (ImageView) view.findViewById(R.id.profile_image1);
            profile2 = (ImageView) view.findViewById(R.id.profile_image2);
            profile3 = (ImageView) view.findViewById(R.id.profile_image3);
            plan_photo = (ImageView) view.findViewById(R.id.plan_header_foto);
        }
    }


    public PlansAdapter(Context mContext, List<Plan> planList) {
        this.mContext = mContext;
        this.planList = planList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Plan plan =  planList.get(position);
        holder.title.setText(plan.getName());
        if (plan.getNum_people()>3) {
            holder.num_users.setText("... and " + Integer.toString(plan.getNum_people() - 3) + " more");
            holder.ocupacion_plan.setText(Integer.toString(plan.getNum_people())+"/"+plan.getMax_people());
        }else if(plan.getNum_people() == 0){
            holder.num_users.setText("Be the first to join!");
            holder.ocupacion_plan.setText("");

        }else{
            holder.num_users.setText("");
        }
        getParticipantsInfo(plan);

        if(urls == null){
            holder.num_users.setText("");
            Glide.with(mContext).load("").into(holder.profile1);
            Glide.with(mContext).load("").into(holder.profile2);
            Glide.with(mContext).load("").into(holder.profile3);
        }else  {
            if(urls.size()>2) {
                Glide.with(mContext).load(urls.get(0)).into(holder.profile1);
                Glide.with(mContext).load(urls.get(1)).into(holder.profile2);
                Glide.with(mContext).load(urls.get(2)).into(holder.profile3);
            }else if(urls.size()>1) {
                Glide.with(mContext).load(urls.get(0)).into(holder.profile1);
                Glide.with(mContext).load(urls.get(1)).into(holder.profile2);
                Glide.with(mContext).load("").into(holder.profile3);
            }else if(urls.size()>0) {
                Glide.with(mContext).load(urls.get(0)).into(holder.profile1);
                Glide.with(mContext).load("").into(holder.profile2);
                Glide.with(mContext).load("").into(holder.profile3);
            }
        }
        if(plan.getPlan_photos() != null) {
            Glide.with(mContext).load(plan.getPlan_photos().get(0)).into(holder.plan_photo);

        }else{
            Glide.with(mContext).load("http://www.sonedesign.jp/img_main/unknown_01.jpg").into(holder.plan_photo);
        }


    }

    public void getParticipantsInfo(Plan plan){
        urls = new ArrayList<>();
        ArrayList<String> ids = plan.getUsers_fbid();
        fakeDbUsers fDbU = new fakeDbUsers();
        urls = fDbU.getPhotoUrls(ids);
    }

    @Override
    public int getItemCount() {
        if (planList == null){
            return 0;
        }else {
            return planList.size();
        }
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PlansAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PlansAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}