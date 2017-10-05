package com.example.uri.iungointerface.fragments.plans;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.db.classes.Plan;


/**
 * Created by Uri on 04/11/2016.
 * This Activity ...
 */
public class PlanInfoFragment extends Fragment {


    public PlanInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v =  inflater.inflate(R.layout.plan_info_fragment, container, false);

        TextView plan_description = (TextView) v.findViewById(R.id.tv_plan_description);
        TextView plan_location = (TextView) v.findViewById(R.id.plan_place2);
        TextView plan_meeting = (TextView) v.findViewById(R.id.plan_punto_encuentro2);
        TextView plan_day = (TextView) v.findViewById(R.id.plan_tv_date);
        TextView plan_hour = (TextView) v.findViewById(R.id.plan_tv_time);

        String planDescription = "ERROR";
        String planLocation = "ERROR";
        String planMeeting = "ERROR";
        String planDate = "ERROR";
        String planHour = "ERROR";

        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            Plan plan = bundle.getParcelable("PLAN");
            planDescription = plan.getDescription();
            planLocation = plan.getDirection_activity();
            planMeeting = plan.getDirection_meeting();
            planDate = plan.getDate();
            planHour = plan.getHour();
        }

        plan_description.setText(planDescription);
        plan_location.setText(planLocation);
        plan_meeting.setText(planMeeting);
        plan_day.setText(planDate);
        plan_hour.setText(planHour);
        return v;
    }



}