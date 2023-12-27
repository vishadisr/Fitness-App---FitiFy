package com.vishadi.fitify2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelp notificationHelp = new NotificationHelp(context);
        NotificationCompat.Builder nb = notificationHelp.getChannelNotification();
        notificationHelp.getManager().notify(1, nb.build());

    }
}
