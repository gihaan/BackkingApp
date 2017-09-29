package com.example.gihan.backkingapp.waidget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.gihan.backkingapp.R;

import java.util.List;

import static com.example.gihan.backkingapp.waidget.NewAppWidget.ingerdiant;

/**
 * Created by Gihan on 9/26/2017.
 */

public class GridWidget extends RemoteViewsService {



    private List<String> remoteListingerdiant;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewService(this.getApplicationContext(),intent);
    }

     class GridRemoteViewService implements RemoteViewsService.RemoteViewsFactory{

        private Context ctx;

        public  GridRemoteViewService(Context ctx,Intent intent){
            this.ctx=ctx;

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

            remoteListingerdiant= ingerdiant;

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return  remoteListingerdiant.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews views=new RemoteViews(ctx.getPackageName(), R.layout.widget_row);

            views.setTextViewText(R.id.widget_row_tv,remoteListingerdiant.get(position));
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
}
