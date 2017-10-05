package com.example.gihan.backkingapp.Widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Parcelable;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.gihan.backkingapp.ContentProvider.RecipsProvider;
import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.model.Recips;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/29/2017.
 */

public class GridRemoteFactory implements RemoteViewsService.RemoteViewsFactory {


    Context mContext;
    Cursor CR;
    List<RecipsSteps> mList;

    public GridRemoteFactory(Context ApplicationContxt) {
        this.mContext = ApplicationContxt;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        try {
            CR = mContext.getContentResolver().query(RecipsProvider.CONTENT_URI, null, null, null, null);
            mList=new ArrayList<>();
        } catch (Exception ex) {
            String ee = ex.toString();
        }

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
        if (CR == null) {
            return 0;
        } else
            return mList.size();

    }

    @Override
    public RemoteViews getViewAt(int position) {
        RecipsSteps mItem = mList.get(position);
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.recips_widget);

        remoteView.setTextViewText(R.id.widget_recip_step, mItem.getShortDescrptionOfStep());
        remoteView.setTextColor(R.id.widget_recip_step, Color.WHITE);

        Intent intent = new Intent();
        intent.putExtra("reciprecip", mItem);
        //  intent.putExtra("step_id", position);

        intent.putParcelableArrayListExtra("step", (ArrayList<? extends Parcelable>) mList);
      //  views.setOnClickPendingIntent(R.id.widget_grid_view, PendingIntent.getActivity(context, 0, new Intent(context, RecipsDetail.class), 0));

        remoteView.setOnClickFillInIntent(R.id.root, intent);

        try {

        } catch (Exception e) {
            String ff = e.toString();
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
