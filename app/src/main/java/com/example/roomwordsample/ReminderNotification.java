package com.example.roomwordsample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ReminderNotification {
    private static final String NOTIFICATION_TAG="NoteReminder";
    private static NotificationManager sNotificationManager;

    public static void notify(final Context context, final String noteTitle, final String noteText){
        final Resources res=context.getResources();
        sNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final Bitmap picture= BitmapFactory.decodeResource(res,R.drawable.logo);
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            sNotificationManager.createNotificationChannel(mChannel);
        }

        Intent mainActivity=new Intent(context,MainActivity.class);
        //mainActivity.putExtra(NoteActivity.NOTE_ID,noteId);
        Intent newWordActivity=new Intent(context,NewWordActivity.class);
        //newWordActivity.putExtra(NoteBackupService.EXTRA_COURSE_ID,NoteBackup.ALL_COURSES);

        final NotificationCompat.Builder builder=new NotificationCompat.Builder(context,channelId)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("Review Notification")
                .setContentText(noteText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setTicker("Review Notification")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(noteText)
                        .setBigContentTitle(noteTitle)
                        .setSummaryText("Review Note"))
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(picture)
                .setContentIntent(PendingIntent.getActivity(context,0,
                        mainActivity,PendingIntent.FLAG_UPDATE_CURRENT ))
                .addAction(0,
                        "View All Notes",
                        PendingIntent.getActivity(context,
                                0,new Intent(context,MainActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(0,
                        "Backup Notes",
                        PendingIntent.getService(context,
                                0,newWordActivity,
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true);
        notify(context,builder.build());
    }

    private static void notify(Context context, Notification notification) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.ECLAIR){
            sNotificationManager.notify(NOTIFICATION_TAG,0,notification);
        }
        else {
            sNotificationManager.notify(NOTIFICATION_TAG.hashCode(),notification);
        }
    }
}
