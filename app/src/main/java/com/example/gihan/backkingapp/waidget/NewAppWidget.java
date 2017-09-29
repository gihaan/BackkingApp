package com.example.gihan.backkingapp.waidget;



import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.model.Recips;

import java.util.ArrayList;
import java.util.List;

import static com.example.gihan.backkingapp.waidget.BakkingAppService.ACTION_BACKING_APP;

public class NewAppWidget extends AppWidgetProvider {

    static List<String>ingerdiant=new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int [] appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        // Set the GridWidgetService intent to act as the adapter for the GridView
        Intent intent = new Intent(context, Recips.class);
        views.setRemoteAdapter(R.layout.new_app_widget, intent);


        //set detail activity launched when click on it
        Intent appIntent = new Intent(context, RecipsDetail.class);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_row_tv, appPendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetIds);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int  appWidgetIds [] = appWidgetManager.getAppWidgetIds(new ComponentName(context , NewAppWidget.class));

        final String action = intent.getAction();

        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE2")) {
            ingerdiant = intent.getExtras().getStringArrayList(ACTION_BACKING_APP);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_grid_view);

            //Now update all widgets
            NewAppWidget.updateAppWidget(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }

    }
}

