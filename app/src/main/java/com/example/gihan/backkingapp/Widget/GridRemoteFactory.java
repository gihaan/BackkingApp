package com.example.gihan.backkingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Parcelable;
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
        mList.clear();
        CR.moveToFirst();
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
        RecipsSteps mItem = mList.get(position);
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.recips_widget);

        remoteView.setTextViewText(R.id.widget_recip_step, mItem.getShortDescrptionOfStep());

//        Intent intent = new Intent();
//        intent.putExtra("recipe", mItem);
//        intent.putExtra("step_id", position);
//        intent.putExtra("list", (Parcelable) mList);
//        remoteView.setOnClickFillInIntent(R.id.root, intent);
//        return remoteView;
        try{

        }catch (Exception e){
            String ff=e.toString();
        }



        return remoteView;


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
