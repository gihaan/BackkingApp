package com.example.gihan.backkingapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.fragment.MainFragment;
import com.example.gihan.backkingapp.fragment.RecipsDetailFragment;
import com.example.gihan.backkingapp.fragment.StepDetailFragment;
import com.example.gihan.backkingapp.model.NameListener;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.io.Serializable;
import java.util.List;

import static java.security.AccessController.getContext;

public class RecipsDetail extends AppCompatActivity implements NameListener{

    boolean mIsTwoPane=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recips_detail);

        RecipsDetailFragment recipsDetailFragment=new RecipsDetailFragment();
        recipsDetailFragment.setmNameListener(this);


        getSupportFragmentManager().beginTransaction().add(R.id.recips_detail,recipsDetailFragment).commit();


        if(null!=findViewById(R.id.item_detil)){
            mIsTwoPane=true;

        }
    }

    @Override
    public void setSelectedStep(RecipsSteps step, List<RecipsSteps>mlist) {


        if(!mIsTwoPane){
                Intent itemDetail = new Intent(getApplicationContext(), StepDetail.class);
                itemDetail.putExtra("item", (Serializable) step);
                itemDetail.putExtra("list", (Serializable) mlist);
                startActivity(itemDetail);
        }else {
            StepDetailFragment stepDetail=new StepDetailFragment();
            Bundle bundle=new Bundle();
            bundle.putSerializable("item",step);
            bundle.putSerializable("list", (Serializable) mlist);
            stepDetail.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.item_detil,stepDetail,"").commit();

        }
    }


}
