package com.example.patrick_848481.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Hannah on 5/22/2017.
 */

public class MyRecieve extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, Notify.class);
        context.startService(service1);
        Log.i("App", "Called Receiver Method");
        try {
            Notify.generateNotification(context);
        }
        catch(Exception e){
             e.printStackTrace();
        }
    }
}

