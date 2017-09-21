package com.example.gihan.backkingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.backkingapp.fragment.MainFragment;
import com.example.gihan.backkingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager().beginTransaction().add(R.id.main_activity,new MainFragment()).commit();

    }
}
