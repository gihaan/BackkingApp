package com.example.gihan.backkingapp.Widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Gihan on 9/29/2017.
 */

public class igerdiantServies extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String ACTION_GET_INGERDIANT ="com.example.android.ingerdiant";

    public static void startActionGetIngerdiant(Context context) {
        Intent intent = new Intent(context, igerdiantServies.class);
        intent.setAction(ACTION_GET_INGERDIANT);
        context.startService(intent);
    }

    public igerdiantServies(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_INGERDIANT.equals(action)) {
                handleActionGetIngerdiant();
            }
        }
    }

    private void handleActionGetIngerdiant() {
    }


}
