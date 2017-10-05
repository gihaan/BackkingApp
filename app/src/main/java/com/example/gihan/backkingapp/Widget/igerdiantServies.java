package com.example.gihan.backkingapp.Widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.RemoteViewsService;

/**
 * Created by Gihan on 9/29/2017.
 */

public class igerdiantServies extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteFactory(this.getApplicationContext());

    }
}
