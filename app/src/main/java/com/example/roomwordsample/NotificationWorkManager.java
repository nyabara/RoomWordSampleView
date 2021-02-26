package com.example.roomwordsample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.ALARM_SERVICE;

public class NotificationWorkManager extends Worker {
    public NotificationWorkManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        Intent intent=new Intent(applicationContext,NotificationReceiver.class);
        PendingIntent pendingIntent=PendingIntent
                .getBroadcast(applicationContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager=(AlarmManager)applicationContext.getSystemService(ALARM_SERVICE);
        long currentTimeMilliseconds= SystemClock.elapsedRealtime();
        long ONE_HOUR=60*60*1000;
        long TEN_SECONDS=10*1000;
        long alarmTime=currentTimeMilliseconds+TEN_SECONDS;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME,alarmTime,pendingIntent);
        return Result.success();
    }
}
