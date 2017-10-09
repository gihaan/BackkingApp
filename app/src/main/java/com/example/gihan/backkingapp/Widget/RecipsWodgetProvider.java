package com.example.gihan.backkingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.MainActivity;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.activity.StepDetail;
import com.example.gihan.backkingapp.fragment.RecipsDetailFragment;

/**
 * Implementation of App Widget functionality.
 */
public class RecipsWodgetProvider extends AppWidgetProvider {





    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);

        views.setRemoteAdapter(R.id.widget_grid_view, new Intent(context, igerdiantServies.class));
        views.setPendingIntentTemplate(R.id.widget_grid_view, PendingIntent.getActivity(context, 0, new Intent(context, RecipsDetail.class), 0));

        views.setTextViewText(R.id.widget_recip_name, RecipsDetailFragment.recipName);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, GridRemoteFactory.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widget_grid_view);
        }
        super.onReceive(context, intent);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


}