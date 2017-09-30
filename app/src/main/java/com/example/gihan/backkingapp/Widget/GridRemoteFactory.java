package com.example.gihan.backkingapp.Widget;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.gihan.backkingapp.ContentProvider.RecipsProvider;
import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/29/2017.
 */

public class GridRemoteFactory implements RemoteViewsService.RemoteViewsFactory {


    Context mContext;

    Cursor CR = mContext.getContentResolver().query(RecipsProvider.CONTENT_URI, null, null, null, null);
    List<RecipsSteps> mList = new ArrayList<>();


    public GridRemoteFactory(Context ApplicationContxt) {
        this.mContext = ApplicationContxt;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        CR.moveToFirst();
        mList.clear();
        while ((CR.moveToNext())) {
            RecipsSteps ob = new RecipsSteps();
            ob.setStepID(CR.getInt(2));
            ob.setShortDescrptionOfStep(CR.getString(3));
            ob.setFullDescrptionOfStep(CR.getString(4));
            ob.setVideoUrl(CR.getString(5));
            ob.setThumpUrl(CR.getString(6));

            mList.add(ob);
        }
    }

    @Override
    public void onDestroy() {
        CR.close();

    }

    @Override
    public int getCount() {
        if(CR==null){
            return 0;
        }else
           return mList.size();

    }

    @Override
    public RemoteViews getViewAt(int position) {

        if(CR==null||CR.getCount()==0)return null;
        CR.move(position);

        RecipsSteps step = new RecipsSteps();
        step.setStepID(CR.getInt(2));
        step.setShortDescrptionOfStep(CR.getString(3));
        step.setFullDescrptionOfStep(CR.getString(4));
        step.setVideoUrl(CR.getString(5));
        step.setThumpUrl(CR.getString(6));


        RemoteViews views=new RemoteViews(mContext.getPackageName(), R.layout.recips_widget);
        views.setTextViewText(R.id.widget_recip_image,String.valueOf(step.getShortDescrptionOfStep()));




        return views;


    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
