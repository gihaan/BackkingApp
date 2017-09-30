package com.example.gihan.backkingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.gihan.backkingapp.R;
import com.example.gihan.backkingapp.activity.MainActivity;
import com.example.gihan.backkingapp.activity.RecipsDetail;
import com.example.gihan.backkingapp.activity.StepDetail;

/**
 * Implementation of App Widget functionality.
 */
public class RecipsWodgetProvider extends AppWidgetProvider {

    public static RemoteViews getRecipGridView(Context context){
        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_grid_view);

        Intent intent=new Intent(context,GridRemoteFactory.class);
        views.setRemoteAdapter(R.id.widget_grid_view,intent);

        Intent appIntent=new Intent(context, StepDetail.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_grid_view,pendingIntent);
        views.setOnClickPendingIntent(R.id.widget_recip_image,pendingIntent);




        return views;

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recips_widget);

        //Create intent to activity want to go when click on it
        Intent intent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.widget_recip_image,pendingIntent);


        views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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
}

