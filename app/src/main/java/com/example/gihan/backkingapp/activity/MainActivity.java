package com.example.gihan.backkingapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.gihan.backkingapp.fragment.MainFragment;
import com.example.gihan.backkingapp.R;

public class MainActivity extends AppCompatActivity {

    public static  int TABLET_MODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(null!=findViewById(R.id.main_activity_tablet)){
            TABLET_MODE=1;

        }


        if(TABLET_MODE==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity,new MainFragment()).commit();

        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_tablet,new MainFragment()).commit();

        }
    }
}