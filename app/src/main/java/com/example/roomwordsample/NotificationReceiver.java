package com.example.roomwordsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ReminderNotification.notify(context,"This is a Notification","Displayed after oneDay");
    }
}
