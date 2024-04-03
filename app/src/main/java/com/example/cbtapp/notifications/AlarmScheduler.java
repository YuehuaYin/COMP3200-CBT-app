package com.example.cbtapp.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AlarmScheduler {

    Context context;
    AlarmManager alarmManager;

    public AlarmScheduler(Context context) {
        this.context = context;
        this.alarmManager = context.getSystemService(AlarmManager.class);
    }

    public void schedule(Solution alarm) {
        Intent intent = new Intent(context, AlarmReceiver.class).putExtra("NotifMessage", alarm.getText());

        // get current day of week
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(currentDay);
        System.out.println(alarm.notifsetday);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Integer nextAlarmDay = 7;
            for (int i = 0; i < 7; i++) {
                if (alarm.getDaysSelected().get((currentDay - 1 + i) % 7)) {
                    nextAlarmDay = i % 7;
                    break;
                }
            }

            System.out.println(nextAlarmDay);

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, nextAlarmDay);
            String strTime = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            strTime += " " + String.format("%02d",alarm.notifhour) + ":" + String.format("%02d",alarm.notifmin);

            System.out.println(strTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(strTime, formatter);

            alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                    PendingIntent.getBroadcast(context, alarm.hashCode(), intent, PendingIntent.FLAG_IMMUTABLE));
        }
    }

    public void cancel(Solution alarm){
        alarmManager.cancel(PendingIntent.getBroadcast(context, alarm.hashCode(), new Intent(context, AlarmReceiver.class), PendingIntent.FLAG_IMMUTABLE));
    }
}
