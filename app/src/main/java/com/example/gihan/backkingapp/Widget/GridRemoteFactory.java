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
import com.example.gihan.backkingapp.model.RecipsIngerdiant;
import com.example.gihan.backkingapp.model.RecipsSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/29/2017.
 */

public class GridRemoteFactory implements RemoteViewsService.RemoteViewsFactory {


    Context mContext;
    Cursor CR;
    List<RecipsIngerdiant> mList;

    public GridRemoteFactory(Context ApplicationContxt) {
        this.mContext = ApplicationContxt;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {




            CR = mContext.getContentResolver().query(RecipsProvider.CONTENT_URI, null, null, null, null);
            mList = new ArrayList<>();

            CR.moveToFirst();
            while ((CR.moveToNext())) {
                RecipsIngerdiant ob = new RecipsIngerdiant();

                ob.setIngrediantQuality(CR.getString(1));
                ob.setMeaureOfIngerdiant(CR.getString(2));
                ob.setIngerdiantName(CR.getString(3));

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
        RecipsIngerdiant mItem = mList.get(position);
        String grdiant=mItem.getIngrediantQuality()+"   "+mItem.getIngerdiantName()+"   "+mItem.getMeaureOfIngerdiant();
        RemoteViews remoteView = new RemoteViews(mContext.getPackageName(), R.layout.recips_widget);

        remoteView.setTextViewText(R.id.widget_recip_step, grdiant);
        remoteView.setTextColor(R.id.widget_recip_step, Color.WHITE);

        Intent intent = new Intent();

        remoteView.setOnClickFillInIntent(R.id.recips_detail, intent);


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