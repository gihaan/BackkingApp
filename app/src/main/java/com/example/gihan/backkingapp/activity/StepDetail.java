package com.example.gihan.backkingapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.fragment.StepDetailFragment;

public class StepDetail extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        Intent sentIntent=getIntent();
        Bundle sentBundle=sentIntent.getExtras();

        StepDetailFragment stepDetailFragment=new StepDetailFragment();
        stepDetailFragment.setArguments(sentBundle);


        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detil, new StepDetailFragment())
                    .commit();


        }

    }
}
