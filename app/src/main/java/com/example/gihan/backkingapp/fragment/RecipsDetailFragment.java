package com.example.gihan.backkingapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.activity.StepDetail;
import com.example.gihan.backkingapp.adapter.RecyclerAdapterGrdiant;
import com.example.gihan.backkingapp.adapter.RecyclerAdapterItems;
import com.example.gihan.backkingapp.adapter.RecyclerAdapterRecipsDetail;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsIngerdiant;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipsDetailFragment extends Fragment {

    Toolbar mToolbar;
    TextView mRecipsName;
    TextView mGrdiant;
    RecyclerView mRecyclerViewRecips;
    RecyclerView mRecyclerViewGrdiant;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.LayoutManager mLayout;
    List<RecipsSteps>mSteps=new ArrayList<>();
    List<RecipsIngerdiant>mIngrdiant=new ArrayList<>();

    RecyclerAdapterGrdiant mGrdiantAdapter;
    RecyclerAdapterRecipsDetail mItemAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_recips_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle == null) {
            bundle = getActivity().getIntent().getExtras();
        }
        final Recips object = (Recips) bundle.get("recip");
        mSteps=object.getRecipsSteps();
        mIngrdiant=object.getRecipsIngerdiant();


        mToolbar=(Toolbar)v.findViewById(R.id.detail_backing_time_toolbar);
        mToolbar.setTitle(object.getRecipsName());

        mRecipsName=(TextView)v.findViewById(R.id.recips_detail_tv_recip);
        mGrdiant=(TextView)v.findViewById(R.id.recips_detail_tv_grdiant);
        mRecyclerViewRecips=(RecyclerView)v.findViewById(R.id.recips_detail_recycler_recips);
        mRecyclerViewGrdiant=(RecyclerView)v.findViewById(R.id.recips_detail_recycler_grdiant);
        mLayoutManager=new LinearLayoutManager(getContext());
        mLayout=new LinearLayoutManager(getContext());


        mRecyclerViewRecips.setLayoutManager(mLayoutManager);
        mRecyclerViewRecips.setHasFixedSize(true);
        mItemAdapter=new RecyclerAdapterRecipsDetail(mSteps,getContext());
        mRecyclerViewRecips.setAdapter(mItemAdapter);


        mRecyclerViewGrdiant.setLayoutManager(mLayout);
        mRecyclerViewGrdiant.setHasFixedSize(true);

        mGrdiantAdapter=new RecyclerAdapterGrdiant(mIngrdiant,getContext());
        mRecyclerViewGrdiant.setAdapter(mGrdiantAdapter);



        mItemAdapter.setOnItemClickListener(new RecyclerAdapterRecipsDetail.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(getContext(),  " was clicked!", Toast.LENGTH_SHORT).show();

                RecipsSteps r = mSteps.get(position);
                Intent i = new Intent(getContext(), StepDetail.class);
                i.putExtra("recip", (Serializable) r);
                startActivity(i);


            }
        });








        return v;
    }

}
