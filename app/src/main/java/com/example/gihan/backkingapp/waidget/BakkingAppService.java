package com.example.gihan.backkingapp.waidget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gihan on 9/24/2017.
 */

public class BakkingAppService extends IntentService {

    public static final String ACTION_BACKING_APP="ACTION_BACKING_APP";

    public BakkingAppService(String name) {
        super(name);
    }

    public static void startActionBackingApp(Context context, ArrayList<String>igrdiantList) {
        Intent intent = new Intent(context, BakkingAppService.class);
        intent.setAction(ACTION_BACKING_APP);
        intent.putExtra(ACTION_BACKING_APP,igrdiantList);
        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            List<String>ingerdiantList=intent.getExtras().getStringArrayList(ACTION_BACKING_APP);
            if (ACTION_BACKING_APP.equals(action)) {
                handleActionBackingApp(ingerdiantList);
            }
        }

    }

    private void handleActionBackingApp(List<String>igrdiantList) {

        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.putExtra(ACTION_BACKING_APP, (Serializable) igrdiantList);
        sendBroadcast(intent);


    }
}
