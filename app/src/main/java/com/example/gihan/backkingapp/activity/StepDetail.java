package com.example.gihan.backkingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.fragment.StepDetailFragment;

public class StepDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        getSupportFragmentManager().beginTransaction().add(R.id.item_detil,new StepDetailFragment()).commit();

    }
}
