package com.example.gihan.backkingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.fragment.MainFragment;
import com.example.gihan.backkingapp.fragment.RecipsDetailFragment;

public class RecipsDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recips_detail);


        getSupportFragmentManager().beginTransaction().add(R.id.recips_detail,new RecipsDetailFragment()).commit();

    }
}
