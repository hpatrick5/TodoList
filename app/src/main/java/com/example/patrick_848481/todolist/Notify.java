package com.example.patrick_848481.todolist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by Hannah on 5/22/2017.
 */

public class Notify {
    public static NotificationManager mManager;

    public static void generateNotification(Context context){
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
        nb.setSmallIcon(R.drawable.notification_icon);
        nb.setContentTitle("Todo List");
        nb.setContentText("It is time to do your tasks!");
        nb.setAutoCancel(true);

        Intent resultIntent = new Intent(context, MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(MainActivity.class);

        taskStackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        nb.setContentIntent(resultPendingIntent);
        //nb.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(11221, nb.build());

    }
}
