package com.example.cbtapp.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import com.example.cbtapp.activityLog.DbCmd;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            System.out.println("alarm time");

            String text = intent.getStringExtra("NotifMessage");
            Solution alarm = DbCmd.getSolution(text, context);

            // make notification
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            try {
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(context, notification);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

            AlarmManager alarmManager = context.getSystemService(AlarmManager.class);

            // get current day of week
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

            if (currentDay <= alarm.notifsetday) {
                alarm.notifrepeating -= 1;
            }

            if (alarm.notifrepeating > 0) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Integer nextAlarmDay = 7;
                    for (int i = 0; i < 7; i++) {
                        if (alarm.getDaysSelected().get((currentDay + i) % 7)) {
                            nextAlarmDay = i;
                            break;
                        }
                    }

                    System.out.println(nextAlarmDay);

                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, nextAlarmDay);
                    String strTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());

                    System.out.println(strTime);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime time = LocalDateTime.parse(strTime, formatter);

                    alarmManager.set(
                            AlarmManager.RTC_WAKEUP,
                            time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                            PendingIntent.getBroadcast(context, alarm.hashCode(), intent, PendingIntent.FLAG_IMMUTABLE));

                    alarm.notifsetday = currentDay;
                    DbCmd.deleteSolution(alarm.uid, context);
                    DbCmd.saveSolution(alarm, context);
                }
            }
            else {
                alarm.resetNotif();
                DbCmd.deleteSolution(alarm.uid, context);
            }
        } catch (NullPointerException e){
            System.out.println("null pointer");
        }
    }
}