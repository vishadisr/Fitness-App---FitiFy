package com.vishadi.fitify2;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelp extends ContextWrapper {
    public static final String CHANNEL_ID = "WaterReminderChannel";
    public static final String NOTIFICATION_ID = "1";
    private NotificationManager Manager;

    public NotificationHelp(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, NOTIFICATION_ID, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);

    }

    public NotificationManager getManager() {
        if (Manager == null) {
            Manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return Manager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle("Water Reminder!")
                .setContentText("It's Time to Drink water.")
                .setSmallIcon(R.drawable.water_drop);
    }
}

