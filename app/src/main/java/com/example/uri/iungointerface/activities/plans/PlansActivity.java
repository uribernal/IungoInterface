package com.example.uri.iungointerface.activities.plans;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.activities.BaseActivity;
import com.example.uri.iungointerface.db.Plan;
import com.example.uri.iungointerface.db.adapters.PlansAdapter;
import com.example.uri.iungointerface.db.fakeDB.fakeDbPlans;
import com.example.uri.iungointerface.db.fakeDB.fakeDbUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class PlansActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private PlansAdapter adapter;
    private List<Plan> planList;
    private FloatingActionButton add_plan;
    private TextView noPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_plans, null, false);
        drawer.addView(contentView, 0);

        // Initialize Buttons, TextViews, etc
        initControls();

        // Load plans from DB
        preparePlans();

        // Click on listener
        recyclerView.addOnItemTouchListener(new PlansAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new PlansAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                intent.putExtra(PLAN, planList.get(itemPosition));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                String item = planList.get(itemPosition).getName();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_LONG).show();

            }
        }));

        // Click on add plan button listener
        add_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                /*Intent intent = new Intent(PlansActivity.this, CreatePlanActivity.class);
                startActivity(intent);
                finish();*/
            }
        });
    }

    public void initControls() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        noPlans = (TextView) findViewById(R.id.tv_plans_infoNoPlans);
        planList = new ArrayList<>();
        adapter = new PlansAdapter(this, planList);

        RecyclerView.LayoutManager mLayoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //portrait
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 3); //num columnes
        }else{
            //landscape
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 2); //num columnes
        }

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new PlansActivity.GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        add_plan = (FloatingActionButton) findViewById(R.id.fb_plans_add_plan);

    }

    private void preparePlans() {
        fakeDbPlans fDbP = new fakeDbPlans();

        planList.clear();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(planList != null ) {
            planList.addAll(fDbP.getPlans());
            adapter.notifyDataSetChanged();
        } else {
            noPlans.setText("No disponemos de más planes por el momento");
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        PlansActivity.super.onBackPressed();
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }
}
